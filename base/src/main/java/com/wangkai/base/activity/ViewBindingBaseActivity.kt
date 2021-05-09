package com.wangkai.base.activity

import android.os.Bundle
import android.view.*
import androidx.viewbinding.ViewBinding
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType


abstract class ViewBindingBaseActivity<VB : ViewBinding> : BaseActivity() {
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
            setContentView(mViewBinding.root)
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }
}