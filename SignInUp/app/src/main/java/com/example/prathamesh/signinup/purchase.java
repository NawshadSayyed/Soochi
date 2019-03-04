package com.example.prathamesh.signinup;

import android.content.Context;
import android.content.DialogInterface;
import android.icu.util.Output;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import name.Dialog;
import name.Information;

public class purchase extends AppCompatActivity {

     Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase);


        final EditText party = (EditText) findViewById(R.id.party);

              final EditText party_1 = (EditText) findViewById(R.id.party_1);
              Button button = (Button) findViewById(R.id.button2);
              Button button_1 = (Button) findViewById(R.id.button3);

        TextView textview = (TextView) findViewById(R.id.textView);



        button.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {


                      Realm.init(purchase.this);    //initialize to access database for this activity

                      RealmConfiguration config2 = new RealmConfiguration.Builder()
                              .name("User")
                              .schemaVersion(1)
                              .deleteRealmIfMigrationNeeded()
                              .build();

                      Realm.getInstance(config2);//create a object for read and write database
                      realm.beginTransaction();

                      Information object = realm.createObject(Information.class);
                      object.setName(party_1.getText().toString());
                      Toast.makeText(purchase.this, "Value Added", Toast.LENGTH_SHORT).show();
                      party_1.getText().clear();

                      realm.commitTransaction(); //close the database


                  }
              });

              button_1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      Realm.init(purchase.this);    //initialize to access database for this activity
                      RealmConfiguration config2 = new RealmConfiguration.Builder()
                              .name("User")
                              .schemaVersion(1)
                              .deleteRealmIfMigrationNeeded()
                              .build();

                      Realm.getInstance(config2);

                      realm.beginTransaction();
                      RealmResults<Information> results = realm.where(Information.class).findAllAsync();
                      //fetching the data
                     /* results.load();
                      Log.e("results", "" + results.size()); */

                    /* for (Information information:results)
                     {
                         Toast.makeText(purchase.this, results.toString(), Toast.LENGTH_SHORT).show();

                     }
                     realm.commitTransaction(); */


                      textview.setText(results.toString());



                      textview.setMovementMethod(new ScrollingMovementMethod());
                      Dialog fragment = new Dialog();

                      FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                      transaction.replace(R.id.fragment, fragment).commit();

                      realm.commitTransaction(); //close the database


                      realm.close();
                  }
                     /* final AlertDialog.Builder builder = new AlertDialog.Builder(purchase.this);
                      builder.setTitle("Value");

                      // Get the layout inflater
                      final LayoutInflater inflater = purchase.this.getLayoutInflater();

                      // Inflate and set the layout for the dialog
                      // Pass null as the parent view because its going in the dialog layout
                      ViewGroup nullParent = null;

                              // Add action buttons
                      AlertDialog doneBuild = builder.create();
                      doneBuild.show();
                  } */
                  }
              );


        party.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    party.setHint("Party");
                else
                    party.setHint("");
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.dynamic_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.brew_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // Create an ArrayAdapter using the string array and a default spinner


        final boolean[] flag = {false};        // Here


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 1: {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(purchase.this);
                        builder.setTitle("  Enter the details");
                        // Get the layout inflater
                        final LayoutInflater inflater = purchase.this.getLayoutInflater();

                        // Inflate and set the layout for the dialog
                        // Pass null as the parent view because its going in the dialog layout
                        ViewGroup nullParent = null;
                        builder.setView(inflater.inflate(R.layout.dialog_party, nullParent))
                                // Add action buttons

                                .setPositiveButton(R.string.username, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(purchase.this, "Submitted", Toast.LENGTH_SHORT).show();
                                    }

                                })
                                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // Send the negative button event back to the host activity
                                        dialog.dismiss();
                                    }
                                });

                        AlertDialog doneBuild = builder.create();
                        doneBuild.show();
                        break;
                    }
                    case 2: {

                        Toast.makeText(purchase.this, "Nothing will happen", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }


}



   /*    switch(position) {
                   case 1: {
                       final AlertDialog.Builder builder = new AlertDialog.Builder(purchase.this);
                       builder.setTitle("  Enter the details");
                       // Get the layout inflater
                       final LayoutInflater inflater = purchase.this.getLayoutInflater();

                  pur     // Inflate and set the layout for the dialog
                       // Pass null as the parent view because its going in the dialog layout
                       ViewGroup nullParent = null;
                       builder.setView(inflater.inflate(R.layout.dialog_party, nullParent))
                               // Add action buttons
                               .setPositiveButton(R.string.username, new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int id) {
                                       Toast.makeText(purchase.this, "Submitted", Toast.LENGTH_SHORT).show();
                                   }

                               })
                               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                   public void onClick(DialogInterface dialog, int id) {
                                       // Send the negative button event back to the host activity
                                       dialog.dismiss();
                                   }
                               });
                       AlertDialog doneBuild = builder.create();
                       doneBuild.show();

                   }
                   case 2:
                   {

                       Toast.makeText(purchase.this, "Nothing will happen", Toast.LENGTH_SHORT).show();
                   }
                   }
               }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });







        }


    /*  Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);





        String[] items = new String[]{"Add a new party"};


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner

        dynamicSpinner.setAdapter(adapter);


        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        }); */

       /* final AutoCompleteTextView acTV1 = findViewById(R.id.ac);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, getResources()
                .getStringArray(R.array.party));

        final String[] selection = new String[1];
        acTV1.setAdapter(arrayAdapter);
        acTV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                acTV1.showDropDown();
                selection[0] = (String) parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), selection[0],
                        Toast.LENGTH_SHORT);

            }
        });

        acTV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View arg0) {
                acTV1.showDropDown();
            }
        }); */



