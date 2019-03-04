package com.example.prathamesh.signinup;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import name.Cardname;
import name.User;

  class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<User> list;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView_1, textView_2;

        public MyViewHolder(View v) {
            super(v);
            textView_1 = (TextView) v.findViewById(R.id.textView14);
            textView_2 = (TextView) v.findViewById(R.id.textView15);

        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<User> list) {
       this.list = list;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);


        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.textView_1.setText(list.get(position).getUsername());
        holder.textView_2.setText(Integer.toString(list.get(position).getPhonenumber()));

        holder.textView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.textView_1.getContext(), Cardname.class);
                intent.putExtra("Value", list.get(position).getUsername());
                holder.textView_1.getContext().startActivity(intent);
            }
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}