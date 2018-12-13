package viewer.android.gaiaproject.eu.gaiaviewer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import viewer.android.gaiaproject.eu.gaiaviewer.R;
import viewer.android.gaiaproject.eu.gaiaviewer.model.SchoolModel;

public class SchoolListAdapter extends ArrayAdapter<SchoolModel> implements View.OnClickListener{

    private List<SchoolModel> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
    }

    public SchoolListAdapter(List<SchoolModel> data, Context context) {
        super(context, R.layout.school_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        SchoolModel dataModel=(SchoolModel)object;

        switch (v.getId())
        {
            case R.id.name:
//                Snackbar.make(v, "Release date " +dataModel.getName(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        SchoolModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            final LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.school_item, parent, false);
            viewHolder.txtName = convertView.findViewById(R.id.name);
            result=convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        lastPosition = position;

        viewHolder.txtName.setText(dataModel.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
