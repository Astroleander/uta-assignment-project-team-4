package mavs.uta.team4carental.ui.user.viewReservation;

import androidx.appcompat.app.AppCompatActivity;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.adapter.CarListAdapter;
import mavs.uta.team4carental.adapter.ReservationListAdapter;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.pojo.Rental;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

public class SpecificReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_reservation);
        Intent i = getIntent();
        Rental reservation = (Rental) i.getSerializableExtra(ReservationListAdapter.RESERVATION_INTENT_TOKEN);
        if (reservation != null) {
            ((TextView) findViewById(R.id.Numer_of_Reservation)).setText(reservation.getID());
            ((TextView) findViewById(R.id.UserName)).setText(reservation.getUsername());
            ((TextView) findViewById(R.id.Start)).setText(reservation.getStart());
            ((TextView) findViewById(R.id.Back)).setText(reservation.getEnd());
//            ((TextView) findViewById(R.id.Duration)).setText(reservation.getDuration());
            ((TextView) findViewById(R.id.Car_name)).setText(reservation.getCarName());
//            ((TextView) findViewById(R.id.Capacity)).setText(reservation.getCapability());
            ((TextView) findViewById(R.id.total_price)).setText("$"+reservation.getTotalPrice());
            if (reservation.getGPS().equals("1")){
                ((CheckBox) findViewById(R.id.checkBox_gps)).setChecked(true);
            }
            if (reservation.getOnstar().equals("1")){
                ((CheckBox) findViewById(R.id.checkBox_onstar)).setChecked(true);
            }
            if (reservation.getSiriusxm().equals("1")){
                ((CheckBox) findViewById(R.id.checkBox_siriusXM)).setChecked(true);
            }
        }
    }
}