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
    val projects: List<String>,
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
        val projects = listOf("Upi", "banking", "growth")
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
            projects,
            dob,
            doj
        )
        dummyProfiles.add(profile)
    }
    return dummyProfiles
}

data class BasicDetails(
    val firstName: String,
    val lastName: String,
    val dob: String,
    val gender: String,
    val currAddress: String,
    val mobileNumber: String
)

data class AdditionalDetails(
    val tShirtSize: String,
    val shippingAddress: String
)

data class TeamInformation(
    val teamName: String,
    val reportingManager: String,
    val slackUrl: String,
    val confluenceUrl: String,
)