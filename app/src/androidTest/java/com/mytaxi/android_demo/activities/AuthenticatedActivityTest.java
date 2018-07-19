package com.mytaxi.android_demo.activities;


import android.content.Intent;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;

import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.ComponentNameMatchers;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.matcher.UriMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.jakewharton.espresso.OkHttp3IdlingResource;
import com.mytaxi.android_demo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.matcher.IntentMatchers.anyIntent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static android.support.test.espresso.matcher.RootMatchers.isPlatformPopup;;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class AuthenticatedActivityTest {
    @Rule
    public IntentsTestRule<MainActivity> activityRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void searchDriver() {
        IdlingResource resource = OkHttp3IdlingResource.create("OkHttp", activityRule.getActivity().mHttpClient.getClient());

        onView(withId(R.id.edt_username)).perform(replaceText("crazydog335"));
        onView(withId(R.id.edt_password)).perform(replaceText("venture"));

        closeSoftKeyboard();

        onView(withId(R.id.btn_login)).perform(click());

        IdlingRegistry.getInstance().register(resource);

        ViewInteraction searchDriverEditText = onView(withId(R.id.textSearch));
        searchDriverEditText.check(matches(isDisplayed()));

        IdlingRegistry.getInstance().unregister(resource);

        searchDriverEditText.perform(typeText("sa"));

        closeSoftKeyboard();

        onView(withText("Sarah Scott"))
                .inRoot(isPlatformPopup())
                .perform(click());

        onView(allOf(withId(R.id.textViewDriverName), withText("Sarah Scott")))
                .check(matches(isDisplayed()));

        onView(withId(R.id.fab))
                .check(matches(isDisplayed()))
                .perform(click());

        Intents.intended(IntentMatchers.hasAction(Intent.ACTION_DIAL));

    }
}