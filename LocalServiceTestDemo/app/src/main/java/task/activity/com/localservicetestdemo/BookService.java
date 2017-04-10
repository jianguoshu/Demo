package task.activity.com.localservicetestdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BookService extends Service {
    public BookService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i("douzi", "onBind : " + Thread.currentThread().toString() + "-" + Thread.currentThread().getId());
        return new BookBinder();
    }

    List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBookList() {
        return books;
    }

    public class BookBinder extends Binder {
        public void addBook(Book book) {
            BookService.this.addBook(book);
        }

        public List<Book> getBookList() {
            Log.i("douzi", "getBookList : " + Thread.currentThread().toString() + "-" + Thread.currentThread().getId());
            return BookService.this.getBookList();
        }
    }
}
