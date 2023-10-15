package com.defey.onepiecestorybase.presentation.screens.detail.location

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.domain.model.LocationPersonage
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardBlock
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardDescriptionBlock
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardImageBlock
import com.defey.onepiecestorybase.presentation.screens.detail.personage.ExpandButton
import com.defey.onepiecestorybase.presentation.screens.detail.personage.PersonageUiEvent
import com.defey.onepiecestorybase.presentation.theme.OPTheme
import com.defey.onepiecestorybase.presentation.utils.bounceClick

@Composable
fun IslandScreen(
    state: IslandUiState,
    onEvent: (IslandUiEvent) -> Unit,
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
                    image = state.imageLocation,
                    imageDescription = state.location?.namePlace,
                    onEvent = { onEvent(IslandUiEvent.CloseLocation) }
                )
            }

            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .paint(
                            painterResource(id = R.drawable.board),
                            contentScale = ContentScale.FillBounds
                        )
                ) {
                    Text(
                        text = state.location?.namePlace.toString(),
                        style = OPTheme.typography.segoe,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                    )
                    if (!state.manga.isNullOrEmpty()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.title_manga),
                                style = OPTheme.typography.title,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(
                                text = stringResource(R.string.title_charpters),
                                style = OPTheme.typography.title,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(
                                text = state.manga.toString(),
                                style = OPTheme.typography.title
                            )
                        }
                    }
                    if (!state.anime.isNullOrEmpty()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.title_anime),
                                style = OPTheme.typography.title,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(
                                text = state.anime.toString(),
                                style = OPTheme.typography.title
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                }
            }

            item {
                CardBlock {
                    if (state.location?.sea != null) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(R.string.title_whereabouts),
                                style = OPTheme.typography.title,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(
                                text = state.location.sea,
                                style = OPTheme.typography.title,
                            )
                        }
                    }
                    if (state.location?.islandType != null) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = stringResource(R.string.title_type),
                                style = OPTheme.typography.title,
                                modifier = Modifier.padding(end = 4.dp)
                            )
                            Text(
                                text = state.location.islandType,
                                style = OPTheme.typography.title,
                            )
                        }
                    }
                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                }
            }

            item {
                CardDescriptionBlock(
                    title = stringResource(id = R.string.title_description),
                    description = state.description
                )
            }

            item {
                CardDescriptionBlock(
                    title = stringResource(id = R.string.title_event),
                    description = state.event
                )
            }

            if (state.personageList.isNotEmpty()) {
                item {
                    CardBlock {
                        var expanded by remember { mutableStateOf(false) }
                        Text(
                            text = stringResource(R.string.title_personages),
                            style = OPTheme.typography.title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                        state.personageList.take(3).forEach { personage ->
                            ExpandPersonageBlock(personage = personage)
                        }

                        AnimatedVisibility(visible = expanded) {
                            Column {
                                state.personageList.drop(3).forEach { personage ->
                                    ExpandPersonageBlock(personage = personage)
                                }
                            }
                        }

                        if (state.personageList.size > 3) {
                            ExpandButton(
                                expanded = expanded,
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                onClick = { expanded = it }
                            )
                        } else {
                            Spacer(modifier = Modifier.padding(bottom = 8.dp))
                        }
                    }
                }
            }

            if (state.subjectList.isNotEmpty()) {
                item {
                    CardBlock {
                        Text(
                            text = stringResource(R.string.title_subject),
                            style = OPTheme.typography.title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                        state.subjectList.forEach { subject ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = subject.image,
                                    contentDescription = subject.name,
                                    contentScale = ContentScale.Crop,
                                    alignment = Alignment.TopCenter,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .clip(ShapeDefaults.Medium)
                                )
                                Text(
                                    text = subject.name,
                                    style = OPTheme.typography.title,
                                    modifier = Modifier.padding(start = 16.dp)
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

@Composable
fun ExpandPersonageBlock(personage: LocationPersonage) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
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
            modifier = Modifier
        )
        if (personage.isFruiting) {
            Image(
                painter = painterResource(id = R.drawable.fruit),
                contentDescription = stringResource(
                    id = R.string.title_fruit
                ),
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp)
            )
        } else {
            Spacer(modifier = Modifier.padding(end = 56.dp))
        }
    }
}