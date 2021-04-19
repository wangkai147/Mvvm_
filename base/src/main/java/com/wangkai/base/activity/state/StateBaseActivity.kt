package com.wangkai.base.activity.state

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wangkai.base.R
import com.wangkai.base.activity.BaseActivity
import com.wangkai.base.status.EState
import com.wangkai.base.status.StateViewModel

/**
 * @Description: 带有状态管理的baseActivity
 * @Author: JWangZzz
 * @CreateDate: 2021/4/19 10:22
 */

abstract class StateBaseActivity : BaseActivity() {

    //管理页面状态的viewModel
    val stateViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
            StateViewModel::class.java
        )
    }

    fun initStatusViewModel() {
        stateViewModel.stateLiveData.observe(this, Observer{ t ->
            t?.let {
                when (t) {
                    EState.Loading -> {
                    }
                    EState.Error -> {
                    }
                    EState.None -> {
                    }
                    EState.Empty -> {
                    }
                    EState.Success -> {
                    }
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }


}