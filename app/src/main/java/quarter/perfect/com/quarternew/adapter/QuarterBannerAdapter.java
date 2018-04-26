package quarter.perfect.com.quarternew.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import quarter.perfect.com.quarternew.bean.QuarterCarouselBean;

/**
 * Created by Administrator on 2018/4/21.
 */

public class QuarterBannerAdapter extends PagerAdapter {
    Context context;
    List<QuarterCarouselBean.DataBean> list;

    public QuarterBannerAdapter(Context context, List<QuarterCarouselBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position % list.size();
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(list.get(position % list.size()).getIcon()).into(imageView);
        //加入容器
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
