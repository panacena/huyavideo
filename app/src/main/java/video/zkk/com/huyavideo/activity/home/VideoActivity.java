package video.zkk.com.huyavideo.activity.home;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnPlayerBackListener;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import okhttp3.Call;
import video.zkk.com.huyavideo.R;
import video.zkk.com.huyavideo.api.Api;
import video.zkk.com.huyavideo.bean.home.FuliBean;
import video.zkk.com.huyavideo.bean.home.HuYaVideoBean;


/**
 * Created by Administrator on 2017/6/7 0007.
 */

public class VideoActivity extends AppCompatActivity {

    private PlayerView player;


    private String videoId;
    private String videoName;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_player_view_player);
        mContext=this;
        videoId=getIntent().getExtras().getString("videoId");
        videoName=getIntent().getExtras().getString("videoName");
        getPlayUrl();
    }

    void getPlayUrl(){

        //?vid=5748057&partner=&r=vhuyaplay%2Fvideo
        //http://v-api-play.huya.com/index.php?vid=5748057&partner=&r=vhuyaplay%2Fvideo
        OkHttpUtils
                .get()
                .url(Api.Video)
                .addParams("vid",videoId)
                .addParams("partner", "")
                .addParams("r", "vhuyaplay/video")
                .build()
                .execute(new StringCallback()
                {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        HuYaVideoBean huYaVideoBean= JSON.parseObject(response,HuYaVideoBean.class);

                        List<VideoijkBean> list = new ArrayList<VideoijkBean>();
                        String url1 = huYaVideoBean.getResult().getItems().get(1).getTranscode().getUrls().get(0);
                        String url2 = huYaVideoBean.getResult().getItems().get(2).getTranscode().getUrls().get(0);
                        VideoijkBean m2 = new VideoijkBean();
                        m2.setStream("高清");
                        m2.setUrl(url2);
                        VideoijkBean m1 = new VideoijkBean();
                        m1.setStream("标清");
                        m1.setUrl(url1);

                        list.add(m2);
                        list.add(m1);

                        if(list!=null&&list.size()>2){
                            String url3 = huYaVideoBean.getResult().getItems().get(3).getTranscode().getUrls().get(0);
                            VideoijkBean m3 = new VideoijkBean();
                            m3.setStream("流畅");
                            m3.setUrl(url3);
                            list.add(m3);
                        }



                        startPlay(huYaVideoBean.getResult().getCover()
                                ,list);
                    }
                });
       /* OkHttpUtils
                .post()
                .url(Api.DouYuVideo)
                .addParams("vid","GLDBbMAbKnBWyJRP\n")
                .build()
                .execute(new StringCallback()
                {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.i("zkk---",response);
                    }
                });*/


    }

    private  void startPlay(String mp4Url,List<VideoijkBean> list){
        player = new PlayerView(this)
                .setTitle(videoName)
                .setScaleType(PlayStateParams.fillparent)
                .forbidTouch(false)
                .hideMenu(true)
                /*.showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(mContext)
                                .load(imgurl)
                                .into(ivThumbnail);
                    }
                })*/
                .setPlaySource(list)
                //.setPlaySource(mp4Url)
                .setPlayerBackListener(new OnPlayerBackListener() {
                    @Override
                    public void onPlayerBack() {
                        finish();
                    }
                })
                .startPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
