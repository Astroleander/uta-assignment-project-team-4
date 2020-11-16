package mavs.uta.team4carental.ui.admin;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mavs.uta.team4carental.adapter.UserListAdapter;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.utils.DBHelper;

public class SearchForUsersActivity extends AppCompatActivity {

    private EditText et_lastname;
    private Button btn_searchUsers;

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


    }

    private void initView() {
        et_lastname = findViewById(R.id.Search_For_Users_LastNameEditText);
        btn_searchUsers = findViewById(R.id.Search_For_Users_SearchButton);
        userListView = findViewById(R.id.Search_For_Users_UsersListView);

        btn_searchUsers.setOnClickListener(view -> {
            User[] userarray = this.searchForUsers();
            List<User> userList = new ArrayList<User>(Arrays.asList(userarray));

            ViewGroup userListTitle = (ViewGroup) findViewById(R.id.user_table_title);

            UserListAdapter adapter = new UserListAdapter(this, userList);
            userListView.setAdapter(adapter);


            userListView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    Intent intent = new Intent();
                    intent.setClass(SearchForUsersActivity.this, ViewSelectedUserProfileActivity.class);
                    System.out.println(userarray[(int)l].getUsername());
                    intent.putExtra("username", userarray[(int)l].getUsername());
                    startActivity(intent);

                }
            });

        });

    }

    private  User[] searchForUsers(){
        dbHelper = new DBHelper(this);
        String lastname = et_lastname.getText().toString().trim();

        if(lastname == null){
            Toast.makeText(this, "Please input the lastname.", Toast.LENGTH_SHORT).show();
            return null;
        }
        String[] lastnameToDB = new String[]{lastname};
        User[] result = dbHelper.queryUser("LASTNAME=?", lastnameToDB);
        return result;


    }


}
