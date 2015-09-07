package nz.co.jonker.skyrewards.data;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by jonker on 6/09/15.
 */
@Module
public class DataModule {
    private static final String ENDPOINT = "https://not_implemented";

    @Provides
    @Singleton
    Gson provideGson() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson;
    }

    @Provides @Singleton
    RestAdapter provideRestAdapter(Gson gson) {
        return new RestAdapter.Builder()
                .setClient(new OkClient())
                .setEndpoint(ENDPOINT)
                .setConverter(new GsonConverter(gson))
                .build();
    }
}
