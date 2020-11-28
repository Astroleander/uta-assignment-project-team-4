package mavs.uta.team4carental.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.ArrayList;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.Car;
import mavs.uta.team4carental.ui.manager.DisplayCarActivity;
import mavs.uta.team4carental.ui.user.requestCar.SpecificCarActivity;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarListAdapter extends BaseAdapter {
    public static final String CAR_INTENT_TOKEN = "CAR_INTENT_TOKEN";
    private Context ctx;
    ArrayList<Car> items;
    private String start;
    private String back;
    private String user;
    private String occupants;



    public CarListAdapter(Context ctx, ArrayList<Car> list,String userName,String start,String back,String occupants) {
        this.ctx = ctx;
        this.items = list;
        this.user=userName;
        this.start = start;
        this.back = back;
        this.occupants = occupants;
    }

    /*
     * 每一个 item_car 对应一个列表项
     * 在这里统一管理放置你在 item_car 里所有的字段
     */
    public static class CarViewHolder {
//        TextView textView;
//        Button button;

        TextView rate;
        TextView capacity;
        TextView carName;
        Button button;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CarViewHolder viewHolder;
//        if (view == null) {
//            viewHolder = new CarViewHolder();
//            LayoutInflater inflater = LayoutInflater.from(ctx);
//            view = inflater.inflate(R.layout.item_car, viewGroup, false);
//            /*
//             * 在这里绑定字段
//             */
//            viewHolder.textView = view.findViewById(R.id.for_test);
//            viewHolder.button = view.findViewById(R.id.btn);
//            view.setTag(viewHolder);
//        } else {
//            viewHolder = (CarViewHolder) view.getTag();
//        }
        if (view == null) {
            viewHolder = new CarListAdapter.CarViewHolder();
            LayoutInflater inflater = LayoutInflater.from(ctx);
            view = inflater.inflate(R.layout.item_car, viewGroup, false);
            /*
             * 在这里绑定字段
             */
            viewHolder.carName = view.findViewById(R.id.car_name);
            viewHolder.capacity = view.findViewById(R.id.capacity);
            viewHolder.rate = view.findViewById(R.id.rate);
            viewHolder.button = view.findViewById(R.id.btn);
            view.setTag(viewHolder);
        } else {
            viewHolder = (CarListAdapter.CarViewHolder) view.getTag();
        }
        /*
         * 在这里对你新加入的字段赋值
         */

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        Date startdate = null;
        Date backdate = null;
        try {
            startdate = format.parse(start);
            backdate = format.parse(back);
        }catch (ParseException e){
            e.printStackTrace();
        }

        float durations = (backdate.getTime() - startdate.getTime())/(1000*24*60*60);
        if(((backdate.getTime() - startdate.getTime())%(1000*24*60*60))==0){

        }else{
            durations+=1;
        }
        float price=Float.valueOf(items.get(i).getWeekday());
        float totalprice = durations*price;
        String str_price = String.valueOf(totalprice);

        viewHolder.carName.setText(items.get(i).getCarname());
        viewHolder.capacity.setText("Capicity: " + items.get(i).getCapicity());
        viewHolder.rate.setText(
                "Estimated Total Price:"+str_price +"$"
        );
        viewHolder.button.setText(R.string.car_detail_btn);
        viewHolder.button.setOnClickListener(v -> {
            Intent intent = new Intent(ctx, SpecificCarActivity.class);
            intent.putExtra(CAR_INTENT_TOKEN, items.get(i));
            intent.putExtra("user", user);
            intent.putExtra("start", start);
            intent.putExtra("back", back);
            intent.putExtra("occupants",occupants);
            ctx.startActivity(intent);
        });
        return view;
    }
}
