package quarter.perfect.com.quarternew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.bean.SegmentBean;


/**
 * Created by liyongkai on 2018/4/19.
 */

public class SegmentAdapter extends XRecyclerView.Adapter<SegmentAdapter.ViewHolder> {
    private Context context;
    private List<SegmentBean.DataBean> data = new ArrayList<>();

    public SegmentAdapter(Context context, List<SegmentBean.DataBean> data, Map<Integer, Boolean> map) {
        this.context = context;
        this.data = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.quarter_segment_adapter,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.sim.setImageURI(data.get(i).getImgUrls()+"");
        viewHolder.sim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        viewHolder.tv_name.setText(data.get(i).getUser().getNickname()+"");
        viewHolder.tv_time.setText(data.get(i).getCreateTime());
        viewHolder.tv_txt.setText(data.get(i).getContent());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends XRecyclerView.ViewHolder {
        private SimpleDraweeView sim;
        private TextView tv_time;
        private TextView tv_name;
        private TextView tv_txt;

        public ViewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            //根据onCreateViewHolder的HoldView所添加的xml布局找到空间
            sim = itemView.findViewById(R.id.sdv_segment_photo);
            tv_name = itemView.findViewById(R.id.sdv_segment_name);
            tv_time = itemView.findViewById(R.id.sdv_segment_time);
            tv_txt = itemView.findViewById(R.id.sdv_segment_txt);
        }
    }

}
