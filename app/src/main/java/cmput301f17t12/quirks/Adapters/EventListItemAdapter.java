package cmput301f17t12.quirks.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cmput301f17t12.quirks.Interfaces.Newsable;
import cmput301f17t12.quirks.Models.Event;
import cmput301f17t12.quirks.Models.EventList;
import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.R;

/**
 * Created by charleshoang on 2017-11-12.
 */

public class EventListItemAdapter extends BaseAdapter implements ListAdapter{
    private EventList eventList = new EventList();
    private Context context;

    public EventListItemAdapter(QuirkList quirkList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }

    @Override
    public int getCount(){ return eventList.getList().size(); }

    @Override
    public Object getItem(int i){ return eventList.getEvent(i); }

    @Override //Not sure if this needs to be changed
    public long getItemId(int i){ return 0; }

    @Override //TODO:
    public View getView(final int pos, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.quirk_item, null);
        }

        Event eventItem = eventList.getEvent(pos);

        // Set item title to event comment
        TextView quirkHeader = (TextView) view.findViewById(R.id.headerText);
        quirkHeader.setText(eventItem.getComment());


        // Handle Button
        Button quirkButton = (Button) view.findViewById(R.id.quirk_button);
        quirkButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Get information associated with the event
            }
        });

        return view;
    }


}
