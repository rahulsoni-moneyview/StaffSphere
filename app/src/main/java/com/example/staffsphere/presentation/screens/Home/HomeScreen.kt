package com.example.staffsphere.presentation.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.staffsphere.R
import com.example.staffsphere.domain.StandardResponse
import com.example.staffsphere.domain.models.HomeUiModel
import com.example.staffsphere.ui.theme.PoppinsMedium

@Composable
fun HomeScreen() {
    val viewModel = hiltViewModel<HomeViewModel>()
    viewModel.fetchHomeData()
    val homeLivedata by viewModel.liveHomeData.collectAsStateWithLifecycle()
    when (val currentData = homeLivedata) {
        StandardResponse.Loading -> {}
        is StandardResponse.Success -> HomeMainScreen(currentData.data)
        is StandardResponse.Failed -> {}
        null -> {}
    }
}

@Composable
@Preview(showBackground = true)
fun HomeMainScreen(data: HomeUiModel? = null) {
Box(modifier = Modifier.fillMaxSize()){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 10.dp)
            .height(150.dp),
        elevation = 3.dp,
        shape = RoundedCornerShape(15.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)

        ) {
            Column() {
                Text(
                    text = "  Hey , ${data?.employeeName} 👋 ",
                    fontFamily = PoppinsMedium,
                    fontSize = 20.sp,

                )

                Row(
                    verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 4.dp, start = 12.dp),
                        text = "${data?.welcomeMsg}",
                        fontSize = 12.sp,
                        fontFamily = PoppinsMedium,
                        color = Color.Gray
                    )
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .padding(end = 12.dp)
                    )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.workspace),
                            contentDescription = null
                        )
                    }
                }
//                   Spacer(modifier = Modifier.height(24.dp))
            }

        }
    }
}

    }
