package com.example.cryptme3;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.opengl.GLException;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.ListActivity;


import org.w3c.dom.Comment;

import com.example.cryptme3.AES;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    final Context context = this;
    ListView lv;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Button btnAdd,btnDel,btnDelAll;
    EditText editText,edDelID;
    TextView tv;
    Boolean clicked = false;
    int counter = 3;
    final String pass4KeyGen = "password"; /// for debugging purposes only. password should be much more secure than this.
    String encPass, encStr, decStr;

    public SwipeRefreshLayout swipeRefresh;




    //Views





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LayoutInflater li = LayoutInflater.from(context);
        final View promptsView = li.inflate(R.layout.prompts, null);
        final View displayView = li.inflate(R.layout.display, null);




        /**
         *
         *  Database stuff: Testing
         *
         *  after commented out :
         *
         *  " cursor.close();" in getPasword Count "
         *
         *  THe error message seemed to go away: see "MySQLHelper.java -> method: getPasswordCount for more details
         *
         */
        /*******************************************************
         *
         *      BEGIN DATABASE TESTING / DEBUGGING
         *
         *      SUCCESSFULLY
         *
         *****************************************************/

      // db = new MySQLiteHelper(this);
       // Log.d("Debug: ", "Database Instatiated");
        //Passwords pass1 = new Passwords();
       // pass1.setId(2);
        //pass1.setName("Walmart");
        //pass1.setPassword("password");

       // Log.d("Debug: ", "Before adding Passwords");
        //db.addPasswords(new Passwords(2,"Walmart", "password"));
       // Log.d("Debug: ", "After adding Passwords");
       // List<Passwords> pass2= db.getAllPasswords();
        /*for(Passwords pass:pass2){
            Log.d("Pass Test: ", pass.getName().toString());
            Log.d("Pass Test: ", Long.toString(pass.getID()));
            Log.d("Pass Test: ", pass.getPassword().toString());
        }*/
       // int test = db.getPasswordCount();
       // Log.d("Password Count ", Integer.toString(test));
        // This breaks it..... :(
       /* if(db.getPasswordCount() > 0){
            List<Passwords> passwords = db.getAllPasswords();
            for(Passwords pswd: passwords){
                list.add(pswd.toString());
            }
        }*/
       // Log.d("Debug: ", " after Passwords");

      //  db.close();
      // Log.d("Debug: ", "Database closed");


        /*******************************************************
         *
         *      END DATABASE TESTING / DEBUGING
         *
         *****************************************************/

        /*******************************************************
         *
         *      BEGIN DATABASE  TEST INSERTION / QUERRY
         *      SUCCESSFULLY
         *****************************************************/

        //db1 = new MySQLiteHelper(this);
       // Log.d("Debug: ", "Before adding Passwords");
       // db1.addPasswords(new Passwords(1,"Walmart", "password"));
        //Passwords password = new Passwords();
        //password.setId(1);
      //  db1.deletePassword(password);
      //  int test1 = db1.getPasswordCount();
      //  Log.d("Password Count ", Integer.toString(test1));

       /**if(db1.getPasswordCount() > 0){
            List<Passwords> passwords = db1.getAllPasswords();
            for(Passwords pswd: passwords){
                Log.d("Passwords: ", pswd.toString());
            }
        }*/
        //Log.d("Querry: Password 1::  ", db1.getPasword(1).toString());
        //Log.d("Debug: ", " after Passwords");



       // db1.close();
      //  Log.d("Debug: ", "Database closed");

        /*******************************************************
         *
         *      END DATABASE  TEST INSERTION / QUERRY
         *      SUCCESSFULLY
         *****************************************************/

        /*******************************************************
         *
         *
         *      END DATABASE STUFF
         *****************************************************/



        /**
         *
         *  Begin ListView , Alert Dialog and Add Button
         *
         *
         */


        lv = (ListView)findViewById(R.id.listView);
        list = new ArrayList<String>();
        /*******************************************
         *  List index from 0 to n-1
         *  I am using the position of the list as key
         *  so rather than starting with key 0,
         *  will add a blank element to the list to fix issue
         * ******************************************/
        //insert blank entry
        list.add(" ");
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnAdd.bringToFront();
        btnDel = (Button)findViewById(R.id.btnDel);
        btnDelAll = (Button)findViewById(R.id.btnDELALL);
        edDelID = (EditText)findViewById(R.id.edDelID);
        // editText = (EditText)findViewById(R.id.editText); --> depricated
        //tv = (TextView)findViewById(R.id.tv_test);
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, android.R.id.text1, list);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);



        //assign and adapter to the listView
        lv.setAdapter(adapter);
        //ListView onClickLister

        /***********************************
         *Delete the db contents if needed
         * ** FOR DEVELOPMENT PURPOSES ONLY**
         **********************************/

       // deleteDB();
        //deleteEntry(1);
        //deleteEntry(2);
        //deleteEntry(3);
        //deleteEntry(4);

        /***********************************
         * Fill the list with values from the database
         **********************************/
        fillList();

        swipeRefresh = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fillList();;
                        swipeRefresh.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                }, 3000);
            }
        });

        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                                             android.R.color.holo_green_light,
                                             android.R.color.holo_orange_light,
                                             android.R.color.holo_red_light);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    final int position, long id) {


                if(!lv.isItemChecked(position)) {
                    //Toast.makeText(getApplicationContext(),position + " is checked", Toast.LENGTH_LONG).show();


                // ListView Clicked item index
                int itemPosition     = position;

                    //CheckBox cb = (CheckBox)view.findViewById(R.id.checkBox);

                    // ListView Clicked item value
                    String itemValue = (String) lv.getItemAtPosition(position);

                    // Show Alert
                    Toast.makeText(getApplicationContext(),
                            "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                            .show();
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                    alertDialog.setTitle("Enter Password to view");
                    final EditText input = new EditText(context);
                    ///input.setHint("hint");
                    alertDialog.setTitle("Enter your password");
                    //alertDialog.setMessage(message);
                    alertDialog.setView(input);
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //provide user with caution before uninstalling
                            //also here should be added a AsyncTask that going to read the password and once its checked the password is correct the app will be removed
                            //value1=usernameInput.getText().toString();
                            // value2=passwordInput.getText().toString();
                            // if(value1.equals(null)&&value2.equals(null))
                            // {Toast.makeText(context, "Enter username and password", Toast.LENGTH_SHORT).show();}
                            if (whichButton == AlertDialog.BUTTON_POSITIVE) {
                                //Toast.makeText(getApplicationContext(), input.getText().toString(), Toast.LENGTH_LONG).show();
                                if(input.getText().toString().equals(pass4KeyGen)){
                                    long ID = (long)position;
                                    String tempPass = getPass(ID);
                                    String asdf = decrypt(pass4KeyGen,tempPass);
                                    Toast.makeText(getApplicationContext(),asdf,Toast.LENGTH_LONG).show();
                                }

                            }
                        }
                    });

                    alertDialog.show();
                }
                //Toast.makeText(getApplicationContext(),input.getText().toString(), Toast.LENGTH_LONG).show();
                // I'm using fragment here so I'm using getView() to provide ViewGroup
                // but you can provide here any other instance of ViewGroup from your Fragment / Activity
                /*View viewInflated = LayoutInflater.from(context).inflate(R.layout.password, (ViewGroup) view, false);
                // Set up the input
                final EditText input = (EditText) viewInflated.findViewById(R.id.input);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                builder.setView(viewInflated);

                // Set up the buttons
                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        password = input.getText().toString();
                        Toast.makeText(getApplicationContext(),password,Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();*/

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
               final long ID = list.size();
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
                        if(password.getText().toString().equals(cpassword.getText().toString())){

                                 Log.d("Password: Added: ","ID: " + Long.toString(ID)+ "\n" +
                                         "Name: " + name.getText().toString() + "\n" +
                                         "Password :" + password.getText().toString());
                            try {


                                encPass = encrypt(pass4KeyGen, password.getText().toString());
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                                addToDB(name.getText().toString(), encPass.toString(), ID);
                            list.add(name.getText().toString());
                            adapter.notifyDataSetChanged();
                             Toast.makeText(getApplicationContext(),encPass,Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Passwords do not mactch!", Toast.LENGTH_LONG).show();
                        }
                        //addToDB(name.getText().toString(), password.getText().toString(), ID);
                       // list.add(name.getText().toString());
                       // adapter.notifyDataSetChanged();
                       // Toast.makeText(getApplicationContext(),Integer.toString(list.size()),Toast.LENGTH_LONG).show();
                       // dialog.dismiss();


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

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ID = Integer.parseInt(edDelID.getText().toString());
                deleteEntry(ID);
            }
        });

        btnDelAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDB();
            }
        });
    }

    public String encrypt(String key, String pass){

        try {

             encStr = AES.encrypt(key, pass);
        }catch (GeneralSecurityException e){
            e.printStackTrace();
        }
        return encStr;
    }

    public String decrypt(String key, String pass){
        try {


            decStr = AES.decrypt(key, pass);
        }catch (GeneralSecurityException e){
            e.printStackTrace();
        }
            return decStr;
    }
    public String getPass(long ID){
        MySQLiteHelper db = new MySQLiteHelper(this);
     Passwords pw =  db.getPasword(ID);
        String pass = pw.getPassword();
        db.close();
        return pass;
    }

    public void addToDB(String name, String password, long ID){
        MySQLiteHelper db = new MySQLiteHelper(this);
       db.addPasswords(new Passwords(ID,name,password));
        db.close();
    }


    public void fillList(){
        MySQLiteHelper db = new MySQLiteHelper(this);
        List<Passwords> passwords = db.getAllPasswords();
        for(Passwords pswd: passwords){
            list.add(pswd.getName().toString());
            Log.d("PasswordList :", pswd.toString());
        }
    }




    /******************************************************
     *
     *  For development only
     *  Used to clear the database while
     *  the app is being develeoped and tested
     *
     *
     ******************************************************/
    /*****************************************************
     *
     *  While Integrating the db into the UI and list
     *  use the below lines to delete passwords if neccassary.
     *
     *  Just change to id (number) of the password in the deletePasswords method
     *
     *****************************************************/

    /*************************************************************
     * Delete Single Contact
     *************************************************************/
    public void deleteEntry(int id) {
        MySQLiteHelper db = new MySQLiteHelper(this);
        Passwords ps = new Passwords();
        Log.d("Deleting Password: ",db.getPasword(id).toString());
        ps.setId(id); // <-- set the id here
        db.deletePassword(ps);
        db.close();
    }

    /*************************************************************
     * Delete Entire Database
     *************************************************************/
    public void deleteDB(){
        Passwords ps = new Passwords();
        MySQLiteHelper db = new MySQLiteHelper(this);
        long pswdCount = db.getPasswordCount();
        Log.d("Size: ",Long.toString(pswdCount));
        for(long i=1; i<=pswdCount;i++){
            Log.d("Deleting Password: ",db.getPasword(i).toString());
            ps.setId(i);
            db.deletePassword(ps);
            db.close();
        }
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
