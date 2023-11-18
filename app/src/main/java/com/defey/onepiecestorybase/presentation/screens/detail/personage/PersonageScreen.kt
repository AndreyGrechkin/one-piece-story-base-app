package com.defey.onepiecestorybase.presentation.screens.detail.personage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.domain.model.Career
import com.defey.onepiecestorybase.domain.model.Manga
import com.defey.onepiecestorybase.presentation.theme.OPTheme
import com.defey.onepiecestorybase.presentation.utils.bounceClick
import com.defey.onepiecestorybase.presentation.utils.formatNumberWithSeparators

@Composable
fun PersonageScreen(
    state: PersonageUiState,
    onEvent: (PersonageUiEvent) -> Unit
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
                    image = state.personageImage,
                    imageDescription = state.namePersonage,
                    onEvent = { onEvent(PersonageUiEvent.ClosePersonage) }
                )
            }

            item {
                CardBlock {
                    Text(
                        text = state.namePersonage,
                        style = OPTheme.typography.heading,
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    if (state.surnamePersonage != null)
                        Text(
                            text = state.surnamePersonage,
                            style = OPTheme.typography.title,
                            modifier = Modifier
                                .padding(bottom = 8.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                }
            }
            if (state.careerList.isNotEmpty()) {
                item {
                    var expanded by remember { mutableStateOf(false) }
                    val careerActive = state.careerList.first()
                    CardBlock {
                        CareerExpandItemBlocK(career = careerActive)
                        AnimatedVisibility(visible = expanded) {
                            Column {
                                state.careerList.drop(1).forEach { career ->
                                    CareerExpandItemBlocK(
                                        career = career,
                                        textDecoration = TextDecoration.LineThrough
                                    )
                                }
                            }
                        }
                        if (state.careerList.size > 1) {
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

            if (state.rewardList.isNotEmpty()) {
                item {
                    var expanded by remember { mutableStateOf(false) }
                    CardBlock {
                        Text(
                            text = stringResource(R.string.reward_title),
                            style = OPTheme.typography.title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                        Text(
                            text = state.rewardList.first().rewardType,
                            style = OPTheme.typography.subHeading,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.beli),
                                contentDescription = stringResource(R.string.description_icon_belli),
                                modifier = Modifier
                                    .padding(bottom = 8.dp)
                            )
                            Text(
                                text = formatNumberWithSeparators(state.rewardList.first().reward),
                                style = OPTheme.typography.heading
                            )
                            if (state.rewardList.size > 1) {
                                Icon(
                                    imageVector =
                                    if (expanded)
                                        ImageVector.vectorResource(id = R.drawable.close_icon)
                                    else
                                        ImageVector.vectorResource(id = R.drawable.old_reward),
                                    contentDescription = stringResource(R.string.description_icon_old_reward),
                                    modifier = Modifier.clickable { expanded = !expanded })
                            } else {
                                Spacer(modifier = Modifier.size(24.dp))
                            }
                        }

                        AnimatedVisibility(visible = expanded) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                state.rewardList.drop(1).forEach {
                                    Text(
                                        text = formatNumberWithSeparators(it.reward),
                                        style = OPTheme.typography.subHeading,
                                        textDecoration = TextDecoration.LineThrough
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item {
                CardMangaBlock(manga = state.manga)
            }

            if (state.description != null) {
                item {
                    CardDescriptionBlock(
                        title = stringResource(R.string.title_description),
                        description = state.description
                    )
                }
            }

            if (state.fruit != null) {
                item {
                    CardBlock {
                        Text(
                            text = stringResource(R.string.title_fruit),
                            style = OPTheme.typography.title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                        ) {
                            AsyncImage(
                                model = state.fruit.image,
                                contentDescription = stringResource(R.string.title_fruit),
                                alignment = Alignment.CenterStart,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                                    .size(36.dp)
                            )
                            Text(
                                text = state.fruit.nameFruit,
                                style = OPTheme.typography.subHeading,
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(horizontal = 52.dp)
                            )
                        }
                    }
                }
            }

            if (state.weapons.isNotEmpty()) {
                item {
                    var expanded by remember { mutableStateOf(false) }
                    val mapWeapons = state.weapons.associate { it.id to false }
                    val expandedItem =
                        remember { mutableStateMapOf(*mapWeapons.toList().toTypedArray()) }
                    CardBlock {
                        Text(
                            text = stringResource(R.string.title_weapons),
                            style = OPTheme.typography.title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                        state.weapons.filterNot { it.oldWeapon }.forEach { weapon ->
                            ExpandItemBlock(
                                expandedItem = expandedItem,
                                id = weapon.id,
                                name = weapon.nameWeapons.toString(),
                                description = weapon.description.toString(),
                                onClick = { expandedItem[weapon.id] = it }
                            )
                        }

                        AnimatedVisibility(visible = expanded) {
                            Column {
                                state.weapons.filter { it.oldWeapon }.forEach { weapon ->
                                    ExpandItemBlock(
                                        expandedItem = expandedItem,
                                        id = weapon.id,
                                        name = weapon.nameWeapons.toString(),
                                        description = weapon.description.toString(),
                                        textDecoration = TextDecoration.LineThrough,
                                        onClick = { expandedItem[weapon.id] = it }
                                    )
                                }
                            }
                        }

                        if (state.weapons.any { it.oldWeapon }) {
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

            if (state.skillList.isNotEmpty()) {
                item {
                    var expanded by remember { mutableStateOf(false) }
                    val mapSkills = state.skillList.associate { it.id to false }
                    val expandedItem =
                        remember { mutableStateMapOf(*mapSkills.toList().toTypedArray()) }
                    CardBlock {
                        Text(
                            text = stringResource(R.string.title_skills),
                            style = OPTheme.typography.title,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        )
                        state.skillList.take(5).forEach { skill ->
                            ExpandItemBlock(
                                expandedItem = expandedItem,
                                id = skill.id,
                                name = skill.nameSkill,
                                description = skill.description.toString(),
                                onClick = { expandedItem[skill.id] = it }
                            )
                        }

                        AnimatedVisibility(visible = expanded) {
                            Column {
                                state.skillList.drop(5).forEach { skill ->
                                    ExpandItemBlock(
                                        expandedItem = expandedItem,
                                        id = skill.id,
                                        name = skill.nameSkill,
                                        description = skill.description.toString(),
                                        onClick = { expandedItem[skill.id] = it }
                                    )
                                }
                            }
                        }

                        if (state.skillList.size > 5) {
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
        }
    }
}

@Composable
fun CardBlock(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        modifier = modifier
            .fillMaxWidth()
            .paint(
                painterResource(id = R.drawable.card_backgraund),
                contentScale = ContentScale.FillBounds
            ),
        content = content
    )
}

@Composable
fun CardImageBlock(image: String?, imageDescription: String?, onEvent: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            AsyncImage(
                model = image,
                contentDescription = imageDescription,
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 3f),
            )
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.close_icon),
                contentDescription = stringResource(id = R.string.close),
                modifier = Modifier
                    .padding(top = 8.dp, end = 8.dp)
                    .align(Alignment.TopEnd)
                    .bounceClick(onClick = onEvent),
            )
        }
    }
}

@Composable
fun ExpandButton(expanded: Boolean, modifier: Modifier, onClick: (Boolean) -> Unit) {
    IconButton(
        onClick = { onClick(!expanded) },
        modifier = modifier
            .size(24.dp)
    ) {
        if (expanded) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.keyboard_arrow_down),
                contentDescription = stringResource(R.string.icon_description_arrow_expand),
                modifier = Modifier.rotate(180f)
            )
        } else {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.keyboard_arrow_down),
                contentDescription = stringResource(R.string.icon_description_arrow_expand)
            )
        }
    }
}

@Composable
fun ExpandItemBlock(
    expandedItem: SnapshotStateMap<Int, Boolean>,
    id: Int,
    name: String,
    description: String,
    textDecoration: TextDecoration? = null,
    onClick: (Boolean) -> Unit
) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = name,
                style = OPTheme.typography.title,
                textDecoration = textDecoration
            )
            IconButton(
                onClick = {
                    onClick(!expandedItem.getValue(id))
                },
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(16.dp)
                    .align(Alignment.CenterEnd)
            ) {
                if (expandedItem.getValue(id)) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.close_icon),
                        contentDescription = stringResource(R.string.icon_description_arrow_expand),
                        modifier = Modifier.size(16.dp)
                    )
                } else {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.close_icon),
                        contentDescription = stringResource(R.string.icon_description_arrow_expand),
                        modifier = Modifier
                            .size(16.dp)
                            .rotate(45f)
                    )
                }
            }
        }

        AnimatedVisibility(visible = expandedItem.getValue(id)) {
            Text(
                text = description,
                style = OPTheme.typography.body,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
}

@Composable
fun CareerExpandItemBlocK(career: Career, textDecoration: TextDecoration? = null) {
    Box(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = career.type.toString(),
                style = OPTheme.typography.title,
                textDecoration = textDecoration
            )
            if (career.bandName != null)
                Text(
                    text = career.bandName,
                    style = OPTheme.typography.title,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 52.dp),
                    textDecoration = textDecoration
                )
            if (career.career != null)
                Text(
                    text = career.career,
                    style = OPTheme.typography.title,
                    textDecoration = textDecoration
                )
        }
        if (career.bandImage != null)
            AsyncImage(
                model = career.bandImage,
                contentDescription = career.bandName,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(36.dp)
                    .align(
                        alignment = Alignment.CenterEnd
                    )
            )
    }
}

@Composable
fun CardDescriptionBlock(title: String, description: String) {
    var expanded by remember { mutableStateOf(false) }
    var isTextBig by remember { mutableStateOf(false) }
    CardBlock {
        Text(
            text = title,
            style = OPTheme.typography.title,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )

        AnimatedVisibility(visible = !expanded) {
            Text(
                text = description,
                style = OPTheme.typography.body,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                onTextLayout = {
                    isTextBig = it.didOverflowHeight
                }
            )
        }
        AnimatedVisibility(visible = expanded) {
            Text(
                text = description,
                style = OPTheme.typography.body,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        }
        if (isTextBig) {
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

@Composable
fun CardMangaBlock(manga: Manga?) {
    CardBlock {
        Text(
            text = stringResource(R.string.title_manga_in),
            style = OPTheme.typography.title,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        if (manga?.chapter != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(R.string.title_manga),
                    style = OPTheme.typography.title,
                    modifier = Modifier
                        .padding(end = 4.dp)
                )
                Text(
                    text = manga.volume.toString(),
                    style = OPTheme.typography.title,
                    modifier = Modifier
                        .padding(end = 4.dp)
                )
                Text(
                    text = stringResource(R.string.title_chapter),
                    style = OPTheme.typography.title,
                    modifier = Modifier
                        .padding(end = 4.dp)
                )
                Text(text = manga.chapter, style = OPTheme.typography.title)
            }
        }
        if (manga?.animeSeries != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    text = stringResource(R.string.title_anime),
                    style = OPTheme.typography.title,
                    modifier = Modifier
                        .padding(end = 4.dp)
                )
                Text(
                    text = manga.animeType.toString(),
                    style = OPTheme.typography.title,
                    modifier = Modifier
                        .padding(end = 4.dp)
                )
                Text(text = manga.animeSeries, style = OPTheme.typography.title)
            }
        }
        Spacer(modifier = Modifier.padding(bottom = 8.dp))
    }
}