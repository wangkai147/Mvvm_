package com.wangkai.base.status

/**
 * @Description: 页面状态的viewModel
 * @Author: JWangZzz
 * @CreateDate: 2021/4/19 10:22
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
            stateLiveData.postValue(EState.Loading)
            SystemClock.sleep(3000)
            stateLiveData.postValue(EState.Error)
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
        LogUtil.i("UserViewModel", "onCleared：ViewModel 即将销毁")
    }

}