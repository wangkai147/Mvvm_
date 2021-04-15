package com.wangkai.base.activity

import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Build
import android.os.Bundle
import android.text.Layout
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.wangkai.base.R
import com.wangkai.base.status.EState
import com.wangkai.base.status.StateViewModel
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.lang.reflect.ParameterizedType


abstract class ViewBindingBaseActivity<VB : ViewBinding> : AppCompatActivity() {
    //用于打印日志
    lateinit var TAG: String

    lateinit var mContext: Context
    lateinit var mViewBinding: VB

    //管理页面状态的viewModel
    lateinit var stateViewModel: StateViewModel

    //activity根布局，用于动态添加view
    private lateinit var rootView: ViewGroup

    //网络状态页面
    private lateinit var vsLoading: ViewStub
    private lateinit var vsError: ViewStub
    private lateinit var inflaterLoading: View
    private lateinit var inflaterError: View

    abstract fun getBundleExtras(extras: Bundle?) //接收bundle数据

    abstract fun initData()
    abstract fun initView()

    abstract fun initViewModel() //初始化ViewModel

    //设置状态栏导航栏颜色
    open fun setBarColor(statusBarColor: Int, navigationBarColor: Int) {
        window.statusBarColor = statusBarColor
        window.navigationBarColor = navigationBarColor
    }

    //app全屏
    private fun isFullScreen() {
        val controller: WindowInsetsControllerCompat? =
            ViewCompat.getWindowInsetsController(window.decorView)
        controller?.hide(WindowInsetsCompat.Type.systemBars())

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this
        TAG = mContext.javaClass.simpleName
        rootView = this.window.decorView.findViewById(android.R.id.content)
        rootView.fitsSystemWindows = false
        //接受其他页面传递的值
        intent.extras?.let { getBundleExtras(it) }
//        rootView.setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener { view, insets ->
//            view.updatePadding(top = insets.systemWindowInsetTop)
//            insets
//        })

        setBarColor(Color.TRANSPARENT, Color.TRANSPARENT)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            //使布局延伸到状态栏
            window.setDecorFitsSystemWindows(false)
            //稳定布局，防止闪烁
            val controller: WindowInsetsControllerCompat? =
                ViewCompat.getWindowInsetsController(window.decorView)
            controller?.systemBarsBehavior
            WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            //适配异形屏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                window.attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    )
        }
//        isFullScreen()

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
        initStatusViewModel()
        initView()
        initData()

    }


    private fun initStatusViewModel() {
        //初始化状态的viewModel
        stateViewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                StateViewModel::class.java
            )
        stateViewModel.stateLiveData.observe(this, { t ->
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


    //显示加载中界面
    open fun showLoadingLayout(boolean: Boolean) {
        if (boolean) {
            try {
                inflaterLoading = vsLoading.inflate()
            } catch (e: Exception) {
                if (!::vsLoading.isInitialized) {
                    initStatusView()
                    inflaterLoading = vsLoading.inflate()
                }
            } finally {
                inflaterLoading.visibility = View.VISIBLE
            }

        } else {
            try {
                inflaterLoading.visibility = View.INVISIBLE
            } catch (e: Exception) {

            }
        }
    }

    //显示加载错误界面
    open fun showErrorLayout(boolean: Boolean) {
        if (boolean) {
            try {
                inflaterError = vsError.inflate()
            } catch (e: Exception) {
                if (!::vsError.isInitialized) {
                    initStatusView()
                    inflaterError = vsError.inflate()
                }
            } finally {
                inflaterError.visibility = View.VISIBLE
            }
        } else {
            try {
                inflaterError.visibility = View.INVISIBLE
            } catch (e: Exception) {

            }
        }
    }
}