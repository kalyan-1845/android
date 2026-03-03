package com.omniagent.app

import android.app.Application
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.omniagent.app.di.AppContainer

/**
 * Base Application class for the OmniAgent app.
 * Initializes the Dependency Injection container and Python environment.
 */
class OmniAgentApplication : Application() {
    
    // Application-level dependency container
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        
        // Initialize Python environment (MUST BE FIRST for Kernels)
        if (!Python.isStarted()) {
            Python.start(AndroidPlatform(this))
        }

        // Initialize manual DI container
        container = AppContainer(this)
    }
}
