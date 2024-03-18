package com.example.staffsphere.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.staffsphere.R
import com.example.staffsphere.presentation.screens.Profile.BottomSheet
import com.example.staffsphere.presentation.screens.Profile.Department

@Composable
fun MembersList(userList: List<User>) {

    var showSheet by remember { mutableStateOf(false) }
    if (showSheet) {
        BottomSheet() {
            showSheet = false
        }
    }
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(13.dp)) {
            // Search bar
            PrimarySearchBar(
                text = "",
                onTextChange = {},
                placeHolder = "Search",
                onCloseClicked = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding()
            ) {
                Text(
                    text = "People", style = TextStyle(
                        color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Medium
                    ), modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, bottom = 16.dp)
                    .height(0.5.dp)
                    .background(Color.LightGray)
            )
            // Lazy column
            LazyColumn(modifier = Modifier.padding()) {
                items(userList.size) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding()
                            .clickable { showSheet = true }) {
                        UserProfileUi(userList[index])
                    }
                    // Horizontal line
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.5.dp)
                            .background(Color.LightGray)
                    )
                }
            }
        }
    }
}


@Composable
@Preview
fun PrimarySearchBar(
    modifier: Modifier = Modifier,
    text: String = "Search here",
    onTextChange: (String) -> Unit = {},
    placeHolder: String = "Something",
    onCloseClicked: () -> Unit = {},
    onMicClicked: () -> Unit = {},
    marginHorizontal: Dp = 0.dp
) {
    OutlinedTextField(shape = CircleShape, value = text, onValueChange = {
        onTextChange(it)
    }, placeholder = {
        Text(
            text = placeHolder
        )
    }, leadingIcon = {
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .size(22.dp)
                    .then(modifier)
            )
        }
    }, trailingIcon = {
        IconButton(onClick = {
            if (text.isNotBlank()) {
                onCloseClicked()
            } else {
                onMicClicked()
            }
        }) {
            if (text.isNotBlank()) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    modifier = modifier.size(22.dp)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    modifier = modifier.size(22.dp)
                )
            }
        }
    }, modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = marginHorizontal)

    )
}

@Composable
fun MemberCount(member: String) {
    Text(
        text = member,
        style = TextStyle(color = Color.Gray, fontSize = 16.sp, fontWeight = FontWeight.Medium),
        modifier = Modifier.padding(start = 32.dp, top = 12.dp)
    )
}

@Composable
fun UserProfileUi(user: User) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable { }) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://fastly.picsum.photos/id/516/300/300.jpg")
                    .crossfade(true).build(),
                contentScale = ContentScale.FillBounds,
                contentDescription = "Name",
                placeholder = painterResource(R.drawable.ic_not_found),
                error = painterResource(R.drawable.ic_not_found)
            )
        }
        UserDetails(
            userName = user.name, userJobDescription = user.jobDescription, teamName = user.teamName
        )
    }
}

@Composable
fun UserDetails(userName: String, userJobDescription: String, teamName: TeamName) {
    Column(modifier = Modifier.padding(start = 16.dp)) {
        Text(
            text = userName,
            style = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        )
        Text(text = userJobDescription, style = TextStyle(color = Color.Gray, fontSize = 12.sp))
        Spacer(modifier = Modifier.size(4.dp))

        val deptId = when (teamName) {
            TeamName.ENGINEER -> R.drawable.icon_engg
            TeamName.PRODUCT -> R.drawable.icon_product
            TeamName.FINANCE -> R.drawable.icon_finance
            TeamName.DESIGNER -> R.drawable.icon_design
            else -> 5 // Default value if jobType doesn't match any case
        }
        Department(deptName = userJobDescription, deptIcon = deptId)
    }
}

@Composable
fun DesignerChip(teamName: TeamName) {

    when (teamName) {
        TeamName.DESIGNER -> {
            ChipDesign(icon = R.drawable.icon_design, text = "Design", color = Color.Cyan)
        }

        TeamName.ENGINEER -> {
            ChipDesign(
                icon = R.drawable.icon_engg, text = "Engineering", color = Color.LightGray
            )
        }

        TeamName.PRODUCT -> {
            ChipDesign(
                icon = R.drawable.icon_product, text = "Product", color = Color.Blue
            )
        }

        TeamName.FINANCE -> {
            ChipDesign(
                icon = R.drawable.icon_product, text = "Product", color = Color.Green
            )
        }

        TeamName.UNKNOWN -> {
            ChipDesign(
                icon = R.drawable.icon_product, text = "Unknown", color = Color.Black
            )
        }

        else -> {}
    }

}

@Composable
fun ChipDesign(icon: Int, text: String, color: Color = Color.LightGray) {
    Row(
        modifier = Modifier.background(color, shape = CircleShape)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = text,
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp)
        )
        Text(
            text = text, style = TextStyle(
                color = Color.Black, fontSize = 12.sp, fontWeight = FontWeight.Medium
            ), modifier = Modifier.padding(end = 8.dp, start = 2.dp, top = 4.dp, bottom = 4.dp)
        )
    }
}

@Composable
@Preview
fun UserProfileUiPreview() {
    UserProfileUi(User("Sudharsan Parthsarathi", "Designer", TeamName.DESIGNER))
}

enum class TeamName {
    DESIGNER, ENGINEER, PRODUCT, FINANCE, UNKNOWN
}

data class User(val name: String, val jobDescription: String, val teamName: TeamName)