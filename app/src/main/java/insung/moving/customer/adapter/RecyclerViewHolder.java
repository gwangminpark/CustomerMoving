package insung.moving.customer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import insung.moving.customer.R;

/**
 * Created by user on 2018-07-11.
 */

class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView order_day;
    public TextView moving_type;
    public TextView moving_day;
    public TextView start_addrses;
    public TextView finish_address;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        order_day = (TextView) itemView.findViewById( R.id.order_day);
        moving_type = (TextView) itemView.findViewById(R.id.type);
        moving_day = (TextView) itemView.findViewById( R.id.moving_day);
        start_addrses = (TextView) itemView.findViewById(R.id.start_address);
        finish_address = (TextView) itemView.findViewById( R.id.finish_address);

    }
}
