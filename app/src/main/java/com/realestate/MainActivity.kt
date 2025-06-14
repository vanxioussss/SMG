package com.realestate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration.Indefinite
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.realestate.data.util.network.NetworkMonitor
import com.realestate.navigation.NavGraphHost
import com.realestate.ui.theme.RealestateTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RealestateTheme {
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(
                            snackbarHostState,
                            modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
                        )
                    }) { innerPadding ->
                    val isOffline =
                        networkMonitor.isOnline.collectAsState(initial = true).value.not()

                    val notConnectedMessage = stringResource(R.string.not_connected)
                    LaunchedEffect(isOffline) {
                        if (isOffline) {
                            snackbarHostState.showSnackbar(
                                message = notConnectedMessage,
                                duration = Indefinite,
                            )
                        }
                    }

                    NavGraphHost(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}