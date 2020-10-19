package mavs.uta.team4carental.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.utils.EnumTable;

public class ViewReservations extends AppCompatActivity {
    private Reservations reservations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mavs.uta.team4carental.R.layout.activity_view_reservations);

        //实例化Reservations fragement
        reservations = new Reservations();
        //把Reservations fragement 添加到fragment_flcontaomer中
        getSupportFragmentManager().beginTransaction().add(R.id.reservation_flcontainer, reservations).commitAllowingStateLoss();

    }
}