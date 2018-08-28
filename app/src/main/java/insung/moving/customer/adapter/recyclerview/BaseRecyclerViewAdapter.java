package insung.moving.customer.adapter.recyclerview;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by kim on 2017-07-15.
 */

public abstract class BaseRecyclerViewAdapter<T, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected ArrayList<T> items;
    protected Context context;
    protected OnItemClickListener onItemClickListener;
    protected OnItemLongClickListener onItemLongClickListener;

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    SharedPreferences prefs = getSharedPreferences( "PrefName_start", MODE_PRIVATE );
                  /*  if(prefs.getString( "Start_position1", "" ) !=null) {
                        onItemClickListener.onItemClick( view, Integer.parseInt( prefs.getString( "Start_position1", "" ) ) );
                        Log.i( "gho", prefs.getString( "Start_position1", "" ) );
                        holder.itemView.setBackgroundResource( R.color.title_blue );
                    }*/
                    onItemClickListener.onItemClick( view, position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (onItemLongClickListener != null) {
                    onItemLongClickListener.onItemLongClick(view, position);
                }

                return false;
            }
        });

        onBindView((H) holder, position);
    }

    protected abstract SharedPreferences getSharedPreferences(String prefName_start, int modePrivate);

    abstract public void onBindView(H holder, int position);

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        }

        return 0;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public void initItems(ArrayList<T> list) {
        if (items == null) {
            items = new ArrayList<>();
        } else {
            items.clear();
        }

        if (list != null) {
            items.addAll(list);
        }

        notifyDataSetChanged();
    }

    public void setItem(T item) {
        if (items == null) {
            items = new ArrayList<>();
        } else {
            items.clear();
        }

        if (item != null) {
            items.add(item);
        }

        notifyDataSetChanged();
    }

    public void addItems(T item) {
        if (items == null) {
            items = new ArrayList<>();
        }

        if (item != null) {
            items.add(item);
        }

        notifyDataSetChanged();
    }

    public void addItems(ArrayList<T> items) {
        if (this.items == null) {
            this.items = items;
        } else {
            this.items.addAll(items);
        }

        notifyDataSetChanged();
    }

    public ArrayList<T> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }

        return items;
    }

    public T getItem(int position) {
        try {
            if (items != null) {
                return items.get(position);
            }
        } catch (Exception e) {

        }

        return null;
    }

    public void clear() {
        if (items != null) {
            items.clear();
        } else {
            items = new ArrayList<>();
        }

        notifyDataSetChanged();
    }

    public void remove(int position){
        if(items == null){
            return;
        }

        items.remove(position);
        notifyDataSetChanged();
    }
}
