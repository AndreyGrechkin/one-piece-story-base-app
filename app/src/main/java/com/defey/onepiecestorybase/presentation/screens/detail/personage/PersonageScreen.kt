package com.defey.onepiecestorybase.presentation.screens.detail.personage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.presentation.theme.OPTheme

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
                    colors = listOf(Color(0xFFFFFF), Color(0x99005EEC))
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = state.personageImage,
                        contentDescription = state.namePersonage,
                        contentScale = ContentScale.FillWidth
                    )
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.close_icon),
                        contentDescription = stringResource(id = R.string.close),
                        tint = OPTheme.colors.blackColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, end = 8.dp)
                            .align(Alignment.TopEnd),
                    )
                }
            }

            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.card_backgraund),
                        contentDescription = "background",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth()

                    )
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text(text = state.namePersonage, style = OPTheme.typography.heading)
                            if (state.surnamePersonage != null)
                                Text(text = state.surnamePersonage, style = OPTheme.typography.body)
                        }

                    }
                }
            }

            item {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.card_backgraund),
                        contentDescription = "background",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                    ) {
                        Column {
                            state.careerList.forEachIndexed() { index, career ->
                                Row {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    ) {
                                        if (career.type != null)
                                            Text(
                                                text = career.type,
                                                style = OPTheme.typography.title,
                                                textDecoration = if (index != 0) TextDecoration.LineThrough else null
                                            )

                                        if (career.bandName != null)
                                            Text(
                                                text = career.bandName,
                                                style = OPTheme.typography.body,
                                                textDecoration = if (index != 0) TextDecoration.LineThrough else null
                                            )
                                        if (career.career != null)
                                            Text(
                                                text = career.career,
                                                style = OPTheme.typography.body,
                                                textDecoration = if (index != 0) TextDecoration.LineThrough else null
                                            )
                                    }
                                    if (career.bandImage != null)
                                        AsyncImage(
                                            model = career.bandImage,
                                            contentDescription = career.bandName,
                                            modifier = Modifier.size(24.dp).align(alignment = Alignment.CenterVertically)
                                        )
                                }

                            }
                        }


                    }
                }
            }

        }
    }
}