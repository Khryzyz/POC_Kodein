package com.chris.poc.utils.pump

import com.chris.poc.utils.heater.Heater
import com.chris.poc.utils.logger.Logger
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

class Thermosiphon(
    private val log: Logger,
    private val heater: Heater
) : Pump {

    init {
        log.log("<Crenado Termosifón 'Thermosiphon'>")
    }

    override fun pumpWater() {
        if (heater.isHot)
            log.log("=> => Bombeando => =>")
        else
            log.log("!!! El Agua esta fria !!!")
    }
}

val thermosiphonModule = DI.Module("Termosifón") {
    bind<Pump>() with singleton {
        Thermosiphon(
            instance(),
            instance()
        )
    }
}