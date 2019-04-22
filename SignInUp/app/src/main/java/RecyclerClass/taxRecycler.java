package RecyclerClass;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.prathamesh.Authentication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import Extra.Cardname;
import RealmClasses.DetailsItemSale;
import RealmClasses.SaleInput;
import RealmClasses.Tax_List;
import RealmClasses.User;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class taxRecycler extends RecyclerView.Adapter<taxRecycler.MyViewHolder> {
    private ArrayList<Tax_List> list;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView_1;
        public Button delete;


        public MyViewHolder(View v) {
            super(v);
            textView_1 = (TextView) v.findViewById(R.id.textView14);
            delete = (Button) v.findViewById(R.id.button14);

        }
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public taxRecycler(ArrayList<Tax_List> list) {
        this.list = list;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public taxRecycler.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tax_recycler_list, parent, false);


        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        try {
            holder.textView_1.setText(list.get(position).getTax());
            Log.d("error_tax", list.get(position).getTax());
        }catch(Exception e)
        {

        }

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, list.size());

                // Deleting from the database
                RealmConfiguration config = new RealmConfiguration.Builder()
                        .name("Tax_List")
                        .schemaVersion(1)
                        .deleteRealmIfMigrationNeeded()
                        .build();
                Realm realm = Realm.getInstance(config);
                realm.beginTransaction();
                RealmResults results = realm.where(Tax_List.class).findAll();

                try {
                    if (list.size() >= 1) {
                        results.deleteFromRealm(position);
                    } else {
                        realm.delete(DetailsItemSale.class);
                    }
                }catch(Exception e){}

                realm.commitTransaction();

            }
        });



    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}
