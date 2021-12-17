package com.example.mytaskapplication.ui.onBoard;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytaskapplication.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private String[] list = {"This is 1st screen", "This is 2nd screen", "This is 3rd screen","This is 4th screen"};



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_board,
               parent,
               false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);

    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title, description;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.boardIv);
            title = itemView.findViewById(R.id.boardFirstTv);
            description = itemView.findViewById(R.id.boardSecondTv);
        }

        public void onBind(int position) {
//            switch (position){
//                case 0:
//                    title.setText("This is 1st screen");
//                    break;
//                case 1:
//                    title.setText("This is 2nd screen");
//                    break;
//                case 2:
//                    title.setText("This is 3rd screen");
//                    break;
//                case 3:
//                    title.setText("This is 4th screen");
//                    break;
//            }
            title.setText(position);
        }
    }
}
