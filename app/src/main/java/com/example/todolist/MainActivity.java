package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listViewTasks;
    private Button buttonAddTask;
    private TaskAdapter adapter;
    private ArrayList<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewTasks = findViewById(R.id.listViewTasks);
        buttonAddTask = findViewById(R.id.buttonAddTask);

        tasks = new ArrayList<>();
        adapter = new TaskAdapter(this, tasks);
        listViewTasks.setAdapter(adapter);

        buttonAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditTask.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            String taskTitle = data.getStringExtra("task_title");
            String taskDescription = data.getStringExtra("task_description");
            int position = data.getIntExtra("task_position", -1);

            if (requestCode == 1) { // Добавление новой задачи
                Task newTask = new Task(taskTitle, taskDescription);
                tasks.add(newTask);
            } else if (requestCode == 2 && position >= 0) { // Редактирование существующей задачи
                Task editedTask = tasks.get(position);
                editedTask.setTitle(taskTitle);
                editedTask.setDescription(taskDescription);
            }
            adapter.notifyDataSetChanged();
        }
    }
}
