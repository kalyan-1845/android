package com.omniagent.app

import android.app.Application
import com.omniagent.app.di.AppContainer

/**
 * Base Application class for the OmniAgent app.
 * Initializes the Dependency Injection container.
 */
class OmniAgentApplication : Application() {
    
    // Application-level dependency container
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        
        // Initialize manual DI container
        container = AppContainer(this)
    }
}
