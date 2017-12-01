package cmput301f17t12.quirks.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

/**
 * Created by root on 11/30/17.
 */

public class FriendListItemAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<User> friends = new ArrayList<>();
    private Context context;

    public FriendListItemAdapter(ArrayList<User>friends, Context context) {
        this.friends = friends;
        this.context = context;
    }

    @Override
    public int getCount() { return friends.size();
    }

    @Override
    public Object getItem(int i) {
        return friends.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.friend_item, null);

            User friend = friends.get(i);
            TextView friendHeader = (TextView) view.findViewById(R.id.Friendtext);
            friendHeader.setText(friend.getUsername());

            ImageButton deleteButton = (ImageButton) view.findViewById(R.id.delete_friend_but);
        }

        return view;
    }
}
