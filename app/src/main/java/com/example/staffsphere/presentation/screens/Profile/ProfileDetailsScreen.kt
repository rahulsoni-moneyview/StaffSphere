package com.example.staffsphere.presentation.screens.Profile


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.staffsphere.R
import com.example.staffsphere.domain.models.ProfileUiModel
import com.example.staffsphere.ui.theme.PoppinsBold
import com.example.staffsphere.ui.theme.PoppinsLight
import com.example.staffsphere.ui.theme.PoppinsMedium
import com.example.staffsphere.ui.theme.PoppinsSemiBold

@Preview(showBackground = true)
@Composable
fun ProfileDetailsScreen(data: ProfileUiModel? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 18.dp)
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data?.imgUrl)
                    .crossfade(true).build(),
                contentScale = ContentScale.FillBounds,
                contentDescription = "Name",
                placeholder = painterResource(R.drawable.ic_not_found),
                error = painterResource(R.drawable.ic_not_found)
            )
        }
        Column(
            modifier = Modifier.padding(top = 12.dp), horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = data?.name ?: "Rahul Kumar Soni",
                fontSize = 22.sp,
                fontFamily = PoppinsBold,
                color = Color.Black
            )
            Text(
                modifier = Modifier.padding(bottom = 12.dp, top = 4.dp),
                text = data?.designation ?: "Intern",
                fontSize = 12.sp,
                fontFamily = PoppinsSemiBold,
                color = Color.Gray
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp),
                text = data?.joinedMsg ?: "Joined Moneyview 2 months Ago",
                fontSize = 12.sp,
                fontFamily = PoppinsMedium,
                color = Color.Gray
            )
            Department("${data?.dept}", R.drawable.icon_engg)
        }
        Spacer(modifier = Modifier.height(16.dp))
        IconWithTitle(data?.location ?: "Jamshedpur", R.drawable.baseline_location_on_24)
        IconWithTitle(data?.email ?: "rahul.kumar.soni2511@gmail.com", R.drawable.baseline_email_24)
        Text(
            modifier = Modifier.padding(top = 14.dp),
            text = "Interest",
            fontSize = 12.sp,
            fontFamily = PoppinsSemiBold,
            color = Color.DarkGray
        )
        ChipGridExample(data?.interests ?: chipItemsInterests)
        Text(
            modifier = Modifier.padding(top = 14.dp),
            text = "Projects",
            fontSize = 12.sp,
            fontFamily = PoppinsSemiBold,
            color = Color.DarkGray
        )
        ChipGridExample(data?.projects ?: chipItemsInterests)
        Divider(
            modifier = Modifier
                .width(300.dp)
                .padding(18.dp)
                .align(Alignment.CenterHorizontally)
        )

        Text(
            modifier = Modifier.padding(top = 14.dp),
            text = "Birthday",
            fontSize = 12.sp,
            fontFamily = PoppinsSemiBold,
            color = Color.Gray
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = data?.dob ?: "16th January",
            fontSize = 14.sp,
            fontFamily = PoppinsMedium,
            color = Color.Black
        )
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = "Employement Type",
            fontSize = 12.sp,
            fontFamily = PoppinsSemiBold,
            color = Color.Gray
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = data?.designation ?: "Internship",
            fontSize = 14.sp,
            fontFamily = PoppinsMedium,
            color = Color.Black
        )
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = "Date of Joining",
            fontSize = 12.sp,
            fontFamily = PoppinsSemiBold,
            color = Color.Gray
        )
        Text(
            modifier = Modifier.padding(top = 6.dp),
            text = data?.doj ?: "07 Janunary 2024",
            fontSize = 14.sp,
            fontFamily = PoppinsMedium,
            color = Color.Black
        )


    }

}

val chipItemsInterests = listOf(
    "Traveling",
    "Photography",
    "Cooking",
    "Reading",
    "Fitness",
    "Gardening",
    "Music",
    "Hiking",
    "Painting",
    "Yoga",
    "Volunteering",
    "Programming"
)
val chipItemsProjects = listOf(
    "Upi",
    "Banking ",
    "Project 1",
    "Project 2",
    "Demo project"
)

@Composable
fun ChipGridExample(interestList: List<String>) {
    FlowRowSimpleUsageExample(interestList)
}

@Composable
fun Department(deptName: String, deptIcon: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = listOf(Color.Gray, Color.Black)),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(4.dp)

    ) {
        Image(
            painter = painterResource(deptIcon),
            contentDescription = null, // Provide content description if necessary
            modifier = Modifier
                .width(18.dp)
                .height(18.dp)
                .padding(2.dp),
            contentScale = ContentScale.Fit // Adjust content scale as needed
        )
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = deptName,
            fontSize = 12.sp,
            fontFamily = PoppinsLight,
            color = Color.Gray
        )

    }
}

@Composable
fun IconWithTitle(title: String, icon: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null, // Provide content description if necessary
            modifier = Modifier
                .width(18.dp)
                .height(18.dp),
            contentScale = ContentScale.Fit // Adjust content scale as needed
        )
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = title,
            fontSize = 14.sp,
            fontFamily = PoppinsMedium,
            color = Color.Black
        )
    }
}

@Composable
fun ChipItem(text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(colors = listOf(Color.Gray, Color.Black)),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(4.dp)

    ) {
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = text,
            fontSize = 12.sp,
            fontFamily = PoppinsLight,
            color = Color.Gray
        )

    }
    Spacer(modifier = Modifier.height(4.dp))
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun FlowRowSimpleUsageExample(interestList: List<String>) {
    FlowRow(
        modifier = Modifier
            .padding(top = 4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        interestList.forEach {
            ChipItem(it)
        }
    }
}

