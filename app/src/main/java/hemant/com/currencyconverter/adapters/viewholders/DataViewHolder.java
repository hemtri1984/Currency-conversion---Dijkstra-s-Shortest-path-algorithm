package hemant.com.currencyconverter.adapters.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import hemant.com.currencyconverter.R;

/**
 * View Holder for screen data
 * Created by Hemant on 4/10/17.
 */

public class DataViewHolder extends RecyclerView.ViewHolder {

    //Views
    private TextView titleTextView;
    private TextView subtitleTextView;

    public DataViewHolder(View itemView) {
        super(itemView);

        titleTextView = (TextView)itemView.findViewById(R.id.tv_title);
        subtitleTextView = (TextView)itemView.findViewById(R.id.tv_subtitle);
    }

    protected void setTitle(String title) {
        titleTextView.setText(title);
    }

    protected void setSubtitle(String subtitle) {
        subtitleTextView.setText(subtitle);
    }

}
