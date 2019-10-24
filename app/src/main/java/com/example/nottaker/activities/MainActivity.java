package com.example.nottaker.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.nottaker.R;
import com.example.nottaker.helpers.Adapter;
import com.example.nottaker.helpers.Note;
import com.example.nottaker.helpers.NoteModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recView;
    Adapter adapter;
    List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);

        NoteModel con = new NoteModel(this);
        this.notes = con.getNotes();
        this.recView = findViewById(R.id.noteList);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        this.recView.setLayoutManager(llm);
        this.adapter = new Adapter(this, this.notes);
        this.recView.setAdapter(this.adapter);

    }

    public void handleAddNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }
}
