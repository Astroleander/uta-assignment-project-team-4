package mavs.uta.team4carental.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.ui.RegisterActivity;
import mavs.uta.team4carental.ui.admin.EditSelectedUserProfileActivity;
import mavs.uta.team4carental.utils.DBHelper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

public class ViewProfileActivity extends AppCompatActivity {
    private EditText form_uta_id;
    private EditText form_lastname;
    private EditText form_firstname;
    private EditText form_phone;
    private EditText form_email;
    private EditText form_address;
    private EditText form_city;
    private EditText form_zipcode;
    private EditText form_member;

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_view_profile);
        user = (User) this.getIntent().getSerializableExtra(ProfileFragment.PROFILE_TOKEN);
        FloatingActionButton edit = findViewById(R.id.bt_edit);
        edit.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditProfileActivity.class);
            intent.putExtra(ProfileFragment.PROFILE_TOKEN, user);
            startActivity(intent);
            finish();
        });
        this.initView();
        this.initForm();
    }

    private void initView() {
        FloatingActionButton bt_edit = findViewById(R.id.bt_edit);
        bt_edit.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditProfileActivity.class);
            intent.putExtra(ProfileFragment.PROFILE_TOKEN, user);
            intent.putExtra("username", user.getUsername());
            startActivity(intent);
            finish();
        });
    }

    private void initForm() {
        {
            EditText form_pwd = findViewById(R.id.password);
            form_pwd.setText(user.getPassword());
        }
        {
            form_uta_id = findViewById(R.id.uta_id);
            form_uta_id.setText(user.getUta_id());
        }
        {
            RadioButton form_role_admin = findViewById(R.id.role_radio_admin);
            RadioButton form_role_manager = findViewById(R.id.role_radio_manager);
            RadioButton form_role_user = findViewById(R.id.role_radio_user);

            if (user.getRole().equals(form_role_admin.getText().toString())) {
                form_role_admin.setChecked(true);
            } else if (user.getRole().equals(form_role_manager.getText().toString())) {
                form_role_manager.setChecked(true);
            } else {
                form_role_user.setChecked(true);
            }
        } // role
        {
            EditText usernameEditText = findViewById(R.id.username);
            usernameEditText.setText(user.getUsername());
        } // username
        {
            form_firstname = findViewById(R.id.first_name);
            form_lastname = findViewById(R.id.last_name);
            form_firstname.setText(user.getFirstname());
            form_lastname.setText(user.getLastname());
        } // name
        {
            form_phone = findViewById(R.id.phone);
            form_phone.setText(user.getPhone());
        } // phone
        {
            form_email = findViewById(R.id.email);
            form_email.setText(user.getEmail());
        } // email
        {
            form_address = findViewById(R.id.address);
            form_city = findViewById(R.id.city);
            form_address.setText(user.getAddress());
            form_city.setText(user.getCity());
        } // address
        {
            form_zipcode = findViewById(R.id.zipcode);
            form_zipcode.setText(user.getZipcode());
        } // zip
        {
            if (user.getRole().equals("User") && user.getMember().length() > 0) {
                View member_wrapper = findViewById(R.id.member_wrapper);
                member_wrapper.setVisibility(View.VISIBLE);
                form_member = findViewById(R.id.member);
                form_member.setText(user.getMember());
            }

        } // member
        {
            String[] states = getResources().getStringArray(R.array.state_list);
            Spinner stateSpinner = findViewById(R.id.state);
            stateSpinner.setEnabled(false);
            int idx = Arrays.asList(states).indexOf(user.getState());
            if (idx > -1) {
                stateSpinner.setSelection(idx);
            } else {
                stateSpinner.setSelected(false);
            }
        } // states
        {
            if (user.getRole().equals("User") && user.getMember().length() > 0) {
                EditText statusEd = findViewById(R.id.status);
                statusEd.setText(user.getStatus());
            } else {
                View stWrapper = findViewById(R.id.status_wrapper);
                stWrapper.setVisibility(View.GONE);
            }
        } // status
    }
}