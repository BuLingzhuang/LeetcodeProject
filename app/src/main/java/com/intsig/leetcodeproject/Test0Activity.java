package com.intsig.leetcodeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Test0Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_0);
        Log.e("测试", "Test0Activity onCreate");
        Intent intent = getIntent();
        boolean gggo = intent.getBooleanExtra("gggo", true);
        if (gggo) {
            Intent newIntent = new Intent(this, Test0Activity.class);
            newIntent.putExtra("gggo", false);
            startActivity(newIntent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("测试", "Test0Activity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("测试", "Test0Activity onDestroy");
    }

    public void startActivity1(View view) {
        //Activity1
        startActivity(new Intent(this, Test1Activity.class));
    }

    public void startActivityForResult1(View view) {
        startActivityForResult(new Intent(this, Test1Activity.class), 333);
    }
}
