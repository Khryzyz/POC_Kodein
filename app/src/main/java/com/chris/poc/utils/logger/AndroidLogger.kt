package com.chris.poc.utils.logger

class AndroidLogger : Logger {

    var text: String = ""
        private set

    var callback: (() -> Unit)? = null

    override fun log(msg: String) {
        text += "$msg\n"
        callback?.invoke()
    }
}
