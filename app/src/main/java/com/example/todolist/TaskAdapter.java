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

        // Обработчик для CheckBox
        checkBoxComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Вы хотите удалить задачу?")
                        .setPositiveButton("Да", (dialog, id) -> {
                            tasks.remove(position);
                            notifyDataSetChanged();
                        })
                        .setNegativeButton("Нет", (dialog, id) -> dialog.dismiss())
                        .create()
                        .show();
            }
        });

        // Обработчик для кнопки "Редактировать"
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditTask.class);
                intent.putExtra("task_title", task.getTitle());
                intent.putExtra("task_description", task.getDescription());
                intent.putExtra("task_position", position);
                ((MainActivity) context).startActivityForResult(intent, 2);
            }
        });

        // Обработчик для кнопки "Просмотрить задачу"
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewTask.class);
                intent.putExtra("task_title", task.getTitle());
                intent.putExtra("task_description", task.getDescription());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
