package com.example.gameroommanager.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gameroommanager.R;
import com.example.gameroommanager.adapters.ConsoleAdapter;
import com.example.gameroommanager.database.MyDataBase;
import com.example.gameroommanager.models.Console;

import java.util.List;

public class ConsoleFragment extends Fragment {

    private static ConsoleFragment consoleFragment;

    public static ConsoleFragment getInstance(){
        if(consoleFragment == null){
            consoleFragment = new ConsoleFragment();
        }
        return consoleFragment;
    }

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plain_recyclerview_layout, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerview_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        setAdapterAsynchronous();

        return rootView;
    }

    private void setAdapterAsynchronous(){
        final FragmentActivity fragmentActivity = getActivity();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final List<Console> consoles = MyDataBase.getInstance().getConsoleDao().getAllConsoles();

                fragmentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ConsoleAdapter consoleAdapter = new ConsoleAdapter(consoles);
                        recyclerView.setAdapter(consoleAdapter);
                    }
                });
            }
        });
    }
}
