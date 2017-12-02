package cmput301f17t12.quirks.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cmput301f17t12.quirks.Activities.RequestActivity;
import cmput301f17t12.quirks.Models.User;
import cmput301f17t12.quirks.R;

/**
 * Created by root on 12/1/17.
 */

public class RequestListItemAdapter extends BaseAdapter implements ListAdapter {

    ArrayList<User> requests = new ArrayList<User>();
    private Context context;

    public RequestListItemAdapter(ArrayList<User> requests, Context context){
        this.requests = requests;
        this.context = context;
    }

    @Override
    public int getCount() {
        return requests.size();
    }

    @Override
    public Object getItem(int i) {
        return requests.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.request_item, null);
        }
        final User user = requests.get(i);
        TextView friendHeader = (TextView) view.findViewById(R.id.friendRequestText);
        friendHeader.setText(user.getUsername());

        Button Acceptfriendbut = (Button) view.findViewById(R.id.buttonAccept);
        Button Declinefriendbut = (Button) view.findViewById(R.id.buttonDeclineFriend);

        Acceptfriendbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context instanceof RequestActivity){
                    ((RequestActivity)context).AcceptFriend(i);

                }
            }
        });

        return view;
    }
}
