package com.example.compose_camp_1

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_camp_1.ui.theme.Compose_Camp_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_Camp_1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MyApp()
                    //Greeting1("Android")
                    //OnboardingScreen(onContinueClicked = {})
                    //Greetings1()


                }
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
){
    var showBoarding by remember { mutableStateOf(true) }
    if (showBoarding){
        OnboardingScreen {showBoarding = false}
    } else {
        Greetings()
    }
}


@Composable
fun Greetings1(
modifier: Modifier = Modifier,
names: List<String> = listOf("World", "Compose"),
){
    Column(modifier.padding(vertical = 4.dp)){
        names.forEach { name ->
            Greeting1(name)
        }
    }
}


@Composable
fun Greeting1(name: String){
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp).fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(vertical = 4.dp, horizontal = 3.dp)) {
            Text("Hello,")
            Text(name,)
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    //Column(modifier.padding(vertical = 4.dp).verticalScroll(rememberScrollState())) {
      //  names.forEach { name ->
        //    Greeting(name)
        //}
    //}

    LazyColumn {
        items(items = names) { name ->
            Greeting(name)
        }
    }
}

@Composable
fun Greeting(name: String) {

    var expanded by remember { mutableStateOf(false) }
    //val extraPadding = if (expanded) 48.dp else 0.dp
    val extraPadding by animateDpAsState(
        if (expanded) 10.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessLow,
            visibilityThreshold = 2.dp
        )
    )
    val animatedColor by animateColorAsState(
        if (expanded) Color.Red else MaterialTheme.colorScheme.primary  ,
        animationSpec = tween(
            durationMillis = 900,
            delayMillis = 500,
            easing = CubicBezierEasing(0.45f, 0.0f, 0.56f, 1.0f)
        )
    )
    val textAlpha by animateFloatAsState(
        if (expanded) 1f else 0f
    )


    Surface(
        color = animatedColor,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column {
            Row(Modifier.padding(24.dp)) {
                Column(
                    modifier = Modifier.weight(1f)
                        .padding(bottom = extraPadding.coerceAtLeast(0.dp))
                ) {
                    Text("Hello,")
                    Text(name, style = MaterialTheme.typography.headlineMedium)
                }
                ElevatedButton(
                    onClick = { expanded = !expanded }
                ) {
                    Text(if (expanded) "Show Less" else "Show More")
                }
                /*IconButton(onClick = {expanded = !expanded}){
                Icon(if (expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp, null)
            }*/
            }

            Column {
                AnimatedVisibility(
                    visible = expanded,
                    enter = fadeIn(initialAlpha = 0.0f),
                    exit = fadeOut(targetAlpha = 0.0f)
                ) {
                    Text(
                        "Lorem Ipsuim Coreectum Latyeu sbdiis jahyeu lsishd bdjdksoue jsjj",
                        modifier = Modifier.alpha(textAlpha).padding(16.dp),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    onContinueClicked: () -> Unit,
){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Welcome To Compose Basics!")
        Button(
            modifier = modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ){
            Text("Continue")
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose_Camp_1Theme {
        Greetings()
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    Compose_Camp_1Theme {
        Greetings()
    }
}