package quarter.perfect.com.quarternew.interfaceutil;

import quarter.perfect.com.quarternew.bean.SegmentBean;

/**
 * Created by liyongkai on 2018/4/19.
 * 创建接口（段子页面）
 */

public class SegmentJieKou {
    public interface Segmegmentpresenter {
        void OnSuccess(SegmentBean segmentBean);
    }



    public interface Segmegmentview {
        void OnSuccess(SegmentBean segmentBean);
    }
}
