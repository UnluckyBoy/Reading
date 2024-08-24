package com.cloudstudio.reading.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cloudstudio.reading.R;
import com.cloudstudio.reading.util.BookBean;
import com.cloudstudio.reading.util.GlideRoundCornersTransUtils;

import java.util.List;

/**
 * @ClassName ItemAdapter
 * @Author Create By matrix
 * @Date 2024/8/16 20:39
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<BookBean> bookBean;
    private Activity mActivity;

    public ItemAdapter(Activity mActivity, List<BookBean> list){
        this.bookBean=list;
        this.mActivity=mActivity;

    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        if(bookBean.get(position)!=null){
            SetPic(holder,bookBean.get(position).getPic());
        }

        holder.book_title.setText(bookBean.get(position).getName());
        holder.book_hot.setText(bookBean.get(position).getHot());
        holder.writer.setText(bookBean.get(position).getWriter());
        holder.type.setText(bookBean.get(position).getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getLayoutPosition();
                OnItemClickListener.onItemClick(holder.itemView,position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int position = holder.getLayoutPosition();
                OnItemClickListener.onItemLongClick(holder.itemView,position);
                return false;
            }
        });
    }

    private void SetPic(ItemAdapter.ViewHolder viewHolder,String path){
        if(path!=null){
            Glide.with(mActivity)
                    .load(path)
                    .centerCrop()
                    .placeholder(R.mipmap.warning)
                    .error(R.mipmap.warning)
                    .into(viewHolder.book_pic);
        }
    }

    @Override
    public int getItemCount() {
        return bookBean.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView book_pic;
        private final TextView book_hot;
        private final TextView book_title;
        private final TextView writer;
        private final TextView type;
        public ViewHolder(View itemView) {
            super(itemView);
            book_pic=itemView.findViewById(R.id.book_image);
            book_hot=itemView.findViewById(R.id.hot);
            book_title=itemView.findViewById(R.id.book_title);
            writer=itemView.findViewById(R.id.b_writer);
            type=itemView.findViewById(R.id.b_type);
        }
    }

    public interface OnItemClickListener{
        void  onItemClick(View view, int position);
        void  onItemLongClick(View view, int position);
    }
    private ItemAdapter.OnItemClickListener OnItemClickListener;
    private ItemAdapter.OnItemClickListener OnItemLongClickListener;
    public void SetOnItemClickListener(ItemAdapter.OnItemClickListener onItemClickListener){
        OnItemClickListener =onItemClickListener;
    }
    public void SetOnItemLongClickListener(ItemAdapter.OnItemClickListener onItemLongClickListener){
        OnItemLongClickListener =onItemLongClickListener;
    }
}
