package video.zkk.com.huyavideo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import video.zkk.com.huyavideo.R;
import video.zkk.com.huyavideo.fragment.home.HomeFragment;
import video.zkk.com.huyavideo.fragment.like.LikeFragment;
import video.zkk.com.huyavideo.fragment.my.MyFragment;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static long DOUBLE_CLICK_TIME = 0L;
    @BindView(R.id.navigation_bar_home)
    Button navigation_bar_home;

    @BindView(R.id.navigation_bar_like)
    Button navigation_bar_like;

    @BindView(R.id.navigation_bar_my)
    Button navigation_bar_my;

    @BindView(R.id.frame_container)
    FrameLayout frame_container;

    HomeFragment mHomeFragment;
    LikeFragment mLikeFragment;
    MyFragment mMyFragment;
    private FragmentManager fm;
    private FragmentTransaction ft;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activityhome_bar_layout);
        ButterKnife.bind( this ) ;
        navigation_bar_home.setOnClickListener(this);
        navigation_bar_like.setOnClickListener(this);
        navigation_bar_my.setOnClickListener(this);
        fm=getSupportFragmentManager();
        change_HomeFragment();
    }

    private void change_HomeFragment(){
        navigation_bar_home.setSelected(true);
        ft=fm.beginTransaction();
        if(mHomeFragment==null){
            mHomeFragment=new HomeFragment();
        }
        ft.replace(R.id.frame_container, mHomeFragment);
        ft.commit();
    }

    private void change_LikeFragment(){
        navigation_bar_like.setSelected(true);
        ft=fm.beginTransaction();
        if(mLikeFragment==null){
            mLikeFragment=new LikeFragment();
        }
        ft.replace(R.id.frame_container, mLikeFragment);
        ft.commit();
    }

    private void change_MyFragment(){
        navigation_bar_my.setSelected(true);
        ft=fm.beginTransaction();
        if(mMyFragment==null){
            mMyFragment=new MyFragment();
        }
        ft.replace(R.id.frame_container, mMyFragment);
        ft.commit();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.navigation_bar_home:
                navigation_bar_like.setSelected(false);
                navigation_bar_my.setSelected(false);
                change_HomeFragment();
                break;
            case R.id.navigation_bar_like:
                navigation_bar_home.setSelected(false);
                navigation_bar_my.setSelected(false);
                change_LikeFragment();
            break;
            case R.id.navigation_bar_my:
                navigation_bar_home.setSelected(false);
                navigation_bar_like.setSelected(false);
                change_MyFragment();
            break;
        }
    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            return true;
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - DOUBLE_CLICK_TIME) > 2000) {
                DOUBLE_CLICK_TIME = System.currentTimeMillis();
            } else {
                System.gc();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
