package com.example.instagramui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@ExperimentalFoundationApi
@Composable
fun ProfileScreen() {
    Column(Modifier.fillMaxSize()) {
        var selectedTabIndex by remember {
            mutableStateOf(0)
        }
        TopBar(username = "mazouzi.aymene98", modifier = Modifier.padding(10.dp))
        ProfileSection()
        ButtonSection()
        HighLightSection(
            modifier = Modifier.padding(20.dp),
            items = listOf(
                ImageWithText("YouTube", painterResource(id = R.drawable.youtube)),
                ImageWithText("Q&A", painterResource(id = R.drawable.qa)),
                ImageWithText("Discord", painterResource(id = R.drawable.discord)),
                ImageWithText("Telegram", painterResource(id = R.drawable.telegram)),
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        PostTabView(
            imageWithText = listOf(
                ImageWithText(
                    image = painterResource(id = R.drawable.ic_grid),
                    text = "Posts"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.ic_reels),
                    text = "Reels"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.ic_igtv),
                    text = "IGTV"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.profile),
                    text = "Profile"
                ),
            )
        ) {
            selectedTabIndex = it
        }
        when(selectedTabIndex) {
            0 -> PostSection(
                posts = listOf(
                    painterResource(id = R.drawable.kmm),
                    painterResource(id = R.drawable.intermediate_dev),
                    painterResource(id = R.drawable.master_logical_thinking),
                    painterResource(id = R.drawable.bad_habits),
                    painterResource(id = R.drawable.multiple_languages),
                    painterResource(id = R.drawable.learn_coding_fast),
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}

@Composable
fun TopBar(
    username: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier.size(24.dp)
        )

        Text(
            text = username,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis
        )


        Icon(
            painter = painterResource(id = R.drawable.ic_bell),
            tint = Color.Black,
            contentDescription = "Bell",
            modifier = modifier.size(24.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_dotmenu),
            tint = Color.Black,
            contentDescription = "Bell",
            modifier = modifier.size(20.dp)
        )

    }
}


@Composable
fun ProfileSection(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedImage(
                painter = painterResource(id = R.drawable.philipp),
                modifier = modifier
                    .size(100.dp)
                    .weight(weight = 3f)
            )
            StatSection(modifier.weight(weight = 7f))
        }

        ProfileDescription(
            displayName = "Programming Mentor",
            description = "10 years of coding experience\n" +
                    "Want me to make your app? Send me an email!\n" +
                    "Subscribe to my YouTube channel!",
            url = "https://youtube.com/c/PhilippLackner",
            followers = listOf("codinginflow", "mformatik"),
            otherCount = 17
        )
    }
}


@Composable
fun RoundedImage(painter: Painter, modifier: Modifier = Modifier) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(width = 1.dp, color = Color.LightGray, shape = CircleShape)
            .padding(3.dp)
            .clip(CircleShape)
    )
}

@Composable
fun StatSection(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        ProfileStat(number = "601", text = "Post")
        ProfileStat(number = "110K", text = "Followers")
        ProfileStat(number = "77", text = "Following")
    }
}

@Composable
fun ProfileStat(
    number: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = number, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = modifier.height(4.dp))
        Text(text = text)
    }
}

@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
    url: String,
    followers: List<String>,
    otherCount: Int,
    modifier: Modifier = Modifier
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.Bold,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )

        Text(
            text = description,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )

        Text(
            text = url,
            color = Color.Blue,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )

        if (followers.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)
                    append("Followed by ")
                    pushStyle(boldStyle)
                    append(followers.joinToString(" , "))
                    pop()
                    append(" and ")
                    pushStyle(boldStyle)
                    if (otherCount > 2) {

                        append("$otherCount others")
                    }
                }, letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
        }

    }

}

@Composable
fun ButtonSection(
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(15.dp)
    ) {
        val minWidth = 95.dp
        val height = 30.dp

        ActionButton(
            text = "Following",
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .height(height)
                .defaultMinSize(minWidth = minWidth)
        )
        ActionButton(
            text = "Message",
            modifier = Modifier
                .height(height)
                .defaultMinSize(minWidth = minWidth)
        )
        ActionButton(
            text = "Email",
            modifier = Modifier
                .height(height)
                .defaultMinSize(minWidth = minWidth)
        )
        ActionButton(
            icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .height(height)
                .defaultMinSize(minWidth = minWidth)
        )
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 6.dp)
            .border(1.dp, color = Color.LightGray, RoundedCornerShape(4.dp))
    ) {
        if (text != null) {
            Text(text = text, fontWeight = FontWeight.Bold)
        }
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = null)
        }

    }
}

@Composable
fun HighLightSection(
    modifier: Modifier = Modifier,
    items: List<ImageWithText>
) {
    LazyRow(
        modifier = modifier
    ) {
        items(items.size) {
            HighLightItem(item = items[it])
        }
    }

}

@Composable
fun HighLightItem(
    modifier: Modifier = Modifier,
    item: ImageWithText
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(end = 8.dp)
    ) {
        RoundedImage(painter = item.image, modifier = modifier.size(70.dp))
        Spacer(modifier = modifier.height(8.dp))
        Text(text = item.text, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    imageWithText: List<ImageWithText>,
    onTabSelected: (index: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val inactiveColor = Color(0xFF777777)

    TabRow(
        backgroundColor = Color.Transparent,
        contentColor = Color.Black,
        selectedTabIndex = selectedTabIndex,
        modifier = modifier
    ) {
        imageWithText.forEachIndexed { index, item ->
            Tab(
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                selected = selectedTabIndex == index,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                },
            ) {
                Icon(
                    painter = item.image,
                    contentDescription = item.text,
                    tint = if (selectedTabIndex == index) Color.Black else inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }

        }
    }
}


@ExperimentalFoundationApi
@Composable
fun PostSection(
    posts: List<Painter>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        modifier = modifier
            .scale(1.01f)
    ) {
        items(posts.size) {
            Image(
                painter = posts[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = Color.White
                    )
            )
        }
    }
}