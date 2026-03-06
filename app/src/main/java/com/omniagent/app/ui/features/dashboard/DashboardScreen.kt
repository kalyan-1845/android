package com.omniagent.app.ui.features.dashboard

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.omniagent.app.core.model.*
import com.omniagent.app.ui.theme.OmniColors
import com.omniagent.app.viewmodel.DashboardTab
import com.omniagent.app.viewmodel.OmniAgentUiState
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    uiState: OmniAgentUiState,
    classificationResult: ClassificationResult?,
    engineResult: EngineResult?,
    reasoningSteps: List<String>,
    logs: List<AnalysisLog>,
    onAnalyze: (String) -> Unit,
    onSwitchTab: (DashboardTab) -> Unit,
    onClearResults: () -> Unit,
    onClearLogs: () -> Unit,
    onDecryptLog: (String) -> String,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF14C38E)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("B", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                "Buddy",
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                if (uiState.isProcessing) "typing..." else "online",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 12.sp
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF075E54)
                ),
                actions = {
                    IconButton(onClick = onClearLogs) {
                        Icon(Icons.Default.Delete, contentDescription = "Clear Chat", tint = Color.White)
                    }
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More", tint = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 8.dp
            ) {
                val tabs = listOf(
                    DashboardTab.CHATS to Icons.Default.Chat,
                    DashboardTab.STATUS to Icons.Default.DonutLarge,
                    DashboardTab.COMMUNITIES to Icons.Default.Groups,
                    DashboardTab.SETTINGS to Icons.Default.Settings
                )
                
                tabs.forEach { (tab, icon) ->
                    val isSelected = uiState.activeTab == tab
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { onSwitchTab(tab) },
                        icon = {
                            Icon(
                                icon, 
                                contentDescription = tab.title,
                                tint = if (isSelected) Color(0xFF075E54) else Color.Gray
                            )
                        },
                        label = {
                            Text(
                                tab.title,
                                color = if (isSelected) Color(0xFF075E54) else Color.Gray,
                                fontSize = 10.sp
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color(0xFFE0F2F1)
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFE5DDD5))
        ) {
            when (uiState.activeTab) {
                DashboardTab.CHATS -> ChatScreen(
                    logs = logs,
                    isProcessing = uiState.isProcessing,
                    onAnalyze = onAnalyze,
                    onDecryptLog = onDecryptLog
                )
                DashboardTab.STATUS -> StatusScreen()
                DashboardTab.COMMUNITIES -> CommunitiesScreen()
                DashboardTab.SETTINGS -> SettingsScreen(onClearResults)
            }
        }
    }
}

@Composable
fun ChatScreen(
    logs: List<AnalysisLog>,
    isProcessing: Boolean,
    onAnalyze: (String) -> Unit,
    onDecryptLog: (String) -> String
) {
    var inputText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    
    LaunchedEffect(logs.size, isProcessing) {
        if (logs.isNotEmpty()) {
            listState.animateScrollToItem(logs.size)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(logs.reversed()) { log ->
                ChatBubble(
                    message = log.userInput,
                    isUser = true,
                    timestamp = log.timestamp
                )
                Spacer(modifier = Modifier.height(8.dp))
                
                val decryptedResult = onDecryptLog(log.resultJson)
                val responseText = try {
                    val result = Gson().fromJson(decryptedResult, EngineResult::class.java)
                    buildString {
                        appendLine("Analysis: ${result.module_name.replaceFirstChar { it.uppercase() }}")
                        if (result.reasoning.isNotEmpty()) {
                            appendLine(result.reasoning.joinToString("\n- ", prefix = "- "))
                        }
                        if (result.risk_score > 0) {
                            appendLine("Confidence/Risk: ${result.risk_score.toInt()}%")
                        }
                    }
                } catch (e: Exception) {
                    "Analysis completed successfully."
                }
                
                ChatBubble(
                    message = responseText,
                    isUser = false,
                    timestamp = log.timestamp + 1000 // Slightly after
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            
            if (isProcessing) {
                item {
                    ChatBubble(
                        message = "Thinking...",
                        isUser = false,
                        timestamp = System.currentTimeMillis()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = { Text("Message", color = Color.Gray) },
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(24.dp)),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    maxLines = 4
                )
                Spacer(modifier = Modifier.width(8.dp))
                FloatingActionButton(
                    onClick = {
                        if (inputText.isNotBlank()) {
                            onAnalyze(inputText)
                            inputText = ""
                        }
                    },
                    containerColor = Color(0xFF075E54),
                    contentColor = Color.White,
                    shape = CircleShape,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(Icons.Default.Send, contentDescription = "Send")
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: String, isUser: Boolean, timestamp: Long) {
    val dateFormat = remember { SimpleDateFormat("HH:mm", Locale.getDefault()) }
    val timeString = dateFormat.format(Date(timestamp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (isUser) Color(0xFFDCF8C6) else Color.White,
            shape = RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 12.dp,
                bottomStart = if (isUser) 12.dp else 0.dp,
                bottomEnd = if (isUser) 0.dp else 12.dp
            ),
            shadowElevation = 1.dp,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = message,
                    color = Color.Black,
                    fontSize = 15.sp
                )
                Text(
                    text = timeString,
                    color = Color.Gray,
                    fontSize = 11.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun StatusScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Status Updates will appear here", color = Color.Gray)
    }
}

@Composable
fun CommunitiesScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Groups, contentDescription = null, modifier = Modifier.size(64.dp), tint = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Introducing Communities", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("Easily organize your related groups.", color = Color.Gray, modifier = Modifier.padding(top = 8.dp))
        }
    }
}

@Composable
fun SettingsScreen(onClearResults: () -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            ListItem(
                headlineContent = { Text("Profile") },
                supportingContent = { Text("Manage buddy avatar and name") },
                leadingContent = { Icon(Icons.Default.Person, contentDescription = null) }
            )
            Divider()
            ListItem(
                headlineContent = { Text("Privacy") },
                leadingContent = { Icon(Icons.Default.Lock, contentDescription = null) }
            )
            Divider()
            ListItem(
                headlineContent = { Text("Help") },
                leadingContent = { Icon(Icons.Default.Help, contentDescription = null) }
            )
            Divider()
            ListItem(
                headlineContent = { Text("Clear Results Cache") },
                leadingContent = { Icon(Icons.Default.Delete, contentDescription = null) },
                modifier = Modifier.clickable { onClearResults() }
            )
        }
    }
}
