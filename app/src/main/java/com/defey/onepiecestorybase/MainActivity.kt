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

//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
            }
        }
    }
}

//@Composable
//fun Greeting(
//    name: String,
//    modifier: Modifier = Modifier,
//    viewModel: MainViewModel = hiltViewModel()
//) {
//
//    val map = viewModel.place
//
//    Column() {
//        Text(
//            text = "place: ${map.namePlace}",
//            modifier = modifier
//        )
//        Text(
//            text = "transcription: ${map.transcriptionJp}",
//            modifier = modifier
//        )
//
//        LazyColumn {
//            items(map.personageDescription) {
//                Row {
//                    Image(
//                        painter = rememberImagePainter(data = it.image),
//                        contentDescription = "avatar",
//                        modifier = modifier
//                            .width(60.dp)
//                            .height(60.dp)
//                    )
//                    Column(modifier = modifier.padding(16.dp)) {
//                        Text(text = "Description: ${it.description}")
//                        Text(text = "Type: ${it.personageType}")
//                        Text(text = "Fruit: ${it.fruitId}")
//                        Text(text = "Personage: ${it.personageId}")
//
//                    }
//                }
//            }
//        }
//
//        Button(onClick = { viewModel.getPlace("9") }) {
//            Text(text = "Жмак", modifier = modifier)
//
//        }
//    }
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    OnePieceStoryBaseTheme {
//        Greeting("Android")
//    }
//}