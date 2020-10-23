package mavs.uta.team4carental.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.utils.DBHelper;

public class ViewSelectedUserProfileActivity extends AppCompatActivity {

    TextView tv_username;
    TextView tv_password;
    TextView tv_role;
    TextView tv_UTAID;
    TextView tv_firstname;
    TextView tv_lastname;
    TextView tv_phone;
    TextView tv_email;
    TextView tv_address;
    TextView tv_city;
    TextView tv_state;
    TextView tv_zipcode;
    TextView tv_member;
    TextView tv_status;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_user_profile);
        initView();
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        System.out.println(username);
        showProfile(username);

    }

    private void showProfile(String username) {
        dbHelper = new DBHelper(this);

        String[] usernameToDB = new String[]{username};

        User[] result = dbHelper.queryUser("USERNAME=?", usernameToDB);

        User userProfile = result[0];
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

    }



}
