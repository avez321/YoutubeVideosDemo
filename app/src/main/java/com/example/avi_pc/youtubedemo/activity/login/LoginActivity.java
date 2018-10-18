package com.example.avi_pc.youtubedemo.activity.login;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.example.avi_pc.youtubedemo.Constants;
import com.example.avi_pc.youtubedemo.R;
import com.example.avi_pc.youtubedemo.activity.home.HomeActivity;
import com.example.avi_pc.youtubedemo.base.BaseActivity;
import com.example.avi_pc.youtubedemo.database.UserTable;
import com.example.avi_pc.youtubedemo.databinding.ActivityLoginBinding;
import com.example.avi_pc.youtubedemo.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity implements LoginView {
    private ActivityLoginBinding activityLoginBinding;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        getActivityComponent().inject(this);
        loginPresenter.attachView(this);

        loginPresenter.checkUserAndsignIn();

        activityLoginBinding.googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.signIn();
            }
        });
    }

    @Override
    public void signIn(GoogleSignInClient googleSignInClient) {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, Constants.RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            loginToApp(account);
        } catch (ApiException e) {
            e.toString();
        }
    }

    private void loginToApp(GoogleSignInAccount account) {
        User user = new User(account.getDisplayName(), account.getPhotoUrl()!=null?account.getPhotoUrl().toString():"", account.getEmail());
        loginPresenter.saveUser(user);
        loginUsingUser(user);
    }

    @Override
    public void loginUsingUser(User user){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(Constants.USER, user);
        finish();
        startActivity(intent);
    }

    @Override
    public GoogleSignInClient getGoogleSignInClient(GoogleSignInOptions gso){
        return GoogleSignIn.getClient(this, gso);
    }

}
