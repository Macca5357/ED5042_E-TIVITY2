/* Student name: Gavin McCarthy
 * Student id: 19237766
 */
package com.diary.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.diary.app.Adapters.DiaryAdapter;
import com.diary.app.Models.DiaryModel;
import com.diary.app.database.Database;

import java.util.ArrayList;
import java.util.List;

public class DiaryEntries extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayout linearLayout;
    Database database;
    DiaryAdapter diaryAdapter;
    List<DiaryModel> diaryModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entries);
        database=new Database(this);
        initViews();
        initRecyclerView();
        getAllEntries();

    }

    private void initRecyclerView() {
        diaryModels=new ArrayList<>();
        diaryAdapter = new DiaryAdapter(diaryModels, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(diaryAdapter);
    }

    private void getAllEntries() {
        diaryModels.clear();
        diaryModels.addAll(database.getALL());
        if(diaryModels.size()>0){
            recyclerView.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
        }else {
            recyclerView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }
        diaryAdapter.notifyDataSetChanged();

    }

    private void initViews() {
        recyclerView=findViewById(R.id.recyclerVIew);
        linearLayout=findViewById(R.id.layoutEmpty);
    }
}


