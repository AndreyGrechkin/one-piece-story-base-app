package com.defey.onepiecestorybase

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeComponentActivity
import com.defey.onepiecestorybase.navigation.RootNode
import com.defey.onepiecestorybase.presentation.theme.OPTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : NodeComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OPTheme {
                NodeHost(integrationPoint = appyxIntegrationPoint) { buildContext ->
                    RootNode(
                        buildContext = buildContext,
                        application = application,
                    )
                }
            }
        }
    }
}