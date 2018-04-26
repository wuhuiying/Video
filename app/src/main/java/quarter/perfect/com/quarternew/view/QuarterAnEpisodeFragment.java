package quarter.perfect.com.quarternew.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaohe.base.baseview.BaseMvpFragment;
import com.example.xiaohe.base.presenter.BaseMvpPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.adapter.SegmentAdapter;
import quarter.perfect.com.quarternew.bean.SegmentBean;
import quarter.perfect.com.quarternew.interfaceutil.SegmentJieKou;
import quarter.perfect.com.quarternew.presenter.SegmentPresenter;

/**
 * Created by xiaohe on 2018/4/19.
 */

public class QuarterAnEpisodeFragment extends BaseMvpFragment implements SegmentJieKou.Segmegmentview {
    private XRecyclerView quarter_anepisode_xrv;
    private View view;
    private Map<Integer, Boolean> map;
    private SegmentPresenter segmentPresenter;
    private SegmentAdapter segmentAdapter;
    private int page = 1;
    private List<SegmentBean.DataBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quarter_anepisode_fragment, container, false);
        initView(view);
        return view;
    }

    @Override
    public BaseMvpPresenter initPresenter() {
        segmentPresenter = new SegmentPresenter(this);
        return segmentPresenter;

    }

    private void initView(View view) {
        quarter_anepisode_xrv = (XRecyclerView) view.findViewById(R.id.quarter_anepisode_xrv);
        map = new HashMap<>();
        segmentPresenter = new SegmentPresenter(this);
        segmentPresenter.getSegment(page);

    }

    @Override
    public void OnSuccess(SegmentBean segmentBean) {
        final List<SegmentBean.DataBean> dataBeans = segmentBean.getData();
        list.addAll(dataBeans);
        segmentAdapter = new SegmentAdapter(getContext(), list, map);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        quarter_anepisode_xrv.setLayoutManager(manager);
        quarter_anepisode_xrv.setAdapter(segmentAdapter);
        quarter_anepisode_xrv.addItemDecoration(new DividerItemDecoration(getContext(), 1));

        quarter_anepisode_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                segmentPresenter.getSegment(page);
                quarter_anepisode_xrv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                segmentPresenter.getSegment(page);
                quarter_anepisode_xrv.refreshComplete();
            }
        });

    }
}
