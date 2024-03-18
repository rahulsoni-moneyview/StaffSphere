package com.example.staffsphere.presentation.screens.Team

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.staffsphere.R
import com.example.staffsphere.domain.models.TeamUiModel
import com.example.staffsphere.domain.models.generateDummyProfiles
import com.example.staffsphere.presentation.screens.People.userList
import com.example.staffsphere.presentation.screens.Profile.BottomSheet
import com.example.staffsphere.ui.theme.PoppinsBold
import com.example.staffsphere.utils.MembersList
import com.example.staffsphere.utils.PrimarySearchBar
import com.example.staffsphere.utils.TeamName
import com.example.staffsphere.utils.User


@Composable
@Preview
fun TeamMainScreen() {
    var currentScreenState by rememberSaveable {
        mutableStateOf(TeamScreenState.TEAM_MAIN_SCREEN)
    }

    var selectedTeamMember by remember {
        mutableStateOf(TeamUiModel(team = TeamName.UNKNOWN, memberCount = 0))
    }

    when (currentScreenState) {
        TeamScreenState.TEAM_MAIN_SCREEN -> TeamScreen {
            selectedTeamMember = it
            currentScreenState = TeamScreenState.TEAM_DETAILS_SCREEN
        }

        TeamScreenState.TEAM_DETAILS_SCREEN -> TeamDetailsScreen(
            item = selectedTeamMember,
            onBackPressed = {
                currentScreenState = TeamScreenState.TEAM_MAIN_SCREEN
            })
    }
}

@Composable
fun TeamScreen(
    onCardClickCallback: (item: TeamUiModel) -> Unit
) {

    val members = generateDummyProfiles(20);
    val list = listOf(
        TeamUiModel(TeamName.ENGINEER, 12, members),
        TeamUiModel(TeamName.DESIGNER, 10, members),
        TeamUiModel(TeamName.PRODUCT, 8, members),
        TeamUiModel(TeamName.ENGINEER, 24, members),
        TeamUiModel(TeamName.PRODUCT, 7),
        TeamUiModel(TeamName.FINANCE, 24),
        TeamUiModel(TeamName.ENGINEER, 9),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        PrimarySearchBar(
            text = "",
            onTextChange = {},
            placeHolder = "Search",
            onCloseClicked = { },
            marginHorizontal = 12.dp
        )
        Spacer(modifier = Modifier.height(24.dp))
        LazyColumn {
            itemsIndexed(list) { index, item ->
                TeamCardView(item) {
                    onCardClickCallback(list[index])
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TeamCardView(
    item: TeamUiModel,
    onClickItem: () -> Unit
) {
    val teamId = when (item.team) {
        TeamName.ENGINEER -> R.drawable.icon_engg
        TeamName.PRODUCT -> R.drawable.icon_product
        TeamName.FINANCE -> R.drawable.icon_finance
        TeamName.DESIGNER -> R.drawable.icon_design
        else -> 5 // Default value if jobType doesn't match any case
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickItem() }
            .padding(12.dp)
            .border(width = 1.dp, shape = RoundedCornerShape(20.dp), color = Color.LightGray)
            .padding(vertical = 12.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = teamId),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(
                    color = Color(0xFFADFFD0)
                )
                .padding(12.dp)
                .width(42.dp)
                .height(36.dp)
        )
        Spacer(modifier = Modifier.width(36.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "${item.team}",
                fontSize = 16.sp,
                fontFamily = PoppinsBold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "${item.memberCount} Members",
                fontSize = 10.sp,
                color = Color.Gray.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(32.dp))
        }

    }
}

@Composable
@Preview
fun TeamDetailsScreen(
    item: TeamUiModel? = null,
    onBackPressed: () -> Unit = {},
) {
    @DrawableRes val imgId: Int = R.drawable.icon_engg;
    when (item?.team) {
        TeamName.ENGINEER -> R.drawable.icon_engg
        TeamName.FINANCE -> R.drawable.icon_finance
        TeamName.PRODUCT -> R.drawable.icon_product
        TeamName.DESIGNER -> R.drawable.icon_design
        else -> {}
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(
                            color = Color(0xFFADFFD0).copy(0.2f)
                        )
                        .padding(12.dp)
                        .width(24.dp)
                        .height(24.dp)
                        .clickable { onBackPressed() }
                )
                Spacer(modifier = Modifier.width(14.dp))
                Icon(
                    painter = painterResource(id = imgId),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(
                            color = Color(0xFFADFFD0)
                        )
                        .padding(12.dp)
                        .width(32.dp)
                        .height(24.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    fontFamily = PoppinsBold,
                    fontSize = 16.sp,
                    text = "${item?.team}"
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.5f))
            )
        }
        val engineerList = userList.filter { it.teamName == item?.team }
        MembersList(userList = engineerList)
    }
}

enum class TeamScreenState {
    TEAM_MAIN_SCREEN,
    TEAM_DETAILS_SCREEN
}
