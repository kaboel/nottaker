package com.example.nottaker.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nottaker.R;
import com.example.nottaker.helpers.Note;
import com.example.nottaker.helpers.NoteModel;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private EditText noteTitle;
    private EditText noteContent;

    private Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle("New Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.calendar = Calendar.getInstance();

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);

        return true;
    }

    private String setDate() {
        String dateNow = this.calendar.get(Calendar.YEAR)+"/";
        dateNow += this.calendar.get(Calendar.MONTH)+1+"/";
        dateNow += this.calendar.get(Calendar.DAY_OF_MONTH);

        return dateNow;
    }

    private String setTime() {
        String timeNow = pad(this.calendar.get(Calendar.HOUR))+":";
        timeNow += pad(this.calendar.get(Calendar.MINUTE));

        return timeNow;
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
            note.setDate(this.setDate());
            note.setTime(this.setTime());

            NoteModel con = new NoteModel(this);
            if (con.addNote(note) != -1) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(this,"Note created!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this,"An Error has occurred.", Toast.LENGTH_LONG).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
