package RecyclerClass;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prathamesh.Authentication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import Extra.Cardname;
import RealmClasses.SaleInput;
import RealmClasses.User;

   public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    private ArrayList<SaleInput> list;

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
    public Myadapter(ArrayList<SaleInput> list) {
       this.list = list;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public Myadapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);


        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    int p = 0;
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Log.d("ppppp", "" + String.valueOf(position) + " " + String.valueOf(p));

            float total_1 = 0;
            if (position == 0) {
                holder.textView_1.setText(list.get(position).getName());
                holder.textView_2.setText(Float.toString(list.get(position).getTotal()));


            } else {


                for (int i = 0; i < list.size(); i++) {
                    if (position != i) {
                        boolean value = list.get(position).getName().equals((list.get(i).getName()));
                        if (!value) {

                                holder.textView_1.setText(list.get(position).getName());
                                holder.textView_2.setText(String.valueOf(list.get(position).getTotal()));

                        }
                    }
                }
            }

            float total = 0;
            int a = 0, k = 0;
            // To add the total amount on the bills of a unique customer
            for (int i = 0; i < list.size(); i++) {

                boolean value = list.get(position).getName().equals((list.get(i).getName()));
                if (value) {
                    if (i <= a) {
                        a = i;
                    }
                    total = total + list.get(i).getTotal();

                }
            }
            if (position == a) {
                holder.textView_2.setText(Float.toString(total));


            }


            holder.textView_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.textView_1.getContext(), Cardname.class);
                    intent.putExtra("Value", list.get(position).getName());
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