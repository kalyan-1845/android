package com.omniagent.app.ui.features.dashboard

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.omniagent.app.core.model.*
import com.omniagent.app.ui.common.*
import com.omniagent.app.ui.features.growth.SkillGrowthScreen
import com.omniagent.app.ui.features.export.PdfReportExporter
import com.omniagent.app.ui.theme.OmniColors
import com.omniagent.app.security.CryptoManager
import com.omniagent.app.viewmodel.DashboardTab
import com.omniagent.app.viewmodel.OmniAgentUiState
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.platform.LocalContext

/**
 * Main Dashboard Screen — The OS-style control center.
 */
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
    onAuthenticateAdmin: (String) -> Boolean,
    onSwitchToUser: () -> Unit,
    onDecryptLog: (String) -> String,
    onToggleDemo: (Boolean) -> Unit,
    onRunDemo: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var inputText by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(OmniColors.Background)
    ) {
        // === TOP BAR ===
        TopBar(uiState = uiState)

        // === COMMAND INPUT ===
        CommandInputPanel(
            value = inputText,
            onValueChange = { inputText = it },
            onSubmit = {
                if (inputText.isNotBlank()) {
                    onAnalyze(inputText)
                    inputText = ""
                }
            },
            isProcessing = uiState.isProcessing
        )

        // === MODULE ROUTING INDICATOR ===
        AnimatedVisibility(
            visible = uiState.hasResult && classificationResult != null,
            enter = slideInVertically() + fadeIn(),
            exit = slideOutVertically() + fadeOut()
        ) {
            classificationResult?.let { result ->
                ModuleRoutingIndicator(
                    moduleName = result.moduleName,
                    moduleKey = result.module ?: "",
                    confidence = result.confidence,
                    confidenceLevel = result.confidenceLevel,
                    processingTimeMs = uiState.processingTimeMs
                )
            }
        }

        // === ERROR BANNER ===
        AnimatedVisibility(visible = uiState.error != null) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                color = OmniColors.Danger.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Error,
                        contentDescription = null,
                        tint = OmniColors.Danger,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = uiState.error ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = OmniColors.Danger
                    )
                }
            }
        }

        // === TAB CONTENT ===
        Box(modifier = Modifier.weight(1f)) {
            when (selectedTab) {
                0 -> OutputTab(
                    classificationResult = classificationResult,
                    engineResult = engineResult,
                    uiState = uiState,
                    onClearResults = onClearResults,
                    onDecryptLog = onDecryptLog
                )
                1 -> ReasoningTab(
                    reasoningSteps = reasoningSteps,
                    classificationResult = classificationResult
                )
                2 -> SkillGrowthScreen(logs = logs)
                3 -> LogsTab(
                    logs = logs,
                    onClearLogs = onClearLogs,
                    onDecryptLog = onDecryptLog,
                    isAdmin = uiState.currentRole == UserRole.ADMIN
                )
                4 -> SettingsTab(
                    uiState = uiState,
                    onAuthenticateAdmin = onAuthenticateAdmin,
                    onSwitchToUser = onSwitchToUser,
                    onToggleDemo = onToggleDemo,
                    onRunDemo = onRunDemo
                )
            }
        }

        // === BOTTOM TAB BAR ===
        Divider(color = OmniColors.Border, thickness = 0.5.dp)
        DashboardTabBar(
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )
    }
}

/**
 * Top Bar with app branding and status indicators.
 */
@Composable
private fun TopBar(uiState: OmniAgentUiState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(OmniColors.Surface)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Logo / Brand
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(OmniColors.Primary, OmniColors.Accent)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Ω",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = "OMNIAGENT",
                    style = MaterialTheme.typography.titleLarge,
                    color = OmniColors.TextPrimary,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 2.sp
                )
                Text(
                    text = "AI Control Platform • Offline",
                    style = MaterialTheme.typography.labelSmall,
                    color = OmniColors.TextTertiary
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            // Role indicator
            StatusBadge(
                text = uiState.currentRole.displayName,
                color = if (uiState.currentRole == UserRole.ADMIN)
                    OmniColors.Warning else OmniColors.Secondary
            )
            Spacer(modifier = Modifier.width(8.dp))
            // Offline indicator
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(OmniColors.Secondary)
            )
        }
    }
    Divider(color = OmniColors.Border, thickness = 0.5.dp)
}

// ========================================
// TAB CONTENT SCREENS
// ========================================

/**
 * Output Tab — Displays structured analysis results.
 */
@Composable
private fun OutputTab(
    classificationResult: ClassificationResult?,
    engineResult: EngineResult?,
    uiState: OmniAgentUiState,
    onDecryptLog: (String) -> String,
    onClearResults: () -> Unit
) {
    val context = LocalContext.current
    if (!uiState.hasResult || classificationResult == null) {
        // Empty state
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    Icons.Default.Terminal,
                    contentDescription = null,
                    tint = OmniColors.TextTertiary,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Ready for input",
                    style = MaterialTheme.typography.titleMedium,
                    color = OmniColors.TextSecondary
                )
                Text(
                    text = "Enter a command to analyze",
                    style = MaterialTheme.typography.bodySmall,
                    color = OmniColors.TextTertiary
                )
            }
        }
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        // Score overview
        item {
            DashboardCard(
                title = "Module Scores",
                icon = Icons.Default.Assessment,
                iconColor = OmniColors.Primary,
                headerAction = {
                    IconButton(
                        onClick = onClearResults,
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(
                            Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = OmniColors.TextTertiary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            ) {
                classificationResult.ranking.forEach { entry ->
                    ScoreBar(
                        label = entry.module.replaceFirstChar { it.uppercase() },
                        score = (entry.score * 100).toFloat(),
                        color = OmniColors.getModuleColor(entry.module)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        // PDF Export Button
        item {
            AnimatedVisibility(visible = engineResult != null) {
                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                    ExportButton(
                        onClick = {
                            val log = AnalysisLog(
                                userInput = "Exported Result",
                                classifiedModule = classificationResult.module ?: "none",
                                confidence = classificationResult.confidence,
                                confidenceLevel = classificationResult.confidenceLevel,
                                resultJson = CryptoManager.encrypt(Gson().toJson(engineResult)),
                                reasoningJson = CryptoManager.encrypt(Gson().toJson(classificationResult.reasoning))
                            )
                            PdfReportExporter.exportReport(context, log, Gson().toJson(engineResult) ?: "No result data")
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        // Engine result
        item {
            engineResult?.let { result ->
                DashboardCard(
                    title = "Analysis Result",
                    icon = Icons.Default.Analytics,
                    iconColor = OmniColors.Secondary
                ) {
                    EngineResultContent(result)
                }
            }
        }
    }
}

/**
 * Engine Result Content — Parses and displays engine JSON output.
 */
@Composable
private fun EngineResultContent(engineResult: EngineResult) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Module & Confidence
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = engineResult.module_name,
                    style = MaterialTheme.typography.titleMedium,
                    color = OmniColors.Primary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = engineResult.timestamp,
                    style = MaterialTheme.typography.labelSmall,
                    color = OmniColors.TextTertiary
                )
            }
            MetricDisplay(
                label = "Risk",
                value = "${engineResult.risk_score.toInt()}%",
                color = scoreColor(engineResult.risk_score.toFloat())
            )
        }

        Divider(modifier = Modifier.padding(vertical = 12.dp), color = OmniColors.Border)

        // Reasoning Chain (Engine Level)
        Text(
            text = "Engine Reasoning",
            style = MaterialTheme.typography.labelSmall,
            color = OmniColors.TextTertiary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        engineResult.reasoning.forEach { step ->
            Row(modifier = Modifier.padding(bottom = 6.dp)) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = OmniColors.Success,
                    modifier = Modifier.size(14.dp).padding(top = 2.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = step,
                    style = MaterialTheme.typography.bodySmall,
                    color = OmniColors.TextSecondary
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Structured Analysis
        Text(
            text = "Structured Analysis",
            style = MaterialTheme.typography.labelSmall,
            color = OmniColors.TextTertiary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        engineResult.structured_analysis.forEach { (key, value) ->
            AnalysisEntry(label = formatKey(key), value = value)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun AnalysisEntry(label: String, value: Any?) {
    when (value) {
        is String -> ResultRow(label = label, value = value)
        is Number -> ResultRow(label = label, value = value.toString())
        is List<*> -> {
            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                Text(label, style = MaterialTheme.typography.bodySmall, color = OmniColors.TextTertiary)
                value.forEach { item ->
                    Text("• $item", style = MaterialTheme.typography.bodySmall, color = OmniColors.TextSecondary, modifier = Modifier.padding(start = 12.dp, top = 2.dp))
                }
            }
        }
        is Map<*, *> -> {
            Column(modifier = Modifier.padding(vertical = 4.dp)) {
                Text(label, style = MaterialTheme.typography.labelSmall, color = OmniColors.Accent)
                value.forEach { (k, v) ->
                    ResultRow(label = formatKey(k.toString()), value = v.toString(), indent = true)
                }
            }
        }
        else -> ResultRow(label = label, value = value?.toString() ?: "N/A")
    }
}

@Composable
private fun ResultRow(
    label: String,
    value: String,
    valueColor: Color = OmniColors.TextPrimary,
    indent: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp, horizontal = if (indent) 12.dp else 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = OmniColors.TextTertiary,
            modifier = Modifier.weight(0.45f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = valueColor,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(0.55f)
        )
    }
}

/**
 * Reasoning Tab — Shows transparent decision-making process.
 */
@Composable
private fun ReasoningTab(
    reasoningSteps: List<String>,
    classificationResult: ClassificationResult?
) {
    if (reasoningSteps.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    Icons.Default.Psychology,
                    contentDescription = null,
                    tint = OmniColors.TextTertiary,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "No reasoning data yet",
                    style = MaterialTheme.typography.titleMedium,
                    color = OmniColors.TextSecondary
                )
                Text(
                    text = "Run an analysis to see the decision process",
                    style = MaterialTheme.typography.bodySmall,
                    color = OmniColors.TextTertiary
                )
            }
        }
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        item {
            DashboardCard(
                title = "Decision Pipeline",
                icon = Icons.Default.AccountTree,
                iconColor = OmniColors.Accent
            ) {
                reasoningSteps.forEachIndexed { index, step ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        // Step number badge
                        Box(
                            modifier = Modifier
                                .size(22.dp)
                                .clip(CircleShape)
                                .background(OmniColors.PrimaryDim),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${index + 1}",
                                fontSize = 10.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))

                        // Connector line + step text
                        Column {
                            Text(
                                text = step,
                                style = MaterialTheme.typography.bodySmall,
                                color = OmniColors.TextSecondary,
                                fontFamily = FontFamily.Monospace
                            )
                            if (index < reasoningSteps.lastIndex) {
                                Box(
                                    modifier = Modifier
                                        .padding(start = 0.dp, top = 4.dp, bottom = 4.dp)
                                        .width(1.dp)
                                        .height(12.dp)
                                        .background(OmniColors.Border)
                                )
                            }
                        }
                    }
                }
            }
        }

        // All scores detail
        classificationResult?.let { result ->
            item {
                DashboardCard(
                    title = "Confidence Distribution",
                    icon = Icons.Default.BarChart,
                    iconColor = OmniColors.Secondary
                ) {
                    result.ranking.forEach { entry ->
                        val moduleColor = OmniColors.getModuleColor(entry.module)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(moduleColor)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = entry.module.replaceFirstChar { it.uppercase() },
                                style = MaterialTheme.typography.bodySmall,
                                color = OmniColors.TextSecondary,
                                modifier = Modifier.weight(1f)
                            )
                            Text(
                                text = String.format("%.2f%%", entry.score * 100),
                                style = MaterialTheme.typography.labelSmall,
                                color = moduleColor,
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }

            item {
                DashboardCard(
                    title = "Processing Info",
                    icon = Icons.Default.Info,
                    iconColor = OmniColors.TextTertiary
                ) {
                    ResultRow(label = "Input Features", value = "${result.input_features}")
                    ResultRow(label = "Timestamp", value = result.timestamp)
                    ResultRow(label = "Status", value = result.status)
                }
            }
        }
    }
}

/**
 * Logs Tab — Activity history viewer.
 */
@Composable
private fun LogsTab(
    logs: List<AnalysisLog>,
    onClearLogs: () -> Unit,
    onDecryptLog: (String) -> String,
    isAdmin: Boolean
) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredLogs = remember(logs, searchQuery) {
        if (searchQuery.isBlank()) logs
        else logs.filter { it.userInput.contains(searchQuery, ignoreCase = true) || it.classifiedModule.contains(searchQuery, ignoreCase = true) }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            placeholder = { Text("Search logs...", style = MaterialTheme.typography.bodySmall) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(20.dp)) },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = OmniColors.Border,
                focusedBorderColor = OmniColors.Primary
            )
        )

        if (filteredLogs.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.History,
                        contentDescription = null,
                        tint = OmniColors.TextTertiary,
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "No activity logs",
                        style = MaterialTheme.typography.titleMedium,
                        color = OmniColors.TextSecondary
                    )
                }
            }
            return
        }

        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            // Header with count and clear button
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${logs.size} entries",
                        style = MaterialTheme.typography.labelSmall,
                        color = OmniColors.TextTertiary
                    )
                    if (isAdmin) {
                        TextButton(onClick = onClearLogs) {
                            Icon(
                                Icons.Default.DeleteSweep,
                                contentDescription = "Clear All",
                                tint = OmniColors.Danger,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "Clear All",
                                style = MaterialTheme.typography.labelSmall,
                                color = OmniColors.Danger
                            )
                        }
                    }
                }
            }

            items(logs) { log ->
                LogEntryCard(log = log, onDecrypt = onDecryptLog, isAdmin = isAdmin)
            }
        }
    }
}

@Composable
private fun LogEntryCard(
    log: AnalysisLog,
    onDecrypt: (String) -> String,
    isAdmin: Boolean
) {
    val dateFormat = remember { SimpleDateFormat("MMM dd, HH:mm:ss", Locale.getDefault()) }
    val moduleColor = OmniColors.getModuleColor(log.classifiedModule)
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .background(OmniColors.Surface, RoundedCornerShape(10.dp))
            .border(1.dp, OmniColors.Border, RoundedCornerShape(10.dp))
            .clickable { expanded = !expanded }
            .padding(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(moduleColor)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = log.classifiedModule.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.titleMedium,
                    color = moduleColor,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 13.sp
                )
                Text(
                    text = log.userInput.take(80) + if (log.userInput.length > 80) "..." else "",
                    style = MaterialTheme.typography.bodySmall,
                    color = OmniColors.TextTertiary,
                    maxLines = 1
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = dateFormat.format(Date(log.timestamp)),
                    style = MaterialTheme.typography.labelSmall,
                    color = OmniColors.TextTertiary,
                    fontSize = 10.sp
                )
                Text(
                    text = "${(log.confidence * 100).toInt()}%",
                    style = MaterialTheme.typography.labelSmall,
                    color = moduleColor
                )
            }
        }

        AnimatedVisibility(visible = expanded) {
            Column(modifier = Modifier.padding(top = 8.dp)) {
                Divider(color = OmniColors.Border, thickness = 0.5.dp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Full Input",
                    style = MaterialTheme.typography.labelSmall,
                    color = OmniColors.TextTertiary
                )
                Text(
                    text = log.userInput,
                    style = MaterialTheme.typography.bodySmall,
                    color = OmniColors.TextSecondary,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .background(OmniColors.SurfaceElevated, RoundedCornerShape(6.dp))
                        .padding(8.dp)
                        .fillMaxWidth()
                )
                if (isAdmin) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Decrypted Result",
                        style = MaterialTheme.typography.labelSmall,
                        color = OmniColors.TextTertiary
                    )
                    Text(
                        text = onDecrypt(log.resultJson).take(500),
                        style = MaterialTheme.typography.bodySmall,
                        color = OmniColors.TextSecondary,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 10.sp,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .background(OmniColors.SurfaceElevated, RoundedCornerShape(6.dp))
                            .padding(8.dp)
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                    )
                }
            }
        }
    }
}

/**
 * Settings Tab — Role management and app configuration.
 */
@Composable
private fun SettingsTab(
    uiState: OmniAgentUiState,
    onAuthenticateAdmin: (String) -> Boolean,
    onSwitchToUser: () -> Unit,
    onToggleDemo: (Boolean) -> Unit,
    onRunDemo: (String) -> Unit
) {
    var adminPin by remember { mutableStateOf("") }
    var authMessage by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        // Presentation Header
        item {
            DemoPresentationBar(
                onDemoClick = onRunDemo,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        item {
            DashboardCard(
                title = "Access Control",
                icon = Icons.Default.Security,
                iconColor = OmniColors.Warning
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Current Role",
                        style = MaterialTheme.typography.bodySmall,
                        color = OmniColors.TextSecondary
                    )
                    StatusBadge(
                        text = uiState.currentRole.displayName,
                        color = if (uiState.currentRole == UserRole.ADMIN)
                            OmniColors.Warning else OmniColors.Secondary
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (uiState.currentRole != UserRole.ADMIN) {
                    // Admin authentication
                    OutlinedTextField(
                        value = adminPin,
                        onValueChange = { adminPin = it },
                        label = { Text("Admin PIN") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = OmniColors.Primary,
                            unfocusedBorderColor = OmniColors.Border,
                            cursorColor = OmniColors.Primary,
                            focusedLabelColor = OmniColors.Primary,
                            unfocusedLabelColor = OmniColors.TextTertiary
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            val success = onAuthenticateAdmin(adminPin)
                            authMessage = if (success) "✓ Admin access granted"
                            else "✗ Invalid PIN"
                            adminPin = ""
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = OmniColors.PrimaryDim
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Authenticate as Admin")
                    }
                    if (authMessage.isNotEmpty()) {
                        Text(
                            text = authMessage,
                            style = MaterialTheme.typography.bodySmall,
                            color = if (authMessage.startsWith("✓")) OmniColors.Secondary
                            else OmniColors.Danger,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                } else {
                    Button(
                        onClick = onSwitchToUser,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = OmniColors.SurfaceBright
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Switch to User Role", color = OmniColors.TextPrimary)
                    }
                }
            }
        }

        item {
            DashboardCard(
                title = "System Information",
                icon = Icons.Default.Info,
                iconColor = OmniColors.TextTertiary
            ) {
                ResultRow(label = "Version", value = "1.0.0")
                ResultRow(label = "Network", value = "OFFLINE (Disabled)")
                ResultRow(label = "Encryption", value = "AES-256-GCM")
                ResultRow(label = "Database", value = "Room/SQLite")
                ResultRow(label = "AI Kernel", value = "TF-IDF + Cosine Similarity")
                ResultRow(label = "Modules", value = "4 (Coding, Cyber, Resume, Startup)")
                ResultRow(label = "File Sandbox", value = "/uploads/ only")
                
                Divider(color = OmniColors.Border, modifier = Modifier.padding(vertical = 12.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Demo Presentation Mode", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
                        Text("Enable preloaded examples for judging", style = MaterialTheme.typography.labelSmall, color = OmniColors.TextTertiary)
                    }
                    Switch(
                        checked = uiState.isDemoMode,
                        onCheckedChange = onToggleDemo,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = OmniColors.Primary,
                            checkedTrackColor = OmniColors.Primary.copy(alpha = 0.5f)
                        )
                    )
                }
            }
        }

        item {
            DashboardCard(
                title = "Module Registry",
                icon = Icons.Default.Extension,
                iconColor = OmniColors.Accent
            ) {
                val modules = listOf(
                    Triple("Coding Analysis", "coding", "AST parsing, complexity metrics"),
                    Triple("Cybersecurity", "cybersecurity", "SQLi/XSS detection, risk scoring"),
                    Triple("Resume ATS", "resume", "ATS scoring, skill gap analysis"),
                    Triple("Startup Feasibility", "startup", "SWOT, market analysis")
                )
                modules.forEach { (name, key, desc) ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(OmniColors.getModuleColor(key))
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = name,
                                style = MaterialTheme.typography.bodySmall,
                                color = OmniColors.TextPrimary,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = desc,
                                style = MaterialTheme.typography.labelSmall,
                                color = OmniColors.TextTertiary,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

// === UTILITIES ===

private fun formatKey(key: String): String {
    return key.replace("_", " ")
        .split(" ")
        .joinToString(" ") { it.replaceFirstChar { c -> c.uppercase() } }
}

private fun scoreColor(score: Float): Color {
    return when {
        score >= 80 -> OmniColors.Secondary
        score >= 60 -> OmniColors.Primary
        score >= 40 -> OmniColors.Warning
        else -> OmniColors.Danger
    }
}
