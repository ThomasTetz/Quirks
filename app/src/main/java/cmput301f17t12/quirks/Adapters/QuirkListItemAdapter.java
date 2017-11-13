package cmput301f17t12.quirks.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import cmput301f17t12.quirks.Models.Quirk;
import cmput301f17t12.quirks.Models.QuirkList;
import cmput301f17t12.quirks.R;

/**
 * Created by jlane on 2017-11-11.
 */

public class QuirkListItemAdapter extends BaseAdapter implements ListAdapter {
    private QuirkList quirkList = new QuirkList();
    private Context context;

    public QuirkListItemAdapter(QuirkList quirkList, Context context) {
        this.quirkList = quirkList;
        this.context = context;
    }

    @Override
    public int getCount(){ return quirkList.size(); }

    @Override
    public Object getItem(int i){ return quirkList.getQuirk(i); }

    @Override //Not sure if this needs to be changed
    public long getItemId(int i){ return 0; }

    @Override //TODO:
    public View getView(final int pos, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.quirk_item, null);
        }

        Quirk quirkItem = quirkList.getQuirk(pos);

        // Set item title to quirk title
        TextView quirkHeader = (TextView) view.findViewById(R.id.headerText);
        quirkHeader.setText(quirkItem.getTitle());

        // Initialize and update progress bar
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.quirkProgressBar);
        progressBar.setMax(quirkItem.getGoalValue());
        progressBar.setProgress(quirkItem.getCurrValue());

        // Handle Button
        Button quirkButton = (Button) view.findViewById(R.id.quirk_button);
        quirkButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Get list of all events assosciated with this quirk
            }
        });

        return view;
    }
}
