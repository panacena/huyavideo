package video.zkk.com.huyavideo.fragment.smallvideo;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
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
import video.zkk.com.huyavideo.adapter.smallvideo.SmallVideoAdapter;
import video.zkk.com.huyavideo.api.Api;
import video.zkk.com.huyavideo.bean.smallvideo.SmallVideoBean;
import video.zkk.com.huyavideo.widget.LoadMoreFootView;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class RecommendFragment extends Fragment   implements OnLoadMoreListener {


    @BindView(R.id.swipe_target)
    RecyclerView mSwipeTarget;

    SmallVideoAdapter mSmallVideoAdapter;

    @BindView(R.id.swipe_load_more_footer)
    LoadMoreFootView mSwipeLoadMoreFooter;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    int start = 0;  //当前是第几页

    private Integer type;   // 0: #娱乐  1:推荐

    private List<SmallVideoBean.DataBean.ListBean> mListBeen = new ArrayList<SmallVideoBean.DataBean.ListBean>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        type=getArguments().getInt("type");
        super.onCreate(savedInstanceState);
    }

    public static final RecommendFragment newInstance(Integer types) {
        RecommendFragment fragment = new RecommendFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",types);
        fragment.setArguments(bundle);
        return fragment ;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_smallvideo2, container, false);
        ButterKnife.bind(this, view);

        mSwipeToLoadLayout.setOnLoadMoreListener(this);


        mSmallVideoAdapter=new SmallVideoAdapter(getActivity());
        mSwipeTarget.setLayoutManager(new GridLayoutManager(getActivity(),2) );
        mSwipeTarget.setAdapter(mSmallVideoAdapter);

        mSmallVideoAdapter.setOnItemClickListener(new SmallVideoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent=new Intent(getActivity(), SmallVideoActivity.class);
                intent.putExtra("videoId",mListBeen.get(position).getHash_id());
                startActivity(intent);
            }
        });
        if(type== 0){
            get_YUleVideos(0);
        }else{
            get_MoreVideos(0);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onLoadMore() {
        start=start+20;
        if(type== 0){
            get_YUleVideos(start);
        }else{
            get_MoreVideos(start);
        }

    }


    private  void get_MoreVideos(Integer  nowstart){
        Log.i("zkk---",nowstart  +"");
        //?tid=2&limit=20&client_sys=android
        OkHttpUtils
                .get()
                .url(Api.DouYuVideo)
                .addParams("offset", nowstart+"")
                .build()
                .execute(new StringCallback()
                {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final SmallVideoBean smallVideoBean= JSON.parseObject(response,SmallVideoBean.class);
                        mListBeen.addAll(smallVideoBean.getData().getList());
                        Log.i("zkk--"," " + response);
                        mSwipeToLoadLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mSwipeToLoadLayout.setLoadingMore(false);
                                mSmallVideoAdapter.setList(mListBeen);
                            }
                        }, 10);

                    }
                });

    }



    private  void get_YUleVideos(Integer  nowstart){
        Log.i("zkk---",nowstart  +"");
        //?tid=2&limit=20&client_sys=android
        OkHttpUtils
                .get()
                .url(Api.YuLeVideo2)
                .addParams("offset", nowstart+"")
                .build()
                .execute(new StringCallback()
                {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        final SmallVideoBean smallVideoBean= JSON.parseObject(response,SmallVideoBean.class);
                        mListBeen.addAll(smallVideoBean.getData().getList());
                        Log.i("zkk--"," " + response);
                        mSwipeToLoadLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mSwipeToLoadLayout.setLoadingMore(false);
                                mSmallVideoAdapter.setList(mListBeen);
                            }
                        }, 10);

                    }
                });

    }

}
