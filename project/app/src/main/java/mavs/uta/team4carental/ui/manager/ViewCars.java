package mavs.uta.team4carental.ui.manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.adapter.ManagerCarListAdapter;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.utils.DBHelper;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ViewCars extends AppCompatActivity {
    private ListView carListView;
    private ManagerCarListAdapter adapter;
    private ArrayList<Car> carList;
    private ArrayList<Car> availableCarList;
    private ArrayList<Car> filteredCarList;

    private ManagerViewModel viewModel;

    private EditText startDate;
    private EditText startTime;
    private DatePickerDialog dStartDate;
    private TimePickerDialog dStartTime;

    private EditText endDate;
    private EditText endTime;
    private DatePickerDialog dEndDate;
    private TimePickerDialog dEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cars);
        viewModel = ViewModelProviders.of(this).get(ManagerViewModel.class);
        this.initView();
        this.initPickerEditor();
        this.initDialog();
    }

    private void initCarList() {
        carListView = findViewById(R.id.car_list);
        viewModel.getCarList(this).observe(this, carList -> {
            this.carList = carList;
            this.createListView();
        });
    }

    private void createListView() {
        filteredCarList = new ArrayList<>(carList);
        availableCarList = new ArrayList<>(carList);
        setCarList();
    }

    private void initSearchBar() {
        SearchView searchView = findViewById(R.id.search_view_cars);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                showFilterList(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                showFilterList(s);
                return false;
            }
        });
    }

    private void showFilterList(String s) {
        filteredCarList = new ArrayList<>();
        if (s.length() < 1) {
            adapter = new ManagerCarListAdapter(this, carList);
            carListView.setAdapter(adapter);
            return;
        }
        // else
        for (Car car: carList) {
            if (car.getCarname().toLowerCase().contains(s.toLowerCase())) {
                filteredCarList.add(car);
            }
        }
        availableCarList = new ArrayList<>(filteredCarList);
        this.setCarList();
    }

    private void setCarList() {
        adapter = new ManagerCarListAdapter(this, availableCarList);
        carListView.setAdapter(adapter);
    }

    private void initView() {
        this.initCarList();
        this.initSearchBar();
        this.initConditionFilters();
    }

    private void initConditionFilters() {
        CheckBox cb = findViewById(R.id.checkbox_available_filter);
        cb.setOnCheckedChangeListener((v, checked) -> {
            availableCarList = new ArrayList<>();
            if (checked) {
                if (
                        !startDate.getText().toString().equals("") &&
                        !startTime.getText().toString().equals("") &&
                        !endDate.getText().toString().equals("") &&
                        !endTime.getText().toString().equals("")
                ) {
                    ArrayList<Rental> rentals = queryReservations();
                    for (int i = 0; i < filteredCarList.size(); i++) {
                        Car car = filteredCarList.get(i);
                        boolean isCarActive = false;
                        for (int j = 0; j < rentals.size(); j++) {
                            Rental rental = rentals.get(j);
                            if (car.getCarname().equals(rental.getCarName())) {
                                isCarActive = true;
                                break;
                            }
                        }
                        if (!isCarActive) {
                            availableCarList.add(car);
                        }
                    }
                    setCarList();
                } else {
                    Toast.makeText(this, "Please fill up all selectors", Toast.LENGTH_LONG).show();
                }
            } else {
                availableCarList = new ArrayList<>(filteredCarList);
                setCarList();
            }
        });
    }

    private DatePickerDialog.OnDateSetListener createOnDateSetListener(EditText editText) {
        return (arg0, year, month, day) -> {
            String tmp_month = String.valueOf(month + 1);
            String tmp_day = String.valueOf(day);
            if (month < 9){
                tmp_month = ("0" + String.valueOf(month + 1));
            }
            if (day < 10){
                tmp_day = ("0" + String.valueOf(day));
            }
            editText.setText(year+"-"+tmp_month+"-"+tmp_day+" ");
        };
    }
    private TimePickerDialog.OnTimeSetListener createOnTimeSetListener(EditText editText) {
        return (tp, hour, minute) -> {
            String res = "";
            if (hour < 10){
                res = ("0"+hour).toString();
                editText.setText(res + ":00");
                return;
            }
            editText.setText(hour+":00");
        };
    }

    private void initDialog() {
        dStartDate = new DatePickerDialog(
                this, 0,  createOnDateSetListener(startDate), 2020, 9, 0
        );
        dEndDate = new DatePickerDialog(
                this, 0,  createOnDateSetListener(endDate), 2020, 9, 30
        );
        dStartTime = new TimePickerDialog(
                this, createOnTimeSetListener(startTime), 0, 0, true
        );
        dEndTime = new TimePickerDialog(
                this, createOnTimeSetListener(endTime), 0, 0, true
        );
    }
    private void initPickerEditor() {
        startDate = findViewById(R.id.start_date);
        startDate.setInputType(InputType.TYPE_NULL);
        startTime = findViewById(R.id.start_time);
        startTime.setInputType(InputType.TYPE_NULL);
        endDate = findViewById(R.id.end_date);
        endDate.setInputType(InputType.TYPE_NULL);
        endTime = findViewById(R.id.end_time);
        endTime.setInputType(InputType.TYPE_NULL);
        startDate.setOnClickListener(v -> {
            dStartDate.show();
        });
        startTime.setOnClickListener(v -> {
            dStartTime.show();
        });
        endDate.setOnClickListener(v -> {
            dEndDate.show();
        });
        endTime.setOnClickListener(v -> {
            dEndTime.show();
        });
    }
    private ArrayList<Rental> queryReservations() {
        DBHelper dbHelper = new DBHelper(this);
        Rental[] reservation_list;
        reservation_list = dbHelper.queryActiveReservations();
        return new ArrayList<>(Arrays.asList(reservation_list));
    }
}