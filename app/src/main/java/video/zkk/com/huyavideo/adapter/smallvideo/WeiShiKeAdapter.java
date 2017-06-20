package video.zkk.com.huyavideo.adapter.smallvideo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import video.zkk.com.huyavideo.R;
import video.zkk.com.huyavideo.bean.smallvideo.ShengHuoYuLeBean;
import video.zkk.com.huyavideo.utils.TimeUtils;

/**
 * Created by zkk on 2017/6/4 0004.
 */

public class WeiShiKeAdapter extends RecyclerView.Adapter<WeiShiKeAdapter.MyItemWeishikeHoler> {



    private Context mContext;

    private List<ShengHuoYuLeBean.DataBean> mDataBeen = new ArrayList<ShengHuoYuLeBean.DataBean>();

    private OnItemClickListener mOnItemClickListener;

    public WeiShiKeAdapter(Context context) {
        mContext = context;
    }

    public WeiShiKeAdapter(Context context, List<ShengHuoYuLeBean.DataBean> ShengHuoYuLeBean) {
        mContext = context;
        mDataBeen = ShengHuoYuLeBean;
        notifyDataSetChanged();
    }

    public void setList(List<ShengHuoYuLeBean.DataBean> beanList) {
        mDataBeen.clear();
        mDataBeen.addAll(beanList);
        notifyDataSetChanged();
    }

    @Override
    public MyItemWeishikeHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View ret = LayoutInflater.from(mContext).inflate(R.layout.item_video_weishike, parent, false);


        return new MyItemWeishikeHoler(ret);
    }

    @Override
    public void onBindViewHolder(final MyItemWeishikeHoler holder, int position) {

        ShengHuoYuLeBean.DataBean mshengHuoYuLeBean = mDataBeen.get(position);
        holder.mImgItemGridview.setImageURI(mshengHuoYuLeBean.getVideo_cover());
        holder.mTvColumnItemNickname.setText(mshengHuoYuLeBean.getVideo_title());
        holder.mTvWatchnum.setText( mshengHuoYuLeBean.getView_num()+"" );
        holder.mTvNickname.setText(mshengHuoYuLeBean.getNickname());
        holder.mTvVideoTime.setText(TimeUtils.secToTime(mshengHuoYuLeBean.getVideo_duration()));

        holder.mImgItemGridview.setOnClickListener(new View.OnClickListener() {
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

        if (mDataBeen != null) {
            Log.d("zkk--", mDataBeen.size() + "");
        }

        return mDataBeen == null ? 0 : mDataBeen.size();
    }


    class MyItemWeishikeHoler extends RecyclerView.ViewHolder {

        @BindView(R.id.img_item_gridview)
        SimpleDraweeView mImgItemGridview;
        @BindView(R.id.tv_column_item_nickname)
        TextView mTvColumnItemNickname;
        @BindView(R.id.img_nickname)
        ImageView mImgNickname;
        @BindView(R.id.tv_nickname)
        TextView mTvNickname;
        @BindView(R.id.tv_watchnum)
        TextView mTvWatchnum;
        @BindView(R.id.img_watch)
        ImageView mImgWatch;
        @BindView(R.id.tv_video_time)
        TextView mTvVideoTime;

        public MyItemWeishikeHoler(View itemView) {
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