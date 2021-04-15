package com.wangkai.base.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import com.wangkai.base.BuildConfig


object ToastUtil {
    private var isShowDebug = BuildConfig.DEBUG
    private var isShowFormal = true
    private var mToast: Toast? = null

    //全局控制Toast是否可显示
    fun controlShow(isShow:Boolean){
        isShowFormal = isShow
    }

    fun cancelToast(){
        if (isShowFormal&& mToast!=null){
            mToast!!.cancel()
        }
    }

    fun showShort(context: Context, message: CharSequence?) {
        if (isShowFormal) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            } else {
                mToast?.cancel()
                mToast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
            }
            mToast!!.show()
        }
    }

    fun showShort(context: Context, resId: Int) {
        if (isShowFormal) {
            if (mToast == null) {
                mToast = Toast.makeText(context, resId, Toast.LENGTH_SHORT)
            } else {
                mToast!!.setText(resId)
            }
            mToast!!.show()
        }
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    fun showLong(context: Context, message: CharSequence?) {
        if (isShowFormal) {
            if (mToast == null) {
                mToast = Toast.makeText(context, message, Toast.LENGTH_LONG)
            } else {
                mToast!!.setText(message)
            }
            mToast!!.show()
        }
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param resId   资源ID:getResources().getString(R.string.xxxxxx);
     */
    fun showLong(context: Context, resId: Int) {
        if (isShowFormal) {
            if (mToast == null) {
                mToast = Toast.makeText(context, resId, Toast.LENGTH_LONG)
            } else {
                mToast!!.setText(resId)
            }
            mToast!!.show()
        }
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param resId    资源ID:getResources().getString(R.string.xxxxxx);
     * @param duration 单位:毫秒
     */
    fun show(context: Context, resId: Int, duration: Int) {
        if (isShowFormal) {
            if (mToast == null) {
                mToast = Toast.makeText(context, resId, duration)
            } else {
                mToast!!.setText(resId)
            }
            mToast!!.show()
        }
    }

    fun showNetError(context: Context) {
        val toast = Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun show_center(context: Context?, str: String?) {
        val toast = Toast.makeText(context, str, Toast.LENGTH_SHORT)
        toast.show()
    }


}