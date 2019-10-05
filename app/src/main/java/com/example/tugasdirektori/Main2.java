package com.example.tugasdirektori;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.tugasdirektori.helper.Buah;


public class Main2 extends AppCompatActivity {
    Cursor c;
    SimpleCursorAdapter sca;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);

        ListView lv = findViewById(R.id.lv_list);
        FloatingActionButton fab = findViewById(R.id.fab);

        Buah helper = new Buah(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] datax = {"_id", "title", "type"};
        c = db.query("buah", datax,
                null,
                null,
                null,
                null,
                null);
        sca = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                c, new String[]{"title", "buah"},
                new int[]{android.R.id.text1, android.R.id.text2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv.setAdapter(sca);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "Tes Fab",
//                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Main2.this, Insert.class));
            }
        });
        //tambah menu pada listview
        registerForContextMenu(lv);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.konten_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.update: {
                updateDataBuku(info.id);
//                Toast.makeText(getApplicationContext(), "update berhasil",
//                        Toast.LENGTH_SHORT).show();
            }
            break;
            case R.id.delete: {
                deleteDataBuku(info.id);
//                Toast.makeText(getApplicationContext(),"delete berhasil",
//                        Toast.LENGTH_SHORT).show();
            }
            break;
            default:
        }
        return true;
    }

    public void updateDataBuku(long id) {
        Buah helper = new Buah(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor e = db.query("samplebooks", new String[]{"title", "autor"},
                "_id=?", new String[]{String.valueOf(id)},
                null, null, null);
        e.moveToFirst();
        Intent ii = new Intent(Main2.this, Insert.class);
        ii.putExtra("_id", id);
        ii.putExtra("title", e.getString(e.getColumnIndex("title")));
        ii.putExtra("autor", e.getString(e.getColumnIndex("autor")));
        startActivity(ii);
    }

    public void deleteDataBuku(long id) {
        Buah helper = new Buah(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        db.delete("samplebooks", "_id=?", new String[]{String.valueOf(id)});
        Toast.makeText(this, "data Dihapus", Toast.LENGTH_SHORT).show();
        Cursor x = db.query("samplebooks", new String[]{"_id", "title", "autor"},
                null, null, null, null, null);
        sca.changeCursor(x);
        sca.notifyDataSetChanged();
    }
}
