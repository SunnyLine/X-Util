package com.pullein.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.pullein.common.widget.IndicatorView;

public class IndicatorActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private IndicatorView indicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);
        viewPager = findViewById(R.id.viewPager);
        indicatorView = findViewById(R.id.indicatorView);
        viewPager.setAdapter(new ViewPagerAdapter());
        indicatorView.bindViewPagerAutoScroll(viewPager);
    }

    private class ViewPagerAdapter extends PagerAdapter {
        private int[] drawables = new int[]{R.mipmap.new_feature_1, R.mipmap.new_feature_2, R.mipmap.new_feature_3, R.mipmap.new_feature_4};

        @Override
        public int getCount() {
            return drawables.length;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View imageLayout = LayoutInflater.from(IndicatorActivity.this).inflate(R.layout.layout_image, container, false);
            ImageView imageView = (ImageView) imageLayout.findViewById(R.id.layoutImg);
            imageView.setImageResource(drawables[position]);
            container.addView(imageLayout, 0);
            return imageLayout;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }
    }
}
