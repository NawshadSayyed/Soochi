package Extra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.prathamesh.Authentication.R;

import java.util.ArrayList;

import RealmClasses.DetailsItemPurchase1;
import RealmClasses.DetailsItemSale1;
import RealmClasses.SaleInput;
import RecyclerClass.Myadapter;
import Sale.Sale;
import io.realm.Realm;
import io.realm.RealmConfiguration;


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

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("SaleInput")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm realm = Realm.getInstance(config);



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
    // realm.delete(SaleInput.class);


            //   username.setMovementMethod(new ScrollingMovementMethod());


               ArrayList<SaleInput> list = new ArrayList(realm.where(SaleInput.class)
                       .findAll());


               RecyclerView username = (RecyclerView) findViewById(R.id.textView10);
               username.setHasFixedSize(true);

               LinearLayoutManager layoutManager = new LinearLayoutManager(AllParties.this);
               username.setLayoutManager(layoutManager);

               Myadapter mAdapter = new Myadapter(list);

             username.setAdapter(mAdapter);


           }
       });

        RealmConfiguration config4 = new RealmConfiguration.Builder()
                .name("DetailsItemPurchase1")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm realm_4 = Realm.getInstance(config4);
        realm_4.beginTransaction();
        realm_4.delete(DetailsItemPurchase1.class);
        realm_4.commitTransaction();


    }
    }
