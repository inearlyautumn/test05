package com.example.test05;

import android.support.annotation.NonNull;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.example.test05.view.CustomLinearLayout;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DrawerLayout drawerLayout = findViewById(R.id.drawerlayout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        final CustomLinearLayout customLinearLayout = findViewById(R.id.custom_lly);

        TextView tvTest = findViewById(R.id.tv_test);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                customLinearLayout.clearAllViews();
                keyBoardCancle();
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        tvTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(Gravity.END);

                    ArrayList<String> list = new ArrayList<>();
                    String s = "待处理";
                    for (int i = 0; i < 7; i++) {
                        list.add(s);
                        s += "测";
                    }

                    customLinearLayout.addSideslipOptionLayout(MainActivity.this, "测试一", list);
                    customLinearLayout.addSideslipOptionLayout(MainActivity.this, "测试二", list);
                    customLinearLayout.addSideslipInputLayout(MainActivity.this, "测试三");
                    customLinearLayout.addSideslipInputLayout(MainActivity.this, "测试四");
                }
            }
        });
    }

    //    强制隐藏软键盘
    public void keyBoardCancle() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
