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
import mavs.uta.team4carental.ui.user.requestCar.requestCarActivity;

public class UserActivity extends AppCompatActivity {
    /**
     * [!WARNING] 这仅仅是一个容器，除非你知道你在做什么，不然不要修改这个 ACTIVITY
     *
     * UserActivity
     * |- ProfileFragment
     * |- UserFragment <- 在这里修改 User 界面
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_user, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }
}