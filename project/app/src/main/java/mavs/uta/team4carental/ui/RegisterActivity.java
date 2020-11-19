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
    private EditText uta_id;
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

        this.selectedRole = findViewById(this.role.getCheckedRadioButtonId());
        this.autoToggleIsMember();

        this.role.setOnCheckedChangeListener((group, pos) -> {
            this.selectedRole = findViewById(this.role.getCheckedRadioButtonId());
            this.autoToggleIsMember();
        });

        this.initSubmit();
    }

    private void autoToggleIsMember() {
        String role = this.selectedRole.getText().toString();
        if (role.equals("User")) {
            this.isMember.setVisibility(View.VISIBLE);
        } else {
            this.isMember.setChecked(false);
            this.isMember.setVisibility(View.INVISIBLE);
        }
    }

    private void initSubmit() {
        registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(v -> {

            String unique = getStringFromEditText(username);
            String[] usernameToDB = new String[]{unique};
            User[] result = dbHelper.queryUser("USERNAME=?", usernameToDB);
            if(result.length != 0){
                Toast.makeText(this, "The username has been used. Please change your username.", Toast.LENGTH_LONG).show();
            }else{
                /* parse role */
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
                        getStringFromEditText(uta_id),
                        getStringFromEditText(lastname),
                        getStringFromEditText(firstname),
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
            }

        });
    }

    private void initForm() {
        role = findViewById(R.id.role);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        uta_id = findViewById(R.id.uta_id);
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

