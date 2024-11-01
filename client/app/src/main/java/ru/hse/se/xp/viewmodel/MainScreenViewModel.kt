package ru.hse.se.xp.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.hse.se.xp.model.CurrentUser
import ru.hse.se.xp.model.UserList
import ru.hse.se.xp.network.RetrofitClient

class MainScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState: StateFlow<MainScreenUiState> = _uiState.asStateFlow()

    fun loadLists() {
        if (!uiState.value.status) {
            _uiState.update { oldValue ->
                oldValue.copy(
                    status = false,
                    lists = null
                )
            }
        }
//        val lists: List<UserList> = listOf(
//            UserList(0, "Дедлайны", user_id = 0),
//            UserList(1, "Список покупок", user_id = 0),
//            UserList(0, "фывапролджэ", user_id = 0),
//            UserList(0, "lorem ipsum", user_id = 0),
//            )
//        _uiState.update { oldValue ->
//            oldValue.copy(
//                lists = lists,
//                status = true
//            )
//        }

        RetrofitClient.getUserLists(
            CurrentUser.userId!!,
            onSuccess = { body ->
                if (body != null) {
                    _uiState.update { oldValue ->
                        oldValue.copy(
                            lists = body,
                            status = true
                        )
                    }
                }
            },
            onFailure = {
                _uiState.update { oldValue ->
                    oldValue.copy(
                        lists = null,
                        status = false
                    )
                }
            }
        )
    }
}
