package com.example.simpletodo;

import android.service.autofill.TextValueSanitizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }
    List<String> items;
    OnLongClickListener longClickListener;
    public ItemsAdapter(List<String> items,OnLongClickListener longClickListener) {
    this.items = items;
    this.longClickListener=longClickListener;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        //use layout
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent, false);
        //wrap it
        return new ViewHolder(todoView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //grab the item
        String item = items.get(position);
        holder.bind(item);
        //bind

    }
//tell how many items
    @Override
    public int getItemCount() {
        return items.size();
    }

    // container to provide ez access to views that represent each row of list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //update view
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //notify pos long clicked
                    longClickListener.onItemLongClicked(getAdapterPosition());

                    return true;
                }
            });
        }
    }
}
