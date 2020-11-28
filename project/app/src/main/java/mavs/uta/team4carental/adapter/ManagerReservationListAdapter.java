package mavs.uta.team4carental.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.Rental;
import mavs.uta.team4carental.ui.user.viewReservation.SpecificReservationActivity;

public class ManagerReservationListAdapter extends BaseAdapter {
    public static final String RESERVATION_INTENT_TOKEN = "RESERVATION_INTENT_TOKEN";
    private Context ctx;
    ArrayList<Rental> items;

    public ManagerReservationListAdapter(Context ctx, ArrayList<Rental> list) {
        this.ctx = ctx;
        this.items = list;
    }

    /*
     * 每一个 item_car 对应一个列表项
     * 在这里统一管理放置你在 item_car 里所有的字段
     */
    public static class ReservationViewHolder {
        TextView textView;
        TextView Start_date;
        TextView End_date;
        TextView totalPrice;
        Button button;
        TextView owner;
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
        ReservationViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ReservationViewHolder();
            LayoutInflater inflater = LayoutInflater.from(ctx);
            view = inflater.inflate(R.layout.item_manager_reservation, viewGroup, false);
            /*
             * 在这里绑定字段
             */
            viewHolder.owner = view.findViewById(R.id.owner);
            viewHolder.textView = view.findViewById(R.id.for_test_reservation);
            viewHolder.Start_date = view.findViewById(R.id.for_dateStart_reservation);
            viewHolder.End_date = view.findViewById(R.id.for_dateEnd_reservation);
            viewHolder.totalPrice = view.findViewById(R.id.total_price);
            viewHolder.button = view.findViewById(R.id.btn_reservation);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ReservationViewHolder) view.getTag();
        }
        /*
         * 在这里对你新加入的字段赋值
         */
        viewHolder.textView.setText(items.get(i).getCarName());
        viewHolder.owner.setText(items.get(i).getUsername());
        viewHolder.Start_date.setText("Start from:\n " + items.get(i).getStart());
        viewHolder.End_date.setText("End to:\n " + items.get(i).getEnd());
        viewHolder.totalPrice.setText("$" + items.get(i).getTotalPrice());
        viewHolder.button.setText("Details");
        viewHolder.button.setOnClickListener(v -> {
            Intent intent = new Intent(ctx, SpecificReservationActivity.class);
            intent.putExtra(RESERVATION_INTENT_TOKEN, items.get(i));
            ctx.startActivity(intent);
        });
        return view;
    }
}
