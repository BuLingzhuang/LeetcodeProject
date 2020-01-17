package com.intsig.leetcodeproject.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

public class BookServiceJava extends Service {
    private ArrayList<Book> bookList = new ArrayList<>();
    private IBookManager.Stub stub = new IBookManager.Stub() {
        @Override
        public List<Book> getBookList() throws RemoteException {
            return bookList;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            book.setBookName("远端增加的书，名：" + book.getBookName());
            bookList.add(book);
        }
    };

    public BookServiceJava() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        bookList.add(new Book(1, "大学"));
        bookList.add(new Book(2, "中庸"));
        bookList.add(new Book(3, "春秋"));
        bookList.add(new Book(4, "谷歌"));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }
}
