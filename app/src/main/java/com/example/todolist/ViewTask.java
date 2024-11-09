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

        // Получение данных о задаче из Intent
        Intent intent = getIntent();
        textViewTaskTitle.setText(intent.getStringExtra("task_title"));
        textViewTaskDescription.setText(intent.getStringExtra("task_description"));

        // Кнопка "Назад"
        buttonEditTask.setText("Назад");
        buttonEditTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Возвращает на главный экран
            }
        });
    }
}
