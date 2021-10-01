package com.chris.poc.utils.heater

interface Heater {
    fun on()
    fun off()
    val isHot: Boolean
}
