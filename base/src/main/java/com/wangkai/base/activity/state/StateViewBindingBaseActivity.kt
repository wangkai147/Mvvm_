package com.wangkai.base.activity.state

import android.os.Bundle
import android.view.*
import androidx.viewbinding.ViewBinding
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType

/**
 * @Description: 带有状态管理的baseActivity
 * @Author: JWangZzz
 * @CreateDate: 2021/4/19 10:22
 */

abstract class StateViewBindingBaseActivity<VB : ViewBinding> : StateBaseActivity() {

    lateinit var mViewBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //利用反射，调用指定ViewBinding中的inflate方法填充视图
        val type: ParameterizedType = javaClass.genericSuperclass as ParameterizedType
        val clazz = type.actualTypeArguments[0] as Class<*>
        try {
            val method: Method = clazz.getDeclaredMethod("inflate", LayoutInflater::class.java)
            @Suppress("UNCHECKED_CAST")
            mViewBinding = method.invoke(null, layoutInflater) as VB
            setContentView(getLayout())
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
        initData()
        initView()
    }

    override fun getLayout(): ViewGroup {
        return mViewBinding.root as ViewGroup
    }



}