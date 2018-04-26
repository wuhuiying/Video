package quarter.perfect.com.quarternew.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.adapter.QuarterBannerAdapter;
import quarter.perfect.com.quarternew.adapter.QuarterHotAdapter;
import quarter.perfect.com.quarternew.bean.QuarterCarouselBean;
import quarter.perfect.com.quarternew.bean.QuarterHotBean;
import quarter.perfect.com.quarternew.interfaceutil.QuarterJieKou;
import quarter.perfect.com.quarternew.presenter.QuarterHotCarouselPresenter;
import quarter.perfect.com.quarternew.presenter.QuaterHotPresenter;
import quarter.perfect.com.quarternew.util.QuarterViewPage;

/**
 * Created by xiaohe on 2018/4/19.
 */

public class QuarterHotFragment extends Fragment implements QuarterJieKou.QuarterHot_V,QuarterJieKou.QuarterCarousel_V,XRecyclerView.LoadingListener{
    private XRecyclerView quarter_hot_xrv;
    private QuarterHotAdapter quarterHotAdapter;
    private QuarterHotCarouselPresenter quarterCarouselPresenter;
    List<QuarterHotBean.DataBean> list = new ArrayList<>();
    private QuaterHotPresenter quaterHotPresenter;
    private QuarterViewPage mvp;
    private List<QuarterHotBean.DataBean> data;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quarter_hot_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        View view1 = LayoutInflater.from(getContext()).inflate(R.layout.activity_banner, null);
        mvp = view1.findViewById(R.id.mvp);
        quarter_hot_xrv = (XRecyclerView) view.findViewById(R.id.quarter_hot_xrv);
        quarter_hot_xrv.setItemAnimator(new DefaultItemAnimator());
        quarter_hot_xrv.setLayoutManager(new LinearLayoutManager(getContext()));
        quarter_hot_xrv.addHeaderView(view1);
        quarter_hot_xrv.setLoadingListener(this);
        quaterHotPresenter = new QuaterHotPresenter(this);
        quaterHotPresenter.getHotData();
        QuarterHotCarouselPresenter quarterCarouselPresenter = new QuarterHotCarouselPresenter(this);
        quarterCarouselPresenter.getCarouselData();

    }

    @Override
    public void Onsuccess(QuarterHotBean quarterHotBean) {
        data = quarterHotBean.getData();
        list.addAll(data);
        if(quarterHotAdapter==null)
        {
            quarterHotAdapter = new QuarterHotAdapter(getActivity(),list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            quarter_hot_xrv.setLayoutManager(linearLayoutManager);
            quarter_hot_xrv.setAdapter(quarterHotAdapter);
        }
        else {

            quarterHotAdapter.notifyDataSetChanged();


        }
    }

    @Override
    public void Onsuccess(final QuarterCarouselBean quarterCarouselBean) {
        List<QuarterCarouselBean.DataBean> data = quarterCarouselBean.getData();
        mvp.GetAdapter(getContext(),data);
    }


    @Override
    public void onRefresh() {
//        刷新
        quarter_hot_xrv.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        //加载
        list.addAll(data);
        quarter_hot_xrv.refreshComplete();
    }
}
