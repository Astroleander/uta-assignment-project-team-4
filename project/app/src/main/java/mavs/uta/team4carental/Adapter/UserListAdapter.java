package mavs.uta.team4carental.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import mavs.uta.team4carental.R;
import mavs.uta.team4carental.pojo.User;

public class UserListAdapter extends BaseAdapter {

    private List<User> list;
    private LayoutInflater inflater;

    public UserListAdapter(Context context, List<User> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        int ret = 0;
        if(list!=null){
            ret = list.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        User user = (User) this.getItem(i);

        ViewHolder viewHolder;
        if (view == null){
            viewHolder = new ViewHolder();

            view = inflater.inflate(R.layout.user_list_item, null);
            viewHolder.username = (TextView) view.findViewById(R.id.item_Username);
            viewHolder.firstname = (TextView) view.findViewById(R.id.item_Firstname);
            viewHolder.lastname = (TextView) view.findViewById(R.id.item_Lastname);
            viewHolder.role = (TextView) view.findViewById(R.id.item_userrole);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.username.setText(user.getUsername());
        viewHolder.firstname.setText(user.getFirstname());
        viewHolder.lastname.setText(user.getLastname());
        viewHolder.role.setText(user.getRole());

        return view;
    }

    public static class ViewHolder{
        public TextView username;
        public TextView firstname;
        public TextView lastname;
        public TextView role;
    }
}
