package com.chris.poc.utils

import com.chris.poc.utils.heater.Heater
import com.chris.poc.utils.logger.Logger
import com.chris.poc.utils.ration.Ration
import com.chris.poc.utils.pump.Pump

class Kettle<T : Ration>(
    private val logger: Logger,
    private val heater: Heater,
    private val pump: Pump,
    private val ration: () -> T // We will need a new ration every time
) {

    init {
        logger.log("<Creando Cafetera 'CoffeeMaker'>")
    }

    fun brew() {
        heater.on()
        pump.pumpWater()
        val ration = ration()

        logger.log("[_]P ${ration.name()} [_]P")
        heater.off()
    }
}
