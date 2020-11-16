package mavs.uta.team4carental.ui.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.utils.DBHelper;

public class EditSelectedUserProfileActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private EditText et_role;
    private EditText et_UTAID;
    private EditText et_firstname;
    private EditText et_lastname;
    private EditText et_phone;
    private EditText et_email;
    private EditText et_address;
    private EditText et_city;
    private EditText et_state;
    private EditText et_zipcode;
    private EditText et_member;
    private EditText et_status;

    private String username;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_selected_user_profile);
        initView();
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

    }

    @SuppressLint("WrongViewCast")
    private void initView() {
        et_username = findViewById(R.id.Edit_Selected_User_Profile_Username);
        et_password = findViewById(R.id.Edit_Selected_User_Profile_Password);
        et_role = findViewById(R.id.Edit_Selected_User_Profile_Role);
        et_UTAID = findViewById(R.id.Edit_Selected_User_Profile_UtaID);
        et_firstname = findViewById(R.id.Edit_Selected_User_Profile_Firstname);
        et_lastname = findViewById(R.id.Edit_Selected_User_Profile_Lastname);
        et_phone = findViewById(R.id.Edit_Selected_User_Profile_Phone);
        et_email = findViewById(R.id.Edit_Selected_User_Profile_Email);
        et_address = findViewById(R.id.Edit_Selected_User_Profile_Address);
        et_city = findViewById(R.id.Edit_Selected_User_Profile_City);
        et_state = findViewById(R.id.Edit_Selected_User_Profile_State);
        et_zipcode = findViewById(R.id.Edit_Selected_User_Profile_Zipcode);
        et_member = findViewById(R.id.Edit_Selected_User_Profile_MemberID);
        et_status = findViewById(R.id.Edit_Selected_User_Profile_Status);
    }




}
