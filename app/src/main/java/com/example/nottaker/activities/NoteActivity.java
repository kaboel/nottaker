package com.example.nottaker.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.nottaker.R;
import com.example.nottaker.helpers.Note;
import com.example.nottaker.helpers.NoteModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    TextView noteContent, lastEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = this.getIntent();
        long id = intent.getLongExtra("noteId", 0);

        NoteModel con = new NoteModel(this);
        Note note = con.getNote(id);
        getSupportActionBar().setTitle(note.getTitle());

        this.noteContent = findViewById(R.id.noteDetail);
        this.lastEdit = findViewById(R.id.lastEdited);

        this.noteContent.setText(note.getContent());

        String str = "Last Edited: "+ note.getDate() +" - "+ note.getTime();
        this.lastEdit.setText(str);

        FloatingActionButton fab = findViewById(R.id.editNote);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Edit Btn Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteBtn) {
            Toast.makeText(this, "Delete Btn Clicked", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
