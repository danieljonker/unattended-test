package nz.co.jonker.skyrewards.ui.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import nz.co.jonker.skyrewards.App;
import nz.co.jonker.skyrewards.R;
import nz.co.jonker.skyrewards.data.ApiInterface;
import nz.co.jonker.skyrewards.data.models.Account;
import nz.co.jonker.skyrewards.data.models.Eligibility;
import nz.co.jonker.skyrewards.data.models.Subscription;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by jonker on 7/09/15.
 */
public class SubscriptionsAdapter extends RecyclerView.Adapter<SubscriptionsAdapter.ViewHolder>{
    private static final String TAG = "SubscriptionsAdapter";

    @Inject ApiInterface api;

    Account account;
    Context context;

    public SubscriptionsAdapter(Account data, Context context) {
        this.account = data;
        this.context = context;

        ((App) context.getApplicationContext()).component.inject(this);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.sub_name) TextView subscriptionName;
        @Bind(R.id.reward_waiting) TextView rewardWaiting;
        @Bind(R.id.list_item_layout) LinearLayout listItemLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public int getItemCount() {
        return account.getSubscriptionList().size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Subscription sub = account.getSubscriptionList().get(position);

        holder.subscriptionName.setText(sub.getName());

        if (sub.getReward() != null) { //this channel has reward, check eligibility
            api.checkEligibility(account.getAccountNumber(), sub.getName(), new Callback<Eligibility>() {
                @Override
                public void success(Eligibility eligibility, Response response) {
                    holder.rewardWaiting.setVisibility(View.VISIBLE);
                    holder.listItemLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                            alertDialogBuilder.setTitle("Your Reward");
                            alertDialogBuilder.setMessage(sub.getReward()).setCancelable(false);
                            AlertDialog alertDialog = alertDialogBuilder.create();
                            alertDialog.setCanceledOnTouchOutside(true);
                            alertDialog.show();
                        }
                    });
                }

                @Override
                public void failure(RetrofitError error) {
                    //no rewards :( - display how many more points to go?
                }
            });

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.list_item_subscription, parent, false);
        return new ViewHolder(v);
    }
}