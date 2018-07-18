package com.mytaxi.android_demo.activities;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;

import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.mytaxi.android_demo.R;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class AuthenticationActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);



    @Before
    public void verifySession(){
        if(activityRule.getActivity().mSharedPrefStorage.loadUser()!=null)
            activityRule.getActivity().mSharedPrefStorage.resetUser();
    }

    @Test
    public void testLoginSuccess() {

        IdlingResource resource = OkHttp3IdlingResource.create("OkHttp", activityRule.getActivity().mHttpClient.getClient());


        onView(withId(R.id.btn_login))
                .check(matches(isDisplayed()));

        onView(withId(R.id.edt_username))
                .perform(clearText())
                .perform(typeText("crazydog335"));

        onView(withId(R.id.edt_password))
                .perform(clearText())
                .perform(typeText("venture"));

        closeSoftKeyboard();


        onView(withId(R.id.btn_login))
                .perform(click());
        IdlingRegistry.getInstance().register(resource);

        onView(withId(R.id.textSearch))
                .check(matches(isDisplayed()));

        IdlingRegistry.getInstance().unregister(resource);
    }
}