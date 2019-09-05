package com.example.hitungluas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnHitung = (Button)findViewById(R.id.button);
        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText panjang = (EditText)findViewById(R.id.pjgText);
                final EditText lebar = (EditText)findViewById(R.id.lbrText);
                final TextView hasil = (TextView)findViewById(R.id.hasilText);

                double Panjang = Double.parseDouble(panjang.getText().toString().trim());
                double Lebar = Double.parseDouble(lebar.getText().toString().trim());

                double Hasil = Panjang * Lebar;

                hasil.setText("Hasil = "+Hasil);
            }
        });
    }
}
