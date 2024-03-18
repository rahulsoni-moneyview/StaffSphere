package com.example.staffsphere.presentation.screens.Profile

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.staffsphere.R
import com.example.staffsphere.domain.StandardResponse
import com.example.staffsphere.domain.models.ProfileUiModel
import com.example.staffsphere.ui.theme.PoppinsBold
import com.example.staffsphere.ui.theme.PoppinsMedium
import com.example.staffsphere.ui.theme.PoppinsSemiBold

@Composable
fun ProfileScreen() {

    val viewModel = hiltViewModel<ProfileViewModel>()
    viewModel.fetchProfile()
    val profileLivedata by viewModel.liveProfileData.collectAsStateWithLifecycle()
    when (val currentData = profileLivedata) {
        StandardResponse.Loading -> {}
        is StandardResponse.Success -> Profile(currentData.data)
        is StandardResponse.Failed -> {}
        null -> {}
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onDismiss: () -> Unit) {
    val viewModel = hiltViewModel<ProfileViewModel>()
    viewModel.fetchProfile()
    val profileLivedata by viewModel.liveProfileData.collectAsStateWithLifecycle()
    when (val currentData = profileLivedata) {
        StandardResponse.Loading -> {}
        is StandardResponse.Success -> Profile(currentData.data)
        is StandardResponse.Failed -> {}
        null -> {}
    }


    val modalBottomSheetState = rememberModalBottomSheetState()
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
        ProfileDetailsScreen()
    }
}

data class ProfileData(
    val name: String = "Yogesh",
    val department: String = "Intern -",
    val email: String = "yogeshnarendratiwari@gmai.com",
    val image: Int = R.drawable.ic_launcher_background,
)

val user = ProfileData()

@Composable
fun ProfileCardComponent(
    heading: String,
    image: String,
    content: String,
    horizontalPadding: Dp,
    verticalPadding: Dp,
    isUserData: Boolean = false,
    buttonText: String = "",
    data: ProfileUiModel? = null
) {
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheet {
            showSheet = false
        }
    }

    Card(
        modifier = Modifier
            .height(146.dp)
            .padding(horizontal = horizontalPadding, vertical = verticalPadding)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(3.dp), shape = RoundedCornerShape(7.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier.width(12.dp))
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://fastly.picsum.photos/id/516/200/300.jpg?hmac=hMEuvTcrLNhrMSSGnaRit4YgalzJJ66stNu-UT70DKw")
                            .crossfade(true).build(),
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "Name",
                        placeholder = painterResource(R.drawable.ic_not_found),
                        error = painterResource(R.drawable.ic_not_found)
                    )
                }
                Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {

                    Text(text = heading, fontSize = 18.sp, fontFamily = PoppinsSemiBold)

                    Text(text = content, fontSize = 12.sp, fontFamily = PoppinsMedium)
                    if (isUserData) {
                        Text(text = buttonText,
                            fontSize = 12.sp,
                            fontFamily = PoppinsSemiBold,
                            color = Color.Blue,
                            modifier = Modifier.clickable { showSheet = true }

                        )
                    }

                }
            }

            if (!isUserData) IconButton(
                onClick = {}, modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Icon(
                    Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = "Lock",
                    modifier = Modifier
                        .size(15.dp)
                        .align(Alignment.CenterVertically),
                    tint = Color.Gray

                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Profile(data: ProfileUiModel? = null) {
    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheet {
            showSheet = false
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
            ) {
                Text(
                    text = "Profile",
                    Modifier.padding(start = 18.dp, top = 24.dp, bottom = 18.dp),
                    fontSize = 24.sp,
                    fontFamily = PoppinsBold
                )
            }

            Column {
//                data
                DetailsCard("")

            }

            Text(
                modifier = Modifier.padding(18.dp),
                text = "Your details",
                fontSize = 16.sp,
                color = Color.Gray,
                fontFamily = PoppinsMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            DetailsCard(
                "R.drawable.workspace",
                "Basic Details",
                "First Name, Last Name, Date of Birth, Gender, Current Address, Mobile, Personal Email"
            )
            Spacer(modifier = Modifier.height(16.dp))
            DetailsCard(
                "R.drawable.workspace",
                "Additional Details",
                "T-shirt size , Interests"
            )
            Spacer(modifier = Modifier.height(16.dp))
            DetailsCard(
                "R.drawable.workspace",
                "Work Details",
                "Employee Id, Team, Role, Reporting Manager, Employment Type, Joining Date, Work Email"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsCard(
    icon: String = "",
    title: String = "Basic Details",
    desc: String = "First name last name , date of birth",
    content: @Composable() () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(146.dp)
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://fastly.picsum.photos/id/516/200/300.jpg?hmac=hMEuvTcrLNhrMSSGnaRit4YgalzJJ66stNu-UT70DKw")
                        .crossfade(true).build(),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = "Name",
                    placeholder = painterResource(R.drawable.ic_not_found),
                    error = painterResource(R.drawable.ic_not_found)
                )
            }
            Column(
                modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {

                Text(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = title,
                    fontSize = 16.sp,
                    fontFamily = PoppinsSemiBold,
                    color = Color.Black
                )
                Text(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    text = desc,
                    fontSize = 12.sp,
                    fontFamily = PoppinsMedium,
                    color = Color.Gray
                )


            }

        }


    }


}
