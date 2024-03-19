package com.example.staffsphere.presentation.screens.Login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Password
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.staffsphere.R

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true)
@Composable
fun LoginScreen() {

    var email by remember { mutableStateOf("") };
    var password by remember { mutableStateOf("") };
    var passwordVisibility by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .fillMaxWidth()
            ) {
            Column(modifier = Modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://moneyview.in/img/logo-light.png")
                        .crossfade(true)
                        .build(),
                    contentDescription = "logo",
                    modifier = Modifier
                        .size(300.dp)

                )
                OutlinedTextField(
                    value = email,
                    onValueChange = {email = it},
                    leadingIcon = {
                    Icon(Icons.Outlined.Email,contentDescription = null)
                },
                    label = {Text("Enter your email", color = Color.Gray)},
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)  ,
                    shape = RoundedCornerShape(7.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF6FB935)
                    )
                )
                Spacer(modifier = Modifier.height(3.dp))
                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    leadingIcon = {
                        Icon(Icons.Outlined.Password,contentDescription = null)
                    },
                    trailingIcon = {
                                  val image =  if(passwordVisibility){
                                       Icons.Filled.Visibility
                                   }
                        else{
                            Icons.Filled.VisibilityOff
                                   }
                        IconButton(onClick = {passwordVisibility = !passwordVisibility}) {
                            Icon(imageVector = image,contentDescription = null)
                        }
                    },
                    visualTransformation = if(passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    label = {Text("Enter your password", color = Color.Gray)},
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(7.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF6FB935)
                    )
                )
                Spacer(modifier = Modifier.height(22.dp))
                Button(onClick = {




                },modifier = Modifier
                    .width(130.dp)
                    .align(Alignment.CenterHorizontally),colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF6FB935), contentColor = Color.White)){
                    Text(text = "Login", fontSize = 15.sp)
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 28.dp), horizontalArrangement = Arrangement.Center) {
                    Divider(modifier = Modifier
                        .width(130.dp)
                        .padding(8.dp))
                    Text("Or sign up with")
                    Divider(modifier = Modifier
                        .width(130.dp)
                        .padding(8.dp))
                }

                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp), horizontalArrangement = Arrangement.Center,){

                    Image(painter = painterResource(id = R.drawable.google), contentDescription = "Google login",modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(painter = painterResource(id = R.drawable.facebook), contentDescription = "Google login" ,modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp))
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(painter = painterResource(id = R.drawable.microsoft), contentDescription = "Google login" ,modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp))

                }
            }

        }
    }
}

