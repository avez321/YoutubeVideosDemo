package com.example.avi_pc.youtubedemo.activity.login;


import com.example.avi_pc.youtubedemo.base.BasePresenter;
import com.example.avi_pc.youtubedemo.database.UserTable;
import com.example.avi_pc.youtubedemo.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginView> {
    @Inject
     UserTable userTable;

    @Inject
    LoginPresenter() {
    }


    public void signIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        GoogleSignInClient googleSignInClient = getMvpView().getGoogleSignInClient(gso);
        getMvpView().signIn(googleSignInClient);

    }

    public void checkUserAndsignIn() {
        User user = userTable.getUser();
        if (user != null) {
            getMvpView().loginUsingUser(user);
        }
    }

    public void saveUser(User user) {
        userTable.createOrUpdateUser(user);
    }
}
