package video.zkk.com.huyavideo.adapter.home;

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

/**
 * Created by zkk on 2017/6/4 0004.
 */

public class FuliAdapter extends RecyclerView.Adapter<FuliAdapter.MyItemFuliHoler> {


    private Context mContext;

    private List<FuliBean.UserBean> mFuliAdapters = new ArrayList<FuliBean.UserBean>();

    private OnItemClickListener mOnItemClickListener;

    public FuliAdapter(Context context) {
        mContext = context;
    }

    public FuliAdapter(Context context, List<FuliBean.UserBean> fuliAdapters) {
        mContext = context;
        mFuliAdapters = fuliAdapters;
        notifyDataSetChanged();
    }

    public void setList(List<FuliBean.UserBean> beanList) {
        mFuliAdapters.clear();
        mFuliAdapters.addAll(beanList);
        notifyDataSetChanged();
    }

    @Override
    public MyItemFuliHoler onCreateViewHolder(ViewGroup parent, int viewType) {

        View ret = LayoutInflater.from(mContext).inflate(R.layout.item_fuli, parent, false);


        return new MyItemFuliHoler(ret);
    }

    @Override
    public void onBindViewHolder(final MyItemFuliHoler holder, int position) {

        FuliBean.UserBean fuliBean = mFuliAdapters.get(position);
        Log.i("zkk", "position--" + position + "--" + fuliBean.getImg_src());
        holder.mIvItemFuliImage.setImageURI(fuliBean.getImg_src());
        holder.mTvItemFuliTitle.setText(fuliBean.getTitle());

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

        if (mFuliAdapters != null) {
            Log.d("zkk--", mFuliAdapters.size() + "");
        }

        return mFuliAdapters == null ? 0 : mFuliAdapters.size();
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
        void onItemClick(View view,int position);
    }
}