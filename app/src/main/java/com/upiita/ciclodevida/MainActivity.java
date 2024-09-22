package com.upiita.ciclodevida;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtx_nombre, edtx_edad, edtx_estatura;
    private TextView txt_nombre, txt_edad, txt_estatura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtx_nombre = findViewById(R.id.edtx_nombre);
        edtx_edad = findViewById(R.id.edtx_edad);
        edtx_estatura = findViewById(R.id.edtx_estatura);

        txt_nombre = findViewById(R.id.txt_nombre);
        txt_edad = findViewById(R.id.txt_edad);
        txt_estatura = findViewById(R.id.txt_estatura);

        Button bt_capturar = findViewById(R.id.bt_capturar);

        bt_capturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Se capturaron los datos",Toast.LENGTH_LONG).show();

                txt_nombre.setText("-" + edtx_nombre.getText().toString());
                txt_edad.setText("-" + edtx_edad.getText().toString());
                txt_estatura.setText("-" + edtx_estatura.getText().toString());
            }
        });

        edtx_nombre.setOnKeyListener(new MyKeyListener());
        edtx_edad.setOnKeyListener(new MyKeyListener());
        edtx_estatura.setOnKeyListener(new MyKeyListener());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart() llamado", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume() llamado", Toast.LENGTH_SHORT).show();
        // Cargar los datos guardados si existen
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        edtx_nombre.setText(preferences.getString("nombre", ""));
        edtx_edad.setText(preferences.getString("edad", ""));
        edtx_estatura.setText(preferences.getString("estatura", ""));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause() llamado", Toast.LENGTH_SHORT).show();
        // Guardar los datos en SharedPreferences
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombre", edtx_nombre.getText().toString());
        editor.putString("edad", edtx_edad.getText().toString());
        editor.putString("estatura", edtx_estatura.getText().toString());
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop() llamado", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy() llamado", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart() llamado", Toast.LENGTH_SHORT).show();
    }

    private class MyKeyListener implements View.OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                switch (v.getId()) {
                    case R.id.edtx_nombre:
                        txt_nombre.setText("-" + edtx_nombre.getText().toString());
                        break;
                    case R.id.edtx_edad:
                        txt_edad.setText("-" + edtx_edad.getText().toString());
                        break;
                    case R.id.edtx_estatura:
                        txt_estatura.setText("-" + edtx_estatura.getText().toString());
                        break;
                }
                return true;
            }
            return false;
        }
    }
}
