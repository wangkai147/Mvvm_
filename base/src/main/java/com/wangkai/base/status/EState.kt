package com.wangkai.base.status

/**
 * @Description: 页面状态
 * @Author: JWangZzz
 * @CreateDate: 2021/4/19 10:22
 */

enum class EState {
    None, //正常状态
    Loading, // 加载数据
    Empty, // 内容制作中
    Error, // 网络错误
    Success // 加载成功
}