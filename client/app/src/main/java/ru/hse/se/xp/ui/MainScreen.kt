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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.hse.se.xp.model.UserList
import ru.hse.se.xp.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(toListScreen: ((String, String) -> Unit)? = null) {

    val mainScreenViewModel: MainScreenViewModel = viewModel()
    val uiState by mainScreenViewModel.uiState.collectAsState()

    if (!uiState.status) {
        mainScreenViewModel.loadLists()
    }

    Scaffold(
        topBar = {
            Button(onClick = {

            }) {
                Text("Добавить новый список")
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
                Lists(uiState.lists!!, toListScreen)
            }
        }
    }

}

@Composable
fun Lists(lists: List<UserList>, toListScreen: ((String, String) -> Unit)?) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(lists) { list ->
            ListItem(list, toListScreen)
        }
    }
}

@Composable
fun ListItem(list: UserList, toListScreen: ((String, String) -> Unit)?) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { toListScreen?.invoke(list.id.toString(), list.title) }
        .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(list.title)
    }
}
