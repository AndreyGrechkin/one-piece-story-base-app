package com.defey.onepiecestorybase.presentation.screens.detail.band

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.domain.model.PersonageBandContent
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardBlock
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardDescriptionBlock
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardImageBlock
import com.defey.onepiecestorybase.presentation.screens.detail.personage.ExpandButton
import com.defey.onepiecestorybase.presentation.theme.OPTheme

@Composable
fun BandScreen(
    state: BandUiState,
    onEvent: (BandUiEvent) -> Unit
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
                    image = state.imageBand,
                    imageDescription = state.nameBand,
                    onEvent = { onEvent(BandUiEvent.CloseBand) }
                )
            }

            item {
                CardBlock {
                    Text(
                        text = state.nameBand,
                        style = OPTheme.typography.subHeading,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                    if (state.capitanName != null) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {

                            Text(
                                text = stringResource(R.string.title_capitan),
                                style = OPTheme.typography.title,
                                modifier = Modifier
                                    .padding(end = 4.dp)
                            )
                            Text(
                                text = state.capitanName,
                                style = OPTheme.typography.title,
                            )
                        }

                    }
                    Spacer(modifier = Modifier.padding(bottom = 8.dp))
                }
            }

            if (state.description != null) {
                item {
                    CardDescriptionBlock(
                        title = stringResource(id = R.string.title_description),
                        description = state.description
                    )
                }
            }

            if (state.personageList.isNotEmpty()) {
                item {
                    CardBlock {
                        var expanded by remember { mutableStateOf(false) }
                        Text(
                            text = stringResource(R.string.title_team),
                            style = OPTheme.typography.title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                        state.personageList.take(3).forEach { personage ->
                            ExpandTeamBlock(personage = personage)
                        }

                        AnimatedVisibility(visible = expanded) {
                            Column {
                                state.personageList.drop(3).forEach { personage ->
                                    ExpandTeamBlock(personage = personage)
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

            if (state.shipList.isNotEmpty()) {
                item {
                    CardBlock {
                        Text(
                            text = stringResource(R.string.title_ship),
                            style = OPTheme.typography.title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                        state.shipList.forEach { ship ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 16.dp, end = 16.dp, bottom = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = ship.image,
                                    contentDescription = ship.nameShip,
                                    contentScale = ContentScale.Crop,
                                    alignment = Alignment.TopCenter,
                                    modifier = if (ship.oldShip != null) {
                                        Modifier
                                            .size(60.dp)
                                            .clip(ShapeDefaults.Medium)
                                            .alpha(0.5f)
                                    } else {
                                        Modifier
                                            .size(60.dp)
                                            .clip(ShapeDefaults.Medium)

                                    }
                                )
                                Text(
                                    text = ship.nameShip.toString(),
                                    style = OPTheme.typography.title,
                                    modifier = Modifier.padding(start = 16.dp),
                                    textDecoration = if (ship.oldShip != null) TextDecoration.LineThrough else null,
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
fun ExpandTeamBlock(personage: PersonageBandContent) {
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
            modifier = if (personage.oldPersonage) {
                Modifier
                    .size(60.dp)
                    .clip(ShapeDefaults.Medium)
                    .alpha(0.5f)
            } else {
                Modifier
                    .size(60.dp)
                    .clip(ShapeDefaults.Medium)
            }
        )
        Text(
            text = personage.personageName,
            style = OPTheme.typography.title,
            textDecoration = if (personage.oldPersonage) TextDecoration.LineThrough else null,
            modifier = Modifier
        )
        if (personage.personageIsFruiting) {
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