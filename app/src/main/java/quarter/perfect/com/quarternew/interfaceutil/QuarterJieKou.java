package quarter.perfect.com.quarternew.interfaceutil;


import quarter.perfect.com.quarternew.bean.QuarterCarouselBean;
import quarter.perfect.com.quarternew.bean.QuarterHotBean;
import quarter.perfect.com.quarternew.bean.QuarterLoginBean;
import quarter.perfect.com.quarternew.bean.QuarterRegBean;

/**
 * Created by 小慧莹 on 2018/4/17.
 */

public class QuarterJieKou {

    public  interface QuarterHot_P{
        void Onsuccess(QuarterHotBean quarterHotBean);
    }
    public  interface QuarterHot_V{
        void Onsuccess(QuarterHotBean quarterHotBean);
    }
    public  interface QuarterCarousel_P{
        void Onsuccess(QuarterCarouselBean quarterCarouselBean);
    }
    public  interface QuarterCarousel_V{
        void Onsuccess(QuarterCarouselBean quarterCarouselBean);
    }
    public  interface QuarterLogin_P{
        void Onsuccess(QuarterLoginBean quarterLoginBean);
    }
    public  interface QuarterLogin_V{
        void Onsuccess(QuarterLoginBean quarterLoginBean);
    }

    public  interface QuarterReg_P{
        void Onsuccess(QuarterRegBean quarterRegBean);
    }

    public  interface QuarterReg_V{
        void Onsuccess(QuarterRegBean quarterRegBean);
    }
}
