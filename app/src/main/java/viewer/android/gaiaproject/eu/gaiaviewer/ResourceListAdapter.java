package viewer.android.gaiaproject.eu.gaiaviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import viewer.android.gaiaproject.eu.gaiaviewer.cargo.dto.ResourceDTO;

public class ResourceListAdapter extends ArrayAdapter<ResourceDTO> implements View.OnClickListener {

    private List<ResourceDTO> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        TextView txtType;
        TextView txtValue;
        TextView txtValueUnit;
        ProgressBar imgLoading;
    }

    public ResourceListAdapter(List<ResourceDTO> data, Context context) {
        super(context, R.layout.resource_item_agg, data);
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
//                Snackbar.make(v, "Release date " +dataModel.getName(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ResourceDTO dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.resource_item_agg, parent, false);
            viewHolder.txtType = convertView.findViewById(R.id.type);
            viewHolder.txtName = convertView.findViewById(R.id.name);
            viewHolder.txtValue = convertView.findViewById(R.id.value);
            viewHolder.txtValueUnit = convertView.findViewById(R.id.valueUnit);
            viewHolder.imgLoading = convertView.findViewById(R.id.loading);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        viewHolder.txtType.setText(dataModel.getPhenomenonUuid().toString());
        if (SchoolActivity.phenomena != null && SchoolActivity.phenomena.containsKey(dataModel.getPhenomenonUuid())) {
            viewHolder.txtType.setText(SchoolActivity.phenomena.get(dataModel.getPhenomenonUuid()));
        }
        viewHolder.txtName.setText(dataModel.getUserFriendlyName());
        double rate = 1.0;
        if (dataModel.getUnitUuid().toString().equals("10862083-89df-44f9-8e2b-9fbc58c9f8ce")) {
            rate = 1000000.0;
        }

        if (SchoolActivity.latestValues != null && SchoolActivity.latestValues.containsKey(dataModel.getSystemName())) {
            double val = SchoolActivity.latestValues.get(dataModel.getSystemName()).getLatest() / rate;
            if (rate == 1) {
                viewHolder.txtValue.setText(String.format("%.0f", val));
            } else {
                viewHolder.txtValue.setText(String.format("%.2f", val));
            }
            viewHolder.imgLoading.setVisibility(View.GONE);
            viewHolder.txtValue.setVisibility(View.VISIBLE);
        } else {
            viewHolder.imgLoading.setVisibility(View.VISIBLE);
            viewHolder.txtValue.setVisibility(View.GONE);
        }
        String unit = null;
        if (SchoolActivity.units != null && SchoolActivity.units.containsKey(dataModel.getUnitUuid())) {
            unit = SchoolActivity.units.get(dataModel.getUnitUuid());
            switch (unit) {
                case "mWh":
                    unit = "kWh";
                default:
                    break;
            }

            if (!unit.equals("Raw Value")) {
                viewHolder.txtValueUnit.setText(unit);
            }
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
