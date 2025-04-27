package com.rksrtx76.cinemax.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Subscriptions
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.rksrtx76.cinemax.R

data class BottomNavItem(
    val title : String,
    val selectedIcon : ImageVector,
    val unSelectedIcon : ImageVector,
)

val items = listOf(
    BottomNavItem(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home
    ),
    BottomNavItem(
        title = "Search",
        selectedIcon = Icons.Filled.Search,
        unSelectedIcon = Icons.Outlined.Search
    ),
    BottomNavItem(
        title = "Collections",
        selectedIcon = Icons.Filled.Subscriptions,
        unSelectedIcon = Icons.Outlined.Subscriptions
    ),
    BottomNavItem(
        title = "Account",
        selectedIcon = Icons.Filled.AccountCircle,
        unSelectedIcon = Icons.Outlined.AccountCircle
    )


)
