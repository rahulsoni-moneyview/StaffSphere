package com.example.staffsphere.presentation.navigation

import com.example.staffsphere.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    data object Home : NavigationItem("home", R.drawable.baseline_home_filled_24, "Home")
    data object People : NavigationItem("people", R.drawable.baseline_people_alt_24, "People")
    data object Team : NavigationItem("team", R.drawable.rounded_team_dashboard_24, "Team")
    data object Org_Chart : NavigationItem("org_chart", R.drawable.baseline_bubble_chart_24, "Org Chart")
    data object Profile : NavigationItem("profile", R.drawable.rounded_person_24, "Profile")
}