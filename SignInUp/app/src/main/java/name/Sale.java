package name;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.prathamesh.signinup.AllParties;
import com.example.prathamesh.signinup.MainActivity;
import com.example.prathamesh.signinup.Myadapter;
import com.example.prathamesh.signinup.MyadapterItemSale;
import com.example.prathamesh.signinup.R;
import com.example.prathamesh.signinup.purchase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;
import io.realm.internal.IOException;

public class Sale extends AppCompatActivity {

    int position;
    int position1;
    int key;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sale);


        // Edit_key for item

        try {
            position1 = getIntent().getExtras().getInt("edit_key");
        }catch(Exception e)
        {

        }
        HashMap<Integer, String> hashMap = new HashMap<>();



        Realm.init(Sale.this);
        RealmConfiguration config3 = new RealmConfiguration.Builder()
                .name("DetailsItemSale")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm realm_3 = Realm.getInstance(config3);


        String subtotal = "", tax_value = "", name = "", quantity ="", unit = "", taxx = "", rate = "";
        int value = 0;
      //To get the subtotal and tax value data from AddLineItem
       try {
            subtotal = getIntent().getExtras().getString("one");
            tax_value = getIntent().getExtras().getString("two");
            name = getIntent().getExtras().getString("three");
            quantity = getIntent().getExtras().getString("four");
            unit = getIntent().getExtras().getString("five");
            taxx = getIntent().getExtras().getString("six");
            rate = getIntent().getExtras().getString("seven");
            value = getIntent().getExtras().getInt("key");


            //Adding the items in Realm database
           realm_3.beginTransaction();
           DetailsItemSale detailsItemSale = realm_3.createObject(DetailsItemSale.class);
           detailsItemSale.setName(name);
           detailsItemSale.setSubtotal(subtotal);
           detailsItemSale.setTaxvalue(tax_value);
           detailsItemSale.setQuantity(quantity);
           detailsItemSale.setUnit(unit);
           detailsItemSale.setTaxx(taxx);
           detailsItemSale.setRate(rate);
           //adding it's position

           detailsItemSale.setPosition(position);

           realm_3.commitTransaction();
       }catch(Exception e)
       {
           value = 0;
       }

       if(value == 1) {
           // Adapter for showing the item is called with the help of recycler view!
         ArrayList<DetailsItemSale>  list_1 = new ArrayList<DetailsItemSale>(realm_3.where(DetailsItemSale.class).findAll());
           RecyclerView username = (RecyclerView) findViewById(R.id.recyclerview_item);
           username.setHasFixedSize(true);

           LinearLayoutManager layoutManager = new LinearLayoutManager(Sale.this);
           username.setLayoutManager(layoutManager);
          /* try {
               hashMap.put(position1, String.valueOf(position1));
           }catch(Exception e)*/


              hashMap.put(key, String.valueOf(position));


           //Log.d("Yesss", "" + hashMap.get(key));
           MyadapterItemSale mAdapter = new MyadapterItemSale(list_1, hashMap);
           Log.d("noo", "" + String.valueOf(key) + " " + String.valueOf(position));
           username.setAdapter(mAdapter);
       }
        // Ended



        final Calendar myCalendar = Calendar.getInstance();

        EditText edittext = (EditText) findViewById(R.id.date);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };


        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Sale.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sale);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sale");

        // Calling Add Item Activity
        Button button_add_item = (Button) findViewById(R.id.addItem);
        button_add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sale.this, AddLineItem.class));
            }
        });


        //Spinner code
        final String[] type = new String[]{"Cash", "Cheque"};
        AutoCompleteTextView autoCompleteTextView1 = (AutoCompleteTextView) findViewById(R.id.textView7);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, type);
       autoCompleteTextView1.setAdapter(adapter1);





        RealmConfiguration config2 = new RealmConfiguration.Builder()
                .name("User")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm realm = Realm.getInstance(config2);
        Log.d("Realm", realm.getPath());
       /* SyncUser user = SyncUser.current();
        String url = "http://127.0.0.1:9080/";
        SyncConfiguration config = user.createConfiguration(url).build();*/

        //autocompleteTextView

        realm.beginTransaction();
        ArrayList<User> list = new ArrayList<User>(realm.where(User.class).findAll());
        ArrayList<String> array = new ArrayList<>(list.size()+1);
        array.add(0, "Add a new Party");
        for (int i = 1;i<=list.size();i++)
        {
            array.add(i, list.get(i-1).getUsername());
        }
        final String[] Customers = array.toArray(new String[array.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Customers);
        AutoCompleteTextView auto = (AutoCompleteTextView) findViewById(R.id.spinne);
        realm.commitTransaction();

        auto.setAdapter(adapter);

        auto.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                auto.showDropDown();
                return false;
            }
        });

        auto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    auto.getId(); // Instead of your Toast
            }
        });


        auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String buffer_position = (String) parent.getItemAtPosition(position);
                int real_position = Arrays.asList(Customers).indexOf(buffer_position);
                switch (real_position) {

                    case 0: {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(Sale.this);
                        final LayoutInflater inflater = Sale.this.getLayoutInflater();

                        ViewGroup nullParent = null;


                        // Add action buttons


                        View custom_dialog = inflater.inflate(R.layout.dialog_party, nullParent);

                        EditText username = (EditText) custom_dialog.findViewById(R.id.username);
                        EditText phone = (EditText) custom_dialog.findViewById(R.id.phone);

                        builder.setPositiveButton(R.string.username, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {

                                //Nothing for yet since this part is not working.
                            }

                        })
                                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // Send the negative button event back to the host activity
                                        dialog.dismiss();
                                    }
                                });

                        builder.setView(custom_dialog);
                        AlertDialog doneBuild = builder.create();
                        doneBuild.show();
                        doneBuild.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // write check code
                                if (TextUtils.isEmpty(username.getText().toString())) {
                                    Toast.makeText(Sale.this, "Name should not be empty", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                if (TextUtils.isEmpty(phone.getText().toString())) {
                                    Toast.makeText(Sale.this, "Phone number should not be empty", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                String username_string = username.getText().toString();
                                String phone_string = phone.getText().toString();
                                int phone_integer = Integer.parseInt(phone_string);
                                realm.beginTransaction();

                                User user = realm.createObject(User.class);
                                user.setUsername(username_string);
                                user.setPhonenumber(phone_integer);
                                realm.commitTransaction();
                                Toast.makeText(Sale.this, "Submitted", Toast.LENGTH_SHORT).show();

// if every thing is Ok then dismiss dialog
                                doneBuild.dismiss();


                            }
                        });
                    }


                }

            }
        });

        //(Save and save and new)Button code
        Button save = (Button) findViewById(R.id.save);
        Button savenew = (Button) findViewById(R.id.savenew);


        EditText editText1 = (EditText) findViewById(R.id.invoice);
        EditText editText2 = (EditText) findViewById(R.id.date);
        EditText editText3 = (EditText) findViewById(R.id.supplyplace);
        EditText editText4 = (EditText) findViewById(R.id.editText);
        EditText editText5 = (EditText) findViewById(R.id.editText2);
        EditText editText6 = (EditText) findViewById(R.id.editText3);

        editText5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.length() != 0)
                {
                     int first =  Integer.parseInt(editText4.getText().toString());
                    int second =  Integer.parseInt(editText5.getText().toString());

                    if(first-second>=0)
                    editText6.setText(Integer.toString(first-second));


                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });


        save.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (TextUtils.isEmpty(editText1.getText().toString())) {
                                            Toast.makeText(Sale.this, "Invoice should not be empty", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (TextUtils.isEmpty(editText2.getText().toString())) {
                                            Toast.makeText(Sale.this, "Date should not be empty", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (TextUtils.isEmpty(editText3.getText().toString())) {
                                            Toast.makeText(Sale.this, "Place of supply should not be empty", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (TextUtils.isEmpty(editText4.getText().toString())) {
                                            Toast.makeText(Sale.this, "Total amount should not be empty", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (TextUtils.isEmpty(editText5.getText().toString())) {
                                            Toast.makeText(Sale.this, "Amount received should not be empty", Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        if (TextUtils.isEmpty(auto.getText().toString())) {
                                            Toast.makeText(Sale.this, "Customer name should not be empty", Toast.LENGTH_SHORT).show();
                                            return;
                                        } else {

                                            Toast.makeText(Sale.this, "Submitted", Toast.LENGTH_SHORT).show();

                                            RealmConfiguration config3 = new RealmConfiguration.Builder()
                                                    .name("SaleInput")
                                                    .schemaVersion(1)
                                                    .deleteRealmIfMigrationNeeded()
                                                    .build();

                                            Realm realm_1 = Realm.getInstance(config3);
                                            realm_1.beginTransaction();
                                            SaleInput saleInput = realm_1.createObject(SaleInput.class);
                                            saleInput.setName(auto.getText().toString());
                                            saleInput.setPlace(editText3.getText().toString());
                                            saleInput.setInvoice(Integer.parseInt(editText1.getText().toString()));
                                            saleInput.setDate(editText2.getText().toString());
                                            saleInput.setTotal(Integer.parseInt(editText4.getText().toString()));
                                            saleInput.setReceived(Integer.parseInt(editText5.getText().toString()));
                                            saleInput.setBalance(Integer.parseInt(editText6.getText().toString()));

                                            realm_1.commitTransaction();

                                            // Deleting the item Selected list

                                            realm_3.beginTransaction();
                                            realm_3.delete(DetailsItemSale.class);
                                            realm_3.commitTransaction();

                                            Intent intent1 = new Intent(Sale.this, MainActivity.class);
                                            startActivity(intent1);

                                        }
                                    }
                                });

                savenew.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(editText1.getText().toString())) {
                            Toast.makeText(Sale.this, "Invoice should not be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(editText2.getText().toString())) {
                            Toast.makeText(Sale.this, "Date should not be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(editText3.getText().toString())) {
                            Toast.makeText(Sale.this, "Place of supply should not be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(editText4.getText().toString())) {
                            Toast.makeText(Sale.this, "Total amount should not be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(editText5.getText().toString())) {
                            Toast.makeText(Sale.this, "Amount received should not be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (TextUtils.isEmpty(auto.getText().toString())) {
                            Toast.makeText(Sale.this, "Customer name should not be empty", Toast.LENGTH_SHORT).show();
                            return;
                        } else {

                            Toast.makeText(Sale.this, "Submitted", Toast.LENGTH_SHORT).show();

                            //Realm code for Sale details
                            RealmConfiguration config3 = new RealmConfiguration.Builder()
                                    .name("SaleInput")
                                    .schemaVersion(1)
                                    .deleteRealmIfMigrationNeeded()
                                    .build();

                            Realm realm_1 = Realm.getInstance(config3);
                            realm_1.beginTransaction();
                            SaleInput saleInput = realm_1.createObject(SaleInput.class);
                            saleInput.setName(auto.getText().toString());
                            saleInput.setPlace(editText3.getText().toString());
                            saleInput.setInvoice(Integer.parseInt(editText1.getText().toString()));
                            saleInput.setDate(editText2.getText().toString());
                            saleInput.setTotal(Integer.parseInt(editText4.getText().toString()));
                            saleInput.setReceived(Integer.parseInt(editText5.getText().toString()));
                            saleInput.setBalance(Integer.parseInt(editText6.getText().toString()));

                            realm.commitTransaction();

                            Intent intent2 = new Intent(Sale.this, Sale.class);
                            startActivity(intent2);
                        }
                    }



        });


    }

    //To delete the database on back button
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            RealmConfiguration config3 = new RealmConfiguration.Builder()
                    .name("DetailsItemSale")
                    .schemaVersion(1)
                    .deleteRealmIfMigrationNeeded()
                    .build();
            Realm realm_3 = Realm.getInstance(config3);

            realm_3.beginTransaction();
            realm_3.delete(DetailsItemSale.class);
            realm_3.commitTransaction();
            // finish the activity
            finish();


        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onResume(){
        super.onResume();

        key = key + 1;
        position = position + 1;
        Log.d("noo", "" + String.valueOf(key) + " " + String.valueOf(position));


    }
}








