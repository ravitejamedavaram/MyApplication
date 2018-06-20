package com.example.ravitejam.myapplication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.List;

public class ButtonAdapter extends BaseAdapter {
    private Context context;
    private List<String> pins;

    public ButtonAdapter(Context context, List<String> pins){
        this.context=context;
        this.pins=pins;
    }

    @Override
    public int getCount() {
        return pins.size();
    }

    @Override
    public Object getItem(int i) {
        return pins.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        Button button = new Button(context);
        button.setText(pins.get(i).toString());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,
                        pins.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return button;
    }
}
