package mavs.uta.team4carental.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.ui.user.ViewReservations;

public class UserActivity extends AppCompatActivity {


    private Button requestCarButton;

    private Button mBtnViewReservation;
    private Button mBtnViewRequestCar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        //将布局中的button匹配给变量
        mBtnViewReservation=(Button)findViewById(R.id.btn_ViewReservation);
        //设置ViewReservation的跳转
        mBtnViewReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = getIntent().getStringExtra("userName");
                Intent intent = new Intent(UserActivity.this, mavs.uta.team4carental.ui.user.ViewReservations.class);
                intent.putExtra("userName", userName);
                startActivity(intent);
            }
        });


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        requestCarButton = findViewById(R.id.btn_RequestCar);
        requestCarButton.setOnClickListener(v -> {
            startActivity(new Intent(UserActivity.this, requestCarActivity.class));
        });
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_user, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }
}