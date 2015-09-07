package nz.co.jonker.skyrewards;

import nz.co.jonker.skyrewards.ui.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest extends InjectedBaseActivityTest {
    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();

        getActivity();
    }

    public void testLoginFailure() {
        onView(withId(R.id.account_num)).perform(typeText("12345"));
        onView(withId(R.id.login_button)).perform(click(longClick()));

        onView(withText(getActivity().getString(R.string.account_num_wrong_length)))
                .check(matches(isDisplayed()));
    }

}
