package com.example.staffsphere.presentation.screens.People

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.example.staffsphere.utils.TeamName
import com.example.staffsphere.utils.MembersList
import com.example.staffsphere.utils.User

@Composable
fun PeopleScreen() {

    Surface {
        MembersList(userList)
    }

}

val userList = listOf(
    User("Sudharsan Parthsarathi", "Designer", TeamName.DESIGNER),
    User("Hiren Giridharial Parekh", "Engineering", TeamName.ENGINEER),
    User("Lalit Kamble", "Product", TeamName.PRODUCT),
    User("Lalit Kamble", "Product", TeamName.PRODUCT),
    User("Hiren Giridharial Parekh", "Engineering", TeamName.ENGINEER),
    User("Hiren Giridharial Parekh", "Engineering", TeamName.ENGINEER),
    User("Hiren Giridharial Parekh", "Engineering", TeamName.ENGINEER),
    User("Lalit Kamble", "Product", TeamName.PRODUCT),
    User("Lalit Kamble", "Product", TeamName.PRODUCT),
    User("Lalit Kamble", "Product", TeamName.PRODUCT),
    User("Hiren Giridharial Parekh", "Engineering", TeamName.ENGINEER),
    )

