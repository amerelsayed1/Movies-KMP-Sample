package com.iamer.moviessample.kmp.android

import HomeScreen
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.iamer.moviessample.kmp.android.common.Detail
import com.iamer.moviessample.kmp.android.common.Home
import com.iamer.moviessample.kmp.android.common.MovieAppBar
import com.iamer.moviessample.kmp.android.common.movieDestinations
import com.iamer.moviessample.kmp.android.detail.DetailScreen
import com.iamer.moviessample.kmp.android.detail.DetailViewModel
import com.iamer.moviessample.kmp.android.home.HomeViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieApp() {
    val navController = rememberNavController()

    //For enabling change statusBar
    val systemUiController = rememberSystemUiController()

    val scaffoldState = rememberScaffoldState()

    val isSystemDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemDark) {
        MaterialTheme.colors.primaryVariant
    } else {
        Color.Transparent
    }

    SideEffect {
        systemUiController.setStatusBarColor(statusBarColor, darkIcons = !isSystemDark)
    }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = movieDestinations.find {
        backStackEntry?.destination?.route == it.route || backStackEntry?.destination?.route == it.routeWithArgs
    } ?: Home

    Scaffold(scaffoldState = scaffoldState, topBar = {
        MovieAppBar(
            canNavigate = navController.previousBackStackEntry != null,
            currentScreen = currentScreen
        ) {
            navController.navigateUp()
        }
    }) { innerPaddings ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPaddings),
            startDestination = Home.routeWithArgs
        ) {
            composable(Home.routeWithArgs) {
                val homeViewModel: HomeViewModel = koinViewModel()
                HomeScreen(uiState = homeViewModel.uiState, loadNextMovies = {
                    homeViewModel.loadMovies(forceReload = it)
                }, navigateToDetail = { movie ->
                    navController.navigate("${Detail.route}/${movie.id}")
                })
            }

            composable(Detail.routeWithArgs, arguments = Detail.arguments) {
                val movieId = it.arguments?.getInt("movieId") ?: 0
                val detailViewModel: DetailViewModel =
                    koinViewModel(parameters = { parametersOf(movieId) })

                DetailScreen(uiState = detailViewModel.uiState)
            }

        }

    }
}