package mavs.uta.team4carental.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.User;
import mavs.uta.team4carental.utils.DBHelper;

public class RegisterActivity extends AppCompatActivity {
    private Button registerButton;
    private EditText username;
    private EditText password;
    private RadioGroup role;
    private EditText lastname;
    private EditText firstname;
    private EditText phone;
    private EditText email;
    private EditText address;
    private EditText city;
    private Spinner state;
    private EditText zipcode;
    private CheckBox isMember;
    private EditText member;

    private String selectedState;
    private RadioButton selectedRole;

    private User userForm;
    private DBHelper dbHelper;

    static String getStringFromEditText(EditText editText) {
        return editText.getText().toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.initView();
    }

    private void initView() {
        this.dbHelper = new DBHelper(this);
        this.dbHelper.getReadableDatabase();
        this.initForm();
        this.initSubmit();
    }

    private void initSubmit() {
        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(v -> {
            /* parse role */
            this.selectedRole = findViewById(this.role.getCheckedRadioButtonId());
            String role = this.selectedRole.getText().toString();
            /* parse member */
            String memberCode = null;
            if (isMember.isChecked()) {
                memberCode = getStringFromEditText(member);
            }

            userForm = new User(
                    getStringFromEditText(username),
                    getStringFromEditText(password),
                    role,
                    getStringFromEditText(firstname),
                    getStringFromEditText(lastname),
                    getStringFromEditText(phone),
                    getStringFromEditText(email),
                    getStringFromEditText(address),
                    getStringFromEditText(city),
                    selectedState,
                    getStringFromEditText(zipcode),
                    memberCode,
                    "live"
            );

            Log.d("initSubmit", userForm.toString());
            dbHelper.addUser(userForm);
            Toast.makeText(this, "Register success, dear " + getStringFromEditText(firstname), Toast.LENGTH_LONG).show();
            finish();
        });
    }

    private void initForm() {
        role = findViewById(R.id.role);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        zipcode = findViewById(R.id.zipcode);

        /* state */
        {
            String[] states = getResources().getStringArray(R.array.state_list);
            state = findViewById(R.id.state);
            state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                    RegisterActivity.this.selectedState = states[pos];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            int texas = Arrays.asList(states).indexOf("Texas");
            if (texas > 0) {
                state.setSelection(texas);
            }
        }
        /* member */
        {
            isMember = findViewById(R.id.is_member);
            member = findViewById(R.id.member);
            isMember.setOnCheckedChangeListener((compoundButton, checked) -> {
                if (checked) {
                    member.setVisibility(View.VISIBLE);
                } else {
                    member.setVisibility(View.INVISIBLE);
                }
            });
        }
    }
}

