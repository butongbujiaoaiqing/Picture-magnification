package com.bwie.wangbingyang20170918.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.wangbingyang20170918.R;
import com.bwie.wangbingyang20170918.bean.RecyclerViewBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * 作者：王兵洋  2017/9/18 08:54
 * 类的用途：RecyclerView的适配器
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<RecyclerViewBean.TopStoriesBean> mStoriesBeen;
    private OnItemClickLitener mOnItemClickLitener;

    public RecyclerViewAdapter(Context context, List<RecyclerViewBean.TopStoriesBean> storiesBeen) {
        mContext = context;
        mStoriesBeen = storiesBeen;
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item, null));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tv.setText(mStoriesBeen.get(position).getTitle());
        // 通过ImageLoader来获取网络图片
        ImageLoader.getInstance().displayImage(mStoriesBeen.get(position).getImage(), holder.img);
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mStoriesBeen != null ? mStoriesBeen.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);

            tv = itemView.findViewById(R.id.tv);
            img = itemView.findViewById(R.id.img);

        }
    }
}
