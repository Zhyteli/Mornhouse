package com.test.mornhouse.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.test.mornhouse.presentation.navigation.NumberFactsApp
import com.test.mornhouse.presentation.ui.theme.MornhouseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MornhouseTheme {
                NumberFactsApp()
            }
        }
    }
}