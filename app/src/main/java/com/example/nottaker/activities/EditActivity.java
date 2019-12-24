package com.example.nottaker.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
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
import com.example.nottaker.models.NoteModel;

import java.util.Calendar;

public class EditActivity extends AppCompatActivity {

    private Note note;
    private NoteModel con;
    private Toolbar toolbar;
    private EditText noteTitle;
    private EditText noteContent;
    private Calendar calendar;
    private long noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        this.toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(this.toolbar);

        Intent intent = this.getIntent();
        this.noteId = intent.getLongExtra("noteId", 0);

        this.con = new NoteModel(this);
        this.note = con.getNote(this.noteId);

        this.calendar = Calendar.getInstance();

        this.noteTitle = findViewById(R.id.noteTitle);
        this.noteContent = findViewById(R.id.noteContent);

        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                getSupportActionBar().setTitle("Editing "+ note.getTitle());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        this.noteTitle.setText(this.note.getTitle());
        this.noteContent.setText(this.note.getContent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        inflater.inflate(R.menu.cancel_menu,menu);

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

    private boolean validateTitle() {
        String title = this.noteTitle.getText().toString();
        return !title.equalsIgnoreCase("");
    }

    private boolean validateContent() {
        String content = this.noteContent.getText().toString();
        return !content.equalsIgnoreCase("");
    }

    private void saveNote() {
        Note note = new Note();
        note.setTitle(this.noteTitle.getText().toString());
        note.setContent(this.noteContent.getText().toString());
        note.setDate(this.setDate());
        note.setTime(this.setTime());

        this.con = new NoteModel(getApplicationContext());
        if (con.update(this.noteId, note) != -1) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            Toast.makeText(getApplicationContext(), "Note updated!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"An Error has occurred.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.saveBtn) {

            if (!this.validateTitle() && this.validateContent()) {
                Toast.makeText(this,"Title cannot be empty.", Toast.LENGTH_LONG).show();
            } else if (this.validateTitle() && !this.validateContent()) {
                Toast.makeText(this,"Content cannot be empty.", Toast.LENGTH_LONG).show();
            } else if (!this.validateTitle() && !this.validateContent()) {
                Toast.makeText(this,"Cannot save an empty Note.", Toast.LENGTH_LONG).show();
            } else {
                this.saveNote();
            }

        } else {
            new AlertDialog.Builder(this)
                .setTitle("Confirm Cancel")
                .setMessage("All changes made will note be saved.\nAre you sure you want to cancel?")
                .setPositiveButton(R.string.yes_btn_title, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        Intent intent = new Intent(getApplicationContext(), NoteActivity.class);
                        intent.putExtra("noteId", note.getId());
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton(R.string.no_btn_title, null).show();
        }

        return super.onOptionsItemSelected(item);
    }

}
