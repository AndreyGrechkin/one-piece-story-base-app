package com.defey.onepiecestorybase.navigation.top

import com.defey.onepiecestorybase.presentation.utils.AppOutlineTextField
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.presentation.screens.AppViewModel
import com.defey.onepiecestorybase.presentation.screens.UiEvent
import com.defey.onepiecestorybase.presentation.screens.UiState
import com.defey.onepiecestorybase.presentation.theme.OPTheme
import kotlinx.coroutines.delay

enum class SearchAppBarState {
    CLOSED,
    OPENED
}

enum class TrailingIconState {
    DELETE,
    CLOSE
}

@ExperimentalAnimationApi
@Composable
fun <S : UiState, E : UiEvent> TopBarNavigation(
    viewModel: AppViewModel<S, E>,
    searchAppBarState: SearchAppBarState,
    searchTextState: String
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultTopAppBar(
                onSearchClicked = {
                    viewModel.searchAppBarState.value =
                        SearchAppBarState.OPENED
                },
                viewModel = viewModel
            )
        }

        else -> {
            SearchTopAppBar(
                text = searchTextState,
                onTextChange = { text ->
                    viewModel.searchTextState = text
                    viewModel.topBarData.onAction(text)
                },
                onCloseClicked = {
                    viewModel.searchAppBarState.value =
                        SearchAppBarState.CLOSED
                    viewModel.searchTextState = ""
                },
                onSearchClicked = { text ->
                    viewModel.topBarData.onAction(text)
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <S : UiState, E : UiEvent> DefaultTopAppBar(
    onSearchClicked: () -> Unit,
    viewModel: AppViewModel<S, E>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CenterAlignedTopAppBar(
            modifier = Modifier,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = OPTheme.colors.babyBlueEyesColor,
                scrolledContainerColor = Color.Blue,
                navigationIconContentColor = Color.White,
                titleContentColor = OPTheme.colors.blackColor,
                actionIconContentColor = OPTheme.colors.blackColor
            ),
            navigationIcon = {
                if (viewModel.topBarData.showBackButton) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back),
                        contentDescription = stringResource(id = R.string.back),
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable { viewModel.navigateBack() },
                    )

                }
            },
            title = {
                Text(
                    text = viewModel.topBarData.title.asString(),
                    color = OPTheme.colors.blackColor,
                    style = OPTheme.typography.heading
                )
            },
            actions = {
                SearchAction(onSearchClicked = onSearchClicked)
            }
        )
    }
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(
        onClick = {
            onSearchClicked()
        }
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.search),
            contentDescription = stringResource(id = R.string.search),
            tint = OPTheme.colors.blackColor
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalAnimationApi
@Composable
fun SearchTopAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {

    var trailingIconState by remember {
        mutableStateOf(TrailingIconState.DELETE)
    }
    var textFieldVisible by remember { mutableStateOf(false) }
    val focusRequester = FocusRequester()

    LaunchedEffect(Unit) {
        textFieldVisible = true
        delay(500)
        focusRequester.requestFocus()
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = OPTheme.colors.babyBlueEyesColor,
                scrolledContainerColor = Color.Blue,
                navigationIconContentColor = OPTheme.colors.whiteColor,
                titleContentColor = OPTheme.colors.blackColor,
                actionIconContentColor = OPTheme.colors.blackColor,
            ),
            title = {
                AnimatedVisibility(
                    visible = textFieldVisible,
                    enter = slideInHorizontally()
                            + expandHorizontally(expandFrom = Alignment.Start)
                            + fadeIn(),
                    exit = slideOutHorizontally()
                            + shrinkHorizontally(shrinkTowards = Alignment.End)
                            + fadeOut(),
                ) {
                    AppOutlineTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        value = text,
                        onValueChange = {
                            onTextChange(it)
                        },
                        placeholder = {
                            Text(
                                modifier = Modifier
                                    .alpha(ContentAlpha.medium),
                                text = stringResource(id = R.string.search),
                                color = OPTheme.colors.lightGrayColor,
                                style = OPTheme.typography.body
                            )
                        },
                        textStyle = TextStyle(
                            color = Color.Black,
                            fontSize = 16.sp
                        ),
                        singleLine = true,
                        shape = MaterialTheme.shapes.medium,
                        leadingIcon = {
                            IconButton(
                                modifier = Modifier
                                    .alpha(ContentAlpha.medium),
                                onClick = {
                                    onSearchClicked(text)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = stringResource(id = R.string.search),
                                    tint = Color.Black
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                            onSearch = {
                                onSearchClicked(text)
                            }
                        ),
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = OPTheme.colors.blackColor,
                            focusedIndicatorColor = OPTheme.colors.blackColor,
                            disabledIndicatorColor = OPTheme.colors.grayColor,
                            unfocusedIndicatorColor = OPTheme.colors.blackColor,
                            backgroundColor = OPTheme.colors.whiteColor
                        )
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    when (trailingIconState) {
                        TrailingIconState.DELETE -> {
                            if (text.isEmpty()) {
                                textFieldVisible = false

                                onCloseClicked()
                            } else {
                                onTextChange("")
                                trailingIconState = TrailingIconState.CLOSE
                            }
                        }

                        TrailingIconState.CLOSE -> {
                            if (text.isNotEmpty()) {
                                onTextChange("")
                            } else {
                                textFieldVisible = false

                                onCloseClicked()
                                trailingIconState = TrailingIconState.DELETE
                            }
                        }
                    }
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.close_icon),
                        contentDescription = stringResource(id = R.string.close),
                        tint = OPTheme.colors.blackColor
                    )
                }
            }
        )
    }
}
