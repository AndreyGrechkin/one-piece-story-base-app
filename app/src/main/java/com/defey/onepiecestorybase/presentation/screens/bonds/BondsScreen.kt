package com.defey.onepiecestorybase.presentation.screens.bonds

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.defey.onepiecestorybase.R
import com.defey.onepiecestorybase.domain.enum.BondType
import com.defey.onepiecestorybase.domain.model.BondContent
import com.defey.onepiecestorybase.presentation.screens.detail.personage.CardBlock
import com.defey.onepiecestorybase.presentation.theme.OPTheme
import com.defey.onepiecestorybase.presentation.utils.bounceClick

@Composable
fun BondsScreen(
    state: BondsUiState,
    onEvent: (BondsUiEvent) -> Unit,
) {
    val gridState = rememberLazyGridState()
    LaunchedEffect(key1 = state.choosePersonage) {
        gridState.animateScrollToItem(0)
    }
    if (state.isShowBondDialog) {
        BondDialog(bond = state.dialogBond) {
            onEvent(BondsUiEvent.ClickCloseDialog)
        }
    }
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = gridState
    ) {
        if (state.hasChoosePersonage) {
            item(
                span = {
                    GridItemSpan(4)
                }
            ) {
                val bond = state.choosePersonage
                if (bond != null) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        )
                        CardBonds(
                            modifier = Modifier.weight(2f),
                            bond = bond
                        ) {
                            onEvent(BondsUiEvent.UnClickPersonage(bond.personageId))
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        )
                    }
                }
            }

            items(state.listBond) { bond ->
                val borderStroke =
                    when (bond.bondType) {
                        BondType.FRIEND -> BorderStroke(2.dp, color = OPTheme.colors.greenColor)
                        BondType.ENEMY -> BorderStroke(2.dp, color = OPTheme.colors.redColor)
                        BondType.FAMILY -> BorderStroke(2.dp, color = OPTheme.colors.blueColor)
                        BondType.NAKAMA -> BorderStroke(2.dp, color = OPTheme.colors.violetColor)
                        BondType.NEUTRAL -> BorderStroke(2.dp, color = OPTheme.colors.grayColor)
                    }

                CardBonds(
                    bond = bond,
                    borderStroke = borderStroke,
                    onLongClick = {
                        onEvent(BondsUiEvent.ClickPersonage(bond.personageId))
                    },
                    onClick = {
                        onEvent(BondsUiEvent.ClickBond(bond.id))
                    }
                )
            }

            items(state.listNoBond) { bond ->
                CardBonds(
                    modifier = Modifier
                        .alpha(0.5f)
                        .scale(0.75f),
                    bond = bond
                ) {
                    onEvent(BondsUiEvent.ClickPersonage(bond.personageId))
                }
            }
        } else {
            items(state.bondList) { bond ->
                CardBonds(bond = bond) {
                    onEvent(BondsUiEvent.ClickPersonage(bond.personageId))
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardBonds(
    modifier: Modifier = Modifier,
    bond: BondContent,
    borderStroke: BorderStroke = BorderStroke(1.dp, color = OPTheme.colors.blackColor),
    onLongClick: () -> Unit = {},
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        colors = CardDefaults.cardColors(
            containerColor = OPTheme.colors.yellowColor
        ),
        border = borderStroke,
    ) {
        val lineHeight = with(LocalDensity.current) { 18.sp.toPx() }
        AsyncImage(
            model = bond.imagePersonage,
            contentDescription = stringResource(R.string.image_description_avatar),
            contentScale = ContentScale.Crop,
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f)
        )
        if (bond.namePersonage != null)
            Text(
                text = bond.namePersonage,
                style = OPTheme.typography.title,
                modifier = Modifier
                    .height(lineHeight.dp)
                    .align(Alignment.CenterHorizontally)
                    .wrapContentHeight(align = Alignment.CenterVertically)
                    .padding(vertical = 4.dp, horizontal = 4.dp),
                textAlign = TextAlign.Center,
            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BondDialog(bond: BondWithName, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss
    ) {
        CardBlock(
            modifier = Modifier
                .fillMaxWidth(0.5f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(
                        R.string.title_dialog_bond,
                        bond.firstName,
                        bond.secondName
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .padding(end = 32.dp),
                    textAlign = TextAlign.Center,
                    style = OPTheme.typography.subHeading
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.close_icon),
                    contentDescription = stringResource(id = R.string.close),
                    tint = OPTheme.colors.blackColor,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(top = 8.dp, end = 8.dp)
                        .align(Alignment.TopEnd)
                        .bounceClick(onClick = onDismiss),
                )
            }

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                drawLine(
                    color = Color.Black,
                    start = Offset(x = 0f, y = size.height / 2),
                    end = Offset(x = size.width, y = size.height / 2),
                    strokeWidth = 2f
                )
            }
            Text(
                text = bond.description,
                modifier = Modifier
                    .padding(8.dp)
                    .heightIn(max = 100.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = OPTheme.typography.body,
            )
        }
    }
}
