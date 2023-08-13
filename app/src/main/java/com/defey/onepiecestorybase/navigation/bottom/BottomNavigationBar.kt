package com.defey.onepiecestorybase.navigation.bottom

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.BadgedBox
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bumble.appyx.navmodel.backstack.BackStack
import com.bumble.appyx.navmodel.backstack.activeElement
import com.defey.onepiecestorybase.navigation.NavTarget
import com.defey.onepiecestorybase.presentation.theme.OPTheme

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: BackStack<NavTarget>,
    modifier: Modifier = Modifier,
    color: Brush = Brush.verticalGradient(),
    onItemClick: (BottomNavItem) -> Unit
) {
    BottomNavigation(
        modifier = modifier.background(brush = color),
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    ) {
        items.forEach { item ->

            Log.d("MyLog", "item: ${item.isNew}")
            val selected = item.route == navController.activeElement
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = OPTheme.colors.blackColor,
                unselectedContentColor = OPTheme.colors.grayColor,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        BadgedBox(
                            badge = {
                                if (item.badgeCount > 0) {
                                    Badge {
                                        Text(text = item.badgeCount.toString())
                                    }
                                } else if (item.isNew) {
                                    Badge()
                                }
                            }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = item.icon),
                                contentDescription = item.name.asString()
                            )
                        }
                        if (selected) {
                            Text(
                                text = item.name.asString(),
                                textAlign = TextAlign.Center,
                                style = OPTheme.typography.caption
                            )
                        }
                    }
                }
            )
        }
    }
}
