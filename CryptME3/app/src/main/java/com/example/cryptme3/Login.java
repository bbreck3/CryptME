package com.example.cryptme3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnDel,btnGo;
    TextView ed_pin;
    String pin[] = {"-","-","-","-"};
    String pinStr="";
    int counter =0;
    final int MAX_LEN =4;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pin);





        btn0 = (Button)findViewById(R.id.btn0);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        btn4 = (Button)findViewById(R.id.btn4);
        btn5 = (Button)findViewById(R.id.btn5);
        btn6 = (Button)findViewById(R.id.btn6);
        btn7 = (Button)findViewById(R.id.btn7);
        btn8 = (Button)findViewById(R.id.btn8);
        btn9 = (Button)findViewById(R.id.btn9);
        btnGo = (Button)findViewById(R.id.btnGo);
        btnDel = (Button)findViewById(R.id.btnDel);
        ed_pin = (TextView)findViewById(R.id.tvPin);


        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("Counter: ", Integer.toString(counter));
                if (counter <=3) {
                    pin[counter] = "0";
                    counter++;
                    for (int i = 0; i < pin.length; i++) {
                        pinStr += pin[i];
                    }

                    ed_pin.setText(pinStr);
                    pinStr = "";

                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if (counter <=3) {
                    pin[counter] = "1";
                    counter++;
                    for (int i = 0; i < pin.length; i++) {
                        pinStr += pin[i];
                    }

                    ed_pin.setText(pinStr);
                    pinStr = "";

                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if (counter <=3) {
                    pin[counter] = "2";
                    counter++;
                    for (int i = 0; i < pin.length; i++) {
                        pinStr += pin[i];
                    }

                    ed_pin.setText(pinStr);
                    pinStr = "";

                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if (counter <=3) {
                    pin[counter] = "3";
                    counter++;
                    for (int i = 0; i < pin.length; i++) {
                        pinStr += pin[i];
                    }

                    ed_pin.setText(pinStr);
                    pinStr = "";

                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Counter: ", Integer.toString(counter));
                if (counter <=3) {
                    pin[counter] = "4";
                    counter++;
                    for (int i = 0; i < pin.length; i++) {
                        pinStr += pin[i];
                    }

                    ed_pin.setText(pinStr);
                    pinStr = "";

                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (counter <=3) {
                    Log.d("Counter: ", Integer.toString(counter));
                    pin[counter] = "5";
                    counter++;
                    for (int i = 0; i < pin.length; i++) {
                        pinStr += pin[i];
                    }

                    ed_pin.setText(pinStr);
                    pinStr = "";

                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (counter <=3) {
                    Log.d("Counter: ", Integer.toString(counter));
                    pin[counter] = "6";
                    counter++;
                    for (int i = 0; i < pin.length; i++) {
                        pinStr += pin[i];
                    }

                    ed_pin.setText(pinStr);
                    pinStr = "";

                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (counter <=3) {
                    Log.d("Counter: ", Integer.toString(counter));
                    pin[counter] = "7";
                    counter++;
                    for (int i = 0; i < pin.length; i++) {
                        pinStr += pin[i];
                    }

                    ed_pin.setText(pinStr);
                    pinStr = "";

                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (counter <=3) {
                    Log.d("Counter: ", Integer.toString(counter));
                    pin[counter] = "8";
                    counter++;
                    for (int i = 0; i < pin.length; i++) {
                        pinStr += pin[i];
                    }

                    ed_pin.setText(pinStr);
                    pinStr = "";

                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (counter <=3) {
                    Log.d("Counter: ", Integer.toString(counter));
                    pin[counter] = "9";
                    counter++;
                    for (int i = 0; i < pin.length; i++) {
                        pinStr += pin[i];
                    }

                    ed_pin.setText(pinStr);
                    pinStr = "";

                } else {
                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (counter== 0) {
                    Log.d("Counter: ", Integer.toString(counter));

                    pin[counter] = "-";

                    for (int i = 0; i < pin.length; i++) {
                        pinStr += pin[i];
                    }
                    // String delPrevChar = pinStr.substring(0,pinStr.indexOf(pin[counter]));

                    ed_pin.setText(pinStr);
                    pinStr = "";

                } else if (counter >0) {
                    Log.d("Counter: ", Integer.toString(counter));

                        counter--;
                    pin[counter] = "-";

                    for (int i = 0; i < pin.length; i++) {
                        pinStr += pin[i];
                    }
                    // String delPrevChar = pinStr.substring(0,pinStr.indexOf(pin[counter]));

                    ed_pin.setText(pinStr);
                    pinStr = "";

                }else {

                    Toast.makeText(getApplicationContext(), "Pin limit exceeded", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //implement if else logic later
                //Toast.makeText(getApplicationContext(),pin,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });


        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

       /** FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public void refreshPin(){

        ed_pin.setText("----");
    }

}
