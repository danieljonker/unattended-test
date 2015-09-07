package nz.co.jonker.skyrewards;

import android.app.Application;

/**
 * Created by jonker on 6/09/15.
 */
public class App extends Application {
    public Graph component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerGraph.builder().baseModule(new BaseModule(this)).build();
        component.inject(this);

    }
}
