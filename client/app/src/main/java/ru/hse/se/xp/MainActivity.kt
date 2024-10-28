package ru.hse.se.xp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.hse.se.xp.auth.AuthStorage
import ru.hse.se.xp.ui.theme.SETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AuthStorage.init(this)
        setContent {
            SETheme {
                App()
            }
        }
    }
}
