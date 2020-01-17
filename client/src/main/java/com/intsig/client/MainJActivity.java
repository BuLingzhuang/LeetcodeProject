package com.intsig.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.intsig.leetcodeproject.test.Book;
import com.intsig.leetcodeproject.test.IBookManager;

import java.util.List;

/**
 * @author lingzhuang_bu
 * Description:
 * @date 2019/11/17
 */
public class MainJActivity extends AppCompatActivity {

    private final String TAG = "Client";

    private IBookManager bookController;

    private boolean connected;

    private List<Book> bookList;

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (bookController == null) {
                return;
            }
            bookController.asBinder().unlinkToDeath(deathRecipient, 0);
            bookController = null;
            connected = false;
            Log.e(TAG, "远程服务挂了");
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookController = IBookManager.Stub.asInterface(service);
            try {
                service.linkToDeath(deathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            connected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService();
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.intsig.leetcodeproject.test.BookServiceJava");
        intent.setPackage("com.intsig.leetcodeproject");
        intent.setComponent(new ComponentName("com.intsig.leetcodeproject", "com.intsig.leetcodeproject.test.BookServiceJava"));
        boolean result = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.e(TAG, "result = " + result);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connected) {
            unbindService(serviceConnection);
        }
    }

    public void getList(View view) {
        if (connected) {
            try {
                bookList = bookController.getBookList();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            for (Book book : bookList) {
                Log.e(TAG, "书 ===== " + book.getBookName() + " id = " + book.getBookId());
            }
        }
    }

    public void addBook(View view) {
        if (connected) {
            Book book = new Book(777, "这是一本新书 InOut");
            try {
                bookController.addBook(book);
                Log.e(TAG, "向服务器以InOut方式添加了一本新书");
                Log.e(TAG, "新书名：" + book.getBookName());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
