package mavs.uta.team4carental.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.ui.RegisterActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Arrays;

public class ViewProfileActivity extends AppCompatActivity {
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private EditText addressEditText;
    private EditText cityEditText;
    private EditText zipEditText;
    private EditText member;

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        user = (User) this.getIntent().getSerializableExtra(ProfileFragment.PROFILE_TOKEN);
        this.initForm();
    }

    private void initForm() {
        {
            EditText usernameEditText = findViewById(R.id.username);
            usernameEditText.setText(user.getUsername());
        } // username
        {
            firstNameEditText = findViewById(R.id.first_name);
            lastNameEditText = findViewById(R.id.last_name);
            firstNameEditText.setText(user.getFirstname());
            lastNameEditText.setText(user.getLastname());
        } // name
        {
            phoneEditText = findViewById(R.id.phone);
            phoneEditText.setText(user.getPhone());
        } // phone
        {
            emailEditText = findViewById(R.id.email);
            emailEditText.setText(user.getEmail());
        } // email
        {
            addressEditText = findViewById(R.id.address);
            cityEditText = findViewById(R.id.city);
            addressEditText.setText(user.getAddress());
            cityEditText.setText(user.getCity());
        } // address
        {
            zipEditText = findViewById(R.id.zipcode);
            zipEditText.setText(user.getZipcode());
        } // zip
        {
            member = findViewById(R.id.member);
            member.setText(user.getMember());
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
    }
}