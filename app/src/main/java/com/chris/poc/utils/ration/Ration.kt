package com.chris.poc.utils.ration

import com.chris.poc.utils.logger.Logger

abstract class Ration(val logger: Logger) {
    abstract fun name(): String
}