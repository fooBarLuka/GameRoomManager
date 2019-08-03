package com.example.gameroommanager.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gameroommanager.R;
import com.example.gameroommanager.Utils.AlarmManagerUtil;
import com.example.gameroommanager.database.MyDataBase;
import com.example.gameroommanager.models.Console;
import com.example.gameroommanager.models.Reserve;

import java.util.ArrayList;
import java.util.List;

public class ConsoleAdapter extends RecyclerView.Adapter<ConsoleAdapter.ConsoleHolder> {

    public ConsoleAdapter(List<Console> consoles){
        this.consoles.addAll(consoles);
        notifyDataSetChanged();
    }

    List<Console> consoles = new ArrayList<>();

    @NonNull
    @Override
    public ConsoleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ConsoleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.console_recyclerview_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConsoleHolder holder, int position) {
        holder.setData(consoles.get(position));
    }

    @Override
    public int getItemCount() {
        return consoles.size();
    }

    public void setItems(List<Console> consoles){
        this.consoles = consoles;
        notifyDataSetChanged();
    }

    public class ConsoleHolder extends RecyclerView.ViewHolder{

        private Console console;
        private TextView idTextView;
        private TextView nameTextView;
        private AppCompatSpinner gamesSpinner;
        private EditText moneyEditText;
        private TextView workingTextView;
        private Button reserveButton;

        public ConsoleHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.console_id_textview_id);
            nameTextView = itemView.findViewById(R.id.console_name_textview_inside_console_id);
            gamesSpinner = itemView.findViewById(R.id.console_spinner_id);
            moneyEditText = itemView.findViewById(R.id.money_amount_edit_text_id);
            workingTextView = itemView.findViewById(R.id.console_working_textview_id);
            reserveButton = itemView.findViewById(R.id.reserve_button_id);
        }

        public void setData(Console console){
            this.console = console;
            idTextView.setText(console.id + "");
            nameTextView.setText(console.tag);
            if(console.occupied){
                reserveButton.setVisibility(View.INVISIBLE);
            } else {
                workingTextView.setVisibility(View.INVISIBLE);
            }
            List<String> gamesList = separateGames(console.games);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(nameTextView.getContext(),R.layout.support_simple_spinner_dropdown_item, gamesList);
            gamesSpinner.setAdapter(adapter);

            initUiActions();
        }

        private void initUiActions(){
            reserveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Reserve newReserve = new Reserve(console.id, (String) gamesSpinner.getSelectedItem(),
                            System.currentTimeMillis(), Double.parseDouble(moneyEditText.getText().toString()));

                    reserveButton.setVisibility(View.INVISIBLE);
                    workingTextView.setVisibility(View.VISIBLE);

                    console.occupied = true;
                    MyDataBase.updateConsoleAsynchronous(console);
                    MyDataBase.addReserveAsynchronous(newReserve);

                    AlarmManagerUtil.setAlarm(v.getContext(), newReserve.finished, console.id, newReserve.id);
                }
            });
        }

        private List<String> separateGames(String games){
            List<String> splitGames = new ArrayList<>();
            int beginningOfCurrWord = 0;
            for(int i = 0; i < games.length() - 1; i++){
                char ch = games.charAt(i);
                if(ch == ';'){
                    splitGames.add(games.substring(beginningOfCurrWord,i).trim());
                    beginningOfCurrWord = i + 1;
                }
            }

            if(games.charAt(games.length() - 1) == ';'){
                splitGames.add(games.substring(beginningOfCurrWord, games.length() - 1).trim());
            } else {
                splitGames.add(games.substring(beginningOfCurrWord).trim());
            }
            return splitGames;
        }
    }
}
