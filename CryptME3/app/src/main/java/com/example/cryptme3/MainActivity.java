package com.example.cryptme3;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.ListActivity;


import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    final Context context = this;
    ListView lv;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Button btnAdd;
    EditText editText;
    TextView tv;
    Boolean clicked = false;
    int counter = 3;
    MySQLiteHelper db;


    //Views





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LayoutInflater li = LayoutInflater.from(context);
        final View promptsView = li.inflate(R.layout.prompts, null);
        final View displayView = li.inflate(R.layout.display, null);

        // get prompts.xml view

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //Context curr = getApplicationContext();
        //Toast.makeText(getApplicationContext(), "Debug", Toast.LENGTH_LONG);



        /**
         *
         *  Database stuff
         *
         */

       db = new MySQLiteHelper(this);
        Log.d("Debug: ", "Database Instatiated");
        //Passwords pass1 = new Passwords();
       // pass1.setId(2);
        //pass1.setName("Walmart");
        //pass1.setPassword("password");

        Log.d("Debug: ", "Before adding Passwords");
        //db.addPasswords(new Passwords(2,"Walmart", "password"));
       // Log.d("Debug: ", "After adding Passwords");
       // List<Passwords> pass2= db.getAllPasswords();
        /*for(Passwords pass:pass2){
            Log.d("Pass Test: ", pass.getName().toString());
            Log.d("Pass Test: ", Long.toString(pass.getID()));
            Log.d("Pass Test: ", pass.getPassword().toString());
        }*/
       // int test = db.getPasswordCount();
        //Log.d("Password Count ", Integer.toString(test));
        // This breaks it..... :(
       /* if(db.getPasswordCount() > 0){
            List<Passwords> passwords = db.getAllPasswords();
            for(Passwords pswd: passwords){
                list.add(pswd.toString());
            }
        }*/




        lv = (ListView)findViewById(R.id.listView);
        list = new ArrayList<String>();
        btnAdd = (Button)findViewById(R.id.btnAdd);
        // editText = (EditText)findViewById(R.id.editText); --> depricated
        tv = (TextView)findViewById(R.id.tv_test);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, list);



        //assign and adapter to the listView
        lv.setAdapter(adapter);
        //ListView onClickLister

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) lv.getItemAtPosition(position);

                // Show Alert
                 Toast.makeText(getApplicationContext(),
                     "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                      .show();


                //AlertDialog Example 1
                /*AlertDialog.Builder adb = new AlertDialog.Builder(
                        MainActivity.this);
                adb.setTitle("List");
                adb.setMessage(" selected Item is="
                        +parent.getItemAtPosition(position));
                adb.setPositiveButton("Ok", null);
                adb.show();
                */







            }

    });

        btnAdd = (Button) findViewById(R.id.btnAdd);

        // add button listener
        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.prompts);
                dialog.setTitle("Please fill out the information below");

                // set the custom dialog components - text, image and button
               final EditText name = (EditText) dialog.findViewById(R.id.et_name);
               //EditText type = (spinner) dialog.findViewById(R.id.spinner);
               final EditText password = (EditText) dialog.findViewById(R.id.et_password);
                final EditText cpassword = (EditText) dialog.findViewById(R.id.et_cpassword);
                //text.setText("Android custom dialog example!");
                //ImageView image = (ImageView) dialog.findViewById(R.id.image);
                //image.setImageResource(R.drawable.ic_launcher);
                Button addInfo = (Button) dialog.findViewById(R.id.btnAddInfo);
                // if button is clicked, close the custom dialog

                addInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       /* Toast.makeText(getApplicationContext(),"Name: " + name.getText().toString() + "\n" +
                                                                "Password :" + password.getText().toString() + "\n" +
                                                                 "Confirm Password: " + cpassword.getText().toString(),Toast.LENGTH_LONG).show();*/
                       // list.add(name.getText().toString());
                        list.add(name.getText().toString());
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();


                    }
                });
                /* Toast.makeText(getApplicationContext(),"Name: " + name.getText().toString() + "\n" +
                                                                "Password :" + password.getText().toString() + "\n" +
                                                                 "Confirm Password: " + cpassword.getText().toString(),Toast.LENGTH_LONG).show();*/
                dialog.show();
                //list.add(name.getText().toString());
                //adapter.notifyDataSetChanged();
            }
        });



                    //Button onClickListener When you add a password and place


                    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
