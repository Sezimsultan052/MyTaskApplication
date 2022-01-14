package com.example.mytaskapplication.ui.onBoard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytaskapplication.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private String[] list = {"This is 1st screen - Instagram", "This is 2nd screen - VK", "This is 3rd screen - Instagram","This is 4th screen - TWitter"};

    private String[] list2 = {"американская социальная сеть для обмена фотографиями и видео, основанная Кевином Систромом и Майком Кригером. В апреле 2012 года компания Meta (на тот момент — Facebook Inc.) приобрела сервис примерно за 1 миллиард долларов США наличными и акциями[7]. Приложение позволяет пользователям загружать медиафайлы, которые можно редактировать с помощью фильтров и организовывать с помощью хештегов и географических меток. Сообщениями можно делиться публично или с заранее одобренными подписчиками.", "российская социальная сеть со штаб-квартирой в Санкт-Петербурге. Сайт доступен на 85 языках[4]; особенно популярен среди русскоязычных пользователей. «ВКонтакте» позволяет пользователям отправлять друг другу сообщения, создавать собственные страницы и сообщества, обмениваться изображениями, аудио- и видеозаписями, переводить деньги, играть в браузерные игры. Также позиционирует себя платформой для продвижения бизнеса и решения повседневных задач с помощью мини-приложений","видеохостинг, предоставляющий пользователям услуги хранения, доставки и показа видео. YouTube стал популярнейшим видеохостингом и вторым сайтом в мире по количеству посетителей", " социальная сеть для публичного обмена сообщениями при помощи веб-интерфейса, SMS, средств мгновенного обмена сообщениями или сторонних программ-клиентов для пользователей интернета любого возраста[7]. Публикация коротких заметок в формате блога получила название «микроблогинг». Пользование сервисом бесплатно. Пользование посредством SMS тарифицируется оператором согласно тарифному плану пользователя."};

    private int[] listImg = {R.drawable.ic_vk,R.drawable.ic_youtube, R.drawable.ic_insta, R.drawable.ic_twitter};

     protected ClickListener listener;


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
        holder.homeButton(position);
        holder.homeBtn.setOnClickListener(v -> {
            listener.openHome();
        });

    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title, description;
        private Button homeBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //imageView = itemView.findViewById(R.id.boardIv);

            title = itemView.findViewById(R.id.boardFirstTv);
            description = itemView.findViewById(R.id.boardSecondTv);
            homeBtn = itemView.findViewById(R.id.homeBtn);
        }

        public void onBind(int position) {
            title.setText(list[position]);
            description.setText(list2[position]);
            //imageView.setImageResource(listImg[position]);
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

        }

        public void homeButton(int position) {
                        switch (position){
                case 2:
                    homeBtn.setVisibility(View.VISIBLE);
                    break;

            }
        }
    }
    interface ClickListener {
        void openHome();
    }


}
