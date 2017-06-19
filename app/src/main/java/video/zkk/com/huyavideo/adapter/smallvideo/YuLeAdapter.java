package video.zkk.com.huyavideo.adapter.smallvideo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import video.zkk.com.huyavideo.R;
import video.zkk.com.huyavideo.bean.smallvideo.SmallVideoBean;
import video.zkk.com.huyavideo.bean.smallvideo.YuLeBean;

/**
 * Created by zkk on 2017/6/4 0004.
 */

public class YuLeAdapter extends RecyclerView.Adapter<YuLeAdapter.MyItemSmallVideoHoler> {



    private Context mContext;

    private List<YuLeBean.DataBean> mListBeen = new ArrayList<YuLeBean.DataBean>();

    private OnItemClickListener mOnItemClickListener;

    public YuLeAdapter(Context context) {
        mContext = context;
    }

    public YuLeAdapter(Context context, List<YuLeBean.DataBean> listBeen) {
        mContext = context;
        mListBeen = listBeen;
        notifyDataSetChanged();
    }

    public void setList(List<YuLeBean.DataBean> beanList) {
        mListBeen.clear();
        mListBeen.addAll(beanList);
        notifyDataSetChanged();
    }

    @Override
    public MyItemSmallVideoHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View ret = LayoutInflater.from(mContext).inflate(R.layout.item_yule, parent, false);
        return new MyItemSmallVideoHoler(ret);

    }

    @Override
    public void onBindViewHolder(final MyItemSmallVideoHoler holder, int position) {

        YuLeBean.DataBean yuLeBean = mListBeen.get(position);

        Glide.with(mContext)
                .load(yuLeBean.getVideo_pic())
                .into( holder.mIvItemFuliImage);

        holder.iv_item_header.setImageURI(yuLeBean.getAuthorIcon());
        holder.mTvItemFuliTitle.setText(yuLeBean.getAuthor());
        holder.mTvItemFuliCount.setText(yuLeBean.getTagName());
        holder.mTvItemFuliCount.setVisibility(View.GONE);
        holder.tv_item_yule_title_name.setText(yuLeBean.getContents());
        holder.ll_item_smallvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mOnItemClickListener != null) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        if (mListBeen != null) {
            Log.d("zkk--", mListBeen.size() + "");
        }

        return mListBeen == null ? 0 : mListBeen.size();
    }


    class MyItemSmallVideoHoler extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_item_yule_image)
        ImageView mIvItemFuliImage;


        @BindView(R.id.tv_item_fuli_title)
        TextView mTvItemFuliTitle;
        @BindView(R.id.tv_item_fuli_count)
        TextView mTvItemFuliCount;
        @BindView(R.id.ll_item_smallvideo)
        LinearLayout ll_item_smallvideo;

        @BindView(R.id.iv_item_header)
        SimpleDraweeView iv_item_header;

        @BindView(R.id.tv_item_yule_title_name)
        TextView tv_item_yule_title_name;

        public MyItemSmallVideoHoler(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;

    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}