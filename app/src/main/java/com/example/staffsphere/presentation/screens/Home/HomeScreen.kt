package com.example.staffsphere.presentation.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardDoubleArrowRight
import androidx.compose.material.icons.filled.SwipeLeftAlt

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.compose.ImagePainter
import coil.request.ImageRequest
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
    Column {

        Text(
            text = "Home",
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 30.dp, start = 27.dp, bottom = 20.dp),
            fontFamily = FontFamily.SansSerif
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .height(150.dp),
            elevation = 2.dp,
            shape = RoundedCornerShape(15.dp)
        ) {
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
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
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
                }

            }
        }


    }}}
