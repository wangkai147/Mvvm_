package com.wangkai.base.activity.demo

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.viewbinding.ViewBinding
import com.wangkai.base.R
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

    //子类必须重写该方法
    abstract fun initStatusLayout()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //利用反射，调用指定ViewBinding中的inflate方法填充视图
        val type: ParameterizedType = javaClass.genericSuperclass as ParameterizedType
        val clazz = type.actualTypeArguments[0] as Class<*>
        try {
            val method: Method = clazz.getDeclaredMethod("inflate", LayoutInflater::class.java)
            @Suppress("UNCHECKED_CAST")
            mViewBinding = method.invoke(null, layoutInflater) as VB
            setContentView(R.layout.layout_base)
            initStatusLayout()
            initBaseView()
            initData()
            initView()
        } catch (e: NoSuchMethodException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }

    private fun initBaseView() {
        val ll_main = findViewById<View>(R.id.ll_main) as LinearLayout
        ll_main.addView(statusLayoutManager.rootLayout)
    }

    override fun getLayout(): ViewGroup {
        return mViewBinding.root as ViewGroup
    }

    override fun getLayoutId() : Int {
        return mViewBinding.root.sourceLayoutResId
    }
}