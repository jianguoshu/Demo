package task.activity.com.bindertestdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
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

    public class BookBinder extends IBookManager.Stub {

        List<Book> books = new ArrayList<>();

        @Override
        public List<Book> getBookList() throws RemoteException {
            Log.i("douzi", "getBookList : " + Thread.currentThread().toString() + "-" + Thread.currentThread().getId());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return books;
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            if (book != null) {
                Log.i("douzi", "addBook--book : " + book+ "--" + book.superString());
                books.add(book);
            }
        }
    }
}
