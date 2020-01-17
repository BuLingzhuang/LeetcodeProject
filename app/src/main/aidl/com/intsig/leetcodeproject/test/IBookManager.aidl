// IMyAidlBook.aidl
package com.intsig.leetcodeproject.test;
import com.intsig.leetcodeproject.test.Book;
// Declare any non-default types here with import statements

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
