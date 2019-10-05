package com.example.tugasdirektori;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tugasdirektori.helper.Buah;

public class Insert extends AppCompatActivity implements View.OnClickListener {

    EditText title, type;
    boolean update = false;
    long id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert);
        title = findViewById(R.id.title);
        type = findViewById(R.id.type);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(this);

        id = getIntent().getLongExtra("_id", 0);
        if (id != 0) {
            update = true;
            title.setText(getIntent().getStringExtra("title"));
            type.setText(getIntent().getStringExtra("type"));
        }
    }

    @Override
    public void onClick(View v) {
        Buah helper = new Buah(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", title.getText().toString().trim());
        cv.put("type", type.getText().toString().trim());

        if (update) {
            db.update("buah", cv, "_id=?",
                    new String[]{String.valueOf(id)});
        } else {
            db.insert("buah", null, cv);
        }
        db.close();
        startActivity(new Intent(Insert.this, Main2.class));
        finish();
    }
}
