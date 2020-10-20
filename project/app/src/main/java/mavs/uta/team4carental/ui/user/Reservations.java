package mavs.uta.team4carental.ui.user;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.utils.DBHelper;
import mavs.uta.team4carental.utils.EnumTable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Reservations#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Reservations extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DBHelper dbHelper;
    //这里也需要改类型为可选择的表
    private TextView mTvReservation;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public Reservations() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Reservations.
     */
    // TODO: Rename and change types and number of parameters
    public static Reservations newInstance(String param1, String param2) {
        Reservations fragment = new Reservations();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public static Reservations newInstance(String userName, String start_time, String back_time){
        Reservations reservation = new Reservations();
        Bundle bundle = new Bundle();
        bundle.putString("userName", userName);
        bundle.putString("start_time", start_time);
        bundle.putString("back_time", back_time);
        return reservation;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        //将这里的括号以及前面的类型指定改了，就可以把textview改为可选择的表格
//        mTvReservation = (TextView) view.findViewById(R.id.tv_reservations);
        //这后面可以对 mTvReservation中的值进行设置，将从数据库中读出的写到这儿
        dbHelper = new DBHelper(getActivity());
        //给queryReservations提供参数使其能够进行查找操作
        Rental[] reservation_list = dbHelper.queryReservations("wang", "2020/10/20","2020/10/30");
        if(getArguments() == null){
            reservation_list = dbHelper.queryReservations("wang", "2020/10/20","2020/10/30");
        }
        else {
            reservation_list = dbHelper.queryReservations(getArguments().getString("userName"), getArguments().getString("start_time"), getArguments().getString("back_time"));
        }

        LinearLayout list = (LinearLayout) view.findViewById(R.id.list_reservations);
        for(Rental a:reservation_list){
            TextView tv = new TextView(getActivity());
            tv.setText(a.toString());
            list.addView(tv);
        }
    }
}