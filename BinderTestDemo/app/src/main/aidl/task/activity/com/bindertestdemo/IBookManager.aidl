// IBookManager.aidl
package task.activity.com.bindertestdemo;

import task.activity.com.bindertestdemo.Book;
// Declare any non-default types here with import statements

interface IBookManager {
            List<Book> getBookList();
            void addBook(in Book book);
}
