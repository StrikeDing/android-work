package com.example.ting.access_test;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button request;
    private StringBuilder builder = new StringBuilder();
    private Account[] accounts;
    private Account account;
    private TextView show;
    private Button login;
    private AccountManager manager;
    private Bundle result;
    private String g = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        request = (Button)findViewById(R.id.request);
        show = (TextView)findViewById(R.id.show);
        login = (Button)findViewById(R.id.login);
        request.setOnClickListener(this);
        login.setOnClickListener(this);
        manager = AccountManager.get(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.request:
                accounts = manager.getAccounts();
                for(Account i : accounts)
                {
                builder.append(i.toString()+"\n");
                }
                show.setText(builder.toString());break;

            case R.id.login:
                accounts = manager.getAccounts();
                for(Account i : accounts)
                {
                    if(i.type.equals("com.Account")){account = i;}
                }
                manager.getAuthToken(account, "Manage your tasks", null, this, new AccountManagerCallback<Bundle>() {
                    @Override
                    public void run(AccountManagerFuture<Bundle> future) {
                        try {
                            g = future.getResult().getString(AccountManager.KEY_AUTHTOKEN);
                            Log.d("aaaaaa", "null"+g+"null");
                            Log.d("##", "&&&&&&&&&&&&" + future.getResult().getString(AccountManager.KEY_AUTHTOKEN) + "&&&&&&&&&&&&&&&&&&");

                            //Message msg = new Message();
                          //  msg.what = 1;
                           // msg.obj = future.getResult().getString(AccountManager.KEY_AUTHTOKEN);
                           // handler.sendMessage(msg);
                        }
                        catch (Exception e) {
                            Log.e("hahaha", e.getMessage(), e);
                        }
                    }
                }, null);
                Log.d("aaaaaa", "null"+g+"null");


        }
    }
}
