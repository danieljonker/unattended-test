package nz.co.jonker.skyrewards;

import javax.inject.Singleton;

import nz.co.jonker.skyrewards.data.DataModule;
import nz.co.jonker.skyrewards.data.DebugDataModule;
import nz.co.jonker.skyrewards.ui.AccountActivity;
import nz.co.jonker.skyrewards.ui.MainActivity;
import nz.co.jonker.skyrewards.ui.adapters.SubscriptionsAdapter;

/**
 * Created by jonker on 6/09/15.
 */
@Singleton
@dagger.Component(modules = {BaseModule.class, DataModule.class, DebugDataModule.class})
public interface Graph {

    void inject(App baseApp);
    void inject(MainActivity mainActivity);
    void inject(AccountActivity activity);
    void inject(SubscriptionsAdapter adapter);

    void inject(InjectedBaseActivityTest test);
}
