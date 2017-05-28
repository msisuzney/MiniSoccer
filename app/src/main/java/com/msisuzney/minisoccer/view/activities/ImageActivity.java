package com.msisuzney.minisoccer.view.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.msisuzney.minisoccer.R;
import com.msisuzney.minisoccer.utils.PinchImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageActivity extends AppCompatActivity {

//    private static HandlerThread handlerThread = new HandlerThread("DownloadImage");

    public static final String img_url = "url";
    @BindView(R.id.img)
    PinchImageView imageView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.save_img)
    Button btn;
    File appDir;
    private String url;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        url = intent.getStringExtra(img_url);
        Glide.with(this).load(url).into(imageView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        appDir = new File(Environment.getExternalStorageDirectory(), "迷你懂啊");
        if (!appDir.exists()) appDir.mkdir();

    }

    private void save() {
        Glide.with(this).load(url).downloadOnly(new SimpleTarget<File>() {

            //onResourceReady是在UI线程进行的，但这种所谓下载只是将Glide缓存的图片读出来而已，图片一定是存在的，所以不存在耗时
            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                try {
                    FileInputStream fin = new FileInputStream(resource);
                    FileChannel fcin = fin.getChannel();
                    File file = new File(appDir, "迷你懂_" + System.currentTimeMillis() + ".jpg");
//                Log.d("ImageActivity", file.getAbsolutePath());
                    FileOutputStream fout = new FileOutputStream(file);
                    FileChannel fcout = fout.getChannel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    while (true) {
                        buffer.clear();
                        int r = fcin.read(buffer);
                        if (r == -1) {
                            break;
                        }
                        buffer.flip();
                        fcout.write(buffer);
                    }
                } catch (Exception e) {

                } finally {
                    Toast.makeText(ImageActivity.this, "图片已保存到文件夹 迷你懂啊", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}