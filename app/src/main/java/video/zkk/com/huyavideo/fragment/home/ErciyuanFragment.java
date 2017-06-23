package video.zkk.com.huyavideo.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import video.zkk.com.huyavideo.activity.home.VideoActivity;
import video.zkk.com.huyavideo.adapter.home.FuliAdapter;
import video.zkk.com.huyavideo.api.Api;
import video.zkk.com.huyavideo.bean.home.FuliBean;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class ErciyuanFragment extends Fragment  implements OnRefreshListener,OnLoadMoreListener{

    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    @BindView(R.id.swipe_target)
    RecyclerView swipe_target;
    FuliAdapter mFuliAdapter;

    int start = 0;  //当前是第几页

    private List<FuliBean.UserBean> mFuliBean=new ArrayList<FuliBean.UserBean>();

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


        mFuliAdapter=new FuliAdapter(getActivity());
        swipe_target.setLayoutManager(new LinearLayoutManager(getActivity()));
        swipe_target.setAdapter(mFuliAdapter);

        mFuliAdapter.setOnItemClickListener(new FuliAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent=new Intent(getActivity(), VideoActivity.class);
                intent.putExtra("videoId",mFuliBean.get(position).getVideo_id());
                intent.putExtra("videoName",mFuliBean.get(position).getTitle());
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
        get_fulis();
    }

    @Override
    public void onLoadMore() {

        start=start+40;
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

    private  void get_fulis(){

        OkHttpUtils
                .get()
                .url(Api.Erciyuan_URL)
                .addParams("start", "0")
                .addParams("pageNum", "40")
                .build()
                .execute(new StringCallback()
                {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mFuliBean.clear();
                        FuliBean fuliBean= JSON.parseObject(response,FuliBean.class);
                        mFuliBean.addAll(fuliBean.getUser());

                        Log.i("zkk--"," " + response);
                        swipeToLoadLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                swipeToLoadLayout.setRefreshing(false);
                                mFuliAdapter.setList(mFuliBean);
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
                .url(Api.FULI_URL)
                .addParams("start", nowstart+"")
                .addParams("pageNum", "20")
                .build()
                .execute(new StringCallback()
                {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        FuliBean fuliBean= JSON.parseObject(response,FuliBean.class);
                        mFuliBean.addAll(fuliBean.getUser());
                        Log.i("zkk--"," " + response);
                        swipeToLoadLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                swipeToLoadLayout.setLoadingMore(false);
                                mFuliAdapter.setList(mFuliBean);
                            }
                        }, 10);

                    }
                });

    }

}
