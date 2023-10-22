package com.defey.onepiecestorybase.presentation.screens.lists.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.domain.model.PersonageCompact
import com.defey.onepiecestorybase.presentation.screens.lists.ListsUiEvent
import com.defey.onepiecestorybase.presentation.theme.OPTheme
import com.defey.onepiecestorybase.presentation.utils.bounceClick

@Composable
fun PersonageTab(
    personageList: List<PersonageCompact>,
    onEvent: (ListsUiEvent) -> Unit
) {
    LazyColumn {
        items(personageList) { personage ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .bounceClick {
                        onEvent(ListsUiEvent.PersonageClick(personage.personageId))
                    },
                colors = CardDefaults.cardColors(containerColor = OPTheme.colors.whiteColor),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 0.dp
                ),
                border = BorderStroke(width = 1.dp, brush = SolidColor(OPTheme.colors.blackColor))
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = personage.personageImage,
                        contentDescription = stringResource(R.string.image_description_avatar),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.TopCenter,
                        modifier = Modifier
                            .size(70.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = personage.name,
                            style = OPTheme.typography.title,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.fillMaxWidth()
                        )
                        if (personage.surname != null) {
                            Text(
                                text = personage.surname,
                                style = OPTheme.typography.body,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        if (personage.bandName != null) {
                            Text(
                                text = personage.bandName,
                                style = OPTheme.typography.body,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .size(45.dp, 52.dp)
                            .padding(end = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (personage.isNewPersonage) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.point),
                                contentDescription = null,
                                tint = OPTheme.colors.greenColor,
                                modifier = Modifier
                                    .size(8.dp)
                                    .align(Alignment.TopEnd)
                            )
                        }
                        if (personage.bandImage != null) {
                            AsyncImage(
                                model = personage.bandImage,
                                contentDescription = stringResource(R.string.image_description_flag),
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .size(45.dp, 40.dp)
                                    .align(Alignment.Center)
                            )
                        } else {
                            Spacer(
                                modifier = Modifier
                                    .size(40.dp)
                                    .padding(start = 8.dp, end = 16.dp)
                            )
                        }

                    }
                }
            }
        }
    }
}