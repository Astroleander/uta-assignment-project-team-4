package mavs.uta.team4carental.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.ui.profile.ProfileViewModel;
import mavs.uta.team4carental.ui.profile.ViewProfileActivity;
import mavs.uta.team4carental.utils.DBHelper;

public class ViewSelectedUserProfileActivity extends AppCompatActivity {

    private ProfileViewModel profileViewModel;

    private EditText form_uta_id;
    private EditText form_lastname;
    private EditText form_firstname;
    private EditText form_phone;
    private EditText form_email;
    private EditText form_address;
    private EditText form_city;
    private EditText form_zipcode;
    private CheckBox form_isMember;
    private EditText form_member;

    private Button bt_revoke;
    private Button bt_change;

    private DBHelper dbHelper;
    private String username;
    private User userProfile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_selected_user_profile);
        this.bindViewModel();
        this.initProfile();
    }

    private void bindViewModel() {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
    }

    private void initProfile() {
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        profileViewModel.getUserInfo(this, username).observe(this, u -> {
            userProfile = u;
            initForm();
            initView();
        });
    }

    private void initView() {
        FloatingActionButton bt_edit = findViewById(R.id.bt_edit);
        bt_edit.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditSelectedUserProfileActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        });
    }

    private void initForm() {
        {
            EditText form_pwd = findViewById(R.id.password);
            form_pwd.setText(userProfile.getPassword());
        }
        {
            form_uta_id = findViewById(R.id.uta_id);
            form_uta_id.setText(userProfile.getUta_id());
        }
        {
            EditText usernameEditText = findViewById(R.id.username);
            usernameEditText.setText(userProfile.getUsername());
        } // username
        {
            form_firstname = findViewById(R.id.first_name);
            form_lastname = findViewById(R.id.last_name);
            form_firstname.setText(userProfile.getFirstname());
            form_lastname.setText(userProfile.getLastname());
        } // name
        {
            form_phone = findViewById(R.id.phone);
            form_phone.setText(userProfile.getPhone());
        } // phone
        {
            form_email = findViewById(R.id.email);
            form_email.setText(userProfile.getEmail());
        } // email
        {
            form_address = findViewById(R.id.address);
            form_city = findViewById(R.id.city);
            form_address.setText(userProfile.getAddress());
            form_city.setText(userProfile.getCity());
        } // address
        {
            form_zipcode = findViewById(R.id.zipcode);
            form_zipcode.setText(userProfile.getZipcode());
        } // zip
        {
            if (userProfile.getRole().equals("User") && userProfile.getMember().length() > 0) {
                View member_wrapper = findViewById(R.id.member_wrapper);
                member_wrapper.setVisibility(View.VISIBLE);
                form_member = findViewById(R.id.member);
                form_member.setText(userProfile.getMember());
            }

        } // member
        {
            String[] states = getResources().getStringArray(R.array.state_list);
            Spinner stateSpinner = findViewById(R.id.state);
            stateSpinner.setEnabled(false);
            int idx = Arrays.asList(states).indexOf(userProfile.getState());
            if (idx > -1) {
                stateSpinner.setSelection(idx);
            } else {
                stateSpinner.setSelected(false);
            }
        } // states
        {
            EditText et_role = findViewById(R.id.role);
            et_role.setText(userProfile.getRole());
            bt_change = findViewById(R.id.change);
            if(userProfile.getRole().equals("User"))
                bt_change.setVisibility(View.VISIBLE);
            else
                bt_change.setVisibility(View.GONE);
            bt_change.setOnClickListener(view -> {
                dbHelper = new DBHelper(this);
                userProfile.setRole("Manager");
                dbHelper.editUser(userProfile);
                refresh();
            });

        } // role
        {
            LinearLayout ll_statues = findViewById(R.id.layout_statues);
            if(userProfile.getRole().equals("User"))
                ll_statues.setVisibility(View.VISIBLE);
            else
                ll_statues.setVisibility(View.GONE);
            EditText statusRole = findViewById(R.id.status);
            statusRole.setText(userProfile.getStatus());
            bt_revoke = findViewById(R.id.revoke);
            bt_revoke.setOnClickListener(v -> {
                dbHelper = new DBHelper(this);
                userProfile.setStatus("dead");
                dbHelper.editUser(userProfile);
                refresh();
            });
        } // status
    }

    private void refresh() {
        finish();
        Intent intent = getIntent();
        intent.setClass(ViewSelectedUserProfileActivity.this, ViewSelectedUserProfileActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }


}
