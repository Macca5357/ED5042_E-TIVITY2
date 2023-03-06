/* Student name: Gavin McCarthy
 * Student id: 19237766
 */
package com.diary.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.diary.app.Models.DiaryModel;
import com.diary.app.database.Database;

public class MainActivity extends AppCompatActivity {
    EditText editTextInputText
            ,editTextInputDate;
    Button buttonSubmit,buttonShowAllEntries;
    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new Database(this);
        initViews();
    }

    private void initViews() {
        editTextInputText=findViewById(R.id.editTextInputText);
        editTextInputDate=findViewById(R.id.editTextInputDate);
        buttonSubmit=findViewById(R.id.buttonSubmit);
        buttonShowAllEntries=findViewById(R.id.buttonShowAllEntries);
        buttonShowAllEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DiaryEntries.class));
            }
        });
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEntry();
            }
        });
    }
    private void addEntry() {
        DiaryModel diaryModel=new DiaryModel();
        diaryModel.setText(editTextInputText.getText().toString());
        diaryModel.setDate(editTextInputDate.getText().toString());
        if(diaryModel.getText().equals("")){
            Toast.makeText(this, getString(R.string.text), Toast.LENGTH_SHORT).show();
        }else if(diaryModel.getDate().equals("")){
            Toast.makeText(this, getString(R.string.date), Toast.LENGTH_SHORT).show();
        }else {
           database.insertDiary(diaryModel);
            Toast.makeText(this, getString(R.string.successful), Toast.LENGTH_SHORT).show();
          editTextInputDate.setText("");
          editTextInputText.setText("");
        }
    }
}
