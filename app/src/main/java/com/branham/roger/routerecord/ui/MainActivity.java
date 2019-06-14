package com.branham.roger.routerecord.ui;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.branham.roger.routerecord.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    private static final String TAG = "MainActivity";

    public static final int RC_SIGN_IN = 1;

    //widgets
    private DrawerLayout drawer;

    //vars
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseUser mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Firebase Components
        mFirebaseAuth = FirebaseAuth.getInstance();


        //Set custom toolbar as the action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Hamburger code
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //For opening initial fragment on app start up only
        if(savedInstanceState == null){
            //Load initial fragment code here
        }


        // Choose authentication providers
        final List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //LocalUser is signed in
                    onSignedInInitialize(user);
                    Toast.makeText(MainActivity.this, "You're signed in", Toast.LENGTH_SHORT).show();
                } else {
                    //LocalUser is signed out
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN);

                }
            }
        };


    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onResume(){
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.nav_add_trip:
                //open add trip fragment
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddTripFragment()).commit();
                break;
            case R.id.nav_trip_history:
                //open trip history
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TripHistoryFragment()).commit();
                break;
            case R.id.nav_future_trips:
                //open future trips
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FutureTripsFragment()).commit();
                break;
            case R.id.nav_available_trips:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AvailableTripsFragment()).commit();
                break;
            case R.id.nav_stats:
                //open stats
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StatsFragment()).commit();
                break;
            case R.id.nav_add_future_trip: //TODO: Remove and have an admin only way to add future trips
                //open add Future Trip
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InsertFutureTripFragment()).commit();
                break;
            case R.id.nav_sign_out:
                onSignedOutCleanup();
                Toast.makeText(this, "Sign Out", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override //Override function so that app drawer is closed on first back button press
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void onSignedInInitialize(FirebaseUser user) {
        mUser = user;
    }

    private void onSignedOutCleanup(){
        mUser = null;
        //TODO: Clean up Fragments
        FirebaseAuth.getInstance().signOut();
    }


}
