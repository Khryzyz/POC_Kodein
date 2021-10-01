package com.chris.poc.utils.ration

import com.chris.poc.utils.logger.Logger

class Tea(
    logger: Logger
) : Ration(logger) {
    init {
        logger.log("<Creando Racion de Te 'TeaRation'>")
    }

    override fun name(): String = "Te"
}