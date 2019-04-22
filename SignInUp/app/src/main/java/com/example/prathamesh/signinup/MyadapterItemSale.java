package com.example.prathamesh.signinup;

import android.content.Context;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import name.AddLineItem;
import name.Cardname;
import name.DetailsItemSale;
import name.SaleInput;
import name.User;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static java.lang.System.out;

public class MyadapterItemSale extends RecyclerView.Adapter<MyadapterItemSale.MyViewHolder> {
    private ArrayList<DetailsItemSale> list;
    private  HashMap<Integer, String> hashMap;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // each data item is just a string in this case
        TextView view, view_1, view_2, view_3;
        MyClickListener listener;
        Button edit, delete;

        public MyViewHolder(View v,MyClickListener listener) {
            super(v);
            view = (TextView) v.findViewById(R.id.details);
            view_1 = (TextView) v.findViewById(R.id.date);
            view_2 = (TextView) v.findViewById(R.id.text_View4);
            view_3 = (TextView) v.findViewById(R.id.title);
            delete = (Button) v.findViewById(R.id.delete);
            edit = (Button) v.findViewById(R.id.edit);


            this.listener = listener;
            edit.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            Intent intent = new Intent(view.getContext(), AddLineItem.class);
            intent.putExtra("position", getAdapterPosition());
            intent.putExtra("key", 1);
           // Log.d("nnnn", "" + String.valueOf(getAdapterPosition()));
            view.getContext().startActivity(intent);





        }




    }

    public interface MyClickListener {
        void onEdit(int p);
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyadapterItemSale(ArrayList<DetailsItemSale> list,  HashMap<Integer, String> hashMap) {
        this.list = list;
        this.hashMap = hashMap;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyadapterItemSale.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sale_recycler, parent, false);


        MyViewHolder vh = new MyViewHolder(view, new MyClickListener() {
            @Override
            public void onEdit(int p) {


            }
        });

        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
      //  holder.view.setText(list.getTaxvalue());

       // Log.d("adaptervalue", "" + hashMap.get);
        Boolean value = hashMap.containsValue(position);
        // for edit
        Log.d("yess", "" + hashMap.get(position)+ "  " + String.valueOf(position));
        if(value == Boolean.TRUE) {


            holder.view_1.setText(list.get(position).getsubtotal());
            holder.view_2.setText(list.get(position).getTaxvalue());
            holder.view.setText(String.valueOf(Float.parseFloat(list.get(position).getsubtotal()) + Float.parseFloat(list.get(position).getTaxvalue())));

            holder.view_3.setText(list.get(position).getName());

            /*holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }); */

        }
        else
        {

            holder.view_1.setText(list.get(position).getsubtotal());
            holder.view_2.setText(list.get(position).getTaxvalue());
            holder.view.setText(String.valueOf(Float.parseFloat(list.get(position).getsubtotal()) + Float.parseFloat(list.get(position).getTaxvalue())));

            holder.view_3.setText(list.get(position).getName());

        }



        /*holder.textView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.textView_1.getContext(), Cardname.class);
                intent.putExtra("Value", list.get(position).getUsername());
                holder.textView_1.getContext().startActivity(intent);
            }
        }); */


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }

    private  Context context;
    public MyadapterItemSale(Context context)
    {
        this.context = context;
    }


}