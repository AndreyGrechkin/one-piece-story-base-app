package com.defey.onepiecestorybase.presentation.screens.detail.fruit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardBlock
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardDescriptionBlock
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardImageBlock
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardMangaBlock
import com.defey.onepiecestorybase.presentation.theme.OPTheme

@Composable
fun FruitScreen(
    state: FruitUiState,
    onEvent: (FruitUiEvent) -> Unit
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
                    image = state.fruit?.image,
                    imageDescription = state.fruit?.nameFruit,
                    onEvent = { onEvent(FruitUiEvent.CloseFruit) }
                )
            }

            if (state.fruit != null) {
                item {
                    CardBlock {
                        Text(
                            text = state.fruit.nameFruit,
                            style = OPTheme.typography.subHeading,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                        if (state.fruit.fruitType != null) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Text(
                                    text = stringResource(id = R.string.title_type),
                                    style = OPTheme.typography.title,
                                    modifier = Modifier
                                        .padding(end = 4.dp)
                                )
                                Text(
                                    text = state.fruit.fruitType,
                                    style = OPTheme.typography.title,
                                )
                            }

                        }
                        Spacer(modifier = Modifier.padding(bottom = 8.dp))
                    }
                }
            }

            item {
                CardMangaBlock(manga = state.manga)
            }

            if (state.fruit?.description != null) {
                item {
                    CardDescriptionBlock(
                        title = stringResource(id = R.string.title_description),
                        description = state.fruit.description
                    )
                }
            }

            if (state.personageList.isNotEmpty()) {
                item {
                    CardBlock {
                        Text(
                            text = stringResource(R.string.title_owner),
                            style = OPTheme.typography.title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                        state.personageList.forEach { personage ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = personage.personageImage,
                                    contentDescription = personage.personageName,
                                    contentScale = ContentScale.Crop,
                                    alignment = Alignment.TopCenter,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(ShapeDefaults.Medium)
                                )
                                Text(
                                    text = personage.personageName,
                                    style = OPTheme.typography.title,
                                    modifier = Modifier.padding(start = 16.dp),
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(bottom = 8.dp))
                    }
                }
            }
        }
    }
}