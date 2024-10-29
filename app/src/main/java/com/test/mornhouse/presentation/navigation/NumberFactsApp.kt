package com.test.mornhouse.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.mornhouse.presentation.navigation.Root.root
import com.test.mornhouse.presentation.screens.DetailScreen
import com.test.mornhouse.presentation.screens.MainScreen
import com.test.mornhouse.presentation.ui.theme.MornhouseTheme

@Composable
fun NumberFactsApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.MainScreen,
        route = root
    ) {
        composable(Routes.MainScreen) {
            MainScreen(navController = navController)
        }
        composable(Routes.DetailScreen) {
            DetailScreen(navController = navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NumberFactsAppPreview() {
    MornhouseTheme {
        NumberFactsApp()
    }
}