package teka.mobile.RealtimeFirebaseDb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Model> mList;
    Context context;

    public MyAdapter(Context context , ArrayList<Model> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item , parent ,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());
        holder.quantity.setText(model.getQuantity());
        holder.recordTime.setText(model.getDate());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name , price , quantity, recordTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            recordTime = itemView.findViewById(R.id.record_time);
            name = itemView.findViewById(R.id.feed_name);
            price = itemView.findViewById(R.id.feed_price);
            quantity = itemView.findViewById(R.id.feed_quantity);
        }
    }
}
