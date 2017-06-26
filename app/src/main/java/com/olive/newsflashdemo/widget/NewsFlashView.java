package com.olive.newsflashdemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.olive.newsflashdemo.R;
import com.olive.newsflashdemo.model.NewsFlashBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/23.
 */
public class NewsFlashView extends ViewFlipper {

    private Context mContext;
    private boolean isSetAnimDuration = false;
    private OnItemClickListener onItemClickListener;

    private int interval = 2000;
    private int animDuration = 500;


    public NewsFlashView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        this.mContext = context;

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.MarqueeViewStyle, defStyleAttr, 0);
        interval = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvInterval, interval);
        isSetAnimDuration = typedArray.hasValue(R.styleable.MarqueeViewStyle_mvAnimDuration);
        animDuration = typedArray.getInteger(R.styleable.MarqueeViewStyle_mvAnimDuration, animDuration);

        typedArray.recycle();

        setFlipInterval(interval);

        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
        if (isSetAnimDuration)
            animIn.setDuration(animDuration);
        setInAnimation(animIn);

        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
        if (isSetAnimDuration) animOut.setDuration(animDuration);
        setOutAnimation(animOut);
    }


    // 启动轮播
    public boolean start(List<NewsFlashBean> newsFlashBeanList) {
        if (newsFlashBeanList == null || newsFlashBeanList.size() == 0) {
            return false;
        }
        removeAllViews();

        for (int i = 0; i < newsFlashBeanList.size(); i++) {
            final LinearLayout linearLayout = createLinearLayout(newsFlashBeanList.get(i), i);
            addView(linearLayout);
        }

        if (newsFlashBeanList.size() > 1) {
            startFlipping();
        }
        return true;
    }


    // 创建ViewFlipper下的TextView
    private LinearLayout createLinearLayout(NewsFlashBean newsFlashBean, final int position) {
        View rootView = View.inflate(mContext, R.layout.layout_news_flash, null);
        LinearLayout linearLayout = (LinearLayout) rootView;

        TextView tv = (TextView) linearLayout.findViewById(R.id.tv_title);
        tv.setText(newsFlashBean.getNewsTitle());
        tv.setTag(position);

        TextView tv1 = (TextView) linearLayout.findViewById(R.id.tv_content);
        tv1.setText(newsFlashBean.getNewsContent());
        tv1.setTag(position);
        linearLayout.setClickable(true);
        linearLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });
        return linearLayout;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


}
