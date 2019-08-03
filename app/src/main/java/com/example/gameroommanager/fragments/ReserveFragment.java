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
import com.example.gameroommanager.adapters.ReserveAdapter;
import com.example.gameroommanager.database.MyDataBase;
import com.example.gameroommanager.models.Console;
import com.example.gameroommanager.models.Reserve;

import java.util.List;

public class ReserveFragment extends Fragment {

    private static ReserveFragment reserveFragment;

    public static ReserveFragment getInstance() {
        if (reserveFragment == null) {
            reserveFragment = new ReserveFragment();
        }
        return reserveFragment;
    }

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.plain_recyclerview_layout, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerview_id);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter();

        return rootView;
    }

    private void setAdapter() {
        MyDataBase myDataBase = MyDataBase.getInstance();
        if (myDataBase == null) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        final FragmentActivity fragmentActivity = getActivity();

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                final List<Reserve> reserves = MyDataBase.getInstance().getReserveDao().getAllReserves();
                fragmentActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ReserveAdapter reserveAdapter = new ReserveAdapter();
                        reserveAdapter.setReserves(reserves);
                        recyclerView.setAdapter(reserveAdapter);
                    }
                });
            }
        });


    }
}
