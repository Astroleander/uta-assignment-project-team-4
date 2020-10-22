package mavs.uta.team4carental.ui.user.requestCar;

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
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.utils.DBHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link carResult#newInstance} factory method to
 * create an instance of this fragment.
 */
public class carResult extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private DBHelper dbHelper;

    public carResult() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param starDate Parameter 1.
     * @param endDate Parameter 2.
     * @return A new instance of fragment carResult.
     */
    // TODO: Rename and change types and number of parameters
    public static carResult newInstance(String username,String starDate, String endDate,String capacity) {
        carResult fragment = new carResult();
        Bundle args = new Bundle();
        args.putString("username",username);
        args.putString("starDate", starDate);
        args.putString("endDate", endDate);
        args.putString("capacity", capacity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car_result, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        //将这里的括号以及前面的类型指定改了，就可以把textview改为可选择的表格
//        mTvReservation = (TextView) view.findViewById(R.id.tv_reservations);
        //这后面可以对 mTvReservation中的值进行设置，将从数据库中读出的写到这儿
        dbHelper = new DBHelper(getActivity());
        //给queryReservations提供参数使其能够进行查找操作
        Rental[] reservation_list;
        if(getArguments() == null){
            reservation_list = new Rental[]{};

        }
        else {
            reservation_list = dbHelper.queryReservations(getArguments().getString("userName"), getArguments().getString("start_time"), getArguments().getString("back_time"));
        }

        LinearLayout list = (LinearLayout) view.findViewById(R.id.list_reservations);
        int c = reservation_list.length;
        for(Rental a:reservation_list){
            TextView tv = new TextView(getActivity());
            tv.setText(a.toString());
            list.addView(tv);
        }
    }
}