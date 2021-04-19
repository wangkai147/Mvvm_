package com.wangkai.base.status

sealed class VmState{
    object Success: VmState()
    object Error: VmState()
    object Empty: VmState()
}