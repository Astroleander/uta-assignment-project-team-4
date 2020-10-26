package mavs.uta.team4carental.ui.user.requestCar;

import androidx.appcompat.app.AppCompatActivity;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.adapter.CarListAdapter;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.utils.DBHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SpecificCarActivity extends AppCompatActivity {
    private Button reserve;

    private DBHelper dbHelper;

    private String totalprice;
    private Rental rental;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_car);
        Intent i = getIntent();
        Car car = (Car) i.getSerializableExtra(CarListAdapter.CAR_INTENT_TOKEN);
        String user = getIntent().getStringExtra("userName");
        String start = getIntent().getStringExtra("start");
        String back = getIntent().getStringExtra("back");
        //计算价格



        TextView test = findViewById(R.id.for_test);
        if (car != null) {
            test.setText(car.toString());
        } else {
            test.setText("error token");
        }

        reserve = findViewById(R.id.reserve);
        reserve.setOnClickListener(v -> {
//            dbHelper.addReservation(rental);

            Toast.makeText(this, "Reserve success, estimated price: $100" ,Toast.LENGTH_LONG).show();
            finish();

        });
    }
}