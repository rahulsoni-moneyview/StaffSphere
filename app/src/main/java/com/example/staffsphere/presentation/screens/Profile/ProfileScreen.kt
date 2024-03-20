package com.example.staffsphere.presentation.screens.Profile

import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonColors
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.OutlinedButton
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
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
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
        when (val currentData = profileLivedata) {
            StandardResponse.Loading -> {}
            is StandardResponse.Success -> ProfileDetailsScreen(currentData.data)
            is StandardResponse.Failed -> {}
            null -> {}
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 18.dp)
        ) {
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

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                data
                ProfileCard("https://picsum.photos/id/1/200/300", "Rahul Kumar Soni", "SDE-Intern")
                OutlinedButton(
                    onClick = { showSheet = true },
                    Modifier
                        .width(176.dp)
                        .padding(top = 12.dp)
                ) {
                    Text("view complete profile")

                }
            }

            Text(
                modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
                text = "Your details",
                fontSize = 16.sp,
                color = Color.Gray,
                fontFamily = PoppinsMedium
            )
            Spacer(
                modifier = Modifier.height(
                    1.dp
                )
            )
            ExpandableCard(
                "Basic Details",
                "Lorem Ipsum is simply dummy text of the printing"
            )
            Spacer(modifier = Modifier.height(16.dp))
            ExpandableCard(
                "Additional Details", "Lorem Ipsum is simply dummy text of the printing",
            )

            Spacer(modifier = Modifier.height(16.dp))
            ExpandableCard(
                "Team  Informations", "Lorem Ipsum is simply dummy text of the printing",
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun DetailsCard(
    title: String = "Basic Details",
    desc: String = "First name last name , date of birth",
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(12.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
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


@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun ExpandableCard(
    title: String = "Title",
    desc: String = ""
) {
    var expanded by remember { mutableStateOf(false) }

    Card(shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = {
            expanded = !expanded
        }) {
        Column {
            DetailsCard(title, desc)
            if (expanded) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "title name : value",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "title name : value",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "title name : value",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(8.dp)
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCard(
    icon: String = "",
    title: String = "Basic Details",
    desc: String = "First name last name , date of birth",
) {
    val localUriHandler = LocalUriHandler.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(146.dp)
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
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
                Row(
                    modifier = Modifier
                        .width(100.dp)
                        .padding(start = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Image(painter = painterResource(id = R.drawable.icon_slack),
                        contentDescription = "slack",
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp)
                            .clickable { localUriHandler.openUri("https://openinapp.link/ggd8x") })
                    Image(painter = painterResource(id = R.drawable.gmail),
                        contentDescription = "mail",
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp)
                            .clickable { localUriHandler.openUri("mailto:rahul.kumar.soni2511@gmail.com") })
                    Image(painter = painterResource(id = R.drawable.telephone),
                        contentDescription = "phone",
                        modifier = Modifier
                            .height(16.dp)
                            .width(16.dp)
                            .clickable { localUriHandler.openUri("tel:+91-7070039357") })
                }
            }
        }

    }


}

