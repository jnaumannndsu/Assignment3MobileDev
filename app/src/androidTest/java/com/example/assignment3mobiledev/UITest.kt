package com.example.assignment3mobiledev

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import androidx.test.uiautomator.textAsString
import androidx.test.uiautomator.uiAutomator
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UITest {

    private lateinit var mDevice: UiDevice

    @Before
    fun startAppFromHome() {
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        mDevice.pressHome()
        val launcherPackage = mDevice.launcherPackageName
        mDevice.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            3000
        )
        val appIcon = mDevice.wait(
            Until.findObject(By.text("Assignment3MobileDev")),
            5000
        )
        appIcon.click()

        mDevice.wait(
            Until.hasObject(By.pkg("com.example.assignment3mobiledev")),
            5000
        )
    }

    @Test
    fun testUI() {
        uiAutomator {
            onElement { textAsString() == "Start Activity Explicitly" }.click()

            val challengeText = device.wait(
                Until.findObject(By.desc("challenge_text")),
                5000
            )

            assert(challengeText != null)
        }
    }
}