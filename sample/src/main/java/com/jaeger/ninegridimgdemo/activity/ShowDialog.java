package com.jaeger.ninegridimgdemo.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bm.library.PhotoView;
import com.jaeger.ninegridimgdemo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 轻轻的我来了，不留下一个Bug
 * <p>
 * 创建时间：2018/1/25
 * creator:  LYS
 * 功能描述：
 */

public class ShowDialog extends Dialog {

    TextView flag_tv;
    ViewPager show_big_vp;
    private Context context;

    int index;

    private List<String> imgList=new ArrayList<>();


    public ShowDialog(@NonNull Context context, List<String> imgList,int index) {
        super(context);
        this.context=context;
        this.imgList=imgList;

        this.index=index;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_show);

        setCanceledOnTouchOutside(false);

        flag_tv=(TextView)findViewById(R.id.flag_tv);
        show_big_vp= (ViewPager) findViewById(R.id.show_big_vp);
        show_big_vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(context);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);

                Picasso.with(context).load(imgList.get(position)).placeholder(R.drawable.ic_default_image).into(view);

//                view.setImageDrawable(imgList.get(position));
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        show_big_vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                flag_tv.setText((position+1)+"/"+imgList.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        show_big_vp.setCurrentItem(index);
        flag_tv.setText(index+1+"/"+imgList.size());
    }
}
