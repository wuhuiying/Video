package quarter.perfect.com.quarternew.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.adapter.QuarterBannerAdapter;
import quarter.perfect.com.quarternew.bean.QuarterCarouselBean;

/**
 * Created by Administrator on 2018/4/21.
 */

public class QuarterViewPage extends RelativeLayout {
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            abc();
        }
    };
    private ViewPager viewPager;
    private LinearLayout ly;
    private static final String TAG = "QuarterViewPage";

    public QuarterViewPage(Context context) {
        this(context,null);
    }

    public QuarterViewPage(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }


    public QuarterViewPage(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.activity_page_adapter, this, true);
        viewPager = view.findViewById(R.id.vp);
        // ly = view.findViewById(R.id.ly);
    }
    public void GetAdapter(final Context context, final List<QuarterCarouselBean.DataBean> list){

        QuarterBannerAdapter myAdapter = new QuarterBannerAdapter(context,list);
        viewPager.setAdapter(myAdapter);
        final List<ImageView> list1 = new ArrayList<ImageView>();
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.drawable.selects);
            imageView.setId(i);
            list1.add(imageView);
            //ly.addView(imageView);
        }
        list1.get(0).setSelected(true);
        abc();
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position%list1.size();
                for (int i = 0; i < list1.size(); i++) {
                    if(i==position){
                        list1.get(position).setSelected(true);
                    }else{
                        list1.get(i).setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void abc(){
        handler.sendEmptyMessageDelayed(0,3000);
    }

}
