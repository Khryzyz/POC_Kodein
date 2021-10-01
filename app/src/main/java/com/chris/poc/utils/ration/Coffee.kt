package com.chris.poc.utils.ration

import com.chris.poc.utils.logger.Logger


class Coffee(
    logger: Logger
) : Ration(logger) {
    init {
        logger.log("<Creando Racion de Cafe 'CoffeeRation'>")
    }

    override fun name(): String = "cafe"
}
