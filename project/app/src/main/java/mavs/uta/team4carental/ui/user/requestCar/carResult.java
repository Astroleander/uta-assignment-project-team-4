package mavs.uta.team4carental.ui.user.requestCar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mavs.uta.team4carental.R;

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
    public static carResult newInstance(String starDate, String endDate,String capacity) {
        carResult fragment = new carResult();
        Bundle args = new Bundle();
        args.putString("starDate", starDate);
        args.putString("endDate", endDate);
        args.putString("capacity", capacity);
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_car_result, container, false);
    }
}