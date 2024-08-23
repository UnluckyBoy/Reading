package com.cloudstudio.reading.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cloudstudio.reading.activitys.MainActivity;
import com.cloudstudio.reading.util.BannerDataInfo;
import com.youth.banner.adapter.BannerAdapter;
import com.youth.banner.listener.OnBannerListener;

import java.util.List;

/**
*@ClassName BannerImageAdapter
*@Author Create By matrix
*@Date 2024-08-13 13:53
*/public class BannerImageAdapter extends BannerAdapter<BannerDataInfo, BannerImageAdapter.BannerViewHolder> {
    public BannerImageAdapter(List<BannerDataInfo> datas) {
        super(datas);
    }

    @Override
    public BannerViewHolder onCreateHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        //注意，必须设置为match_parent，这个是viewpager2强制要求的
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return new BannerViewHolder(imageView);
    }

    @Override
    public void onBindView(BannerViewHolder holder, BannerDataInfo data, int position, int size) {
        holder.imageView.setImageResource(data.getImageRes());
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder{
        private final ImageView imageView;
        public BannerViewHolder(@NonNull ImageView view) {
            super(view);
            this.imageView = view;
        }
    }
}
