package com.msisuzney.minisoccer;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.msisuzney.minisoccer.DQDApi.model.LaunchImg;
import com.msisuzney.minisoccer.DQDApi.MyRetrofit;
import com.msisuzney.minisoccer.view.activities.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private static final String URL = "http://dongqiudi.com/app/android/launch_images";
    @BindView(R.id.splash_iv)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        MyRetrofit.getMyRetrofit().getApiService().getLaunchImg(URL).enqueue(new Callback<List<LaunchImg>>() {
            @Override
            public void onResponse(Call<List<LaunchImg>> call, Response<List<LaunchImg>> response) {
                try {
                    List<LaunchImg> launchImgs = response.body();
                    if (launchImgs != null && launchImgs.size() > 0 && !launchImgs.get(0).getIs_ad()) {
                        loadImgFromNet(launchImgs.get(0).getImage_url1());
                    } else {
                        loadImgFromLocal();
                    }
                } catch (Exception e) {
                    loadImgFromLocal();
                }
            }

            @Override
            public void onFailure(Call<List<LaunchImg>> call, Throwable t) {
                loadImgFromLocal();
            }
        });

//        getWindow().getDecorView().findViewById(R.id.splash_iv);
        iv.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        iv.setVisibility(View.VISIBLE);

//        animateImage();
    }

    private void loadImgFromNet(String url) {
        Glide.with(this).load(url).into(iv);
        animateImage();
    }

    private void loadImgFromLocal() {
        iv.setImageResource(R.drawable.lanuch);
        animateImage();
    }

    private void animateImage() {
        ObjectAnimator x = ObjectAnimator.ofFloat(iv, "scaleX", 1f, 1f);
        ObjectAnimator y = ObjectAnimator.ofFloat(iv, "scaleY", 1f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(3400).play(x).with(y);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }
}
