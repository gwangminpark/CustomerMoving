package insung.moving.customer.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import insung.moving.customer.adapter.recyclerview.BaseRecyclerViewAdapter;
import insung.moving.customer.R;
import insung.moving.customer.databinding.AddressItemBinding;

public class AddressItemAdapter extends BaseRecyclerViewAdapter<String, AddressItemAdapter.ViewHolder> {
    private int position = -1;
    public AddressItemAdapter(Context context) {
        super(context);
    }

    @Override
    protected SharedPreferences getSharedPreferences(String prefName_start, int modePrivate) {
        return null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.address_item, parent, false));
    }

    @Override
    public void onBindView(ViewHolder holder, int position) {


        if(this.position == position){
            // select
            holder.binding.tvLocation.setSelected(true);
        }else{
            // unselect
            holder.binding.tvLocation.setSelected(false);
        }
        holder.binding.tvLocation.setText(items.get(position).toString());

    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private AddressItemBinding binding;

        private ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void addItems(String item) {
        super.addItems(item);
    }
}