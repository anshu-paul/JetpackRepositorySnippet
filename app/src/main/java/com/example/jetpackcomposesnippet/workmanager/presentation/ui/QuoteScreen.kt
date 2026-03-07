package com.example.jetpackcomposesnippet.workmanager.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpackcomposesnippet.workmanager.domain.viewmodel.QuoteViewModel

// Runtime composable: do NOT annotate this with @Preview because it depends on Hilt ViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun QuoteScreen() {
    val quoteViewModel: QuoteViewModel = hiltViewModel()
    //val quote = quoteViewModel.quoteLiveData.observeAsState()
    //QuoteContent(author = quote.value?.quote ?: "")
    val uiState by quoteViewModel.uIState.collectAsState()
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Quotes") }, actions = {
            IconButton(onClick = { quoteViewModel.getQuote() }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null
                )
            }
        })
    }) {
        if (uiState.dataLList.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Nothing Found")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                items(uiState.dataLList) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(8.dp)) {
                            Text(text = it.quote!!)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(text = "Author: ${it.author}")
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = it.time.toString())
                                Text(text = it.workType)
                            }
                        }
                    }
                }
            }
        }
    }
}

// Reusable, parameterized UI composable that can be previewed
@Composable
fun QuoteContent(author: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        Text(text = author, color = Color.White)
    }
}

// Parameterless preview wrapper so Android Studio can render the preview

@Composable
fun QuoteScreenPreview() {
    QuoteContent(author = "Preview Author")
}
