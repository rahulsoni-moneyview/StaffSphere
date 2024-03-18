package com.example.staffsphere.domain.models

import com.github.javafaker.Faker

data class ProfileUiModel(
    val name: String,
    val imgUrl: String,
    val designation: String,
    val joinedMsg: String,
    val dept: String,
    val location: String,
    val email: String,
    val interests: List<String>,
    val dob: String,
    val doj: String
)

fun generateDummyProfiles(count: Int): List<ProfileUiModel> {
    val dummyProfiles = mutableListOf<ProfileUiModel>()
    val faker = Faker()

    repeat(count) { index ->
        val name = faker.name().fullName()
        val imgUrl = "https://picsum.photos/200/300"
        val designation = "Software Engineer"
        val joinedMsg = "Joined on ${faker.date().birthday()}"
        val dept = "Engineering"
        val location = "New York"
        val email = "${name.replace(" ", ".").toLowerCase()}$index@example.com"
        val interests = listOf("Programming", "Reading", "Traveling")
        val dob = faker.date().birthday().toString() // Generating DOB as string
        val doj = faker.date().birthday().toString() // Generating DOJ as string

        val profile = ProfileUiModel(
            name,
            imgUrl,
            designation,
            joinedMsg,
            dept,
            location,
            email,
            interests,
            dob,
            doj
        )
        dummyProfiles.add(profile)
    }
    return dummyProfiles
}
