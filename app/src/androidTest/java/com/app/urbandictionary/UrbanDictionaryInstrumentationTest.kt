package com.app.urbandictionary

import android.support.test.filters.LargeTest
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.view.KeyEvent
import android.view.inputmethod.BaseInputConnection
import android.widget.EditText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.action.ViewActions.click
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.app.urbandictionary.ui.UrbanDictionaryActivity


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class UrbanDictionaryInstrumentationTest {
    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<UrbanDictionaryActivity> = ActivityTestRule(
        UrbanDictionaryActivity::class.java
    )

    @Test
    @Throws(InterruptedException::class)
    fun testAppCompatSearchViewFromActionBar() {
        val editText: EditText = mActivityRule.getActivity().findViewById(R.id.search_src_text)
        onView(withId(R.id.mi_search))
            .perform(click())
        onView(withId(R.id.search_src_text))
            .perform(typeText("Hello World"))
        onView(withId(R.id.search_src_text))
            .perform(click())
        val inputConnection = BaseInputConnection(editText, true)
        inputConnection.sendKeyEvent(
            KeyEvent(
                KeyEvent.ACTION_DOWN,
                KeyEvent.KEYCODE_ENTER
            )
        )
        Thread.sleep(3000)
    }
}
