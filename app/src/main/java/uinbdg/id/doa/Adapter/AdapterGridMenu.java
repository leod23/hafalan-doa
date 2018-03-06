package uinbdg.id.doa.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uinbdg.id.doa.Model.GridMenu;
import uinbdg.id.doa.R;

/**
 * Created by Comp on 2/11/2018.
 */

public class AdapterGridMenu extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<GridMenu> items = new ArrayList<>();

    private OnLoadMoreListener onLoadMoreListener;

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, String obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterGridMenu(Context context, List<GridMenu> items) {
        this.items = items;
        ctx = context;
    }


    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView title;
        public View ly_parent;

        public OriginalViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            title = (TextView) v.findViewById(R.id.title);
            ly_parent= (View) v.findViewById(R.id.lyt_parent);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_menu, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        GridMenu gridMenu = items.get(position);
        if (holder instanceof OriginalViewHolder) {
            ((OriginalViewHolder) holder).image.setImageResource(gridMenu.getIcon());
            ((OriginalViewHolder) holder).title.setText(gridMenu.getTitle());

            OriginalViewHolder view = (OriginalViewHolder) holder;
            view.ly_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position).getTitle(), position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnLoadMoreListener {
        void onLoadMore(int current_page);
    }

}
