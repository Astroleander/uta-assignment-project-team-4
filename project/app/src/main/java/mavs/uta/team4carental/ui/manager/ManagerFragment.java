package mavs.uta.team4carental.ui.manager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import mavs.uta.team4carental.R;
import mavs.uta.team4carental.adapter.CarListAdapter;
import mavs.uta.team4carental.adapter.ManagerCarListAdapter;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.ui.user.requestCar.RequestCarActivity;

public class ManagerFragment extends Fragment {

    private ManagerViewModel viewModel;
    private View view;

    private ListView carListView;
    private ManagerCarListAdapter adapter;
    private ArrayList<Car> carList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel =
                ViewModelProviders.of(this).get(ManagerViewModel.class);
        view = inflater.inflate(R.layout.fragment_manager, container, false);
        this.initView();
        return view;
    }

    private void initCarList() {
        carListView = view.findViewById(R.id.car_list);
        viewModel.getCarList(this.getContext()).observe(this, carList -> {
            this.carList = carList;
            this.createListView();
        });
    }

    private void createListView() {
        adapter = new ManagerCarListAdapter(view.getContext(), carList);
        carListView.setAdapter(adapter);
    }

    private void initSearchBar() {
        SearchView searchView = view.findViewById(R.id.search_view_cars);
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
        ArrayList<Car> filteredList = new ArrayList<>();
        if (s.length() < 1) {
            adapter = new ManagerCarListAdapter(view.getContext(), carList);
            carListView.setAdapter(adapter);
            return;
        }
        // else
        for (Car car: carList) {
            if (car.getCarname().toLowerCase().contains(s.toLowerCase())) {
                filteredList.add(car);
            }
        }
        adapter = new ManagerCarListAdapter(view.getContext(), filteredList);
        carListView.setAdapter(adapter);
    }

    private void initView() {
        this.initCarList();
        this.initSearchBar();
        this.initConditionFilters();
    }

    private void initConditionFilters() {
        CheckBox cb = view.findViewById(R.id.checkbox_available_filter);
        cb.setOnCheckedChangeListener((v, checked) -> {
            if (checked) {
                // TODO
            } else {
                // TODO
            }
        });
    }

}