package video.zkk.com.huyavideo.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import video.zkk.com.huyavideo.R;

/**
 * Created by Administrator on 2016/9/13 0013.
 */
public class HomeFragment extends Fragment {


    @BindView(R.id.tableLayout)
    TabLayout tableLayout;

    @BindView(R.id.view_pager)
    ViewPager view_pager;

    private ApnFragmentAdapter mAdapter;
    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frament_home, container,false);
        ButterKnife.bind(this,view) ;

        mFragmentList.add(new MeizhiFragment());
        mFragmentList.add(new ErciyuanFragment());
        mFragmentList.add(new FuliFragment());
        mFragmentList.add(new XiusekexueFragment());
        mFragmentList.add(new ZhaiwuFragment());


        mAdapter = new ApnFragmentAdapter(getFragmentManager());
        Log.e("mAdapter--",mAdapter.toString());
        view_pager.setAdapter(mAdapter);
        tableLayout.setupWithViewPager(view_pager);
        tableLayout.setTabMode(TabLayout.MODE_FIXED);
        view_pager.setOffscreenPageLimit(4);

        return view;
    }



    private class ApnFragmentAdapter extends FragmentPagerAdapter {
        CharSequence[] TITLE = new CharSequence[]{"推荐","二次元","每日福利","羞涩科学","宅舞"};

        public ApnFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return TITLE.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE[position];
        }
    }
}
