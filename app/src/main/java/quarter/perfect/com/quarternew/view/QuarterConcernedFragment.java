package quarter.perfect.com.quarternew.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;

import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.adapter.QuarterHotAdapter;
import quarter.perfect.com.quarternew.bean.QuarterCarouselBean;
import quarter.perfect.com.quarternew.bean.QuarterHotBean;
import quarter.perfect.com.quarternew.interfaceutil.QuarterJieKou;
import quarter.perfect.com.quarternew.presenter.QuarterConcernedCarouselPresenter;
import quarter.perfect.com.quarternew.presenter.QuarterConcernedPresenter;
import quarter.perfect.com.quarternew.util.QuarterViewPage;

/**
 * Created by xiaohe on 2018/4/19.
 */

public class QuarterConcernedFragment extends Fragment implements QuarterJieKou.QuarterHot_V,QuarterJieKou.QuarterCarousel_V,XRecyclerView.LoadingListener {
    private SimpleDraweeView quarter_concerned_sdv;
    private XRecyclerView quarter_concerned_xrv;
    private QuarterHotAdapter quarterHotAdapter;
    private QuarterConcernedCarouselPresenter quarterConcernedCarouselPresenter;
    List<QuarterHotBean.DataBean> list = new ArrayList<>();
    private List<QuarterHotBean.DataBean> data;
    private PullToRefreshView mPullToRefreshView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quarter_concerned_fragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        quarter_concerned_xrv = (XRecyclerView) view.findViewById(R.id.quarter_concerned_xrv);
//        quarter_concerned_xrv.setLayoutManager(new LinearLayoutManager(getContext()));
//        quarter_concerned_xrv.setItemAnimator(new DefaultItemAnimator());
//        quarter_concerned_xrv.setLoadingListener(this);
        quarter_concerned_sdv = (SimpleDraweeView) view.findViewById(R.id.quarter_concerned_sdv);
        QuarterConcernedPresenter quarterConcernedPresenter = new QuarterConcernedPresenter(this);
        quarterConcernedPresenter.getHotData();
        quarterConcernedCarouselPresenter = new QuarterConcernedCarouselPresenter(this);
        quarterConcernedCarouselPresenter.getData();
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 1);
            }
        });
    }

    @Override
    public void Onsuccess(QuarterHotBean quarterHotBean) {
        data = quarterHotBean.getData();
        list.addAll(data);
        if(quarterHotAdapter==null)
        {
            quarterHotAdapter = new QuarterHotAdapter(getActivity(),list);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            quarter_concerned_xrv.setLayoutManager(linearLayoutManager);
            quarter_concerned_xrv.setAdapter(quarterHotAdapter);
        }
        else {

            quarterHotAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void Onsuccess(QuarterCarouselBean quarterCarouselBean) {
        quarter_concerned_sdv.setImageURI(quarterCarouselBean.getData().get(0).getIcon());
    }

    @Override
    public void onRefresh() {
        //刷新
        quarter_concerned_xrv.refreshComplete();
    }

    @Override
    public void onLoadMore() {
        //加载
        list.addAll(data);
        quarter_concerned_xrv.refreshComplete();
    }
}
