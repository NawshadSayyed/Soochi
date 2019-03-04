package com.example.prathamesh.signinup;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import name.Item;
import name.User;

public class ItemList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Item List");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ItemList.this, Item.class);
                startActivity(intent);
            }
        });
        Realm.init(ItemList.this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("ItemList")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm realm = Realm.getInstance(config);

      /*  realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {*/
        ArrayList<name.ItemList> list = new ArrayList(realm.where(name.ItemList.class).findAll());
        RecyclerView username = (RecyclerView) findViewById(R.id.recyclerview);
        username.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(ItemList.this);
        username.setLayoutManager(layoutManager);

        MyAdapteritem mAdapter = new MyAdapteritem(list);

        username.setAdapter(mAdapter);
    }


    }




