package com.example.bmob;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bmob.bean.Person;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {
    private Button button,button_query,button_queryadd;
    private EditText editText_name,editText_address,editText_setname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this,"e5b4f6072cea67aa76895b8d30c00ac3");
        button= (Button) findViewById(R.id.button_add);
        button_query= (Button) findViewById(R.id.button_query);
        button_queryadd= (Button) findViewById(R.id.button_queryadd);
        editText_name= (EditText) findViewById(R.id.editText_name);
        editText_address= (EditText) findViewById(R.id.editText_address);
        editText_setname= (EditText) findViewById(R.id.editText_query);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= editText_name.getText().toString();
                String address=editText_address.getText().toString();
                final Person person =new Person();
                person.setName(name);
                person.setAddress(address);

                person.save(MainActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this,"添加成功,返回objectId为"+person.getObjectId(),Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(MainActivity.this,"添加失败,返回msg为"+s,Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

    }
    public  void  queryAll(View view){
        BmobQuery<Person> query=new BmobQuery<Person>();
        query.findObjects(MainActivity.this, new FindListener<Person>() {
            @Override
            public void onSuccess(List<Person> list) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Query");
                String str ="";
                for(Person person:list){
                    str+=person.getName()+";"+person.getAddress()+"\n";
                }
                builder.setMessage(str);
                builder.create().show();
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }
    public void queryAddress(View view){
        String str = editText_setname.getText().toString();
        BmobQuery<Person> query=new BmobQuery<Person>();
        query.addWhereEqualTo("name",str);
        query.findObjects(MainActivity.this, new FindListener<Person>() {
            @Override
            public void onSuccess(List<Person> list) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Query");
                String str ="";
                for(Person person:list){
                    str+=person.getName()+";"+person.getAddress()+"\n";
                }
                builder.setMessage(str);
                builder.create().show();
            }

            @Override
            public void onError(int i, String s) {

            }
        });

    }
}
