package com.idislikeyou.intelligentautoreply.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    // [RECORD_AUDIO, CAMERA]
    val visiblePermissionDialogQueue = mutableStateListOf<String>()


    fun dismissDialog() {
        visiblePermissionDialogQueue.removeFirst()
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if (!isGranted) {
            visiblePermissionDialogQueue.add(permission)
        }
    }

    fun addRequiredPermission(permission: String) {
        visiblePermissionDialogQueue.add(permission)
    }
}