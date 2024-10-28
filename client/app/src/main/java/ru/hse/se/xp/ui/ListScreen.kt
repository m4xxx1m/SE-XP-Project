package ru.hse.se.xp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.hse.se.xp.viewmodel.ListScreenViewModel

@Composable
fun ListScreen(listId: Int, listTitle: String, backToScreen: (() -> Unit)? = null) {
    val listScreenViewModel: ListScreenViewModel = viewModel()
    val uiState by listScreenViewModel.uiState.collectAsState()

    if (!uiState.status) {
        listScreenViewModel.loadList()
    }

    Scaffold(
        topBar = {
            Button(onClick = {

            }) {
                Text("Редактировать")
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.status) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Text("Title", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                    items(uiState.listItems!!) { item ->
                        val checked = remember { mutableStateOf(item.completed) }
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = checked.value,
                                onCheckedChange = { newValue ->
                                    checked.value = newValue
                                }
                            )
                            Text(item.content)
                        }
                    }
                }
            }
        }
    }
}
