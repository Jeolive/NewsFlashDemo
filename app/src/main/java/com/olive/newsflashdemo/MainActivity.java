package com.olive.newsflashdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.olive.newsflashdemo.model.NewsFlashBean;
import com.olive.newsflashdemo.widget.NewsFlashView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    List<NewsFlashBean> newsFlashBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewsFlashView mMarquee3 = (NewsFlashView) findViewById(R.id.mv_bar3);


        //设置集合

        for (int i = 0; i < 7; i++) {


            NewsFlashBean newsFlashBean = new NewsFlashBean();
            if (i + 1 == 3) {

                newsFlashBean.setNewsTitle("【Title" + (i + 1) + "】");

                newsFlashBean.setNewsContent("这是第" + (i + 1) + "条长长长长长长长长长长长长长长长长长长长长长长长长长");

            } else {
                newsFlashBean.setNewsTitle("【Title" + (i + 1) + "】");
                newsFlashBean.setNewsContent("这是第" + (i + 1) + "条");

            }
            newsFlashBeanList.add(newsFlashBean);
        }


        mMarquee3.start(newsFlashBeanList);

        mMarquee3.setOnItemClickListener(new NewsFlashView.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getApplicationContext(), newsFlashBeanList.get(position).getNewsTitle(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}
