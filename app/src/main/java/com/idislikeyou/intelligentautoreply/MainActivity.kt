package com.idislikeyou.intelligentautoreply

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.dataStore
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.idislikeyou.intelligentautoreply.ui.elements.PermissionDialog
import com.idislikeyou.intelligentautoreply.ui.elements.ReceiveSMSPermissionTextProvider
import com.idislikeyou.intelligentautoreply.ui.elements.SendSMSPermissionTextProvider
import com.idislikeyou.intelligentautoreply.ui.nav.Navigation
import com.idislikeyou.intelligentautoreply.ui.theme.IntelligentAutoReplyTheme
import com.idislikeyou.intelligentautoreply.ui.theme.PurpleBlackGradient
import com.idislikeyou.intelligentautoreply.ui.viewmodels.MainViewModel
import com.idislikeyou.intelligentautoreply.util.AppSettings
import com.idislikeyou.intelligentautoreply.util.AppSettingsSerializer
import kotlinx.coroutines.launch

private val permissionsToRequest = arrayOf(
    Manifest.permission.RECEIVE_SMS,
    Manifest.permission.SEND_SMS
)

val Context.dataStore by dataStore("app-settings.json", AppSettingsSerializer)

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IntelligentAutoReplyTheme {

                val appSettings = dataStore.data.collectAsState(initial = AppSettings()).value

                val viewModel = viewModel<MainViewModel>()
                val dialogQueue = viewModel.visiblePermissionDialogQueue

                val multiplePermissionResultLauncher = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.RequestMultiplePermissions(),
                    onResult = {
                            perms ->
                        perms.keys.forEach { permission ->
                            viewModel.onPermissionResult(
                                permission = permission,
                                isGranted = perms[permission] == true
                            )
                        }

                    }
                )

                // A surface container using the 'background' color from the theme

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(PurpleBlackGradient)
                ) {

                    /*
                    Button(onClick = {
                        multiplePermissionResultLauncher.launch(
                            arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS)
                        )
                    }) {
                        Text(text = "request permissions")
                    }

                     */

                    Navigation()

                }


                    /*
                    dialogQueue.reversed().forEach { permission ->
                        PermissionDialog(
                            permissionTextProvider = when(permission) {
                                Manifest.permission.RECEIVE_SMS -> ReceiveSMSPermissionTextProvider()
                                Manifest.permission.SEND_SMS -> SendSMSPermissionTextProvider()
                                else -> return@forEach
                            },
                            isPermanentlyDeclined = !shouldShowRequestPermissionRationale(permission),
                            onDismiss = viewModel::dismissDialog,
                            onOkClick = {
                                viewModel.dismissDialog()
                                multiplePermissionResultLauncher.launch(
                                    arrayOf(permission)
                                )
                            },
                            onGoToAppSettingsClick = ::openAppSettings)
                    }

                     */

            }
        }
    }
}

fun Activity.openAppSettings() {
    Intent (
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}