package com.dija.fulcrum.service

class IntentServiceResult internal constructor(resultCode: Int, resultValue: String) {

    var result: Int = 0
        internal set
    var resultValue: String
        internal set

    init {
        result = resultCode
        this.resultValue = resultValue
    }
}