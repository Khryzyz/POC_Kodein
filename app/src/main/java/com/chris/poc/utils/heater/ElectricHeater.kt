package com.chris.poc.utils.heater

import com.chris.poc.utils.logger.Logger
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

class ElectricHeater(
    private val log: Logger
) : Heater {

    private var heating: Boolean = false

    init {
        log.log("<Creando Calentador Electrico 'ElectricHeater'>")
    }

    override fun on() {
        log.log("~ ~ ~ Calentando ~ ~ ~")
        this.heating = true
    }

    override fun off() {
        log.log(". . . Enfriando . . .")
        this.heating = false
    }

    override val isHot: Boolean get() = heating
}

val electricHeaterModule = DI.Module("Calentador Electrico") {
    bind<Heater>() with singleton {
        ElectricHeater(instance())
    }
}