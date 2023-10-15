package com.defey.onepiecestorybase.presentation.screens.detail.subject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardBlock
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardDescriptionBlock
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardImageBlock
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardMangaBlock
import com.defey.onepiecestorybase.presentation.theme.OPTheme

@Composable
fun SubjectScreen(
    state: SubjectUiState,
    onEvent: (SubjectUiEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(OPTheme.colors.whiteColor, OPTheme.colors.blueColor)
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                CardImageBlock(
                    image = state.subject?.image,
                    imageDescription = state.subject?.name,
                    onEvent = { onEvent(SubjectUiEvent.SubjectClose) }
                )
            }

            if (state.subject != null) {
                item {
                    CardBlock {
                        Text(
                            text = state.subject.name,
                            style = OPTheme.typography.subHeading,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        )
                    }
                }
            }

            item {
                CardMangaBlock(manga = state.manga)
            }

            if (state.subject?.description != null) {
                item {
                    CardDescriptionBlock(
                        title = stringResource(id = R.string.title_description),
                        description = state.subject.description
                    )
                }
            }
        }
    }
}