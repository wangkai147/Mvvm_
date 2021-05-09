package com.wangkai.base.activity

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.wangkai.base.R
import com.wangkai.base.utils.LogUtil


abstract class DataBindingBaseActivity<DB : ViewDataBinding> : BaseActivity() {
    lateinit var mDataBinding: DB

    abstract fun initViewModel() //初始化ViewModel

    @RequiresApi(Build.VERSION_CODES.P)
    override fun setContentView(layoutResID: Int) {
        mDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(mContext),
            layoutResID, null, false
        )
        mDataBinding.lifecycleOwner = this
        super.setContentView(mDataBinding.root)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mDataBinding.root)
    }
}