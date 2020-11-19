package mavs.uta.team4carental.ui.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.utils.DBHelper;

public class EditSelectedUserProfileActivity extends AppCompatActivity {

    private TextView tv_username;
    private EditText et_password;
    private TextView tv_role;
    private EditText et_UTAID;
    private EditText et_firstname;
    private EditText et_lastname;
    private EditText et_phone;
    private EditText et_email;
    private EditText et_address;
    private EditText et_city;
    private Spinner sp_state;
    private EditText et_zipcode;
    private EditText et_member;
    private EditText et_status;
    private Button bt_edit;

    private String username;
    private User userProfile;
    private String selectedState;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_selected_user_profile);
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        this.getUserProfile();
        this.initView();
        this.showProfile();
        this.initSubmit();

    }

    private void getUserProfile() {
        dbHelper = new DBHelper(this);

        String[] usernameToDB = new String[]{username};

        User[] result = dbHelper.queryUser("USERNAME=?", usernameToDB);
        userProfile = result[0];
    }

    private void showProfile() {
        tv_username.setText(userProfile.getUsername());
        et_password.setText(userProfile.getPassword());
        tv_role.setText(userProfile.getRole());
        et_UTAID.setText(userProfile.getUta_id());
        et_lastname.setText(userProfile.getLastname());
        et_firstname.setText(userProfile.getFirstname());
        et_phone.setText(userProfile.getPhone());
        et_email.setText(userProfile.getEmail());
        et_address.setText(userProfile.getAddress());
        et_city.setText(userProfile.getCity());
        et_zipcode.setText(userProfile.getZipcode());
        et_status.setText(userProfile.getStatus());
    }


    private void initView() {
        tv_username = findViewById(R.id.Edit_Selected_User_Profile_Username);
        et_password = findViewById(R.id.Edit_Selected_User_Profile_Password);
        tv_role = findViewById(R.id.Edit_Selected_User_Profile_Role);
        et_UTAID = findViewById(R.id.Edit_Selected_User_Profile_UtaID);
        et_firstname = findViewById(R.id.Edit_Selected_User_Profile_Firstname);
        et_lastname = findViewById(R.id.Edit_Selected_User_Profile_Lastname);
        et_phone = findViewById(R.id.Edit_Selected_User_Profile_Phone);
        et_email = findViewById(R.id.Edit_Selected_User_Profile_Email);
        et_address = findViewById(R.id.Edit_Selected_User_Profile_Address);
        et_city = findViewById(R.id.Edit_Selected_User_Profile_City);
        sp_state = findViewById(R.id.Edit_Selected_User_Profile_State);
        /* state */
        {
            String[] states = getResources().getStringArray(R.array.state_list);
            sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                    EditSelectedUserProfileActivity.this.selectedState = states[pos];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            int texas = Arrays.asList(states).indexOf(userProfile.getState());
            if (texas > 0) {
                sp_state.setSelection(texas);
            }
        }
        et_zipcode = findViewById(R.id.Edit_Selected_User_Profile_Zipcode);
        et_member = findViewById(R.id.Edit_Selected_User_Profile_MemberID);
        if(userProfile.getRole().equals("User")){
            et_member.setVisibility(View.VISIBLE);
        }else{
            et_member.setVisibility(View.INVISIBLE);
        }
        et_status = findViewById(R.id.Edit_Selected_User_Profile_Status);
    }
    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }

    private void initSubmit() {
        bt_edit = findViewById(R.id.Edit_Selected_User_Profile_Button);
        bt_edit.setOnClickListener(view -> {
            userProfile.setPassword(getStringFromEditText(et_password));
            userProfile.setUta_id(getStringFromEditText(et_UTAID));
            userProfile.setFirstname(getStringFromEditText(et_firstname));
            userProfile.setLastname(getStringFromEditText(et_lastname));
            userProfile.setPhone(getStringFromEditText(et_phone));
            userProfile.setEmail(getStringFromEditText(et_email));
            userProfile.setAddress(getStringFromEditText(et_address));
            userProfile.setCity(getStringFromEditText(et_city));
            userProfile.setState(selectedState);
            userProfile.setZipcode(getStringFromEditText(et_zipcode));
            System.out.println(userProfile);
            dbHelper.editUser(userProfile);
            Toast.makeText(this, "Edit success", Toast.LENGTH_LONG).show();
            Intent intent = getIntent();
            intent.setClass(EditSelectedUserProfileActivity.this, ViewSelectedUserProfileActivity.class);
            System.out.println(username);
            intent.putExtra("username", username);
            startActivity(intent);
        });
    }


}
