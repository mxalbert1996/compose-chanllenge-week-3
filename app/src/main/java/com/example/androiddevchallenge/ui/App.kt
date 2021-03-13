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
package com.example.androiddevchallenge.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.androiddevchallenge.HomeScreen
import com.example.androiddevchallenge.Screen

@Composable
fun BloomApp(darkTheme: Boolean = false) {
    var screen: Screen by rememberSaveable { mutableStateOf(Screen.Welcome) }
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        Crossfade(screen) {
            when (it) {
                Screen.Welcome -> {
                    WelcomeScreen(
                        darkTheme = darkTheme,
                        navigateToLogIn = { screen = Screen.LogIn }
                    )
                }
                Screen.LogIn -> {
                    BackHandler { screen = Screen.Welcome }
                    LogInScreen(
                        navigateToHome = { screen = Screen.Home }
                    )
                }
                Screen.Home -> {
                    BackHandler { screen = Screen.LogIn }
                    HomeScreen()
                }
            }
        }
    }
}
