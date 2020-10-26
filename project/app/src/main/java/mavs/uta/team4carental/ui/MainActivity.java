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
    private String qusername;
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
        qusername = getStringFromEditText(this.username);
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
        Intent intent;
        switch (result) {
            case "User":
                intent = new Intent(this, UserActivity.class);
                intent.putExtra("userName", qusername);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Successful Login!", Toast.LENGTH_LONG).show();
                break;
            case "Admin":
                intent = new Intent(this, AdminActivity.class);
                intent.putExtra("userName", qusername);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Successful Login!", Toast.LENGTH_LONG).show();
                break;
            case "Manager":
                intent = new Intent(this, ManagerActivity.class);
                intent.putExtra("userName", qusername);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Successful Login!", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this, "Incorrectly Inputs!", Toast.LENGTH_LONG).show();
        }
    }
}