package com.defey.onepiecestorybase.presentation.screens.lists.tabs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.domain.model.LocationCompact
import com.defey.onepiecestorybase.presentation.screens.lists.ListsUiEvent
import com.defey.onepiecestorybase.presentation.theme.OPTheme
import com.defey.onepiecestorybase.presentation.utils.bounceClick

@Composable
fun LocationTab(
    locationList: List<LocationCompact>,
    onEvent: (ListsUiEvent) -> Unit
) {
    LazyColumn {
        items(locationList) { location ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .bounceClick {
                        onEvent(ListsUiEvent.LocationClick(location.locationId))
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
                        model = location.locationImage,
                        contentDescription = stringResource(id = R.string.image_description_avatar),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    ) {
                        if (location.placeName != null) {
                            Text(
                                text = location.placeName,
                                style = OPTheme.typography.title,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        if (location.locationName != null) {
                            Text(
                                text = location.locationName,
                                style = OPTheme.typography.body,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                        if (location.sea != null) {
                            Text(
                                text = location.sea,
                                style = OPTheme.typography.body,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}