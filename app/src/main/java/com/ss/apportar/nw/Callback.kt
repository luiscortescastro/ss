package com.ss.apportar.nw

import java.lang.Exception

interface Callback<T> {
    fun onSuccess(response: T?)

    fun onFailure(exception: Exception)
}