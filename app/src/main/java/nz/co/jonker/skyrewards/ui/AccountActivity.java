package nz.co.jonker.skyrewards.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import nz.co.jonker.skyrewards.App;
import nz.co.jonker.skyrewards.R;
import nz.co.jonker.skyrewards.data.ApiInterface;
import nz.co.jonker.skyrewards.data.models.Account;
import nz.co.jonker.skyrewards.ui.adapters.DividerItemDecoration;
import nz.co.jonker.skyrewards.ui.adapters.SubscriptionsAdapter;

public class AccountActivity extends AppCompatActivity {

    public static final String ARG_ACCOUNT= "account";

    @Inject ApiInterface api;

    App app;
    Account account;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.welcome_text) TextView welcomeText;
    @Bind(R.id.recycler) RecyclerView subsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        app = (App) getApplication();
        app.component.inject(this);

        account = getIntent().getParcelableExtra(ARG_ACCOUNT);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setTitle("Your Account");

        welcomeText.setText("Hi " + account.getAccountHolderName());

        subsRecycler.setLayoutManager(new LinearLayoutManager(this));
        subsRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        subsRecycler.setAdapter(new SubscriptionsAdapter(account, this));

    }


}
