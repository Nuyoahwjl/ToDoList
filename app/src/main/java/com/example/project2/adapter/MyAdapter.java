package com.example.project2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.EditActivity;
import com.example.project2.R;
import com.example.project2.bean.Note;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Note> mBeanList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public MyAdapter(Context mcontext,List<Note> mBeanList){
        this.mBeanList=mBeanList;
        this.mContext=mcontext;
        mLayoutInflater=LayoutInflater.from(mcontext);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=mLayoutInflater.inflate(R.layout.list_item_layout,parent,false);

        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note=mBeanList.get(position);

        holder.mTvTitle.setText(note.getTitle());
        holder.mTvContent.setText(note.getContent());
        holder.mTvTime.setText(note.getCreatedTime());
        holder.rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, EditActivity.class);
                intent.putExtra("note", note);
                mContext.startActivity(intent);
            }
        });

        holder.rlContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v){
                //长按弹出弹窗删除或者编辑
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    public  void refresh(List<Note> notes){
        this.mBeanList=notes;
        notifyDataSetChanged();

    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView mTvTitle,mTvContent,mTvTime;
        ViewGroup rlContainer;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTvTitle=itemView.findViewById(R.id.tv_tittle);
            this.mTvContent=itemView.findViewById(R.id.tv_content);
            this.mTvTime=itemView.findViewById(R.id.tv_time);
            this.rlContainer=itemView.findViewById(R.id.rl_item_container);
        }
    }

}
