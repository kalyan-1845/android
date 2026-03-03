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
import com.omniagent.app.core.theme.OmniAgentTheme
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
                // Get DI container from Application
                val appContainer = (application as OmniAgentApplication).container
                
                // Inject repository into ViewModel via factory
                val viewModel: OmniAgentViewModel = viewModel(
                    factory = OmniAgentViewModelFactory(
                        repository = appContainer.analysisRepository,
                        application = application
                    )
                )
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val classificationResult by viewModel.classificationResult.collectAsStateWithLifecycle()
                val engineResult by viewModel.engineResult.collectAsStateWithLifecycle()
                val reasoningSteps by viewModel.reasoningSteps.collectAsStateWithLifecycle()
                val logs by viewModel.recentLogs.collectAsStateWithLifecycle(initialValue = emptyList())

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
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                )
            }
        }
    }
}
