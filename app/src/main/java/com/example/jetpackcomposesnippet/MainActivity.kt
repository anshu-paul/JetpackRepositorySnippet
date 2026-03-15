package com.example.jetpackcomposesnippet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposesnippet.screen.ObserveScreen
import com.example.jetpackcomposesnippet.ui.theme.JetPackComposeSnippetTheme
import com.example.jetpackcomposesnippet.viewmodel.ObserveViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[ObserveViewModel::class.java]
        enableEdgeToEdge()
        setContent {
            JetPackComposeSnippetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ObserveScreen(
                        modifier = Modifier.padding(innerPadding), viewModel
                    )
                }
            }
        }
    }
}