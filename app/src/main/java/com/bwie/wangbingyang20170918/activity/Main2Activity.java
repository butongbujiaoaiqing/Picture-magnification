package com.bwie.wangbingyang20170918.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bwie.wangbingyang20170918.R;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Main2Activity extends AppCompatActivity {

    private ImageView mImageView;
    private Button mBtn;
    private RelativeLayout mBgclor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mImageView = (ImageView) findViewById(R.id.imageView);
        Intent intent = getIntent();
        String img = intent.getStringExtra("image");
        ImageLoader.getInstance().displayImage(img, mImageView);
        mBtn = (Button) findViewById(R.id.btn);

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, "rotationX", 0f, 360f);
                animator.setDuration(2000);
                animator.start();
            }
        });

        mBgclor = (RelativeLayout) findViewById(R.id.bgclor);
        mBgclor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(mBgclor, "alpha", 1f, 0.5f);
                animator.setDuration(2000);
                animator.start();
            }
        });
    }
}
