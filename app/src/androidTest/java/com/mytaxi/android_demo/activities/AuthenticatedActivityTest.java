package com.mytaxi.android_demo.activities;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class AuthenticatedActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Test
    public void testStarts() {
        //Intent intent = new Intent()
        try{
            Thread.sleep(5000);
        }catch (Exception e){

        }
    }

}