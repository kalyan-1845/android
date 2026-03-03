package com.omniagent.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.omniagent.app.ui.features.dashboard.DashboardScreen
import com.omniagent.app.ui.features.admin.AdminDashboardScreen
import com.omniagent.app.ui.features.splash.SplashScreen
import com.omniagent.app.core.model.UserRole
import com.omniagent.app.ui.theme.OmniAgentTheme
import com.omniagent.app.viewmodel.OmniAgentViewModel
import com.omniagent.app.viewmodel.OmniAgentViewModelFactory

/**
 * MainActivity — Entry point for the OmniAgent control platform.
 * Uses Jetpack Compose for the entire UI.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            OmniAgentTheme {
                var showSplash by remember { mutableStateOf(true) }

                if (showSplash) {
                    SplashScreen(
                        onSplashFinished = { showSplash = false }
                    )
                } else {
                    // Get DI container from Application
                    val appContainer = (applicationContext as OmniAgentApplication).container
                
                    // Inject repository into ViewModel via factory
                    val viewModel: OmniAgentViewModel = viewModel(
                        factory = OmniAgentViewModelFactory(
                            repository = appContainer.analysisRepository,
                            application = applicationContext as OmniAgentApplication
                        )
                    )
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                    val classificationResult by viewModel.classificationResult.collectAsStateWithLifecycle()
                    val engineResult by viewModel.engineResult.collectAsStateWithLifecycle()
                    val reasoningSteps by viewModel.reasoningSteps.collectAsStateWithLifecycle()
                    val logs by viewModel.recentLogs.collectAsStateWithLifecycle(initialValue = emptyList())

                    if (uiState.currentRole == UserRole.ADMIN) {
                        AdminDashboardScreen(
                            logs = logs,
                            onClearLogs = { viewModel.clearAllLogs() },
                            onDecryptLog = { viewModel.decryptLogResult(it) },
                            onExitAdmin = { viewModel.switchToUserRole() }
                        )
                    } else {
                        DashboardScreen(
                            uiState = uiState,
                            classificationResult = classificationResult,
                            engineResult = engineResult,
                            reasoningSteps = reasoningSteps,
                            logs = logs,
                            onAnalyze = { viewModel.analyzeInput(it) },
                            onSwitchTab = { viewModel.switchTab(it) },
                            onClearResults = { viewModel.clearResults() },
                            onClearLogs = { viewModel.clearAllLogs() },
                            onAuthenticateAdmin = { viewModel.authenticateAdmin(it) },
                            onSwitchToUser = { viewModel.switchToUserRole() },
                            onDecryptLog = { viewModel.decryptLogResult(it) },
                            onToggleDemo = { viewModel.toggleDemoMode(it) },
                            onRunDemo = { viewModel.runDemo(it) },
                            modifier = Modifier
                                .fillMaxSize()
                                .systemBarsPadding()
                        )
                    }
                }
            }
        }
    }
}
