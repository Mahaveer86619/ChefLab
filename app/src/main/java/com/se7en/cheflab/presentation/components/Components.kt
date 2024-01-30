package com.se7en.cheflab.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.se7en.cheflab.utils.OnBoardingPages

@Composable
fun PagerScreen(
    onBoardingPages: OnBoardingPages
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.6f)
                .clip(RoundedCornerShape(5.dp)),
            painter = painterResource(id = onBoardingPages.image),
            contentDescription = null
        )

        Text(
            text = onBoardingPages.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 22.dp,
                    vertical = 6.dp
                ),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = onBoardingPages.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 22.dp,
                    vertical = 6.dp
                ),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {

    Row(
        modifier = modifier
            .padding(horizontal = 40.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == 2
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = "Ready to Explore?")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFirstOnBoarding() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPages = OnBoardingPages.FirstPages)
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewSecondOnBoarding() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPages = OnBoardingPages.SecondPages)
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewThirdOnBoarding() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPages = OnBoardingPages.ThirdPages)
    }
}