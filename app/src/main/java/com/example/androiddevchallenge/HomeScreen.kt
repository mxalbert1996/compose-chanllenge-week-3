/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.Preview
import com.example.androiddevchallenge.ui.baselineHeight
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier.navigationBarsPadding().fillMaxSize(),
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.primary,
                elevation = 16.dp
            ) {
                NavigationItem.values().forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = {
                            Text(
                                text = stringResource(item.titleRes),
                                style = MaterialTheme.typography.caption
                            )
                        },
                        selected = index == 0,
                        onClick = {}
                    )
                }
            }
        }
    ) { padding ->
        val horizontalPadding = Modifier.padding(horizontal = 16.dp)
        LazyColumn(
            modifier = Modifier.padding(padding).fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp)
        ) {
            item {
                var keyword: String by rememberSaveable { mutableStateOf("") }
                OutlinedTextField(
                    value = keyword,
                    onValueChange = { keyword = it },
                    modifier = horizontalPadding.padding(top = 24.dp).fillMaxWidth().height(64.dp),
                    textStyle = MaterialTheme.typography.body1,
                    placeholder = { Text(text = stringResource(R.string.search)) },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    },
                    singleLine = true
                )
            }

            item {
                Text(
                    text = stringResource(R.string.browse_themes),
                    modifier = horizontalPadding
                        .baselineHeight(topPadding = 32.dp, bottomPadding = 8.dp),
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h1
                )
            }

            item {
                LazyRow(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)) {
                    items(10) {
                        Box(modifier = Modifier.padding(8.dp)) {
                            Card(
                                backgroundColor = MaterialTheme.colors.background,
                                shape = MaterialTheme.shapes.small,
                                elevation = 1.dp
                            ) {
                                Column(modifier = Modifier.size(136.dp)) {
                                    // Dummy content
                                    Spacer(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(17f / 12f)
                                            .background(MaterialTheme.colors.primary)
                                    )
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        Text(
                                            text = "Theme name",
                                            modifier = horizontalPadding,
                                            style = MaterialTheme.typography.h2
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                Layout(
                    content = {
                        Text(
                            text = stringResource(R.string.design_your_home_garden),
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.h1
                        )
                        Icon(
                            Icons.Filled.FilterList,
                            contentDescription = stringResource(R.string.filter),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                ) { measurables, constraints ->
                    val paddingPx = 16.dp.roundToPx()
                    val contentWidth = constraints.maxWidth - paddingPx * 2
                    val icon = measurables[1].measure(constraints)
                    val text = measurables[0].measure(
                        constraints.copy(maxWidth = contentWidth - icon.width - 8.dp.roundToPx())
                    )
                    val baseline = text[FirstBaseline]
                    val top = 32.dp.roundToPx() - baseline
                    layout(constraints.maxWidth, 44.dp.roundToPx()) {
                        text.place(paddingPx, top)
                        icon.place(
                            constraints.maxWidth - icon.width - paddingPx,
                            top + (text.height - icon.height) / 2
                        )
                    }
                }
            }

            items(10) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .fillMaxWidth()
                        .height(64.dp)
                ) {
                    // Dummy content
                    Surface(shape = MaterialTheme.shapes.small) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f)
                                .background(MaterialTheme.colors.primary)
                        )
                    }

                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp).fillMaxHeight().weight(1f)
                    ) {
                        Text(
                            text = "Design",
                            modifier = Modifier.baselineHeight(topPadding = 24.dp),
                            style = MaterialTheme.typography.h2
                        )
                        Text(
                            text = "This is a description",
                            modifier = Modifier.baselineHeight(topPadding = 16.dp),
                            style = MaterialTheme.typography.body1
                        )
                    }

                    var checked: Boolean by rememberSaveable { mutableStateOf(false) }
                    Checkbox(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        modifier = Modifier.padding(top = 16.dp).size(24.dp)
                    )
                }
            }
        }
    }
}

private enum class NavigationItem(@StringRes val titleRes: Int, val icon: ImageVector) {
    Home(R.string.home, Icons.Filled.Home),
    Favorites(R.string.favorites, Icons.Filled.FavoriteBorder),
    Profile(R.string.profile, Icons.Filled.AccountCircle),
    Cart(R.string.cart, Icons.Filled.ShoppingCart)
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenPreview() {
    Preview {
        HomeScreen()
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenDarkPreview() {
    Preview(darkTheme = true) {
        HomeScreen()
    }
}
