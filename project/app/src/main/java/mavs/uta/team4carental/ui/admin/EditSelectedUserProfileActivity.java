package mavs.uta.team4carental.ui.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.utils.DBHelper;

public class EditSelectedUserProfileActivity extends AppCompatActivity {

    private TextView tv_username;
    private EditText et_password;
    private RadioGroup tv_role;
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
    private TextView tv_member;
    private TextView tv_status;

    private FloatingActionButton bt_edit;
    private FloatingActionButton bt_back;

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

        RadioButton form_role_admin = findViewById(R.id.role_radio_admin);
        RadioButton form_role_manager = findViewById(R.id.role_radio_manager);
        RadioButton form_role_user = findViewById(R.id.role_radio_user);

        if (userProfile.getRole().equals(form_role_admin.getText().toString())) {
            form_role_admin.setChecked(true);
        } else if (userProfile.getRole().equals(form_role_manager.getText().toString())) {
            form_role_manager.setChecked(true);
        } else {
            form_role_user.setChecked(true);
        }

        et_UTAID.setText(userProfile.getUta_id());
        et_lastname.setText(userProfile.getLastname());
        et_firstname.setText(userProfile.getFirstname());
        et_phone.setText(userProfile.getPhone());
        et_email.setText(userProfile.getEmail());
        et_address.setText(userProfile.getAddress());
        et_city.setText(userProfile.getCity());
        et_zipcode.setText(userProfile.getZipcode());
        tv_status.setText(userProfile.getStatus());
        if(userProfile.getRole().equals("User")){
            et_member.setText(userProfile.getMember());
        }
    }


    private void initView() {
        bt_edit = findViewById(R.id.bt_edit);
        bt_back = findViewById(R.id.bt_back);

        tv_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);
        et_UTAID = findViewById(R.id.uta_id);
        et_firstname = findViewById(R.id.first_name);
        et_lastname = findViewById(R.id.last_name);
        et_phone = findViewById(R.id.phone);
        et_email = findViewById(R.id.email);
        et_address = findViewById(R.id.address);
        et_city = findViewById(R.id.city);
        sp_state = findViewById(R.id.state);
        tv_role = findViewById(R.id.role);
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
        et_zipcode = findViewById(R.id.zipcode);
        View memberWrapper = findViewById(R.id.member_wrapper);
        et_member = findViewById(R.id.member);
        if(userProfile.getRole().equals("User")){
            memberWrapper.setVisibility(View.VISIBLE);
            et_member.setVisibility(View.VISIBLE);
        }
        tv_status = findViewById(R.id.status);
    }
    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }

    private void initSubmit() {
        String role = ((RadioButton)findViewById(tv_role.getCheckedRadioButtonId())).getText().toString();
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
            userProfile.setRole(role);
            userProfile.setZipcode(getStringFromEditText(et_zipcode));
            if(userProfile.getRole().equals("User")){
                userProfile.setMember(getStringFromEditText(et_member));
            }
            String result = dbHelper.editUser(userProfile);
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            Intent intent = getIntent();
            intent.setClass(EditSelectedUserProfileActivity.this, ViewSelectedUserProfileActivity.class);
            System.out.println(username);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        });

        bt_back.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewSelectedUserProfileActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        });
    }


}
