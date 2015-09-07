package nz.co.jonker.skyrewards.ui;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import nz.co.jonker.skyrewards.App;
import nz.co.jonker.skyrewards.R;
import nz.co.jonker.skyrewards.data.ApiInterface;
import nz.co.jonker.skyrewards.data.models.Account;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    @Inject ApiInterface api;

    App app;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.login_button) Button button;
    @Bind(R.id.account_number_input) TextInputLayout accountNumLayout;
    @Bind(R.id.account_num) EditText accountNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        app = (App) getApplication();
        app.component.inject(this);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountNumLayout.setError(null);
                accountNum.setEnabled(false);
                button.setEnabled(false);

                if (accountNum.getText().toString().trim().isEmpty()){
                    accountNumLayout.setError("Need an account number");
                    return;
                } else if (accountNum.getText().toString().trim().length() != 6) { //assuming the account number would have some constraints around the length
                    accountNumLayout.setError(getString(R.string.account_num_wrong_length));
                }

                api.login(Long.parseLong(accountNum.getText().toString()), new Callback<Account>() {
                    @Override
                    public void success(Account account, Response response) {
                        //success! - move to next screen
                        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
                        intent.putExtra(AccountActivity.ARG_ACCOUNT, account);
                        startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        //failure :(
                        accountNumLayout.setError("Incorrect account number");
                        accountNum.setEnabled(true);
                        button.setEnabled(true);
                    }
                });

            }
        });


    }
}
