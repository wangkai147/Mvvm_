package com.wangkai.base.status

/**
 * 当前页面状态
 */

enum class EState {
    None, //正常状态
    Loading, // 加载数据
    Empty, // 内容制作中
    Error, // 网络错误
    Success // 加载成功
}