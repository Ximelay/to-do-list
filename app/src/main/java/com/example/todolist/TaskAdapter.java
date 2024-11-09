package com.example.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {
    private ArrayList<Task> tasks;
    private Context context;

    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        super(context, 0, tasks);
        this.tasks = tasks;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        Task task = getItem(position);

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        CheckBox checkBoxComplete = convertView.findViewById(R.id.checkBoxComplete);
        Button buttonEdit = convertView.findViewById(R.id.buttonEdit);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);

        textViewTitle.setText(task.getTitle());
        checkBoxComplete.setChecked(task.isCompleted());

        // Обработчик для CheckBox завершения задачи
        checkBoxComplete.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                new AlertDialog.Builder(context)
                        .setMessage("Вы точно хотите закрыть задачу?")
                        .setPositiveButton("Да", (dialog, which) -> {
                            tasks.remove(task);
                            notifyDataSetChanged();
                        })
                        .setNegativeButton("Нет", (dialog, which) -> checkBoxComplete.setChecked(false))
                        .show();
            }
        });

        // Обработчик для кнопки "Удалить"
        buttonDelete.setOnClickListener(v -> {
            tasks.remove(task);
            notifyDataSetChanged();
        });

        // Обработчик для кнопки "Редактировать"
        buttonEdit.setOnClickListener(v -> {
            Intent editIntent = new Intent(context, EditTask.class);
            editIntent.putExtra("task_title", task.getTitle());
            editIntent.putExtra("task_description", task.getDescription());
            editIntent.putExtra("task_position", position);
            ((MainActivity) context).startActivityForResult(editIntent, 2);
        });

        return convertView;
    }
}
