package com.agarcia.myfirstandroidapp.ui.layout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(val label: String, val icon: ImageVector, val route: String)

@Composable
fun CustomBottomBar() {
  val navItems = listOf(
    NavItem("Home", Icons.Filled.Home, "home"),
    NavItem("Favorite", Icons.Filled.Favorite, "favorite"),
    NavItem("ShoppingCart", Icons.Filled.ShoppingCart, "shoppingCart")
  )

  var selectedItem by rememberSaveable { mutableStateOf("home") }

  NavigationBar(
    //containerColor = Color.Green,
    //contentColor = Color.Black,
  ) {

    navItems.forEach { item ->
      NavigationBarItem(
        label = { Text(item.label) },
        icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
        selected = selectedItem == item.route,
        onClick = { selectedItem = item.route },
//        colors = NavigationBarItemDefaults.colors(
//          selectedIconColor = Color.Black,
//          unselectedIconColor = Color.Gray,
//          selectedTextColor = Color.Black,
//          unselectedTextColor = Color.Gray,
//          indicatorColor = Color.LightGray,
//        )
      )
    }
  }
}
