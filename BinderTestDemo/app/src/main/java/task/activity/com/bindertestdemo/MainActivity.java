package task.activity.com.bindertestdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private BookServiceConnection serviceConnection;

    IBookManager bookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceConnection = new BookServiceConnection();
        Intent service = new Intent(this, BookService.class);
        bindService(service, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    public class BookServiceConnection implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("douzi", "onServiceConnected : " + Thread.currentThread().toString() + "-" + Thread.currentThread().getId());
            bookManager = IBookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public void add(View view) {
        try {
            Book book = new Book("one", 125);
            Log.i("douzi", "client-addBook--book : " + book + "--" + book.superString());
            bookManager.addBook(book);
            Book book2 = new Book("two", 521);
            Log.i("douzi", "client-addBook--book2 : " + book2+ "--" + book2.superString());
            bookManager.addBook(book2);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void show(View view) {
        try {
            Log.i("douzi", "show : " + bookManager.getBookList().toString() + "-" + Thread.currentThread().getId());
            Log.i("douzi", "show : ==================================");
            Log.i("douzi", "show : " + Thread.currentThread().toString() + "-" + Thread.currentThread().getId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
