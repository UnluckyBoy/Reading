package com.cloudstudio.reading.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.cloudstudio.reading.R;
import com.cloudstudio.reading.util.BookBean;
import com.cloudstudio.reading.util.DipPx;
import com.cloudstudio.reading.util.GlideRoundCornersTransUtils;

import java.security.MessageDigest;
import java.util.List;

/**
 * @ClassName ImageItemAdapter
 * @Author Create By matrix
 * @Date 2024/8/23 23:08
 */
public class ImageItemAdapter extends RecyclerView.Adapter<ImageItemAdapter.ViewHolder>{
    private List<BookBean> bookBean;
    private Activity mActivity;

    public ImageItemAdapter(Activity mActivity, List<BookBean> list){
        this.bookBean=list;
        this.mActivity=mActivity;

    }

    @NonNull
    @Override
    public ImageItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_view,null);
        ImageItemAdapter.ViewHolder viewHolder=new ImageItemAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageItemAdapter.ViewHolder holder, int position) {
        if(bookBean.get(position)!=null){
            SetPic(holder,bookBean.get(position).getPic());
        }

        holder.bookScore.setText("9.9分");
        holder.bookType.setText(bookBean.get(position).getType());
        //holder.bookRating.setText(bookBean.get(position).getWriter());
        holder.bookName.setText(bookBean.get(position).getName());
        holder.itemDescription.setText(bookBean.get(position).getDes());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getLayoutPosition();
                OnImageItemClickListener.onImageItemClick(holder.itemView,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookBean.size();
    }

    private void SetPic(ImageItemAdapter.ViewHolder viewHolder,String path){
        if(path!=null){
            Glide.with(mActivity)
                    .load(path)
                    .centerCrop()
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.warning)
                            .error(R.mipmap.warning)
                            .transform(new GlideRoundCornersTransUtils(
                                    mActivity,
                                    DipPx.dip2px(mActivity, 6),true, false, true, false)))
                    .into(viewHolder.bookPic);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView bookPic;
        private final TextView bookScore;
        private final TextView bookType;
        private final ImageView bookRating;
        private final TextView bookName;
        private final TextView itemDescription;
        public ViewHolder(View itemView) {
            super(itemView);
            bookPic=itemView.findViewById(R.id.itemImageView);
            bookScore=itemView.findViewById(R.id.itemScoreView);
            bookType=itemView.findViewById(R.id.itemTypeView);
            bookRating=itemView.findViewById(R.id.itemRatingView);
            bookName=itemView.findViewById(R.id.itemNameView);
            itemDescription=itemView.findViewById(R.id.itemDescriptionView);
        }
    }

    public interface OnImageItemClickListener{
        void  onImageItemClick(View view, int position);
    }
    private ImageItemAdapter.OnImageItemClickListener OnImageItemClickListener;
    public void SetOnImageItemClickListener(ImageItemAdapter.OnImageItemClickListener onImageItemClickListener){
        OnImageItemClickListener =onImageItemClickListener;
    }
}
