package org.pythonistas.AutoConference;

import android.test.ActivityInstrumentationTestCase;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class org.pythonistas.autoconference.autoconferenceTest \
 * org.pythonistas.autoconference.tests/android.test.InstrumentationTestRunner
 */
public class AutoConferenceTest extends ActivityInstrumentationTestCase<autoconference> {

    public AutoConferenceTest() {
        super("org.pythonistas.AutoConference", AutoConference.class);
    }

}
