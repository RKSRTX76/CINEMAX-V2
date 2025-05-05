package com.rksrtx76.cinemax.presentation.screens.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rksrtx76.cinemax.ui.theme.font
import com.rksrtx76.cinemax.util.BottomNav
import com.rksrtx76.cinemax.util.items

@Composable
fun BottomNavigationBar(
    selectedItem : MutableState<Int>,
    bottomNavController: NavController
){
    val isDarkTheme = isSystemInDarkTheme()
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        items.forEachIndexed{ idx, item ->
            NavigationBarItem(
                selected = selectedItem.value == idx,
                onClick = {
                    selectedItem.value = idx
                    when(selectedItem.value){
                        0 -> bottomNavController.navigate(BottomNav.HOME_SCREEN)
                        1 -> bottomNavController.navigate(BottomNav.SEARCH_SCREEN)
                        2 -> bottomNavController.navigate(BottomNav.BOOKMARK_SCREEN)
                        3 -> bottomNavController.navigate(BottomNav.PROFILE_SCREEN)
                    }
                },
                label = {
                    Text(
                        text = item.title,
                        fontFamily = font,
                        style = MaterialTheme.typography.bodySmall
                    )
                },
                icon = {
                    Icon(
                        imageVector = if(idx == selectedItem.value) item.selectedIcon else item.unSelectedIcon,
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = if(isDarkTheme) Color.White else Color.Black,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}