package mavs.uta.team4carental.ui.manager;

import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.utils.DBHelper;

public class ManagerViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Car>> carList;

    public ManagerViewModel() {
        carList = new MutableLiveData<>();
    }

    public LiveData<ArrayList<Car>> getCarList(Context context) {
        DBHelper dbHelper = new DBHelper(context.getApplicationContext());
        Car[] car_list = dbHelper.queryCar();
        ArrayList<Car> result = new ArrayList<>(Arrays.asList(car_list));
        carList.setValue(result);
        return carList;
    }
}