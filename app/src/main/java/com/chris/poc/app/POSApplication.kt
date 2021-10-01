package com.chris.poc.app

import android.app.Activity
import android.app.Application
import com.chris.poc.utils.logger.AndroidLogger
import com.chris.poc.utils.ration.Coffee
import com.chris.poc.utils.Kettle
import com.chris.poc.utils.heater.electricHeaterModule
import org.kodein.di.*
import org.kodein.di.android.x.androidXModule
import org.kodein.di.bindings.WeakContextScope

class POSApplication : Application(), DIAware {

    override val di by DI.lazy {

        import(androidXModule(this@POSApplication))

        bind()
        bind { instance(AndroidLogger()) }

        import(electricHeaterModule)

        bind<Coffee>() with provider { Coffee(instance()) }

        // this is bound in the scope of an activity so any retrieval using the same activity will return the same Kettle instance
        bind<Kettle<Coffee>>() with scoped(WeakContextScope.of<Activity>()).singleton {
            Kettle<Coffee>(
                instance(),
                instance(),
                instance(),
                provider()
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        val k = di
        println(k)
    }

}
