package com.cloudstudio.reading.adapter.fregment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.cloudstudio.reading.R;

import java.util.List;

/**
 * @ClassName DashboardFragmentPagerAdapter
 * @Author Create By matrix
 * @Date 2024/8/25 15:34
 */
public class DashboardFragmentPagerAdapter extends  RecyclerView.Adapter<DashboardFragmentPagerAdapter.ViewHolder> {
    private List<String> items;
    private int mLayView;

    public DashboardFragmentPagerAdapter(List<String> items,int layView) {
        this.items = items;
        this.mLayView=layView;
    }

    @NonNull
    @Override
    public DashboardFragmentPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayView, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardFragmentPagerAdapter.ViewHolder holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.classificationFragmentTitle);
        }
    }
}
