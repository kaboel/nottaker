package com.example.nottaker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText noteTitle, noteContent;
    Calendar calendar;
    String dateNow;
    String timeNow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle("New Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.noteTitle = findViewById(R.id.noteTitle);
        this.noteContent = findViewById(R.id.noteContent);

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if (charSequence.length() != 0) {
                    getSupportActionBar().setTitle(charSequence);
                } else {
                    getSupportActionBar().setTitle("New Note");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        calendar = Calendar.getInstance();
        dateNow = calendar.get(Calendar.YEAR)+"/";
        dateNow += calendar.get(Calendar.MONTH)+1+"/";
        dateNow += calendar.get(Calendar.DAY_OF_MONTH);

        timeNow = pad(calendar.get(Calendar.HOUR))+":";
        timeNow += pad(calendar.get(Calendar.MINUTE));

//        Log.d("Calendar", "Date and Time: "+ this.dateNow +" || "+ this.timeNow);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);

        return true;
    }

    private String pad(int i) {
        if (i<10) {
            return "0"+i;
        }
        return String.valueOf(i);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.saveBtn) {
            Note note = new Note();
            note.setTitle(this.noteTitle.getText().toString());
            note.setContent(this.noteContent.getText().toString());
            note.setDate(this.dateNow);
            note.setTime(this.timeNow);

            NoteDB con = new NoteDB(this);
            if (con.addNote(note) != -1) {
                Toast.makeText(this,"Note saved!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
