package com.example.ting.access_server_test;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by wzm on 2016/5/12.
 */
public class SleepyAccountAuthenticatorActivity extends AccountAuthenticatorActivity {
    @Override
    protected void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        setContentView(R.layout.new_account);

        final Button done = (Button) findViewById(R.id.new_account_done);
        final EditText server = (EditText) findViewById(R.id.new_account_server);
        final EditText username = (EditText) findViewById(R.id.new_account_username);
        final EditText password = (EditText) findViewById(R.id.new_account_password);

        final Activity self = this;
        done.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {

                Account account = new Account(username.getText().toString(), getString(R.string.ACCOUNT_TYPE));

                Bundle userdata = new Bundle();
                userdata.putString("SERVER", server.getText().toString());

                AccountManager am = AccountManager.get(self);

                if (am.addAccountExplicitly(account, password.getText().toString(), userdata))
                {
                    Bundle result = new Bundle();
                    result.putString(AccountManager.KEY_ACCOUNT_NAME, username.getText().toString());
                    result.putString(AccountManager.KEY_ACCOUNT_TYPE, getString(R.string.ACCOUNT_TYPE));
                    setAccountAuthenticatorResult(result);
                }
                finish();
            }
        });
    }
}
