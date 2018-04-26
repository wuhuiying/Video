package quarter.perfect.com.quarternew.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.xiaohe.base.baseview.BaseMvpFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.adapter.QuarterVideoHotAdapter;
import quarter.perfect.com.quarternew.bean.QuarterVideoHotBean;
import quarter.perfect.com.quarternew.interfaceall.OnItemClickListener;
import quarter.perfect.com.quarternew.interfaceall.QuarterJieKou;
import quarter.perfect.com.quarternew.presenter.QuarterVideoHotPresenter;
import quarter.perfect.com.quarternew.util.SpacesItemDecoration;

/**
 * Created by xiaohe on 2018/4/19.
 */

public class QuarterVideoHotFragment extends BaseMvpFragment<QuarterVideoHotFragment,QuarterVideoHotPresenter> implements QuarterJieKou.QuarterVideoHot_V {

    private View view;
    private XRecyclerView hot_xrv;
    private List<QuarterVideoHotBean.DataBean> data;
    private QuarterVideoHotAdapter quarterVideoHotAdapter;
    private QuarterVideoHotPresenter quarterVideoHotPresenter;
    int page=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.quarter_vidoe_hot_fragment, container, false);

        hot_xrv = view.findViewById(R.id.quarter_video_hot_xrv);
        quarterVideoHotPresenter.getData(page);

        return view;
    }

    @Override
    public QuarterVideoHotPresenter initPresenter() {
        quarterVideoHotPresenter = new QuarterVideoHotPresenter(this,getContext());

        return quarterVideoHotPresenter;
    }
    public OnItemClickListener onItemClickListener=new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(getContext()," 点击了 "+position,Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getContext(),QuarterDetailActivity.class);
            startActivity(intent);
        }
    };

    @Override
    public void Onsuccess(QuarterVideoHotBean quarterVideoHotBean) {
        data = quarterVideoHotBean.getData();
        hot_xrv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        quarterVideoHotAdapter = new QuarterVideoHotAdapter(getContext(),data,onItemClickListener);
        hot_xrv.setAdapter(quarterVideoHotAdapter);
        SpacesItemDecoration decoration=new SpacesItemDecoration(0);
        hot_xrv.addItemDecoration(decoration);


        hot_xrv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                quarterVideoHotPresenter.getData(page);

            }

            @Override
            public void onLoadMore() {
                page=page+1;
                quarterVideoHotPresenter.getData(page);

            }
        });
    }
}

