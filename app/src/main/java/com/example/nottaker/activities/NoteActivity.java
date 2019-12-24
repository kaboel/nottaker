package com.example.nottaker.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.nottaker.R;
import com.example.nottaker.helpers.Note;
import com.example.nottaker.models.NoteModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    NoteModel con;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = this.getIntent();
        long id = intent.getLongExtra("noteId", 0);

        this.con = new NoteModel(this);
        this.note = con.getNote(id);

        this.noteContent = findViewById(R.id.noteDetail);
        this.lastEdit = findViewById(R.id.lastEdited);

        getSupportActionBar().setTitle(note.getTitle());
        this.noteContent.setText(note.getContent());
        String str = "Last Edited: "+ note.getDate() +" - "+ note.getTime();
        this.lastEdit.setText(str);

        FloatingActionButton fab = findViewById(R.id.editNote);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("noteId", note.getId());
                startActivity(intent);
                finish();
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
            if (this.con.destroy(this.note.getId()) != -1) {
                new AlertDialog.Builder(this)
                    .setTitle("Confirm Delete")
                    .setMessage("Are you sure you want to delete this note?")
                    .setPositiveButton(R.string.yes_btn_title, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                            Toast.makeText(getApplicationContext(), "Note deleted!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton(R.string.no_btn_title, null).show();
            } else {
                Toast.makeText(this, "An Error has occurred.", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
