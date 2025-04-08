package com.agarcia.myfirstandroidapp.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object MovieListScreenNavigation

@Serializable
object MyFavoritesScreenNavigation

@Serializable
object AlreadySeenScreenNavigation

@Serializable
data class MovieDetailScreenNavigation(val id: Int)

