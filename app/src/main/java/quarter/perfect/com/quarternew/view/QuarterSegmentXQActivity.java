package quarter.perfect.com.quarternew.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import quarter.perfect.com.quarternew.R;

 public class QuarterSegmentXQActivity extends AppCompatActivity implements View.OnClickListener {boolean flag = false;//定义全局变量用来保存按钮的状态
    private ImageView mImgBack;
    private ImageView mImgShare;
    private ImageView mImgMessage;
    /**
     * bt_att
     */
    private Button mBtAtt;
    private ImageView mImgDianzan;
    /**
     * 16
     */
    private TextView mTvLike;
    private LinearLayout mLlLike;
    private FrameLayout mFrameUserinfo;
    /**
     * tv_product
     */
    private TextView mTvUserinfo;
    private View mViewUserinfo;
    private XRecyclerView mRecycleUserinfo;
    private RelativeLayout mRlUserinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segment_xq);
        initView();
    }

    private void initView() {
        mImgBack = (ImageView) findViewById(R.id.img_back);
        mImgShare = (ImageView) findViewById(R.id.img_share);
        mImgMessage = (ImageView) findViewById(R.id.img_message);
        mBtAtt = (Button) findViewById(R.id.bt_att);
        mBtAtt.setOnClickListener(this);
        mImgDianzan = (ImageView) findViewById(R.id.img_dianzan);
        mTvLike = (TextView) findViewById(R.id.tv_like);
        mLlLike = (LinearLayout) findViewById(R.id.ll_like);
        mFrameUserinfo = (FrameLayout) findViewById(R.id.frame_userinfo);
        mTvUserinfo = (TextView) findViewById(R.id.tv_userinfo);
        mViewUserinfo = (View) findViewById(R.id.view_userinfo);
        mRecycleUserinfo = (XRecyclerView) findViewById(R.id.recycle_userinfo);
        mRlUserinfo = (RelativeLayout) findViewById(R.id.rl_userinfo);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.bt_att:
                String s = mBtAtt.getText().toString();
                if(s.equals("已关注")){
                    mBtAtt.setText("关注");
                    mBtAtt.setBackgroundColor(Color.RED);

                }else{
                    mBtAtt.setText("已关注");
                    mBtAtt.setBackgroundColor(Color.GREEN);
                }

                break;
            default:
                break;
        }
    }
}
