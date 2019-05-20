package com.example.test05.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.test05.R;
import com.example.test05.ToastUtil;
import com.example.test05.utils.DrawableUtil;
import com.example.test05.utils.UiUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomLinearLayout extends LinearLayout {
    public static final String TAG = "CustomLinearLayout";

    private LinearLayout llyContainer;
    private LinearLayout llyBottom;
    private ScrollView scrollView;

    private int originalHeight;
    private boolean keyboardShowed;
    private List<String> dataList;

    public CustomLinearLayout(Context context) {
        super(context);
        init();
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        dataList = new ArrayList<>();

        View viewOne = LayoutInflater.from(getContext()).inflate(R.layout.sideslip_scrollview_layout, this);
        View viewTwo = LayoutInflater.from(getContext()).inflate(R.layout.sideslip_bottom_layout, this);

        llyContainer = viewOne.findViewById(R.id.lly_container);
        scrollView = viewOne.findViewById(R.id.scroll_view);

        llyBottom = viewTwo.findViewById(R.id.lly_bottom);
        viewTwo.findViewById(R.id.tv_reset).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast("重置");
            }
        });
        viewTwo.findViewById(R.id.tv_confirm).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (llyBottom.getHeight() != 0) {
            //这里保存llyBottom的高度
            originalHeight = llyBottom.getHeight();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
//        Log.i(TAG, "onLayout changed = " + changed + " left = " + left + " top = " + top + " right = " + right + " bottom = " + bottom);
        float width = right - left;
        float height = bottom - top;
        float value = height / width;
        float value02 = 4f / 3f;
        if (changed) {
            if (value < value02) {  // 如果高宽比小于4:3说明此时键盘弹出
                llyBottom.getLayoutParams().height = 0;
                keyboardShowed = true;
            } else {
                if (keyboardShowed) {
                    llyBottom.getLayoutParams().height = originalHeight;
                }
            }
            llyBottom.requestLayout();
        }
    }

    public void addSideslipOptionLayout(Context context, String title, List<String> list) {
        View view = View.inflate(context, R.layout.sideslip_option_layout, null);
        TextView tvTitle = view.findViewById(R.id.tv_option_title);
        FlowLayout flowLayout = view.findViewById(R.id.flow_layout);

        tvTitle.setText(title);

        int padLedt = UiUtils.dip2px(12);
        int padTop = UiUtils.dip2px(10);

        for (String str : list) {
            final TextView textView = new TextView(context);
            textView.setText(str);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.parseColor("#7ebe4a"));
            textView.setPadding(padLedt, padTop, padLedt, padTop);

//            Drawable normalBg = UiUtils.getDrawalbe(R.drawable.sideslip_btn_normal_bg);
//            Drawable selectedBg = UiUtils.getDrawalbe(R.drawable.sideslip_btn_selected_bg);
            textView.setBackgroundDrawable(UiUtils.getDrawalbe(R.drawable.sidelip_btn_selector));
            flowLayout.addView(textView);

            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setSelected(true);
                    // TODO: 2019/3/31  用集合在这里获值
                }
            });
        }
        llyContainer.addView(view);
    }

    public void addSideslipInputLayout(Context context, String title) {
        View view = View.inflate(context, R.layout.sideslip_input_layout, null);
        TextView tvTile = view.findViewById(R.id.tv_input_title);
        EditText editText = view.findViewById(R.id.et_input);

        tvTile.setText(title);
        // TODO: 2019/4/1  用集合获取对象

        llyContainer.addView(view);
    }

    public void clearAllViews() {
        llyContainer.removeAllViews();
    }
}
