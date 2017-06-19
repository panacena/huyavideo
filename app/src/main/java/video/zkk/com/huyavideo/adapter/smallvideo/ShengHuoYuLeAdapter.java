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
import video.zkk.com.huyavideo.bean.home.FuliBean;
import video.zkk.com.huyavideo.bean.smallvideo.ShengHuoYuLeBean;

/**
 * Created by zkk on 2017/6/4 0004.
 */

public class ShengHuoYuLeAdapter extends RecyclerView.Adapter<ShengHuoYuLeAdapter.MyItemFuliHoler> {


    private Context mContext;

    private List<ShengHuoYuLeBean.DataBean> mDataBeen = new ArrayList<ShengHuoYuLeBean.DataBean>();

    private OnItemClickListener mOnItemClickListener;

    public ShengHuoYuLeAdapter(Context context) {
        mContext = context;
    }

    public ShengHuoYuLeAdapter(Context context, List<ShengHuoYuLeBean.DataBean> ShengHuoYuLeBean) {
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
    public MyItemFuliHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View ret = LayoutInflater.from(mContext).inflate(R.layout.item_fuli, parent, false);


        return new MyItemFuliHoler(ret);
    }

    @Override
    public void onBindViewHolder(final MyItemFuliHoler holder, int position) {

        ShengHuoYuLeBean.DataBean mshengHuoYuLeBean = mDataBeen.get(position);
        holder.mIvItemFuliImage.setImageURI(mshengHuoYuLeBean.getVideo_cover());
        holder.mTvItemFuliTitle.setText(mshengHuoYuLeBean.getVideo_title());

        holder.ll_fuli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mOnItemClickListener!=null){
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
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


    class MyItemFuliHoler extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_item_fuli_image)
        SimpleDraweeView mIvItemFuliImage;
        @BindView(R.id.tv_item_fuli_title)
        TextView mTvItemFuliTitle;
        @BindView(R.id.tv_item_fuli_count)
        TextView mTvItemFuliCount;

        @BindView(R.id.ll_fuli)
        LinearLayout ll_fuli;


        public MyItemFuliHoler(View itemView) {
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