/*
    ProfilePreferencesRequest gets the preferences from the user in firebase.
 */

package com.example.thymen.positivenews.Request;

import android.content.Context;

import com.example.thymen.positivenews.Object.Preferences;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class ProfilePreferencesRequest {
    public Callback activity;
    public Context context;
    private Preferences preferences;


    public interface Callback {
        void gotPreferences(Preferences preferences);
        void gotPreferencesError (String message);
    }

    public ProfilePreferencesRequest(Context context) {
        this.context = context;
    }

    public void getPreferences (final Callback activity) {
        this.activity = activity;
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userId = user.getUid();
        Query updatePreferenceScore =  databaseReference.child("users").child(userId).child("preferences");
        updatePreferenceScore.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                preferences = snapshot.getValue(Preferences.class);
                activity.gotPreferences(preferences);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                String message = databaseError.getMessage();
                activity.gotPreferencesError(message);
            }

        });
    }
}
