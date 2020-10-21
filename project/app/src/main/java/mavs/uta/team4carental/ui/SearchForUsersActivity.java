package mavs.uta.team4carental.ui;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import mavs.uta.team4carental.Adapter.UserListAdapter;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.utils.DBHelper;

public class SearchForUsersActivity extends AppCompatActivity {

    private EditText lastname;
    private Button searchUserButton;

    private ListView userListView;


    private DBHelper dbHelper;


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

        ViewGroup userListTitle = (ViewGroup) findViewById(R.id.user_table_title);

        List<User> userList = new ArrayList<User>();

        userList.add(new User("admin","123456", "a","Zhu","Yuting","123123123","asdf","adsf","asf","qwr","123412","asdf","yes"));
        userList.add(new User("admin1","123456", "a","Zhu","Yuting","123123123","asdf","adsf","asf","qwr","123412","asdf","yes"));

        UserListAdapter adapter = new UserListAdapter(this, userList);
        userListView.setAdapter(adapter);


        userListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });


    }

    private void initView() {
        lastname = findViewById(R.id.Search_For_Users_LastNameEditText);
        searchUserButton = findViewById(R.id.Search_For_Users_SearchButton);

        userListView = findViewById(R.id.Search_For_Users_UsersListView);


    }


}
