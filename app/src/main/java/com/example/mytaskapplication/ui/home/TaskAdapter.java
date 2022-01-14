package com.example.mytaskapplication.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytaskapplication.App;
import com.example.mytaskapplication.databinding.ItemRvBinding;
import com.example.mytaskapplication.ui.models.User;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private ItemRvBinding binding;

    private List<User> list = new ArrayList<>();

    private OnItemClick listener;




//    public void setUser(User user){
//        list.add(user);
//        notifyDataSetChanged();
//
//    }


    public void setList(List<User> list){
        //list.addAll(list);
        this.list = list;
        //this.list.addAll(list);
        notifyDataSetChanged();
    }
 //инициализация интерфейса
     public void setListener(Context context, OnItemClick listener){
        this.listener = listener;

    }

    public List<User> getList(){
        return list;
    }

    public void removeItem(int position){
        //list.remove(position);
        App.dataBase.userDao().deleteUser(list.remove(position));
        notifyItemRemoved(position);

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
        //holder.editTitle(list.get(position));

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

            itemView.setOnClickListener(v -> {
                listener.onClick(getAdapterPosition());
            });
        }

        public void onBind(User user) {

            binding.nameTv.setText(user.getName());
            binding.surnameTv.setText(user.getSurname());

        }


//        public void editTitle(String s) {
//
//            binding
//
//                String s = binding.titleRv.getText().toString();
//                Bundle bundle = new Bundle();
//                bundle.putString("edit", s);
//                getParentFragmentManager().setFragmentResult("edited", bundle);
//
//        }
    }

    interface OnItemClick {
        void onClick(int position);
        void onLongClick(int position);
    }

}
