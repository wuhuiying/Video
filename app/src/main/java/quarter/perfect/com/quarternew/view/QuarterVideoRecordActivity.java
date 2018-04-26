package quarter.perfect.com.quarternew.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import com.zhy.autolayout.AutoLayoutActivity;

import java.io.File;

import quarter.perfect.com.quarternew.R;

public class QuarterVideoRecordActivity extends AutoLayoutActivity {
    private static final String TAG = "你猜";
    private QuarterVideoRecorderView recoderView;
    private Button videoController;
    private boolean isCancel = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_record);
        cancelTitle();
        recoderView = (QuarterVideoRecorderView) findViewById(R.id.recoder);
        videoController = (Button) findViewById(R.id.videoController);
        ViewGroup.LayoutParams params = recoderView.getLayoutParams();
        int[] dev = getDisplayMetrics();//PhoneUtil.getResolution(this);
        params.width = dev[0];
        params.height = dev[1]*2/3;//(int) (((float) dev[1])*4/5);
        recoderView.setLayoutParams(params);
        videoController.setOnTouchListener(new VideoTouchListener());



        recoderView.setRecorderListener(new QuarterVideoRecorderView.RecorderListener() {

            @Override
            public void recording(int maxtime, int nowtime) {

            }

            @SuppressLint("LongLogTag")
            @Override
            public void recordSuccess(File videoFile) {
                //System.out.println("recordSuccess");
                if (videoFile != null)
                    Log.e(TAG+".recordSuccess()","videoFilePath======"+videoFile.getAbsolutePath());
                if (videoFile.getAbsolutePath() != null) {
                    //showDialog(videoFile.getAbsolutePath());
                    Log.d("为什么", "recordSuccess: "+videoFile.getAbsolutePath());
                    showDialog(videoFile.getAbsolutePath());
                }
            }

            @SuppressLint("LongLogTag")
            @Override
            public void recordStop() {
                Log.e(TAG+".recordStop()","=========");
            }

            @SuppressLint("LongLogTag")
            @Override
            public void recordCancel() {
                Log.e(TAG+".recordCancel()","=========");
                releaseAnimations();
            }

            @SuppressLint("LongLogTag")
            @Override
            public void recordStart() {
                Log.e(TAG+".recordStart()","=========");
            }

            @SuppressLint("LongLogTag")
            @Override
            public void videoStop() {
                Log.e(TAG+".videoStop()","=========");
            }

            @SuppressLint("LongLogTag")
            @Override
            public void videoStart() {
                Log.e(TAG+".videoStart()","=========");
            }


        });
    }



    public class VideoTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {


            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    recoderView.startRecord();
                    isCancel = false;
                    pressAnimations();
                    holdAnimations();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (event.getX() > 0
                            && event.getX() < videoController.getWidth()
                            && event.getY() > 0
                            && event.getY() < videoController.getHeight()) {
                        //showPressMessage();
                        isCancel = false;
                    } else {
                        //cancelAnimations();
                        isCancel = true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (isCancel) {
                        recoderView.cancelRecord();
                    }else{
                        recoderView.endRecord();
                    }
                    releaseAnimations();
                    break;
                default:
                    break;
            }
            return false;
        }
    }


    @SuppressLint("LongLogTag")
    protected void showDialog(final String path) {
        AlertDialog.Builder builder = new AlertDialog.Builder(QuarterVideoRecordActivity.this);
        builder.setMessage("确定去发表？");
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(QuarterVideoRecordActivity.this, QuarterPublishActivity.class);
                intent.putExtra("path",path);
                startActivity(intent);
                dialog.dismiss();


            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    /**
     * 按下时候动画效果
     */
    public void pressAnimations() {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 1.5f,
                1, 1.5f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(200);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(200);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillAfter(true);
        //message.setText("松手完成");
        videoController.startAnimation(animationSet);
    }

    /**
     * 释放时候动画效果
     */
    public void releaseAnimations() {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.5f, 1f,
                1.5f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(200);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(200);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillAfter(true);

        //message.setVisibility(View.GONE);
        videoController.setText("按住拍");
        videoController.startAnimation(animationSet);
    }

    public void holdAnimations() {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.5f, 1f,
                1.5f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(200);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(200);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setFillAfter(true);

        videoController.setText("松手完成");
        videoController.startAnimation(animationSet);
    }


    /**
     * get resolution
     *
     * @return
     */
    @SuppressLint("LongLogTag")
    public int[] getDisplayMetrics() {
        int resolution[] = new int[2];
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay()
                .getMetrics(dm);
        resolution[0] = dm.widthPixels;
        resolution[1] = dm.heightPixels;
        Log.e(TAG+".getDisplayMetrics()","DisplayMetrics.width="+dm.widthPixels+"***DisplayMetrics.height=dm.heightPixels");
        return resolution;
    }
    private void cancelTitle() {
        //取消标题栏
        getSupportActionBar().hide();
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
