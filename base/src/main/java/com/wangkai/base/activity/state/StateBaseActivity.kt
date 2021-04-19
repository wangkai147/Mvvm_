package com.wangkai.base.activity.state

import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wangkai.base.R
import com.wangkai.base.activity.BaseActivity
import com.wangkai.base.status.EState
import com.wangkai.base.status.StateViewModel
import com.wangkai.base.utils.LogUtil
import org.jetbrains.anko.backgroundColor

/**
 * @Description: 带有状态管理的baseActivity
 * @Author: JWangZzz
 * @CreateDate: 2021/4/19 10:22
 */

abstract class StateBaseActivity : BaseActivity() {


    //网络状态页面
    private lateinit var vsLoading: ViewStub
    private lateinit var vsError: ViewStub
    private lateinit var inflaterLoading: View
    private lateinit var inflaterError: View

    //管理页面状态的viewModel
    val stateViewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
            StateViewModel::class.java
        )
    }

    //显示加载中界面
    open fun showLoadingLayout(boolean: Boolean) {
        LogUtil.e("WASSSSSSSSSSS","LOADING")
        try {
            inflaterLoading = vsLoading.inflate()
        } catch (e: Exception) {
            if (!::vsLoading.isInitialized) {
                initStatusView()
                inflaterLoading = vsLoading.inflate()
            }
        } finally {
            if (boolean) {
                inflaterLoading.visibility = View.VISIBLE
            } else {
                inflaterLoading.visibility = View.INVISIBLE
            }
        }
    }

    //显示加载错误界面
    open fun showErrorLayout(boolean: Boolean) {
        LogUtil.e("WASSSSSSSSSSS","ERROR")
        try {
            inflaterError = vsError.inflate()
        } catch (e: Exception) {
            if (!::vsError.isInitialized) {
                initStatusView()
                inflaterError = vsError.inflate()
            }
        } finally {
            if (boolean) {
                inflaterError.visibility = View.VISIBLE
            } else {
                inflaterError.visibility = View.INVISIBLE
            }
        }
    }


    //初始化状态的布局
    private fun initStatusView() {

        //加载成功的布局
        vsLoading = ViewStub(this, R.layout.common_base_loading)
        rootView.addView(vsLoading)

        //加载失败的布局
        vsError = ViewStub(this, R.layout.common_base_error)
        rootView.addView(vsError)
    }

    fun initStatusViewModel() {
        stateViewModel.stateLiveData.observe(this, Observer{ t ->
            t?.let {
                when (t) {
                    EState.Loading -> {
                        showLoadingLayout(true)
                    }
                    EState.Error -> {
                        showErrorLayout(true)
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