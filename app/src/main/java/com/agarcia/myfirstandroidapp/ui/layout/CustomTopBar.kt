package com.agarcia.myfirstandroidapp.ui.layout

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
  title: String = "¡Qué Peli!",
) {
  TopAppBar(
    title = { Text(text = title) },
    navigationIcon = {
      IconButton(onClick = {}) {
        Icon(
          imageVector = Icons.Filled.Menu,
          contentDescription = "Menu",
        )
      }
    },
    actions = {
      Row() {
        IconButton(onClick = {}) {
          Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Back",
          )
        }
        IconButton(onClick = {}) {
          Icon(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = "Back",
          )
        }
      }
    },
//    colors = TopAppBarDefaults.topAppBarColors(
//      containerColor = MaterialTheme.colorScheme.primary,
//      titleContentColor = Color.Black,
//      actionIconContentColor = Color.Black,
//      navigationIconContentColor = Color.Black,
//    )
  )
}
