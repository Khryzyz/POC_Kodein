package com.chris.poc.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chris.poc.R
import com.chris.poc.ui.fragment.MainFragment
import com.chris.poc.utils.logger.AndroidLogger
import com.chris.poc.utils.ration.Coffee
import com.chris.poc.utils.Kettle
import com.chris.poc.utils.pump.thermosiphonModule
import org.kodein.di.*
import org.kodein.di.android.closestDI
import org.kodein.di.android.retainedSubDI

class MainActivity : AppCompatActivity(), DIAware {

    override val diContext: DIContext<*> = diContext(this)

    override val di by retainedSubDI(closestDI(), copy = Copy.All) {
        import(thermosiphonModule)
    }

    // will be the same instance as the coffeeMaker in MainFragment
    val coffeeMaker: Kettle<Coffee> by instance()
    val log: AndroidLogger by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            log.log("Yendo a preparar cafe usando $coffeeMaker")

            supportFragmentManager.beginTransaction().add(
                R.id.fragment,
                MainFragment()
            ).commit()
        }

        Log.i("Kodein", "=====================-BINDINGS-=====================")
        Log.i("Kodein", di.container.tree.bindings.description())
        Log.i("Kodein", "====================================================")
    }

}
