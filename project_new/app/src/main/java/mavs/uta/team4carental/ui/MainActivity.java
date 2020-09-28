package mavs.uta.team4carental.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.utils.DBHelper;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button registerButton;
    private Button loginButton;

    private EditText username;
    private EditText password;

    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initView();
    }

    private void initView() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        loginButton = findViewById(R.id.login);
        loginButton.setOnClickListener(v -> {
            this.login();
        });

    }

    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString().trim();
    }

    private void login() {
        dbHelper = new DBHelper(this);
        String qusername = getStringFromEditText(this.username);
        String qpassword = getStringFromEditText(this.password);
        if (qusername.length() > 0 && qpassword.length() > 0) {
            String result = dbHelper.queryLogin(
                qusername, qpassword
            );
            handleAfterLogin(result);
        } else {
            Toast.makeText(this, "Field is required!", Toast.LENGTH_LONG).show();
            // TODO: to handle exception
        }
    }

    private void handleAfterLogin(String result) {
        switch (result) {
            case "User":
                startActivity(new Intent(this, UserActivity.class));
                break;
            case "Admin":
                startActivity(new Intent(this, AdminActivity.class));
                break;
            case "Manager":
                startActivity(new Intent(this, ManagerActivity.class));
                break;
            default:
                Toast.makeText(this, "Incorrectly inputs!", Toast.LENGTH_LONG).show();
        }
    }
}