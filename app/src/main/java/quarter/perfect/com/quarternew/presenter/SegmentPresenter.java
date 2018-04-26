package quarter.perfect.com.quarternew.presenter;


import com.example.xiaohe.base.presenter.BaseMvpPresenter;

import quarter.perfect.com.quarternew.bean.SegmentBean;
import quarter.perfect.com.quarternew.interfaceutil.SegmentJieKou;
import quarter.perfect.com.quarternew.model.SegmentModel;
import quarter.perfect.com.quarternew.view.QuarterAnEpisodeFragment;

/**
 * Created by liyongkai on 2018/4/19.
 */

public class SegmentPresenter extends BaseMvpPresenter<QuarterAnEpisodeFragment> implements SegmentJieKou.Segmegmentpresenter {
    private SegmentModel segmentModel;
    QuarterAnEpisodeFragment segmentFragment;

    public SegmentPresenter(QuarterAnEpisodeFragment segmentFragment) {
        this.segmentFragment = segmentFragment;
        segmentModel = new SegmentModel(this);
    }

    public void getSegment(int page){
        segmentModel.getSegment(page);
    }

    @Override
    public void OnSuccess(SegmentBean segmentBean) {
        segmentFragment.OnSuccess(segmentBean);
    }
}
