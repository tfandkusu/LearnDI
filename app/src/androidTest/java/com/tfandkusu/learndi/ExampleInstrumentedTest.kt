package com.tfandkusu.learndi

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented testLoad, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under testLoad.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.tfandkusu.learndi", appContext.packageName)
    }
}
