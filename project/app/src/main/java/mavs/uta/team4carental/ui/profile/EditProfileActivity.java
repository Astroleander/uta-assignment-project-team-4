package mavs.uta.team4carental.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.ui.admin.EditSelectedUserProfileActivity;
import mavs.uta.team4carental.ui.admin.ViewSelectedUserProfileActivity;
import mavs.uta.team4carental.utils.DBHelper;

public class EditProfileActivity extends AppCompatActivity {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private EditText addressEditText;
    private EditText cityEditText;
    private EditText zipEditText;
    private EditText memberEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText utaIdEditText;
    private EditText statusEditText;

    private Spinner sp_state;
    private FloatingActionButton edit;
    private FloatingActionButton back;
    private String selectedState;
    private DBHelper dbHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rm_edit_profile);
        user = (User) this.getIntent().getSerializableExtra(ProfileFragment.PROFILE_TOKEN);
        FloatingActionButton edit = findViewById(R.id.bt_edit);
        this.initView();
        this.initSubmit();
        this.showProfile();
    }

    private void showProfile() {
        usernameEditText.setText(user.getUsername());
        lastNameEditText.setText(user.getLastname());
        firstNameEditText.setText(user.getFirstname());
        passwordEditText.setText(user.getPassword());
        utaIdEditText.setText(user.getUta_id());
        phoneEditText.setText(user.getPhone());
        emailEditText.setText(user.getEmail());
        addressEditText.setText(user.getAddress());
        cityEditText.setText(user.getCity());
        zipEditText.setText(user.getZipcode());
        
        if(user.getRole().equals("User")){
            View memWrapper = findViewById(R.id.member_wrapper);
            memWrapper.setVisibility(View.VISIBLE);
            memberEditText.setText(user.getMember());
            statusEditText.setText(user.getStatus());
        } else {
            View stWrapper = findViewById(R.id.status_wrapper);
            stWrapper.setVisibility(View.GONE);
        }
    }
    private void initView() {
        edit = findViewById(R.id.bt_edit);
        back = findViewById(R.id.bt_back);

        usernameEditText = findViewById(R.id.username);
        utaIdEditText = findViewById(R.id.uta_id);
        firstNameEditText = findViewById(R.id.first_name);
        lastNameEditText = findViewById(R.id.last_name);
        passwordEditText = findViewById(R.id.password);
        statusEditText = findViewById(R.id.status);
        phoneEditText = findViewById(R.id.phone);
        emailEditText= findViewById(R.id.email);
        addressEditText= findViewById(R.id.address);
        cityEditText= findViewById(R.id.city);
        sp_state = findViewById(R.id.state);
        /* state */
        {
            String[] states = getResources().getStringArray(R.array.state_list);
            sp_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                    EditProfileActivity.this.selectedState = states[pos];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            int texas = Arrays.asList(states).indexOf(user.getState());
            if (texas > 0) {
                sp_state.setSelection(texas);
            }
        }
        zipEditText = findViewById(R.id.zipcode);
        View memberWrapper = findViewById(R.id.member_wrapper);
        memberEditText = findViewById(R.id.member);
        if(user.getRole().equals("User")){
            memberWrapper.setVisibility(View.VISIBLE);
            memberWrapper.setVisibility(View.VISIBLE);
        }
    }
    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }

    private void initSubmit() {
        dbHelper = new DBHelper(this);
        edit.setOnClickListener(view -> {
            user.setFirstname(getStringFromEditText(firstNameEditText));
            user.setLastname(getStringFromEditText(lastNameEditText));
            user.setPassword(getStringFromEditText(passwordEditText));
            user.setUta_id(getStringFromEditText(utaIdEditText));
            user.setPhone(getStringFromEditText(phoneEditText));
            user.setEmail(getStringFromEditText(emailEditText));
            user.setAddress(getStringFromEditText(addressEditText));
            user.setCity(getStringFromEditText(cityEditText));
            user.setState(selectedState);
            user.setZipcode(getStringFromEditText(zipEditText));
            if(user.getRole().equals("User")){
                user.setMember(getStringFromEditText(memberEditText));
            }
            String result = dbHelper.editUser(user);
            Toast.makeText(this, result, Toast.LENGTH_LONG).show();
            Intent intent = getIntent();
            intent.setClass(this, ViewProfileActivity.class);
            intent.putExtra(ProfileFragment.PROFILE_TOKEN, user);
            startActivity(intent);
            finish();
        });

        back.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewProfileActivity.class);
            intent.putExtra(ProfileFragment.PROFILE_TOKEN, user);
            startActivity(intent);
            finish();
        });
    }
}