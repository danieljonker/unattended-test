package nz.co.jonker.skyrewards.data;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import nz.co.jonker.skyrewards.data.models.Account;
import nz.co.jonker.skyrewards.data.models.Eligibility;
import retrofit.Callback;
import retrofit.client.Header;
import retrofit.client.Response;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by datacom_mobile01 on 15/05/15.
 */
public class MockApiImpl implements ApiInterface {
    public static final String TAG = "MockApiImpl";
    private Context mContext;

    @Inject
    Gson gson;

    @Inject
    MockApiImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public void login(long accountNum, Callback<Account> cb) {

        String json = loadJSONFromAsset(mContext, String.valueOf(accountNum) + ".json");

        Log.d(TAG, "json: " + json);

        Account mockResponse = gson.fromJson(json, Account.class);

        if (mockResponse != null){
            cb.success(mockResponse, new Response("response", 200, "OK", new ArrayList<Header>(), null));
        } else {
            //in current version of retrofit can't mock the retrofit error
            cb.failure(null);
            Log.w(TAG, "failure");
        }
    }

    @Override
    public void checkEligibility(long id, String subscriptionName, Callback<Eligibility> cb) {
        String json = loadJSONFromAsset(mContext, String.valueOf(id) + "-" + subscriptionName.toLowerCase() + ".json");

        Log.d(TAG, "json: " + json);

        Eligibility mockResponse = gson.fromJson(json, Eligibility.class);

        if (mockResponse != null){
            cb.success(mockResponse, new Response("response", 200, "OK", new ArrayList<Header>(), null));
        } else {
            cb.failure(null);
            Log.w(TAG, "failure");
        }
    }

    /**
     * util method to load json from assets folder
      */
    public String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
