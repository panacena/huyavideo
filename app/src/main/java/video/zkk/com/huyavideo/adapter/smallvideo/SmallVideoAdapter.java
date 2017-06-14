package video.zkk.com.huyavideo.adapter.smallvideo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import video.zkk.com.huyavideo.R;
import video.zkk.com.huyavideo.bean.smallvideo.SmallVideoBean;

/**
 * Created by zkk on 2017/6/4 0004.
 */

public class SmallVideoAdapter extends RecyclerView.Adapter<SmallVideoAdapter.MyItemSmallVideoHoler> {



    private Context mContext;

    private List<SmallVideoBean.DataBean.ListBean> mListBeen = new ArrayList<SmallVideoBean.DataBean.ListBean>();

    private OnItemClickListener mOnItemClickListener;

    public SmallVideoAdapter(Context context) {
        mContext = context;
    }

    public SmallVideoAdapter(Context context, List<SmallVideoBean.DataBean.ListBean> listBeen) {
        mContext = context;
        mListBeen = listBeen;
        notifyDataSetChanged();
    }

    public void setList(List<SmallVideoBean.DataBean.ListBean> beanList) {
        mListBeen.clear();
        mListBeen.addAll(beanList);
        notifyDataSetChanged();
    }

    @Override
    public MyItemSmallVideoHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View ret = LayoutInflater.from(mContext).inflate(R.layout.item_smallvideo, parent, false);
        return new MyItemSmallVideoHoler(ret);

    }

    @Override
    public void onBindViewHolder(final MyItemSmallVideoHoler holder, int position) {

        SmallVideoBean.DataBean.ListBean fuliBean = mListBeen.get(position);
        holder.mIvItemFuliImage.setImageURI(fuliBean.getVideo_vertical_cover());
        holder.mTvItemFuliTitle.setText(fuliBean.getNickname());
        holder.mTvItemFuliCount.setText(fuliBean.getCate2_name());

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

        @BindView(R.id.iv_item_fuli_image)
        SimpleDraweeView mIvItemFuliImage;
        @BindView(R.id.tv_item_fuli_title)
        TextView mTvItemFuliTitle;
        @BindView(R.id.tv_item_fuli_count)
        TextView mTvItemFuliCount;
        @BindView(R.id.ll_item_smallvideo)
        LinearLayout ll_item_smallvideo;

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