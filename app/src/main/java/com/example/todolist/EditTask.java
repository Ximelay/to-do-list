package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditTask extends AppCompatActivity {
    private EditText editTextTitle, editTextDescription;
    private Button buttonSaveTask;
    private int taskPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        buttonSaveTask = findViewById(R.id.buttonSaveTask);

        Intent intent = getIntent();
        if (intent != null) {
            String taskTitle = intent.getStringExtra("task_title");
            String taskDescription = intent.getStringExtra("task_description");
            taskPosition = intent.getIntExtra("task_position", -1);

            editTextTitle.setText(taskTitle);
            editTextDescription.setText(taskDescription);
        }

        buttonSaveTask.setOnClickListener(v -> {
            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();

            if (!title.isEmpty() && !description.isEmpty()) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("task_title", title);
                resultIntent.putExtra("task_description", description);
                resultIntent.putExtra("task_position", taskPosition);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
