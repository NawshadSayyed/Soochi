package com.example.prathamesh.signinup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import io.realm.ObjectServerError;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncUser;
import name.User;


public class AllParties extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_parties);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_all_parties);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("All Parties");


   final String TAG = "AllParties";

        //TextView Phonenumber = (TextView) findViewById(R.id.textView11);

        Realm.init(AllParties.this);



        // Create the configuration
      /*  SyncUser user = SyncUser.current();
        String url = "http://127.0.0.1:9080/";
        SyncConfiguration config = user.createConfiguration(url).build();
        Realm realm = Realm.getInstance(config); */
// Open the remote Realm

// Any changes made to this Realm will be synced across all devices!


        RealmConfiguration config2 = new RealmConfiguration.Builder()
                .name("User")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm realm = Realm.getInstance(config2);

        Log.i(TAG, realm.getPath());

        // Run this on the device to find the path on the emulator

        // Create the configuration
        // Create the configuration

        // Create the configuration


// Open the remote Realm

// Any changes made to this Realm will be synced across all devices!
// Any changes made to this Realm will be synced across all devices!



       realm.executeTransaction(new Realm.Transaction() {
           @Override
           public void execute(Realm realm) {

               RealmResults<User> query_one = realm.where(name.User.class)
                                            .findAll();

            //   username.setMovementMethod(new ScrollingMovementMethod());


               ArrayList<User> list = new ArrayList(realm.where(User.class).findAll());
               ArrayList<String> list1 = new ArrayList(realm.where(User.class).findAll());

               RecyclerView username = (RecyclerView) findViewById(R.id.textView10);
               username.setHasFixedSize(true);

               LinearLayoutManager layoutManager = new LinearLayoutManager(AllParties.this);
               username.setLayoutManager(layoutManager);

               MyAdapter  mAdapter = new MyAdapter(list);

             username.setAdapter(mAdapter);


           }
       });


    }
    }
