package com.example.avi_pc.youtubedemo.activity.login;

import com.example.avi_pc.youtubedemo.base.MvpView;
import com.example.avi_pc.youtubedemo.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public interface LoginView extends MvpView {
    GoogleSignInClient getGoogleSignInClient(GoogleSignInOptions gso);

    void signIn(GoogleSignInClient googleSignInClient);

    void loginUsingUser(User user);
}
