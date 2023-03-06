/* Student name: Gavin McCarthy
 * Student id: 19237766
 */
package com.diary.app.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.diary.app.Models.DiaryModel;
import com.diary.app.R;

import org.w3c.dom.Text;

import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.CustomViewHolder> {
    List<DiaryModel> diaryModels;
    Context context;

     public static class  CustomViewHolder extends RecyclerView.ViewHolder{
         TextView textViewDiaryEntries
                 ,textViewDate;
          public CustomViewHolder(View itemView) {
             super(itemView);
              textViewDiaryEntries=itemView.findViewById(R.id.textViewDiaryEntries);
              textViewDate=itemView.findViewById(R.id.textViewDate);
        }
    }
    public DiaryAdapter(List<DiaryModel> diaryModels, Context context) {
        this.diaryModels=diaryModels;
        this.context = context;
    }
    @Override
    public int getItemViewType(int position) {
            return R.layout.diary_item;
    }
    @Override
    public int getItemCount() {
        return  diaryModels.size();
    }
    
    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
          holder.textViewDate.setText(diaryModels.get(position).getDate());
          holder.textViewDiaryEntries.setText(diaryModels.get(position).getText());
      }
}
