package com.wangkai.base.entity

/**
 * 封装的实体类（带有code个message）
 */

open class JsonResultBean<T>(
    var code: String,
    var msg: String,
    var data: T
) {}