package com.example.cryptme3;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final Context context = this;
    ListView lv;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Button btnAdd;
    EditText editText;
    Boolean clicked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView)findViewById(R.id.listView);
        list = new ArrayList<String>();
        btnAdd = (Button)findViewById(R.id.button2);
        editText = (EditText)findViewById(R.id.editText);
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
                //int itemPosition     = position;

                // ListView Clicked item value
                //String  itemValue    = (String) lv.getItemAtPosition(position);

                // Show Alert
                // Toast.makeText(getApplicationContext(),
                //       "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                //        .show();


                //AlertDialog Example 1
                /*AlertDialog.Builder adb = new AlertDialog.Builder(
                        MainActivity.this);
                adb.setTitle("List");
                adb.setMessage(" selected Item is="
                        +parent.getItemAtPosition(position));
                adb.setPositiveButton("Ok", null);
                adb.show();
                */


                // get prompts.xml view
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompts, null);
                View displayView = li.inflate(R.layout.display, null);


                if(clicked==false) {
                    clicked =true;
                    AlertDialog.Builder alertDialogBuilderSetPass = new AlertDialog.Builder(
                            context);


                    // set prompts.xml to alertdialog builder
                    alertDialogBuilderSetPass.setView(promptsView);


                    /**
                     *
                     *      Set Password AlertDialog
                     *
                     */
                    final EditText password = (EditText) promptsView
                            .findViewById(R.id.editTextDialogUserInput);

                    final EditText cPassword = (EditText) promptsView
                            .findViewById(R.id.editText2);
                    // set dialog message
                    alertDialogBuilderSetPass
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // get user input and set it to result
                                            // edit text
                                            //result.setText(userInput.getText());
                                            if (password.getText().toString().equals(cPassword.getText().toString())) {
                                                Toast.makeText(getApplicationContext(), password.getText().toString(), Toast.LENGTH_LONG);

                                            } else {
                                                Toast.makeText(getApplicationContext(), "Password do not match. Please try again", Toast.LENGTH_LONG);
                                            }
                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialogSetPass = alertDialogBuilderSetPass.create();

                    // show it
                    alertDialogSetPass.show();
                } else if(clicked) {
                        clicked=false;
                    /**
                     *
                     *      Show Password AlertDialog
                     *
                     */


                    AlertDialog.Builder alertDialogBuilderShowPass = new AlertDialog.Builder(
                            context);
                    final EditText confirmPassword = (EditText) displayView
                            .findViewById(R.id.editText3);
                    // set dialog message
                    alertDialogBuilderShowPass
                            .setCancelable(false)
                            .setPositiveButton("OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            // get user input and set it to result
                                            // edit text
                                            //result.setText(userInput.getText());
                                            Toast.makeText(getApplicationContext(), "Password Confirmed", Toast.LENGTH_LONG);

                                        }
                                    })
                            .setNegativeButton("Cancel",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });

                    // create alert dialog
                    AlertDialog alertDialogShowPass = alertDialogBuilderShowPass.create();

                    // show it
                    alertDialogShowPass.show();
                }

            }

    });












        //Button onClickListener

        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input = editText.getText().toString();
                    list.add(input);

            }
        });


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
