package com.se7en.cheflab.utils

import androidx.annotation.DrawableRes
import com.se7en.cheflab.R

sealed class OnBoardingPage(

    @DrawableRes
    val image: Int,
    val title: String,
    val description: String

) {

    // Welcome to Your Culinary Journey
    object FirstPage: OnBoardingPage(
        image = R.drawable.first,
        title = "Explore a World of Delicious Flavors",
        description = "ChefLab is your gateway to a smorgasbord of culinary delights from around the globe. Discover new cuisines, uncover hidden gems, and satisfy your cravings with ease. Ditch the recipe hassle and dive into a captivating exploration of food details."
    )

    // Your Food Compass
    object SecondPage: OnBoardingPage(
        image = R.drawable.second,
        title = "Find Your Perfect Bite",
        description = "Whether you're a seasoned gourmet or a curious foodie, ChefLab empowers you to find the ideal dish for every occasion. Filter by dietary preferences, explore regional specialties, or search by flavor profiles. The perfect bite awaits with just a few taps."
    )

    // Dive into Food Stories
    object ThirdPage: OnBoardingPage(
        image = R.drawable.third,
        title = "Beyond the Recipe",
        description = "ChefLab offers more than just ingredients and instructions. Uncover intriguing details about each dish, including its origin story, cultural significance, and unique flavor profile. Immerse yourself in the world of food, bite by fascinating bite."
    )

}