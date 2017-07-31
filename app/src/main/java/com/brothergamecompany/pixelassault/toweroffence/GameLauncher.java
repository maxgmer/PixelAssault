package com.brothergamecompany.pixelassault.toweroffence;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.Toast;

import com.brothergamecompany.pixelassault.R;
import com.brothergamecompany.pixelassault.framework.Game;
import com.brothergamecompany.pixelassault.framework.Screen;
import com.brothergamecompany.pixelassault.framework.impl.GLGame;
import com.brothergamecompany.pixelassault.toweroffence.GameObjects.MapBuilder;
import com.brothergamecompany.pixelassault.toweroffence.GameScreens.GameScreen;
import com.brothergamecompany.pixelassault.toweroffence.Other.Assets.Assets;
import com.brothergamecompany.pixelassault.toweroffence.Other.GameGestureDetector;
import com.brothergamecompany.pixelassault.toweroffence.Other.Network.SignIn;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.app.Activity.RESULT_OK;

/**
 * Created by maxgm_umv4xdu on 04.06.2017.
 */

public class GameLauncher extends GLGame{
    private boolean firstTimeCreate = true;
    private ScaleGestureDetector mScaleDetector;
    private GestureDetectorCompat mGestureDetector;
    private SignIn signIn;

    @Override
    public Screen getStartScreen() {
        return new GameScreen(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        super.onSurfaceCreated(gl, config);
        if (firstTimeCreate) {
            Assets.load(this);
            firstTimeCreate = false;
        }
        else {
            Assets.reload();
        }

    }

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
        signIn = new SignIn(this, b);
        GameGestureDetector gestureDetector = new GameGestureDetector(getGLGraphics());
        mGestureDetector = new GestureDetectorCompat(this, gestureDetector.simpleOnGestureListener);
        mScaleDetector = new ScaleGestureDetector(this, gestureDetector.mScaleGestureListener);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        if (MapBuilder.cellChosen == null) {
            mScaleDetector.onTouchEvent(event);
            mGestureDetector.onTouchEvent(event);
        }
        return false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        signIn.mGoogleApiClient.disconnect();
    }

    @Override
    public void onStart(){
        super.onStart();
        signIn.mGoogleApiClient.connect();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        signIn.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        signIn.onSaveInstanceState(outState);
    }
}

