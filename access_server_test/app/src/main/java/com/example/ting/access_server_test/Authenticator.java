package com.example.ting.access_server_test;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.AccountManager;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Ting on 2016/5/12.
 */
public class Authenticator extends AbstractAccountAuthenticator {
    private AccountManager manager;
    private Context i;
    public Authenticator(Context context){
        super(context);
        i = context;
        manager = AccountManager.get(context);

    }
    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse response, Account account, Bundle options) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse response, String accountType, String authTokenType, String[] requiredFeatures, Bundle options) throws NetworkErrorException {
      /*  final Intent intent = new Intent(mContext, AuthenticatorActivity.class);
        intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, accountType);
        intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, authTokenType);
        intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;*/
        //        Account mAccount = new Account("wzm", "com.wzm");
//        AccountManager accountManager = AccountManager.get(mContext);
//        accountManager.addAccountExplicitly(mAccount, "654321", null);
//        accountManager.setAuthToken(mAccount, "Manager your tasks", "654321");
        Bundle ret = new Bundle();

        Intent intent = new Intent(i, SleepyAccountAuthenticatorActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);

        ret.putParcelable(AccountManager.KEY_INTENT, intent);
        return ret;
//        return null;

    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse response, String accountType) {
        return null;

    }

    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
       /* String token = manager.peekAuthToken(account,authTokenType);
        if(!TextUtils.isEmpty(token)){
            token = "is_not_empty";
            Bundle result_1 = new Bundle();
            result_1.putString(AccountManager.KEY_AUTHTOKEN,token);
            return result_1;
        }
        Bundle result = new Bundle();
        result.putString(AccountManager.KEY_AUTHTOKEN,token);
        return result;*/
        Log.e("abc", ".getAuthToken..........");
        AccountManager am = AccountManager.get(i);
       // String authToken = am.peekAuthToken(account, authTokenType);
        String authToken = "";
        if (TextUtils.isEmpty(authToken)) {
            final String password = am.getPassword(account);
            Log.e("haha", "authToken is empty");
            if (password != null) {
                authToken = "123456789";
                Log.e("haha", "haha");
            }
        }

        // If we get an authToken - we return it
        if (!TextUtils.isEmpty(authToken)) {
            Log.e("haha", "@@@@@@@@@@@@@@@@@@@@");
            final Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            result.putString(AccountManager.KEY_ACCOUNT_TYPE, account.type);
            result.putString(AccountManager.KEY_AUTHTOKEN, authToken);
            return result;
        }

        final Intent intent = new Intent(i, SleepyAccountAuthenticatorActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
        intent.putExtra("com.Account", account.type);
        intent.putExtra(null, authTokenType);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    @Override
    public String getAuthTokenLabel(String authTokenType) {
        return null;
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse response, Account account, String[] features) throws NetworkErrorException {
        return null;
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse response, Account account, String authTokenType, Bundle options) throws NetworkErrorException {
        return null;
    }
}
