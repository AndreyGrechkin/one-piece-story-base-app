package com.defey.onepiecestorybase.presentation.screens.lists.tabs

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.domain.model.SubjectCompact
import com.defey.onepiecestorybase.presentation.screens.lists.ListsUiEvent
import com.defey.onepiecestorybase.presentation.theme.OPTheme
import com.defey.onepiecestorybase.presentation.utils.bounceClick

@Composable
fun SubjectTab(
    subjectList: List<SubjectCompact>,
    onEvent: (ListsUiEvent) -> Unit
) {
    LazyColumn {
        items(subjectList) { subject ->
           // Log.d("MyLog", "band: $subject")
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .bounceClick {
                        onEvent(ListsUiEvent.SubjectClick(subject.subjectId))
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
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AsyncImage(
                        model = subject.subjectImage,
                        contentDescription = "avatar",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(70.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .weight(1f)
                    ) {
                        Text(
                            text = subject.subjectName,
                            style = OPTheme.typography.title,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    if (subject.isNewSubject) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.point),
                            contentDescription = null,
                            tint = OPTheme.colors.greenColor,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(8.dp)
                                .align(Alignment.Top)
                        )
                    } else {
                        Spacer(modifier = Modifier.size(8.dp))
                    }
                }
            }
        }
    }
}