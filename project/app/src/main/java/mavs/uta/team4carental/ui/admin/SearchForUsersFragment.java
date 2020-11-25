package mavs.uta.team4carental.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.adapter.UserListAdapter;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.utils.DBHelper;

public class SearchForUsersFragment extends Fragment {

    private EditText et_lastname;
    private Button btn_searchUsers;
    private ListView userListView;

    private View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_admin_search_for_users, container, false);
        this.initView();
        return root;
    }

    private void initView() {
        et_lastname = root.findViewById(R.id.Search_For_Users_LastNameEditText);
        btn_searchUsers = root.findViewById(R.id.Search_For_Users_SearchButton);
        userListView = root.findViewById(R.id.Search_For_Users_UsersListView);

        btn_searchUsers.setOnClickListener(view -> {
            User[] userarray = this.searchForUsers();
            List<User> userList = new ArrayList<User>(Arrays.asList(userarray));

            UserListAdapter adapter = new UserListAdapter(this.getContext(), userList);
            userListView.setAdapter(adapter);

            userListView.setOnItemClickListener((adapterView, view1, i, l) -> {
                Intent intent = new Intent(SearchForUsersFragment.this.getContext(), ViewSelectedUserProfileActivity.class);
                intent.putExtra("username", userarray[(int)l].getUsername());
                startActivity(intent);
            });

        });
    }

    private  User[] searchForUsers(){
        DBHelper dbHelper = new DBHelper(this.getContext());
        String lastname = et_lastname.getText().toString().trim();
        User[] results = {};
        if(lastname.equals("")){
            Toast.makeText(this.getContext(), "Please input the lastname.", Toast.LENGTH_SHORT).show();
            return results;
        }
        String[] lastnameToDB = new String[]{lastname};
        results = dbHelper.queryUser("LASTNAME=?", lastnameToDB);
        dbHelper.close();
        return results;
    }
}