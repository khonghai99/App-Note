package com.example.appnote;

import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBNote DBNote;
    ListView lstNote;
    NoteAdapter adapter;
//    EditText editFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstNote = (ListView) findViewById(R.id.listNote);
//        editFilter = (EditText) findViewById(R.id.editFilter);

        DBNote = new DBNote(MainActivity.this, "note.sqlite", null, 1);

        DBNote.QueryData("CREATE TABLE IF NOT EXISTS Note(ID INTEGER PRIMARY KEY AUTOINCREMENT, NameNote NVARCHAR(255),DateNote DATE);");
//        database.QueryData("INSERT INTO Note VALUES(null,'abcd','2/3/2012')");
//        database.QueryData("DELETE FROM Note WHERE id=3");
        adapter = new NoteAdapter(MainActivity.this, getNote());
        lstNote.setAdapter(adapter);
//        editFilter.requestFocus();
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(editFilter, InputMethodManager.SHOW_IMPLICIT);
//
//        editFilter.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                adapter.getFilter().filter(s);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
    }

    public ArrayList<Note> getNote() {
        ArrayList<Note> arrayNotes = new ArrayList<>();

        Note note;
        Cursor cursor = DBNote.GetData("SELECT * FROM Note");
        while (cursor.moveToNext()) {
            note = new Note(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
            arrayNotes.add(note);
        }
        return arrayNotes;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}
