package name;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfDocument;
import android.icu.util.Output;
import android.os.Bundle;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.prathamesh.signinup.AllParties;

import com.example.prathamesh.signinup.MyadapterBill;
import com.example.prathamesh.signinup.R;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static java.lang.System.out;

public class Cardname extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardname);
        String string = getIntent().getExtras().getString("Value");

        TextView view = (TextView) findViewById(R.id.text_View1);
        TextView view_1 = (TextView) findViewById(R.id.text_View2);
        TextView view_2 = (TextView) findViewById(R.id.text_View3);
        TextView view_3 = (TextView) findViewById(R.id.text_View4);
        TextView view_4 = (TextView) findViewById(R.id.text_View5);





        Realm.init(Cardname.this);

        RealmConfiguration config3 = new RealmConfiguration.Builder()
                .name("SaleInput")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm realm = Realm.getInstance(config3);
        realm.beginTransaction();

      /*  ArrayList<SaleInput> list = new ArrayList<>(realm.where(name.SaleInput.class).findAll());
      //  view_sample.setText(list.toString());
        if(list !=null && list.size()>=1) {
            view.setText(list.get(0).getName());
            view_1.setText(list.get(0).getDate());
            view_2.setText(Integer.toString(list.get(0).getTotal()));
            view_3.setText(Integer.toString(list.get(0).getReceived()));
            view_4.setText(Integer.toString(list.get(0).getBalance()));
        }
        realm.commitTransaction();
 */
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cardname);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(string);


        ArrayList<SaleInput> list = new ArrayList<>(realm.where(SaleInput.class)
                .equalTo("name", string)
                .findAll());

        RecyclerView username = (RecyclerView) findViewById(R.id.recyclerview1);
        username.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(Cardname.this);
        username.setLayoutManager(layoutManager);

        if(list.size()>=1) {
            MyadapterBill mAdapter = new MyadapterBill(list);
            username.setAdapter(mAdapter);
        }


        realm.commitTransaction();



    }
}
