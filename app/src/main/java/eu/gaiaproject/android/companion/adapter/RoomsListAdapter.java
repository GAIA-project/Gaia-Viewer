package eu.gaiaproject.android.companion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import eu.gaiaproject.android.companion.R;
import eu.gaiaproject.android.companion.model.SchoolModel;

public class RoomsListAdapter extends ArrayAdapter<SchoolModel> implements View.OnClickListener {

    private List<SchoolModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtTitle;
    }

    public RoomsListAdapter(List<SchoolModel> data, Context context) {
        super(context, R.layout.room_item, data);
        this.dataSet = data;
        this.mContext = context;

    }

    @Override
    public void onClick(View v) {

        int position = (Integer) v.getTag();
        Object object = getItem(position);
        SchoolModel dataModel = (SchoolModel) object;

        switch (v.getId()) {
            case R.id.name:
                break;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SchoolModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.room_item, parent, false);
            viewHolder.txtName = convertView.findViewById(R.id.name);
            viewHolder.txtTitle = convertView.findViewById(R.id.title);
            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        int lastPosition = position;
        viewHolder.txtName.setText(dataModel.getName());
        viewHolder.txtTitle.setText(dataModel.getUuid().toString());
        viewHolder.txtTitle.setText("");

        // Return the completed view to render on screen
        return convertView;
    }
}
