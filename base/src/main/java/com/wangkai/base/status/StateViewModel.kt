package com.wangkai.base.status

/**
 * 带有页面状态的viewModel
 */

import android.app.Application
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.wangkai.base.status.EState
import com.wangkai.base.utils.LogUtil

open class StateViewModel(application: Application) : AndroidViewModel(application) {
    var stateLiveData: MutableLiveData<EState> = MutableLiveData()

    init {
        stateLiveData.value = EState.None
    }

    fun getNet() {
        // 延迟3秒后修改数据，UI自动更新
        Thread {
            SystemClock.sleep(3000)
            if (stateLiveData.value == EState.None)
                stateLiveData.postValue(EState.Error)
            else
                stateLiveData.postValue(EState.Loading)
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
        LogUtil.i("UserViewModel", "onCleared：ViewModel 即将销毁")
    }

}