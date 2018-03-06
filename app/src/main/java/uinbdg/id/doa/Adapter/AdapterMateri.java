package uinbdg.id.doa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uinbdg.id.doa.Activities.DetailMateriActivity;
import uinbdg.id.doa.Model.Doa;
import uinbdg.id.doa.R;

/**
 * Created by pragmadev on 3/5/18.
 */

public class AdapterMateri extends RecyclerView.Adapter<AdapterMateri.MyHolder> {
    List<Doa> list;
    Context context;

    public AdapterMateri(Context context, List<Doa> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_doa, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
            holder.namaDoa.setText(list.get(position).getNamaDoa());
            holder.viewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailMateriActivity.class);
                    intent.putExtra("nama_doa",list.get(position).getNamaDoa());
                    intent.putExtra("lapadz",list.get(position).getLapadz());
                    intent.putExtra("arti",list.get(position).getArti());
                    intent.putExtra("latin",list.get(position).getLatin());
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nama_doa)
        TextView namaDoa;
        @BindView(R.id.view_item)
        LinearLayout viewItem;

        public MyHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
