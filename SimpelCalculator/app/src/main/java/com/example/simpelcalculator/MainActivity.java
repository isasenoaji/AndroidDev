package com.example.simpelcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.*;
import android.view.*;
import java.lang.*;
public class MainActivity extends AppCompatActivity {

    private Button button0, button1, button2, button3, button4, button5, button6,
            button7, button8, button9, buttonAdd, buttonSub, buttonDivision,
            buttonMul, button10, buttonC, buttonEqual;
    private EditText panelEditText;

    float mValueOne, mValueTwo;
    String toProccess;
    String fix[];

    boolean Addition, Subtract, Multiplication, Division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);
        buttonAdd = (Button) findViewById(R.id.buttonadd);
        buttonSub = (Button) findViewById(R.id.buttonsub);
        buttonMul = (Button) findViewById(R.id.buttonmul);
        buttonDivision = (Button) findViewById(R.id.buttondiv);
        buttonC = (Button) findViewById(R.id.buttonC);
        buttonEqual = (Button) findViewById(R.id.buttoneql);
        panelEditText = (EditText) findViewById(R.id.edt1);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelEditText.setText(panelEditText.getText() + "1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelEditText.setText(panelEditText.getText() + "2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelEditText.setText(panelEditText.getText() + "3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelEditText.setText(panelEditText.getText() + "4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelEditText.setText(panelEditText.getText() + "5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelEditText.setText(panelEditText.getText() + "6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelEditText.setText(panelEditText.getText() + "7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelEditText.setText(panelEditText.getText() + "8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelEditText.setText(panelEditText.getText() + "9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelEditText.setText(panelEditText.getText() + "0");
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (panelEditText == null) {
                    panelEditText.setText("");
                } else {
                    mValueOne = Float.parseFloat(panelEditText.getText() + "");
                    Addition = true;
                    int val1 = (int) mValueOne;
                    panelEditText.setText(val1+"+");
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (panelEditText == null) {
                    panelEditText.setText("");
                } else {
                    mValueOne = Float.parseFloat(panelEditText.getText() + "");
                    Subtract = true;
                    int val1 = (int) mValueOne;
                    panelEditText.setText(val1+"-");
                }
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (panelEditText == null) {
                    panelEditText.setText("");
                } else {
                    mValueOne = Float.parseFloat(panelEditText.getText() + "");
                    Multiplication = true;
                    int val1 = (int) mValueOne;
                    panelEditText.setText(val1+"x");
                }
            }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (panelEditText == null) {
                    panelEditText.setText("");
                } else {
                    mValueOne = Float.parseFloat(panelEditText.getText() + "");
                    Division = true;
                    int val1 = (int) mValueOne;
                    panelEditText.setText(val1+"/");
                }
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProccess = panelEditText.getText().toString();

                if (Addition == true) {
                    fix = toProccess.split("\\+");
                    mValueTwo = Float.parseFloat(fix[1]);
                    panelEditText.setText(mValueOne + mValueTwo + "");
                    Addition = false;
                }

                if (Subtract == true) {
                    fix = toProccess.split("-");
                    mValueTwo = Float.parseFloat(fix[1]);
                    panelEditText.setText(mValueOne - mValueTwo + "");
                    Subtract = false;
                }

                if (Multiplication == true) {
                    fix = toProccess.split("x");
                    mValueTwo = Float.parseFloat(fix[1]);
                    panelEditText.setText(mValueOne * mValueTwo + "");
                    Multiplication = false;
                }

                if (Division == true) {
                    fix = toProccess.split("/");
                    mValueTwo = Float.parseFloat(fix[1]);
                    panelEditText.setText(mValueOne / mValueTwo + "");
                    Division = false;
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelEditText.setText("");
            }
        });

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panelEditText.setText(panelEditText.getText() + ".");
            }
        });
    }
}
