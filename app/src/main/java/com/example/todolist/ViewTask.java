package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewTask extends AppCompatActivity {
    private TextView textViewTaskTitle, textViewTaskDescription;
    private Button buttonEditTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        textViewTaskTitle = findViewById(R.id.textViewTaskTitle);
        textViewTaskDescription = findViewById(R.id.textViewTaskDescription);
        buttonEditTask = findViewById(R.id.buttonEditTask);

        Intent intent = getIntent();
        if (intent != null) {
            String taskTitle = intent.getStringExtra("task_title");
            String taskDescription = intent.getStringExtra("task_description");

            textViewTaskTitle.setText(taskTitle);
            textViewTaskDescription.setText(taskDescription);
        }

        buttonEditTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(ViewTask.this, EditTask.class);
                editIntent.putExtra("task_title", textViewTaskTitle.getText().toString());
                editIntent.putExtra("task_description", textViewTaskDescription.getText().toString());
                startActivityForResult(editIntent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String updatedTitle = data.getStringExtra("task_title");
            String updatedDescription = data.getStringExtra("task_description");

            textViewTaskTitle.setText(updatedTitle);
            textViewTaskDescription.setText(updatedDescription);
        }
    }
}
