package com.example.myapplication;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.UserService;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        findViews();
    }
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;

    private void findViews() {
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        register=(Button) findViewById(R.id.register);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String name=username.getText().toString();
            System.out.println(name);
            String pass=password.getText().toString();
            System.out.println(pass);
            System.out.println("****************************************");
            Log.i("TAG",name+"_"+pass);
            UserService uService=new UserService(LoginActivity.this);
            boolean flag=uService.login(name, pass);
            String r = uService.searchRole(name);
            System.out.println("==============================================");
            if(flag){
                System.out.println("--------------获取成功-----------------------");
                Log.i("TAG","登录成功");
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                if(r.equals("user")){
                    Intent intent = new Intent(LoginActivity.this,userActivity.class);
                    startActivity(intent);
                }
                else if(r.equals("manger")){
                    Intent intent = new Intent(LoginActivity.this,mangerActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(LoginActivity.this,adminActivity.class);
                    startActivity(intent);
                }
//                    Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
//                    startActivity(intent);
            }else{
                Log.i("TAG","登录失败");
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
            }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}

