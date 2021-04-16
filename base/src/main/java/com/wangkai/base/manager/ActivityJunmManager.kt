package com.wangkai.base.manager

import android.app.Activity
import android.content.Context
import org.jetbrains.anko.startActivity

/**
 * @Description: activity管理
 * @Author: JWangZzz
 * @CreateDate: 2021/4/16 23:35
 */
inline fun <reified T: Activity> Context.startActivity() =
    startActivity<T>()