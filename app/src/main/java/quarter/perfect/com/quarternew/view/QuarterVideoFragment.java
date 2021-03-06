package quarter.perfect.com.quarternew.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.adapter.MyPagerAdapter;

/**
 * Created by xiaohe on 2018/4/19.
 */

public class QuarterVideoFragment extends Fragment {
    private TabLayout quarter_video_tl;
    private ViewPager quarter_video_vp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quarter_video_fragment, container, false);
        initView(view);
        initViewPager();
        return view;
    }

    private void initView(View view) {
        quarter_video_tl = (TabLayout) view.findViewById(R.id.quarter_video_tl);
        quarter_video_vp = (ViewPager) view.findViewById(R.id.quarter_video_vp);
    }
    private void initViewPager() {
        // 创建一个集合,装填Fragment
        ArrayList<Fragment> fragments = new ArrayList<>();
        // 装填
        fragments.add(new QuarterVideoHotFragment());
        fragments.add(new QuarterVideoNearbyFragment());
        // 创建ViewPager适配器
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        myPagerAdapter.setFragments(fragments);
        // 给ViewPager设置适配器
        quarter_video_vp.setAdapter(myPagerAdapter);
        // TabLayout 指示器 (记得自己手动创建4个Fragment,注意是 app包下的Fragment 还是 V4包下的 Fragment)
        quarter_video_tl.addTab(quarter_video_tl.newTab());
        quarter_video_tl.addTab(quarter_video_tl.newTab());
        quarter_video_tl.addTab(quarter_video_tl.newTab());
        quarter_video_tl.addTab(quarter_video_tl.newTab());
        // 使用 TabLayout 和 ViewPager 相关联
        quarter_video_tl.setupWithViewPager(quarter_video_vp);
        // TabLayout指示器添加文本
        quarter_video_tl.getTabAt(0).setText("热门");
        quarter_video_tl.getTabAt(1).setText("附近");
    }
}
