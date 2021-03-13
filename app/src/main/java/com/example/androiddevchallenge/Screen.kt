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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.setValue

enum class Screen { Welcome, LogIn, Home }

class AppState(
    screen: Screen = Screen.Welcome
) {
    var screen: Screen by mutableStateOf(screen)
}

private val Saver: Saver<AppState, *> = listSaver(
    save = {
        listOf(
            it.screen.ordinal
        )
    },
    restore = {
        AppState(
            screen = Screen.values()[it[0]]
        )
    }
)
