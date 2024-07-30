package com.example.lessontest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameRecycleAdapter extends RecyclerView.Adapter<GameRecycleAdapter.ViewHolder> {
    private  List<GameBean> data;

//定义接口类型变量存储数据
    private OnItemClickListener mOnItemClickListener;

    //定义回调接口
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
//定义回调方法
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public GameRecycleAdapter(List<GameBean> data){
        this.data=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.game,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GameBean gameBean=data.get(position);
        holder.mGameIcon.setImageResource(gameBean.getGameIcon());
        holder.mGameName.setText(gameBean.getGameName());


        //调用接口方法
        if(mOnItemClickListener!=null){
            holder.mGameIcon.setOnClickListener(v -> mOnItemClickListener.onItemClick(v,position));
            holder.mButton.setOnClickListener(v -> mOnItemClickListener.onItemClick(v,position));
            holder.mGameName.setOnClickListener(v -> mOnItemClickListener.onItemClick(v,position));
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mGameIcon;
        TextView mGameName;
        Button mButton;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            mGameName=itemView.findViewById(R.id.game_name);
            mGameIcon=itemView.findViewById(R.id.game_icon);
            mButton=itemView.findViewById(R.id.game_button);

        }
    }
}
