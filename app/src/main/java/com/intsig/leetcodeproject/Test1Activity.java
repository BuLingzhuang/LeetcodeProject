package com.intsig.leetcodeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Test1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("测试", "Test1Activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("测试", "Test1Activity onDestroy");
    }

    public void startActivity2(View view) {
        //Activity1
        startActivity(new Intent(this, Test2Activity.class));
    }

    public void startActivityForResult2(View view) {
        startActivityForResult(new Intent(this, Test2Activity.class), 333);
    }
}
