package quarter.perfect.com.quarternew.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import quarter.perfect.com.quarternew.R;
import quarter.perfect.com.quarternew.bean.QuarterVideoHotBean;
import quarter.perfect.com.quarternew.interfaceall.OnItemClickListener;


/**
 * Created by dell on 2018/4/18.
 */

public class QuarterVideoHotAdapter extends RecyclerView.Adapter<QuarterVideoHotAdapter.HotviewHolder>{

    Context context;
    List<QuarterVideoHotBean.DataBean> data;
    private OnItemClickListener mOnItemClickListener;

    public QuarterVideoHotAdapter(Context context, List<QuarterVideoHotBean.DataBean> data, OnItemClickListener mOnItemClickListener) {
        this.context = context;
        this.data = data;
        this.mOnItemClickListener = mOnItemClickListener;
    }


    @Override
    public HotviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_layout_videorm, parent, false);
        HotviewHolder hotviewHolder=new HotviewHolder(view);
        return hotviewHolder;
    }

    @Override
    public void onBindViewHolder(HotviewHolder holder, final int position) {
        holder.sdv_rm.setImageURI(data.get(position).getCover());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v,position);
            }
        });

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class HotviewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView sdv_rm;

        public HotviewHolder(View itemView) {
            super(itemView);
            AutoUtils.autoSize(itemView);
            sdv_rm = itemView.findViewById(R.id.perfect_sdv_rm);
            sdv_rm.setAspectRatio(0.9f);
            //设置对其方式
            sdv_rm.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        }
    }

}
