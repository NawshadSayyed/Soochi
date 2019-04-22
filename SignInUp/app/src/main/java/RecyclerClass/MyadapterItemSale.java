package RecyclerClass;

import android.app.Activity;
import android.content.Context;
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


import Sale.Sale;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import RealmClasses.DetailsItemSale;

public class MyadapterItemSale extends RecyclerView.Adapter<MyadapterItemSale.MyViewHolder> {
    private ArrayList<DetailsItemSale> list;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public  class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // each data item is just a string in this case
        TextView view, view_1, view_2, view_3;
        MyClickListener listener;
        Button  delete;

        public MyViewHolder(View v,MyClickListener listener) {
            super(v);
            view = (TextView) v.findViewById(R.id.details);
            view_1 = (TextView) v.findViewById(R.id.date);
            view_2 = (TextView) v.findViewById(R.id.text_View4);
            view_3 = (TextView) v.findViewById(R.id.title);
            delete = (Button) v.findViewById(R.id.delete);





        }


        @Override
        public void onClick(View view) {




        }




    }

    public interface MyClickListener {
        void onEdit(int p);
    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public MyadapterItemSale(ArrayList<DetailsItemSale> list) {
        this.list = list;
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
            public void onEdit(int position) {


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

        // for edit



           try { // if(Integer.parseInt(hashMap.get(1)) == position) {
               holder.view_1.setText(list.get(position).getsubtotal());
               holder.view_2.setText(list.get(position).getTaxvalue());
               holder.view.setText(String.valueOf(Float.parseFloat(list.get(position).getsubtotal()) + Float.parseFloat(list.get(position).getTaxvalue())));

               holder.view_3.setText(list.get(position).getName());
           }catch(Exception e){}

             // to delete the item from Sale
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    list.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, list.size());


                    Realm.init(v.getContext());
                    RealmConfiguration config3 = new RealmConfiguration.Builder()
                            .name("DetailsItemSale")
                            .schemaVersion(1)
                            .deleteRealmIfMigrationNeeded()
                            .build();
                    Realm realm_3 = Realm.getInstance(config3);
                    realm_3.beginTransaction();
                    RealmResults results = realm_3.where(DetailsItemSale.class).findAll();

                    if(list.size() >= 1) {
                        results.deleteFromRealm(position);
                    }
                    else {
                            realm_3.delete(DetailsItemSale.class);
                    }

                   realm_3.commitTransaction();

                RealmConfiguration config4 = new RealmConfiguration.Builder()
                        .name("DetailsItemSale1")
                        .schemaVersion(1)
                        .deleteRealmIfMigrationNeeded()
                        .build();
                Realm realm_4 = Realm.getInstance(config4);
                realm_4.beginTransaction();
                RealmResults results_1 = realm_4.where(DetailsItemSale.class).findAll();

                try {
                    if (list.size() >= 1) {
                        results_1.deleteFromRealm(position);
                    } else {
                        realm_4.delete(DetailsItemSale.class);
                    }
                }catch(Exception e){
                    Log.d("error for deleting second list", e.getMessage());
                }

                realm_4.commitTransaction();
                    v.getContext().startActivity(new Intent(v.getContext(), Sale.class));
                    ((Activity)(v.getContext())).finish();
            }
        });









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