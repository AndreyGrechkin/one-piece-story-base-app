package com.defey.onepiecestorybase.navigation

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.DEFAULT_ARGS_KEY
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SAVED_STATE_REGISTRY_OWNER_KEY
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.VIEW_MODEL_STORE_OWNER_KEY
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.enableSavedStateHandles
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner

class HiltSupport(
    override val lifecycle: Lifecycle,
    private val initialSavedState: Bundle?,
    private val application: Application?,
    val defaultArgs: Bundle? = null,
) : ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner {

    override val viewModelStore = ViewModelStore()
    private val savedStateRegistryController: SavedStateRegistryController =
        SavedStateRegistryController.create(this)

    private var canSaveState: Boolean = false

    init {
        savedStateRegistryController.performAttach()
        savedStateRegistryController.performRestore(initialSavedState?.let { Bundle(it) })
        enableSavedStateHandles()

        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onStart(owner: LifecycleOwner) {
                canSaveState = true
            }

            override fun onDestroy(owner: LifecycleOwner) {
                viewModelStore.clear()
            }
        })
    }

    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = SavedStateViewModelFactory(application, this, defaultArgs)

    override val defaultViewModelCreationExtras: CreationExtras
        get() = createDefaultViewModelCreationExtras()

    private fun createDefaultViewModelCreationExtras(): CreationExtras {
        val extras = MutableCreationExtras()
        application?.let { application ->
            extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] = application
        }
        extras[SAVED_STATE_REGISTRY_OWNER_KEY] = this
        extras[VIEW_MODEL_STORE_OWNER_KEY] = this
        defaultArgs?.let { args ->
            extras[DEFAULT_ARGS_KEY] = args
        }
        return extras
    }

    override val savedStateRegistry: SavedStateRegistry
        get() = savedStateRegistryController.savedStateRegistry

    fun saveState(): Bundle? {
        return if (canSaveState) {
            Bundle().also(savedStateRegistryController::performSave)
        } else {
            initialSavedState
        }
    }
}