package video.zkk.com.huyavideo.activity.live;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnPlayerBackListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;
import video.zkk.com.huyavideo.R;
import video.zkk.com.huyavideo.api.Api;
import video.zkk.com.huyavideo.bean.smallvideo.SmallVideoUrlBean;


/**
 * Created by Administrator on 2017/6/7 0007.
 */

public class HuoShanLiveActivity extends AppCompatActivity {

    private PlayerView player;

    private Context mContext;

    private  String videoUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_player_view_player);
        mContext=this;
        videoUrl=getIntent().getExtras().getString("videoUrl");
        getPlayUrl();
    }


    void getPlayUrl(){
        startPlay(videoUrl,null);
    }

    private  void startPlay(String mp4Url,List<VideoijkBean> list){
        player = new PlayerView(this)
                .setTitle("")
                .setScaleType(PlayStateParams.fillparent)
                .forbidTouch(false)
                .hideMenu(true)
                .setPlaySource(mp4Url)
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
