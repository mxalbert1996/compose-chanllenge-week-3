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

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.view.WindowCompat
import com.example.androiddevchallenge.ui.BloomApp
import com.example.androiddevchallenge.ui.theme.BloomTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import timber.log.Timber

class MainActivity : ComponentActivity() {
    companion object {
        init {
            if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val isDarkTheme = isSystemInDarkTheme()
            BloomTheme(darkTheme = isDarkTheme) {
                ProvideWindowInsets {
                    BloomApp(darkTheme = isDarkTheme)
                }
            }
        }
    }
}
