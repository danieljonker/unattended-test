package nz.co.jonker.skyrewards;

import android.test.ActivityInstrumentationTestCase2;

import javax.inject.Inject;

import nz.co.jonker.skyrewards.data.ApiInterface;
import nz.co.jonker.skyrewards.ui.MainActivity;

public class InjectedBaseActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {
    @Inject
    ApiInterface api;

    public InjectedBaseActivityTest(Class activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        App app = (App) getInstrumentation().getTargetContext().getApplicationContext();
        app.component.inject(this);
    }

    @Override
    protected void tearDown() throws Exception {
    }
}
