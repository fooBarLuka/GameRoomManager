package com.example.gameroommanager.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gameroommanager.R;
import com.example.gameroommanager.database.MyDataBase;
import com.example.gameroommanager.models.Console;

public class AddConsoleFragment extends Fragment {

    private static AddConsoleFragment addConsoleFragment;

    public static AddConsoleFragment getInstance(){
        if(addConsoleFragment == null){
            addConsoleFragment = new AddConsoleFragment();
        }
        return addConsoleFragment;
    }

    private EditText consoleNameEditText;
    private EditText consoleGamesEditText;
    private Button addConsoleButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_console_layout, container, false);
        consoleNameEditText = rootView.findViewById(R.id.create_console_name_edit_text_id);
        consoleGamesEditText = rootView.findViewById(R.id.create_console_games_edit_text_id);
        addConsoleButton = rootView.findViewById(R.id.create_console_button_id);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initUIActions();
    }

    private void initUIActions(){
        addConsoleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trimmedGames = consoleGamesEditText.getText().toString().trim();
                Console newConsole = new Console(consoleNameEditText.getText().toString(), trimmedGames, false);

                MyDataBase.addConsoleAsynchronous(newConsole);
            }
        });
    }
}
