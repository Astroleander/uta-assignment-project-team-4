package mavs.uta.team4carental.ui;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import mavs.uta.team4carental.R;

public class SearchForUsersActivity extends AppCompatActivity {

    private EditText lastname;
    private Button searchUserButton;

    private ListView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchforuser);
        this.initView();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_admin, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void initView() {
        lastname = findViewById(R.id.Search_For_Users_LastNameEditText);
        searchUserButton = findViewById(R.id.Search_For_Users_SearchButton);


    }


}
