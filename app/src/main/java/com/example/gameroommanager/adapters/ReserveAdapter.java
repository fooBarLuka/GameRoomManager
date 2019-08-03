package com.example.gameroommanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gameroommanager.R;
import com.example.gameroommanager.models.Reserve;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReserveAdapter extends RecyclerView.Adapter<ReserveAdapter.ReserveHolder> {

    private List<Reserve> reserves = new ArrayList<>();

    @NonNull
    @Override
    public ReserveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReserveHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.reserve_recyclerview_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReserveHolder holder, int position) {
        holder.setData(reserves.get(position));
    }

    @Override
    public int getItemCount() {
        return reserves.size();
    }

    public void setReserves(List<Reserve> reserves){
        this.reserves = reserves;
        notifyDataSetChanged();
    }

    class ReserveHolder extends RecyclerView.ViewHolder{
        private TextView reserveIdTextView;
        private TextView consoleNameTextView;
        private TextView gameNameTextView;
        private TextView moneyTextView;
        private TextView startedTimeTextView;
        private TextView finishedTimeTextView;
        private TextView stateTextView;


        public ReserveHolder(@NonNull View itemView) {
            super(itemView);

            reserveIdTextView = itemView.findViewById(R.id.reserve_id_textview_id);
            consoleNameTextView = itemView.findViewById(R.id.console_name_textview_id);
            gameNameTextView = itemView.findViewById(R.id.game_name_textview_id);
            moneyTextView = itemView.findViewById(R.id.money_textview_id);
            startedTimeTextView = itemView.findViewById(R.id.time_start_textview_id);
            finishedTimeTextView = itemView.findViewById(R.id.time_end_textview_id);
            stateTextView = itemView.findViewById(R.id.state_textview_id);
        }

        public void setData(Reserve reserve){
            reserveIdTextView.setText(reserve.id + "");
            consoleNameTextView.setText(reserve.consoleId + "");
            gameNameTextView.setText(reserve.game);
            moneyTextView.setText(reserve.price + "");
            startedTimeTextView.setText(convertTimeToDate(reserve.started));
            finishedTimeTextView.setText(convertTimeToDate(reserve.finishedTime));
            if(!reserve.finished){
                stateTextView.setVisibility(View.INVISIBLE);
            }
        }

        private String convertTimeToDate(long millis){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(millis);
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
            return formatter.format(calendar.getTime());
        }
    }
}
