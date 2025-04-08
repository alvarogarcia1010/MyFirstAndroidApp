package com.agarcia.myfirstandroidapp.ui.navigations

import kotlinx.serialization.Serializable

@Serializable
object MovieListScreenNavigation

@Serializable
data class MovieDetailScreenNavigation(val id: Int)