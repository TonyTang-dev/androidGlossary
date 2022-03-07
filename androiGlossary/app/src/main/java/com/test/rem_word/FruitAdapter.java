package com.test.rem_word;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mContext;
    private List<Fruit>mFruitList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            fruitImage=(ImageView)view.findViewById(R.id.fruit_image);
            fruitName=(TextView)view.findViewById(R.id.fruit_name);
        }
    }
    public FruitAdapter(List<Fruit> fruitList){
        mFruitList=fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if(mContext==null){
            mContext=parent.getContext();//获取的是当前上下文
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.fruititem,parent,false);

        //处理item的点击事件
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int position=holder.getAdapterPosition();
                Fruit fruit=mFruitList.get(position);
                Intent intent=new Intent(mContext,FruitContent.class);
                intent.putExtra(FruitContent.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitContent.FRUIT_IMAGE_ID,fruit.getImageId());
                mContext.startActivity(intent);
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Fruit fruit=mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.fruitImage);
    }
    @Override
    public int getItemCount(){
        return mFruitList.size();
    }
}
