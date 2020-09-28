package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;



public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText role;
    EditText lastname;
    EditText firstname;
    EditText phone;
    EditText email;
    EditText address;
    EditText city;
    EditText state;
    EditText zipcode;
    EditText member;

    Button register;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        findViews();
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name=username.getText().toString().trim();
                String pass=password.getText().toString().trim();
                String rol=role.getText().toString().trim();
                String lname=lastname.getText().toString().trim();
                String fname=firstname.getText().toString().trim();
                String pho=phone.getText().toString().trim();
                String ema=email.getText().toString().trim();
                String add=address.getText().toString().trim();
                String cit=city.getText().toString().trim();
                String sta=state.getText().toString().trim();
                String zcode=zipcode.getText().toString().trim();
                String mem = member.getText().toString().trim();



//                Log.i("TAG",name+"_"+pass+"_"+agestr+"_"+sexstr);
                UserService uService=new UserService(RegisterActivity.this);
                User user=new User();
                user.setUsername(name);
                user.setPassword(pass);
                user.setRole(rol);
                user.setLastname(lname);
                user.setFirstname(fname);
                user.setPhone(pho);
                user.setEmail(ema);
                user.setAddress(add);
                user.setCity(cit);
                user.setState(sta);
                user.setZipcode(zcode);
                user.setMember(mem);
                user.setStatus("live");

                uService.register(user);
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void findViews() {
        username=(EditText) findViewById(R.id.usernameRegister);
        password=(EditText) findViewById(R.id.passwordRegister);
        role=(EditText) findViewById(R.id.roleRegister);
        lastname=(EditText) findViewById(R.id.lastnameRegister);
        firstname=(EditText) findViewById(R.id.firstnameRegister);
        phone=(EditText) findViewById(R.id.phoneRegister);
        email=(EditText) findViewById(R.id.emailRegister);
        address=(EditText) findViewById(R.id.addressRegister);
        city=(EditText) findViewById(R.id.cityRegister);
        state=(EditText) findViewById(R.id.stateRegister);
        zipcode=(EditText) findViewById(R.id.zipcodeRegister);
        member=(EditText) findViewById(R.id.memberRegister);

        register=(Button) findViewById(R.id.Register);
    }

}
