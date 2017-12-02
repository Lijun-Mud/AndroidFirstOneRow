package com.example.hb49417.myapplication.tio;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hb49417.myapplication.R;
import com.example.hb49417.myapplication.mylitepal.Book;
import com.example.hb49417.myapplication.mylitepal.Chapter;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

public class FileOutputActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG="FileOutputActivity";

    public static final String OUTPUT_FILE_NAME = "myoutput.dat";
    private EditText textView;

    public static void start(Context context) {
        Intent starter = new Intent(context, FileOutputActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_output);

        savePreference();
        loadPreference();

        createDatabase();

        textView = (EditText) findViewById(R.id.file_output_textview);
        Button saveButton= (Button) findViewById(R.id.file_output_save_button);
        saveButton.setOnClickListener(this);
        Button loadButton= (Button) findViewById(R.id.file_output_load_button);
        loadButton.setOnClickListener(this);

        Button insertButton= (Button) findViewById(R.id.file_db_insert_button);
        insertButton.setOnClickListener(this);
        Button updateButton= (Button) findViewById(R.id.file_db_update_button);
        updateButton.setOnClickListener(this);

        Button myLitePalButton= (Button) findViewById(R.id.file_db_mylitepal_button);
        myLitePalButton.setOnClickListener(this);
    }

    private void updateDataIntoDatabase() {
        MyDatabaseHelper databaseHelper=buildBookstore();
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("price",500);
        db.update("books",values,"name=?",new String[]{"book1"});

        Cursor cursor= db.rawQuery("select * from books",null);
        if (cursor.moveToFirst()){
            do{
                Log.d(TAG, "updateDataIntoDatabase: id:"+cursor.getInt(cursor.getColumnIndex("id")));
                Log.d(TAG, "updateDataIntoDatabase:  name:" +cursor.getString(cursor.getColumnIndex("name")));
                Log.d(TAG, "updateDataIntoDatabase:  price:"+ cursor.getDouble(cursor.getColumnIndex("price")));
            }while(cursor.moveToNext());
        }
        cursor.close();
    }

    private void addDataIntoDatabase() {
        MyDatabaseHelper databaseHelper=buildBookstore();
        SQLiteDatabase db= databaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name","book1");
        values.put("author","a1");
        values.put("price","10.1");
        db.insert("books",null,values);
        values.clear();
        values.put("name","booka");
        values.put("price","50.5");
        db.insert("books",null,values);
    }

    private void createDatabase() {
        MyDatabaseHelper databaseHelper= buildBookstore();
        databaseHelper.getWritableDatabase();
    }

    @NonNull
    private MyDatabaseHelper buildBookstore() {
        return new MyDatabaseHelper(this,"bookstore",null,2);
    }

    private void loadPreference() {
        SharedPreferences preferences= getSharedPreferences("data",Context.MODE_PRIVATE);
        Log.d(TAG, "loadPreference: " + preferences.getBoolean("bool",false));
        Log.d(TAG, "loadPreference: " + preferences.getInt("int",0));
    }

    private void savePreference() {
        SharedPreferences.Editor editor= getSharedPreferences("data",Context.MODE_PRIVATE).edit();
        editor.putBoolean("bool",true);
        editor.putString("string","hello");
        editor.putInt("int",10);
        editor.apply();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.file_output_save_button:
                saveText();
                break;
            case R.id.file_output_load_button:
                loadText();
                break;
            case R.id.file_db_insert_button:
                addDataIntoDatabase();
                break;
            case R.id.file_db_update_button:
                updateDataIntoDatabase();
                break;
            case R.id.file_db_mylitepal_button:
                workWithLitePal();
                break;
            default:
                break;
        }
    }

    private void workWithLitePal() {
        SQLiteDatabase db= LitePal.getDatabase();
        Toast.makeText(this, "created by litepal", Toast.LENGTH_SHORT).show();
        /*Book book=new Book();
        book.setAuthor("author1");
        book.setName("booklitepal1");
        book.setTemp("temp1");
        book.setPrice(100);
        book.save();
        Chapter chapter=new Chapter();
        chapter.setTitle("titile from pal1");
        chapter.setBook(book);
        chapter.save();
        chapter=new Chapter();
        chapter.setTitle("titile from pal2");
        chapter.setComment("comment from pal2");
        chapter.setBook(book);
        chapter.save();*/

        /*Book book=new Book();
        book.setAuthor("authora");
        book.setName("booklitepala");
        book.setPrice(150);
        book.save();
        Chapter chapter=new Chapter();
        chapter.setTitle("titile from pala");
        chapter.setBook(book);
        chapter.save();*/

        /*Book book=new Book();
        book.setPrice(200);
        int result=book.updateAll("name=?","booklitepal1");
        Log.d(TAG, "workWithLitePal: updateAll:" + result);*/

        List<Book> books= DataSupport.where("price>?","150").find(Book.class);
        for (Book book:books
             ) {
            Log.d(TAG, "workWithLitePal: select all:"+ book.getName() +"\t" + book.getPrice());
        }
    }

    private void loadText() {
        BufferedReader reader=null;
        StringBuffer sb=new StringBuffer();
        try {
            FileInputStream stream=openFileInput(OUTPUT_FILE_NAME);
            reader=new BufferedReader(new InputStreamReader(stream));
            String line="";
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
            Toast.makeText(this,sb.toString(),Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveText() {
        BufferedWriter bufferedWriter=null;
        try {
            FileOutputStream fileOutputStream =openFileOutput(OUTPUT_FILE_NAME,Context.MODE_PRIVATE);
            bufferedWriter =new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(textView.getText().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
