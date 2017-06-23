package video.zkk.com.huyavideo.fragment.smallvideo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import video.zkk.com.huyavideo.R;
import video.zkk.com.huyavideo.activity.smallvideo.SmallVideoActivity;
import video.zkk.com.huyavideo.adapter.smallvideo.ShengHuoYuLeAdapter;
import video.zkk.com.huyavideo.adapter.smallvideo.WeiShiKeAdapter;
import video.zkk.com.huyavideo.api.Api;
import video.zkk.com.huyavideo.bean.smallvideo.ShengHuoYuLeBean;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class WeiShikeFragment extends Fragment implements OnRefreshListener,OnLoadMoreListener{


    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    @BindView(R.id.swipe_target)
    RecyclerView swipe_target;
    WeiShiKeAdapter mWeiShiKeAdapter;

    int start = 0;  //当前是第几页

    private List<ShengHuoYuLeBean.DataBean> mDataBeen=new ArrayList<ShengHuoYuLeBean.DataBean>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frament_fuli, container,false);
        ButterKnife.bind(this,view) ;

        swipeToLoadLayout.setOnLoadMoreListener(this);
        swipeToLoadLayout.setOnRefreshListener(this);


        mWeiShiKeAdapter=new WeiShiKeAdapter(getActivity());
        swipe_target.setLayoutManager(new GridLayoutManager(getActivity(),2));
        swipe_target.setAdapter(mWeiShiKeAdapter);

        mWeiShiKeAdapter.setOnItemClickListener(new WeiShiKeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


                Intent intent=new Intent(getActivity(), SmallVideoActivity.class);
                intent.putExtra("videoId",mDataBeen.get(position).getHash_id());
                intent.putExtra("videoName",mDataBeen.get(position).getVideo_title());
                startActivity(intent);

            }
        });
        get_fulisForTest();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onRefresh() {
        get_ShengHuo();
    }

    @Override
    public void onLoadMore() {

        start=start+20;
        get_Morefulis(start);

    }

    private  void get_fulisForTest(){

      swipeToLoadLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeToLoadLayout.setRefreshing(true);
            }
        }, 10);
    }

    private  void get_ShengHuo(){

                OkHttpUtils
                        .get()
                        .url(Api.WeiShiKeVideoUrl)
                        .addParams("offset", "0")
                        .build()
                        .execute(new StringCallback()
                        {

                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(String response, int id) {
                                mDataBeen.clear();
                                ShengHuoYuLeBean shengHuoYuLeBean= JSON.parseObject(response,ShengHuoYuLeBean.class);
                                mDataBeen.addAll(shengHuoYuLeBean.getData());

                                Log.i("zkk--"," " + response);
                                swipeToLoadLayout.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeToLoadLayout.setRefreshing(false);
                                        mWeiShiKeAdapter.setList(mDataBeen);
                                    }
                                }, 10);

                            }
                        });
    }


    /**
     * 下拉获取更多
     */
    private  void get_Morefulis(Integer  nowstart){
        Log.i("zkk---",nowstart  +"");
        OkHttpUtils
                .get()
                .url(Api.ShengHuoYuLeWuDao)
                .addParams("offset", nowstart+"")
                .build()
                .execute(new StringCallback()
                {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        ShengHuoYuLeBean shengHuoYuLeBean= JSON.parseObject(response,ShengHuoYuLeBean.class);
                        mDataBeen.addAll(shengHuoYuLeBean.getData());
                        Log.i("zkk--"," " + response);
                        swipeToLoadLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                swipeToLoadLayout.setLoadingMore(false);
                                mWeiShiKeAdapter.setList(mDataBeen);
                            }
                        }, 10);

                    }
                });

    }
}
