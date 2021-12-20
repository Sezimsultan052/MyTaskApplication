package com.example.mytaskapplication.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytaskapplication.databinding.ItemRvBinding;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ItemRvBinding binding;

    private ArrayList<String> list = new ArrayList<>();

    private OnItemClick listener;

    public void setText(String text){
        list.add(text);
        notifyDataSetChanged();

    }
 //инициализация интерфейса
     public void setListener(OnItemClick listener){
        this.listener = listener;

    }

    public void removeItem(int position){
        list.remove(position);
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemRvBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            itemView.setOnLongClickListener(v -> {

                listener.onLongClick(getAdapterPosition());
                return true;
            });




        }

        public void onBind(String text) {

            binding.titleRv.setText(text);
            itemView.setOnClickListener(v ->{
                listener.onClick(text);
            });

        }
    }

    interface OnItemClick {
        void onClick(String txt);
        void onLongClick(int position);
    }

}
