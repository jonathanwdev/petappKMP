package com.pet.app.presentation.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pet.app.presentation.screens.home.HomeScreen
import com.pet.app.presentation.screens.petDetail.PetDetailScreen
import com.pet.app.presentation.screens.petList.PetListScreen
import com.pet.app.presentation.screens.splash.SplashScreen


@Composable
fun PetNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = PetNavRouts.Splash) {
        composable<PetNavRouts.Splash> {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(PetNavRouts.Home) {
                        popUpTo(PetNavRouts.Splash) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable<PetNavRouts.Home> {
            HomeScreen(
                onNavigateToPetList = {
                    navController.navigate(PetNavRouts.PetList)
                }
            )
        }
        composable<PetNavRouts.PetList> {
            PetListScreen(
                onNavigateToPetDetail = { id ->
                    navController.navigate(PetNavRouts.PetDetail(id))
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<PetNavRouts.PetDetail> { backStackEntry ->
            val petId = backStackEntry.toRoute<PetNavRouts.PetDetail>()
            PetDetailScreen(
                petId = petId.id,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}