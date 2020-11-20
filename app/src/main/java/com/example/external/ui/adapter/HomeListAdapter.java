package com.example.external.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.external.R;
import com.example.external.mvp.bean.ProductBean;
import com.example.external.utils.DataUtils;

import java.util.ArrayList;

/**
 * @ClassName: HomeListAdapter
 * @Description:
 * @CreateDate: 2020/11/20 22:50
 * @Creator: lf
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ProductBean.DataBean.ViplistBean> data;

    public HomeListAdapter(Context context, ArrayList<ProductBean.DataBean.ViplistBean> data) {
        this.context = context;
        this.data = data;
    }

    public void setData(ArrayList<ProductBean.DataBean.ViplistBean> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_list, parent, false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(data.get(position).getIcon_url()).into(holder.image_logo);
        holder.list_names.setText(data.get(position).getCash_name());
        holder.money_scope.setText("₹" + DataUtils.addComma(data.get(position).getMax_interest()) + " ~ " + "₹" + DataUtils.addComma(data.get(position).getMax_amount()));
        holder.apply.setOnClickListener(v -> {
            final Uri uri = Uri.parse(data.get(position).getDownload_url());
            final Intent it = new Intent(Intent.ACTION_VIEW, uri);
            context.startActivity(it);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image_logo;
        private TextView list_names, money_scope, apply;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image_logo = itemView.findViewById(R.id.image_logo);
            list_names = itemView.findViewById(R.id.list_names);
            money_scope = itemView.findViewById(R.id.money_scope);
            apply = itemView.findViewById(R.id.apply);
        }
    }
}
