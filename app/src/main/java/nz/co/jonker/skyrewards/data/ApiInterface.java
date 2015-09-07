package nz.co.jonker.skyrewards.data;

import nz.co.jonker.skyrewards.data.models.Account;
import nz.co.jonker.skyrewards.data.models.Eligibility;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by jonker on 6/09/15.
 */
public interface ApiInterface {

    @POST("/login")
    void login(@Query("account_num") long accountNum, Callback<Account> cb);

    /*
        This is not part of the above query because the diagram in the spec implies that you're wanting
        a second request.
     */
    @GET("/{id}/eligibility")
    void checkEligibility(@Path("id") long id, @Query("subscription") String subscriptionName, Callback<Eligibility> cb);
}
