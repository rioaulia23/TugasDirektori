package com.example.tugasdirektori;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tugasdirektori.helper.Pref;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText etNama = findViewById(R.id.etName);

        Button btn = findViewById(R.id.btnmasuk);

        final Bundle b = new Bundle();
        boolean statusInput = Pref.sharedIntance(getApplicationContext())
                .cekStatus();
        if (statusInput) {
            startActivity(new Intent(MainActivity.this, AfterClick.class));
            finish();
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                b.putString("nama", editText.getText().toString());
                    Intent i = new Intent(MainActivity.this, AfterClick.class);
//                i.putExtras(b);
                    Pref.sharedIntance(getApplicationContext()).setNama(
                            etNama.getText().toString());
                    Pref.sharedIntance(getApplicationContext())
                            .setStatusInput(true);
                    startActivity(i);
                    finish();
                }
            });

        }
    }
}
