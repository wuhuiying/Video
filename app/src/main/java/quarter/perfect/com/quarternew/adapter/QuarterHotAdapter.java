package quarter.perfect.com.quarternew.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.bean.QuarterHotBean;
import quarter.perfect.com.quarternew.util.QuarterMyAnimetion;
import quarter.perfect.com.quarternew.view.QuarterSegmentXQActivity;


/**
 * Created by Administrator on 2018/4/19.
 */

public class QuarterHotAdapter extends XRecyclerView.Adapter<QuarterHotAdapter.QuarterViewVideoHolder>{
    private Context context;

    private List<QuarterHotBean.DataBean> quarterHotBean;
    public QuarterHotAdapter(Context context, List<QuarterHotBean.DataBean> quarterHotBean) {
        this.context = context;
        this.quarterHotBean = quarterHotBean;
    }



    @Override
    public QuarterViewVideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quarter_hot_adapter, null);
        QuarterViewVideoHolder viewHolder = new QuarterViewVideoHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final QuarterViewVideoHolder holder, int position) {
        SimpleDraweeView sdv = holder.getSdv();
        TextView xrv_tv1 = holder.getXrv_tv1();
        TextView xrv_tv2 = holder.getXrv_tv2();
        TextView xrv_tv3 = holder.getXrv_tv3();
        JCVideoPlayer jc = holder.getJc();
        jc.setUp(quarterHotBean.get(position).getVideoUrl(),null);
        DraweeController build = Fresco.newDraweeControllerBuilder()
                .setUri(quarterHotBean.get(position).getUser().getIcon())
                .setOldController(sdv.getController())
                .setTapToRetryEnabled(true).build();
        sdv.setController(build);
        xrv_tv1.setText(quarterHotBean.get(position).getUser().getNickname());
        xrv_tv2.setText(quarterHotBean.get(position).getCreateTime());
        xrv_tv3.setText(quarterHotBean.get(position).getWorkDesc());

        QuarterMyAnimetion myanuim = holder.getMyanuim();
        holder.flag=QuarterMyAnimetion.isState;
        sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuarterSegmentXQActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quarterHotBean.size();
    }

    class QuarterViewVideoHolder extends XRecyclerView.ViewHolder{

        private RelativeLayout rl_flag;
        private QuarterMyAnimetion myanuim;
        private TextView xrv_tv1;
        private TextView xrv_tv2;
        private TextView xrv_tv3;
        private SimpleDraweeView sdv;
        private JCVideoPlayer jc;
        public boolean flag=false;
        public QuarterViewVideoHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            this.sdv = itemView.findViewById(R.id.xrv_touxiang);
            this.xrv_tv1 = itemView.findViewById(R.id.xrv_tv1);
            this.xrv_tv2 = itemView.findViewById(R.id.xrv_tv2);
            this.xrv_tv3 = itemView.findViewById(R.id.xrv_tv3);
            this.jc = itemView.findViewById(R.id.jc);
            this.myanuim = itemView.findViewById(R.id.myanim);
            this.rl_flag = itemView.findViewById(R.id.rl_flag);
        }

        public RelativeLayout getRl_flag() {
            return rl_flag;
        }

        public QuarterMyAnimetion getMyanuim() {
            return myanuim;
        }

        public TextView getXrv_tv1() {
            return xrv_tv1;
        }

        public TextView getXrv_tv2() {
            return xrv_tv2;
        }

        public TextView getXrv_tv3() {
            return xrv_tv3;
        }

        public SimpleDraweeView getSdv() {
            return sdv;
        }

        public JCVideoPlayer getJc() {
            return jc;
        }
    }
}
