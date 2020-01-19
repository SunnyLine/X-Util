package com.pullein.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pullein.common.util.DensityUtil;
import com.pullein.common.util.SpannableUtil;

public class DensityActivity extends AppCompatActivity {

    private TextView tvDensity;
    private TextView tvString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_density);
        tvDensity = findViewById(R.id.tvDensity);
        tvString = findViewById(R.id.tvString);
        tvDensity.setText(DensityUtil.getScreenParams(this));
        SpannableUtil spannableUtil = new SpannableUtil();
        spannableUtil.addText("断章", Color.BLACK, DensityUtil.sp2px(this, 36))
                .addText("\n")
                .addText("作者:", Color.BLACK, DensityUtil.sp2px(this, 14))
                .addText("卞之琳", Color.BLUE, DensityUtil.sp2px(this, 14))
                .addText("\n")
                .addText("你站在桥上看风景，", Color.GRAY, DensityUtil.sp2px(this, 18))
                .addText("\n")
                .addText("看风景的人在楼上看你。 ", Color.YELLOW, DensityUtil.sp2px(this, 18))
                .addText("\n")
                .addText("明月装饰了你的窗子，", Color.LTGRAY, DensityUtil.sp2px(this, 18))
                .addText("\n")
                .addText("你装饰了别人的梦。", Color.MAGENTA, DensityUtil.sp2px(this, 18));
        tvString.setText(spannableUtil.create());
    }
}
