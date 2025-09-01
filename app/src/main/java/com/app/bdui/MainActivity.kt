package com.app.bdui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.app.bdui.core.ui.RenderScreen
import com.app.bdui.ui.theme.BDUITheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BDUIPreview()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BDUIPreview() {
    BDUITheme {
        RenderScreen()
    }
}