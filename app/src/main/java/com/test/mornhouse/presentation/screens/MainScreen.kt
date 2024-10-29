package com.test.mornhouse.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.test.mornhouse.R
import com.test.mornhouse.presentation.model.NumberFactUI
import com.test.mornhouse.presentation.navigation.Root.root
import com.test.mornhouse.presentation.navigation.Routes
import com.test.mornhouse.presentation.viewModel.MainViewModel

@Composable
fun MainScreen(navController: NavController) {
    val parentEntry = remember(navController) {
        navController.getBackStackEntry(root)
    }
    val viewModel: MainViewModel = hiltViewModel(parentEntry)
    val context = LocalContext.current
    val numberInput = remember { mutableStateOf("") }
    val factsHistory by viewModel.factsHistory.collectAsState()
    val numberFact by viewModel.numberFact.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Upper Part: Input Field and Buttons
        OutlinedTextField(
            value = numberInput.value,
            onValueChange = { numberInput.value = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                if (numberInput.value.isNotBlank()) {
                    viewModel.getFact(numberInput.value)
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.please_enter_a_number), Toast.LENGTH_SHORT
                    ).show()
                }
            }) {
                Text(stringResource(R.string.get_fact))
            }
            Button(onClick = {
                viewModel.getRandomFact()
            }) {
                Text(stringResource(R.string.get_random_number_fact))
            }
        }

        // Display the Fetched Fact
        numberFact?.let { fact ->
            Text(text = fact, modifier = Modifier.padding(top = 16.dp))
        }

        // Lower Part: Facts History
        Text(
            text = stringResource(R.string.history),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Divider()
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(factsHistory) { fact ->
                FactItem(fact = fact, onClick = {
                    viewModel.selectFact(fact)
                    navController.navigate(Routes.DetailScreen)
                })
            }
        }
    }
}

@Composable
fun FactItem(fact: NumberFactUI, onClick: () -> Unit) {
    val previewText = if (fact.fact.length > 50) fact.fact.substring(0, 50) + "..." else fact.fact
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = stringResource(R.string.number, fact.number),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = previewText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall
        )
        Divider()
    }
}

