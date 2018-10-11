package insung.moving.customer.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import insung.moving.customer.R;
import insung.moving.customer.databinding.AddressItemBinding;
import insung.moving.customer.model.OrderlistData;

/**
 * Created by user on 2018-07-11.
 */

public class MyOrderlistAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private ArrayList<OrderlistData> mItems;
    private Context mContext;
    public MyOrderlistAdapter (ArrayList itemList) {
        mItems = itemList;
    }
    // 필수 오버라이드 : View 생성, ViewHolder 호출
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate( R.layout.myorder_item, parent, false);
        mContext = parent.getContext();
        RecyclerViewHolder holder = new RecyclerViewHolder(v);
        return holder;
    }
    // 필수 오버라이드 : 재활용되는 View 가 호출, Adapter 가 해당 position 에 해당하는 데이터를 결합
    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, final int position) {
        // 해당 position 에 해당하는 데이터 결합
        holder.order_day.setText(mItems.get(position).getOrder_day());
        holder.moving_type.setText(mItems.get(position).getMoving_type());
        holder.moving_day.setText(mItems.get(position).getMoving_day());
        holder.start_addrses.setText( mItems.get(position).getStart_adderss());
        holder.finish_address.setText( mItems.get(position).getFinish_address());

        // 이벤트처리 : 생성된 List 중 선택된 목록번호를 Toast로 출력
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, String.format("%d 선택", position + 1), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // 필수 오버라이드 : 데이터 갯수 반환
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AddressItemBinding binding;

        private ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
