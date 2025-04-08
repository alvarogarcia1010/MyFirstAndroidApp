package com.agarcia.myfirstandroidapp.ui.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.agarcia.myfirstandroidapp.ui.screens.MovieDetailScreen
import com.agarcia.myfirstandroidapp.ui.screens.MovieListScreen
import kotlinx.coroutines.launch

@Composable
fun CustomScaffold () {
  val corouteScope = rememberCoroutineScope()
  val snackbarHostState = remember { SnackbarHostState() }

  fun onFloatingButtonClick(text:String) {
    corouteScope.launch {
      snackbarHostState.showSnackbar(
        message = text,
        actionLabel = "OK",
        duration = SnackbarDuration.Short,
      )
    }
  }

  Scaffold(
    topBar = {
      CustomTopBar("¡Qué Peli!")
    },
    bottomBar = { CustomBottomBar() },
    floatingActionButton = {
      CustomFloatingButton(
        onClick = { onFloatingButtonClick("Hola Mundo desde el Floating Button")}
      )
    },
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { innerPadding ->
    Column (
      modifier = Modifier.padding(innerPadding).fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
      // MovieDetailScreen(950387)
      // MovieListScreen()
      CustomModalBottonSheet()
    }
  }
}

@Preview(showBackground = true)
@Composable
fun CustomScaffoldPreview() {
  CustomScaffold()
}