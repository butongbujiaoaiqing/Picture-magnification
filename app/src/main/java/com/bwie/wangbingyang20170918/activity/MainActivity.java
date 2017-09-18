package com.bwie.wangbingyang20170918.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bwie.wangbingyang20170918.R;
import com.bwie.wangbingyang20170918.adapter.RecyclerViewAdapter;
import com.bwie.wangbingyang20170918.bean.RecyclerViewBean;
import com.bwie.wangbingyang20170918.utils.OkHttp;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRcy;
    private String path = "https://news-at.zhihu.com/api/4/news/latest";
    private List<RecyclerViewBean.TopStoriesBean> mStoriesBeen = new ArrayList<>();
    private RecyclerViewAdapter mAdapter;
    private List<String> mList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //初始化控件
        mRcy = (RecyclerView) findViewById(R.id.rcy);
        //设置RecyclerView的显示效果
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRcy.setLayoutManager(manager);
        //OkHttp请求网络数据
        OkHttp.getAsync(path, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                //网络请求失败就Toast
                Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                //请求网络数据
                mStoriesBeen.addAll(new Gson().fromJson(result, RecyclerViewBean.class).getTop_stories());
                mAdapter = new RecyclerViewAdapter(MainActivity.this, mStoriesBeen);
                //展示到RecyclerView上
                mRcy.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                mAdapter.setOnItemClickLitener(new RecyclerViewAdapter.OnItemClickLitener() {
                    /**
                     *      RecyclerView点击
                     * @param view
                     * @param position
                     */
                    @Override
                    public void onItemClick(View view, int position) {
                        //点击跳转到另一个Avtivity并把图片传过去
                        String imageforcutting = mStoriesBeen.get(position).getImage();
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        intent.putExtra("image", imageforcutting);
                        startActivity(intent);
                    }

                    /**
                     *      RecyclerView长按
                     * @param view
                     * @param position
                     */
                    @Override
                    public void onItemLongClick(View view, int position) {
                        //自己制造的一个异常
                        mList.add("1111111");
                    }
                });
            }
        });
    }
}
