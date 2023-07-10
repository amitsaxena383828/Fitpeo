package com.assignment.fitpeo.retrofitHelper

class RequestResult<out T> private constructor(
    val status: Status,
    val data: T?,
    val message: String?
) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T): RequestResult<T> {
            return RequestResult(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String? = null, data: T? = null): RequestResult<T> {
            return RequestResult(
                Status.ERROR,
                data,
                msg
            )
        }
    }
}