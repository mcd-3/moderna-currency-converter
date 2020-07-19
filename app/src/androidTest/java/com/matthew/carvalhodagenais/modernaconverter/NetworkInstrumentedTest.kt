package com.matthew.carvalhodagenais.modernaconverter

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.matthew.carvalhodagenais.modernaconverter.utils.NetworkConnectivityUtil

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented Tests to check if the network connectivity is working as intended
 */
@RunWith(AndroidJUnit4::class)
class NetworkInstrumentedTest {

    @Test
    fun isDeviceConnectedToNetwork() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val networkUtil = NetworkConnectivityUtil()
        assert(networkUtil.hasNetworkConnection(appContext))
        System.out.println("Network is connected")
    }
}