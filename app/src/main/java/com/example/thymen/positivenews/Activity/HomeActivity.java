/*
    HomeActivity contains the fragments: ProfileFragment, HomeFragment and SourceFragment
 */

package com.example.thymen.positivenews.Activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.thymen.positivenews.Fragment.HomeFragment;
import com.example.thymen.positivenews.Fragment.ProfileFragment;
import com.example.thymen.positivenews.R;
import com.example.thymen.positivenews.Fragment.SourcesFragment;

public class HomeActivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = findViewById(R.id.navigationView);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new ProfileFragment());
    }

    private Boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {

            case R.id.navigation_personal:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_profile:
                fragment = new ProfileFragment();
                break;
            case R.id.navigation_trending:
                fragment = new SourcesFragment();
                break;
        }
        return loadFragment(fragment);
    }
}
