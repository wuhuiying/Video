package quarter.perfect.com.quarternew.model;

import com.example.xiaohe.base.bean.BaseObserver;
import com.example.xiaohe.base.util.RetrofitAndOkhttpAndRxAndriodUtil;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import quarter.perfect.com.quarternew.bean.SegmentBean;
import quarter.perfect.com.quarternew.presenter.SegmentPresenter;
import quarter.perfect.com.quarternew.util.QuarterApi;


/**
 * Created by liyongkai on 2018/4/19.
 */

public class SegmentModel {
    SegmentPresenter segmentPresenter;

    public SegmentModel(SegmentPresenter segmentPresenter) {
        this.segmentPresenter = segmentPresenter;
    }
    public void getSegment(int page){
        Map<String, String> map=new HashMap<>();
        map.put("page",page+"");
        map.put("appVersion","101");
        RetrofitAndOkhttpAndRxAndriodUtil.get(QuarterApi.HOT_SEGMENT,map)
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSuccess(String result) {
                        SegmentBean segmentBean = new Gson().fromJson(result,SegmentBean.class);
                        segmentPresenter.OnSuccess(segmentBean);
                    }

                });

    }
}
