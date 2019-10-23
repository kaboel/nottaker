package com.example.nottaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.EditText;

public class AddNote extends AppCompatActivity {

    Toolbar toolbar;
    EditText noteTitle, noteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);

        this.noteTitle = findViewById(R.id.noteTitle);
        this.noteContent = findViewById(R.id.noteContent);
    }
}
