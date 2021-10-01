package com.chris.poc.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chris.poc.R
import com.chris.poc.ui.activity.MainActivity
import com.chris.poc.utils.logger.AndroidLogger
import com.chris.poc.utils.ration.Coffee
import com.chris.poc.utils.Kettle
import kotlinx.android.synthetic.main.fragment_main.*
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.subDI
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance

class MainFragment : Fragment(), DIAware {

    override val di: DI by subDI(closestDI()) {}

    // will be the same instance as the coffeeMaker in MainActivity
    val coffeeMaker: Kettle<Coffee> by instance()

    val log: AndroidLogger by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        log.log("onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()

        if (coffeeMaker != (requireActivity() as MainActivity).coffeeMaker) throw AssertionError()

        log.callback = {
            text.text = log.text
        }

        log.log("Comenzando a preparar café usando $coffeeMaker")

        Handler().postDelayed({
            coffeeMaker.brew()
        }, 3000)

        Handler().postDelayed({
            coffeeMaker.brew()
        }, 6000)
    }

    override fun onStop() {
        log.callback = null
        super.onStop()
    }
}
