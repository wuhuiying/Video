package quarter.perfect.com.quarternew.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import quarter.perfect.com.quarternew.R;

/**
 * Created by Administrator on 2018/4/19.
 */

public class QuarterMyAnimetion extends FrameLayout implements View.OnClickListener {

    private Bitmap bitmap;
    public static boolean isState=false;
    private int specSize;
    private OnItemClickListener onItemClickListener;
    private ImageView jubao;
    private ImageView lianjie;
    private ImageView pinbi1;
    Context context;
    public QuarterMyAnimetion(@NonNull Context context) {
        super(context);
        init();
    }

    public QuarterMyAnimetion(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuarterMyAnimetion(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        TextPaint textPaint = new TextPaint();
        View open2 =  View.inflate(getContext(), R.layout.quarter_animetion, this);
        final ImageView open = open2.findViewById(R.id.open);
        final TextView name1 = open2.findViewById(R.id.name1);
//       测量文字的宽度
        final int width = getTextWidth(name1);

        jubao = open2.findViewById(R.id.jubao);
        jubao.setOnClickListener(this);
        final TextView name2 = open2.findViewById(R.id.lianjie1);
        //       测量文字的宽度
        final int width2 = getTextWidth(name2);
        lianjie = open2.findViewById(R.id.lianjie);
        jubao.setOnClickListener(this);
        final TextView name3 = open2.findViewById(R.id.pinbi1);
        //       测量文字的宽度
        final int width3 = getTextWidth(name3);
        pinbi1 = open2.findViewById(R.id.pinbi);
        pinbi1.setOnClickListener(this);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isState=!isState;
                //     控件的宽度
                int ni= jubao.getWidth();
//                控件之间的距离
                int juli=20;
//                    控件的起始位置
                int sta = getMeasuredWidth() - ni;
//                      算出文字的中心距离
                int i = (width - ni)/2;
                int ii = (width2 - ni)/2;
                int iii = (width3 - ni)/2;
                if (isState) {
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(open, "rotation", 0, 360);
                    rotation.setDuration(1000);
                    rotation.start();
                    rotation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            long currentPlayTime = valueAnimator.getCurrentPlayTime();
                            if (currentPlayTime>500){
                                open.setImageResource(R.drawable.item_jian);
                            }

                        }
                    });

                    getStartAnimention(jubao, name1, sta,sta - (ni+juli),sta,sta - (ni+juli+i));
                    getStartAnimention(lianjie, name2, sta, sta-(ni*2+juli*2), sta,sta-(ni*2+juli*2+ii));
                    getStartAnimention(pinbi1, name3, sta,sta-(ni*3+juli*3) , sta,sta-(ni*3+juli*3+iii));
                }else{
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(open, "rotation", 0, 360);
                    rotation.setDuration(1000);
                    rotation.start();
                    rotation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            long currentPlayTime = valueAnimator.getCurrentPlayTime();
                            if (currentPlayTime> 500){
//                                改变图片
                                open.setImageResource(R.drawable.icon_open);
                            }

                        }
                    });
                    getStopAnimention(jubao, name1, sta - (ni+juli), sta ,sta - (ni+juli+i), sta);
                    getStopAnimention(lianjie, name2, sta-(ni*2+juli*2),sta,sta-(ni*2+juli*2+ii),sta);
                    getStopAnimention(pinbi1, name3, sta-(ni*3+juli*3), sta , sta-(ni*3+juli*3+iii),sta);
                }
            }
        });
    }
    //启动动画的方法
    public void getStartAnimention(View imag,View textname,int imagstart,int imagend,int nameState,int nameend){
        textname.setVisibility(VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(imag, "x", imagstart, imagend);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imag, "alpha", 0f, 1.0f);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(imag, "rotation", 0, 360);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator).with(alpha).with(rotation);
        animatorSet.setDuration(1000);
        animatorSet.start();

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(textname, "x", nameState, nameend);
        ObjectAnimator alpha1 = ObjectAnimator.ofFloat(textname, "alpha", -0.5f, 1.0f);
        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.play(animator1).with(alpha1);
        animatorSet1.setDuration(1000);
        animatorSet1.start();
    }
    //停止动画的方法
    public void getStopAnimention(View imag,View textname,int imagstart,int imagend,int nameState,int nameend){
        textname.setVisibility(VISIBLE);
        ObjectAnimator animator = ObjectAnimator.ofFloat(imag, "x", imagstart, imagend);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imag, "alpha", 1.0f, -0.7f);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(imag, "rotation", 0, 360);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animator).with(alpha).with(rotation);
        animatorSet.setDuration(1000);
        animatorSet.start();

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(textname, "x", nameState, nameend);
        ObjectAnimator alpha1 = ObjectAnimator.ofFloat(textname, "alpha", 1.0f, -0.7f);
        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.play(animator1).with(alpha1);
        animatorSet1.setDuration(1000);
        animatorSet1.start();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.jubao :
////                onItemClickListener.onJubaoClick();
//                break;
//            case R.id.lianjie:
////                onItemClickListener.onlianjieClick();
//                break;
//            case R.id.pinbi :
////                onItemClickListener.onpinbiClick();
//                break;
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    interface OnItemClickListener{
        void onJubaoClick();
        void onlianjieClick();
        void onpinbiClick();
    }
    //    测量文字的宽度
    public int getTextWidth(TextView name){
        int spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        name.measure(spec, spec);
        return name.getMeasuredWidth();
    }
}
