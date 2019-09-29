package com.example.kotlin_ex2.network.main


enum class Status { FETCHING, SUCCESS, NO_MORE, FAILED }
data class RequestStatus private constructor(val status: Status, val message: String? = null) {
    companion object {
        fun fetching() = RequestStatus(Status.FETCHING)
        fun succeed() = RequestStatus(Status.SUCCESS)
        fun noMore() = RequestStatus(Status.NO_MORE)
        fun failed(message: String) = RequestStatus(Status.FAILED, message)
    }
}