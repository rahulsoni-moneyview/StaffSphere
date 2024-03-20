package com.example.staffsphere.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.staffsphere.presentation.screens.Home.HomeScreen
import com.example.staffsphere.presentation.screens.Profile.ProfileScreen
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.staffsphere.presentation.navigation.BottomNavigationBar
import com.example.staffsphere.presentation.navigation.NavigationItem
import com.example.staffsphere.presentation.screens.Login.LoginScreen
import com.example.staffsphere.presentation.screens.OrgChart.OrgChartScreen
import com.example.staffsphere.presentation.screens.People.PeopleScreen
import com.example.staffsphere.presentation.screens.Team.TeamMainScreen
import com.example.staffsphere.presentation.screens.Team.TeamScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val appState: AppState = AppState(navController)
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(Color(0x003A2F6E)),
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                BottomNavigationBar(navController)
            }
        },
        content = { it -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Surface(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                Navigation(navController = navController)
            }
        })
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.People.route) {
            PeopleScreen()
        }
        composable(NavigationItem.Org_Chart.route) {
            OrgChartScreen()
        }
        composable(NavigationItem.Team.route) {
            TeamMainScreen()
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }
        composable("login")
        {
            LoginScreen(navController)
        }
    }
}

@Composable
fun rememberAppState(
    navHostController: NavHostController = rememberNavController()
) = remember(navHostController) {
    AppState(navHostController)
}

open class AppState(
    val navHostController: NavHostController
) {

    private val routes = listOf("home", "people", "org_chart", "team", "profile")

    val shouldShowBottomBar: Boolean
        @Composable get() =
            navHostController.currentBackStackEntryAsState().value?.destination?.route in routes
}