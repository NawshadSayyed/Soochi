package com.example.prathamesh.signinup;



import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import name.Dialog;
import name.DialogOne;
import name.Item;
import name.Sale;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView = (TextView) findViewById(R.id.textView16);

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();
         TextView TextView;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.settings:
                        Intent intent = new Intent(MainActivity.this, purchase.class);
                        Toast.makeText(MainActivity.this, "Purchase Details", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                    case R.id.accounts:
                        Intent intent_1 = new Intent(MainActivity.this, signin.class);
                        intent_1.putExtra("key", 2);
                        Toast.makeText(MainActivity.this, "Sign-In", Toast.LENGTH_SHORT).show();
                        startActivity(intent_1);
                }
                return true;

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);


// Remove default title text

// Get access to the custom title view


        Button button11 = (Button) findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Sale.class);
                startActivity(intent);
            }
        });

        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllParties.class);
                startActivity(intent);
            }
        });

        Button button10 = (Button) findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ItemList.class);
                startActivity(intent);
            }
        });

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        View custom_dialog = inflater.inflate(R.layout.dialogone, null);
       float scale = getBaseContext().getResources().getDisplayMetrics().density;

        ViewGroup.LayoutParams params = custom_dialog.getLayoutParams();
        if (params == null) {

           params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.height = (int) Math.round(100 * scale);
            params.width = (int) Math.round(100 * scale);
        }
        else {

        }
        builder.setView(custom_dialog);
        AlertDialog doneBuild = builder.create();
        doneBuild.setCanceledOnTouchOutside(false);

        final boolean[] phase = {false};
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(phase[0] == false) {
                    Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    final OvershootInterpolator interpolator = new OvershootInterpolator();
                    ViewCompat.animate(fab).
                            rotation(135f).
                            withLayer().
                            setDuration(200).
                            setInterpolator(interpolator).
                            start();
                    phase[0] = true;


                    doneBuild.show();
                }
                else
                {
                    final OvershootInterpolator interpolator = new OvershootInterpolator();
                    ViewCompat.animate(fab).
                            rotation(000f).
                            withLayer().
                            setDuration(200).
                            setInterpolator(interpolator).
                            start();
                    phase[0] = false;
                    doneBuild.hide();

                }


            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


}

