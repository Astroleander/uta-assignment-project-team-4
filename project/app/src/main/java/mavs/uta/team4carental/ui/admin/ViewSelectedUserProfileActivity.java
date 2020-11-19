package mavs.uta.team4carental.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.utils.DBHelper;

public class ViewSelectedUserProfileActivity extends AppCompatActivity {

    private TextView tv_username;
    private TextView tv_password;
    private TextView tv_role;
    private TextView tv_UTAID;
    private TextView tv_firstname;
    private TextView tv_lastname;
    private TextView tv_phone;
    private TextView tv_email;
    private TextView tv_address;
    private TextView tv_city;
    private TextView tv_state;
    private TextView tv_zipcode;
    private TextView tv_member;
    private TextView tv_status;
    private Button bt_edit;
    private Button bt_revoke;
    private Button bt_change;

    private DBHelper dbHelper;
    private String username;
    private User userProfile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_user_profile);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        this.getUserProfile();
        this.initView();
        this.showProfile();

    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    private void getUserProfile() {
        dbHelper = new DBHelper(this);

        String[] usernameToDB = new String[]{username};

        User[] result = dbHelper.queryUser("USERNAME=?", usernameToDB);
        userProfile = result[0];
    }


    private void showProfile() {
        tv_username.setText("username:" + userProfile.getUsername());
        tv_password.setText("password:" + userProfile.getPassword());
        tv_role.setText("role:" + userProfile.getRole());
        tv_UTAID.setText("UTAID:" + userProfile.getUta_id());
        tv_lastname.setText("lastname:"+ userProfile.getLastname());
        tv_firstname.setText("firstname:" + userProfile.getFirstname());
        tv_phone.setText("phone:" + userProfile.getPhone());
        tv_email.setText("Email:" + userProfile.getEmail());
        tv_address.setText("address:" + userProfile.getAddress());
        tv_city.setText("city:" + userProfile.getCity());
        tv_state.setText("state:" + userProfile.getState());
        tv_zipcode.setText("zipcode:" + userProfile.getZipcode());
        tv_status.setText("status:" + userProfile.getStatus());
        if(userProfile.getRole().equals("User")){
            tv_member.setText("memberid:" + userProfile.getMember());
        }

    }

    private void initView() {
        tv_username = findViewById(R.id.View_Selected_User_Profile_Username);
        tv_password = findViewById(R.id.View_Selected_User_Profile_Password);
        tv_role = findViewById(R.id.View_Selected_User_Profile_Role);
        tv_UTAID = findViewById(R.id.View_Selected_User_Profile_UTAID);
        tv_firstname = findViewById(R.id.View_Selected_User_Profile_Firstname);
        tv_lastname = findViewById(R.id.View_Selected_User_Profile_Lastname);
        tv_phone = findViewById(R.id.View_Selected_User_Profile_Phone);
        tv_email = findViewById(R.id.View_Selected_User_Profile_Email);
        tv_address = findViewById(R.id.View_Selected_User_Profile_Address);
        tv_city = findViewById(R.id.View_Selected_User_Profile_City);
        tv_state = findViewById(R.id.View_Selected_User_Profile_State);
        tv_zipcode = findViewById(R.id.View_Selected_User_Profile_Zipcode);
        tv_member = findViewById(R.id.View_Selected_User_Profile_Member);
        tv_status = findViewById(R.id.View_Selected_User_Profile_Status);
        if(userProfile.getRole().equals("User")){
            tv_member.setVisibility(View.VISIBLE);
        }

        bt_edit = findViewById(R.id.View_Selected_User_Profile_EditButton);
        bt_edit.setOnClickListener(view -> {
            Intent intent = getIntent();
            intent.setClass(ViewSelectedUserProfileActivity.this, EditSelectedUserProfileActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);

        });

        bt_revoke = findViewById(R.id.View_Selected_User_Profile_Revoke);
        bt_revoke.setOnClickListener(view -> {
            dbHelper = new DBHelper(this);
            userProfile.setStatus("dead");
            dbHelper.editUser(userProfile);
            System.out.println(userProfile);

            refresh();

        });

        bt_change = findViewById(R.id.View_Selected_User_Profile_Change_Role);
        if(userProfile.getRole().equals("User")){
            bt_change.setVisibility(View.VISIBLE);
        }
        bt_change.setOnClickListener(view -> {
            dbHelper = new DBHelper(this);
            userProfile.setRole("Manager");
            dbHelper.editUser(userProfile);
            refresh();
        });

    }

    private void refresh() {
        finish();
        Intent intent = getIntent();
        intent.setClass(ViewSelectedUserProfileActivity.this, ViewSelectedUserProfileActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }


}
