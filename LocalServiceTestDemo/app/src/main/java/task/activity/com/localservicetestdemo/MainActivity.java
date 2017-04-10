package task.activity.com.localservicetestdemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private BookServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, BookService.class);
        connection = new BookServiceConnection();
        this.bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    BookService.BookBinder bookBinder;

    public void add(View view) {
        bookBinder.addBook(new Book("one", 125));
        bookBinder.addBook(new Book("two", 521));
    }

    public void show(View view) {
        Log.i("douzi", "show : " + bookBinder.getBookList().toString() + "-" + Thread.currentThread().getId());
        Log.i("douzi", "show : " + Thread.currentThread().toString() + "-" + Thread.currentThread().getId());
    }

    public class BookServiceConnection implements ServiceConnection {

        /**
         * Called when a connection to the Service has been established, with
         * the {@link IBinder} of the communication channel to the
         * Service.
         *
         * @param name    The concrete component name of the service that has
         *                been connected.
         * @param service The IBinder of the Service's communication channel,
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("douzi", "onServiceConnectedonServiceConnected : " + Thread.currentThread().toString() + "-" + Thread.currentThread().getId());
            bookBinder = (BookService.BookBinder) service;
        }

        /**
         * Called when a connection to the Service has been lost.  This typically
         * happens when the process hosting the service has crashed or been killed.
         * This does <em>not</em> remove the ServiceConnection itself -- this
         * binding to the service will remain active, and you will receive a call
         * to {@link #onServiceConnected} when the Service is next running.
         *
         * @param name The concrete component name of the service whose
         *             connection has been lost.
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
