package cmput301f17t12.quirks.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import cmput301f17t12.quirks.Activities.EditEventActivity;
import cmput301f17t12.quirks.Activities.EventListActivity;
import cmput301f17t12.quirks.Activities.FeedActivity;
import cmput301f17t12.quirks.Activities.QuirksActivity;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.R;

public class FollowFeedItemAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> userlist;
    private ArrayList<Quirk> quirkList;
    private Context context;

    /**
     * Constructor for the FollowFeedItemAdapter. The Adapter is used for the follow feed ListView
     * @param quirkList QuirkList to shown in the ListView
     * @param context Current state of the application
     */
    public FollowFeedItemAdapter(ArrayList<String> userlist, ArrayList<Quirk> quirkList, Context context) {
        this.userlist = userlist;
        this.quirkList = quirkList;
        this.context = context;
    }

    /**
     * Return the size of the QuirkList
     * @return
     */
    @Override
    public int getCount(){ return quirkList.size(); }

    /**
     * Return a quirk at a specified index
     * @param i Index
     * @return Quirk object
     */
    @Override
    public Object getItem(int i){ return quirkList.get(i); }

    @Override //Not sure if this needs to be changed
    public long getItemId(int i){ return 0; }

    /**
     * Displays the data at specified indexes within the ListView
     * @param pos Position of the item selected
     * @param convertView The old view that can be reused
     * @param viewGroup The parent ListView
     * @return A View representing the data at the corresponding position
     */
    @Override
    public View getView(final int pos, View convertView, final ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.following_feeditem, null);
        }
        final Quirk quirkItem = quirkList.get(pos);

        // Set item title to quirk title
        TextView habititle = (TextView) view.findViewById(R.id.followQuirkTitle);
        habititle.setText(String.format("- %s", quirkItem.getTitle()));

        TextView username = (TextView) view.findViewById(R.id.username);
        username.setText(userlist.get(pos));

        // Initialize and update progress bar
        ProgressBar followBar = (ProgressBar) view.findViewById(R.id.followBar);
        followBar.setMax(quirkItem.getGoalValue());
        followBar.setProgress(quirkItem.getCurrValue());

        TextView fraction = (TextView) view.findViewById(R.id.fraction);
        fraction.setText(String.format("%s/%s", quirkItem.getCurrValue(), quirkItem.getGoalValue()));

        // Handle Image Button
        ImageButton eventbtn = (ImageButton) view.findViewById(R.id.eventbtn);
        eventbtn.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (context instanceof FeedActivity) {
                    //Integer serializableInt = ((FeedActivity)context).getFilteredIndex(pos);
                    // open popup most recent event
                }
            }
        });

        return view;
    }
}
