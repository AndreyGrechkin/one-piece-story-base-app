package com.defey.onepiecestorybase.navigation

import android.app.Application
import android.os.Bundle
import com.bumble.appyx.core.modality.BuildContext
import com.bumble.appyx.core.node.Node
import com.bumble.appyx.core.state.MutableSavedStateMap

private const val SAVED_STATE_REGISTRY_KEY = "SAVED_STATE_REGISTRY"

open class HiltComposeNode(
    buildContext: BuildContext,
    application: Application?,
    defaultArgs: Bundle? = null
) : Node(buildContext), HasHiltSupport {

    override val hiltSupport = HiltSupport(
        lifecycle = lifecycle,
        initialSavedState = buildContext.savedStateMap?.get(SAVED_STATE_REGISTRY_KEY) as Bundle?,
        application = application,
        defaultArgs = defaultArgs
    )

    override fun onSaveInstanceState(state: MutableSavedStateMap) {
        super.onSaveInstanceState(state)
        state[SAVED_STATE_REGISTRY_KEY] = hiltSupport.saveState()
    }
}