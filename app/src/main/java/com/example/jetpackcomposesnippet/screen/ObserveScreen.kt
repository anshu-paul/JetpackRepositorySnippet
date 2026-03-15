package com.example.jetpackcomposesnippet.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposesnippet.viewmodel.ObserveViewModel

@Composable
fun ObserveScreen(modifier: Modifier, viewModel: ObserveViewModel) {
    val observeLiveData = viewModel.liveData.observeAsState()
    val observeStateFlow = viewModel.stateFlow.collectAsState()
    val observeSharedFlow = viewModel.sharedFlow.collectAsState("Hello World Shared Flow")
    /*val observeFlow = viewModel.triggerFlow().collect { value ->
        Text(observeFlow.value)
    }*/

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = observeLiveData.value!!)
        Button(modifier = Modifier.width(150.dp), onClick = {
            viewModel.triggerLiveData()
        }) {
            Text("Live Data")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(observeStateFlow.value)
        Button(modifier = Modifier.width(150.dp), onClick = {
            viewModel.triggerStateFlow()
        }) {
            Text("State Flow")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Flow")
        Button(modifier = Modifier.width(150.dp), onClick = {
            viewModel.triggerFlow()
        }) {
            Text("Flow")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(observeSharedFlow.value)
        Button(modifier = Modifier.width(150.dp), onClick = {
            viewModel.triggerSharedFlow()
        }) {
            Text("Shared Flow")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ObserveScreenPreview() {
    ObserveScreen(modifier = Modifier, viewModel = ObserveViewModel())
}

