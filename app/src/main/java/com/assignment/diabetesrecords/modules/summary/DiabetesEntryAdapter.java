package com.assignment.diabetesrecords.modules.summary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assignment.diabetesrecords.R;
import com.assignment.diabetesrecords.common.my_validator.MyValidator;
import com.assignment.diabetesrecords.entity.DiabetesEntry;

import java.util.List;

//==Start UserAdapter Class==========================================================
public class DiabetesEntryAdapter extends
        RecyclerView.Adapter<DiabetesEntryAdapter.ViewHolder> {

    /**
     * The Class ViewHolder.
     */
    public  class ViewHolder extends RecyclerView.ViewHolder {



        /** The title. */
        private TextView tvReading, tvDate, tvTime;

        private LinearLayout LLUserSelected;


        /**
         * Instantiates a new view holder.
         *
         * @param itemView the item view
         */
        public ViewHolder(View itemView) {
            super(itemView);


            tvReading = (TextView) itemView.findViewById(R.id.tvReading);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvTime = (TextView) itemView.findViewById(R.id.tvTime);



        }
    }

    /** The Context. */
    private Context mContext;

    /** The Inflater. */
    private LayoutInflater mInflater;

    /** The Adapter data. */
    private List<DiabetesEntry> mAdapterData;
    int selected_position = 0;

    /**
     * Instantiates a new filter adapter.
     *
     * @param context the context
     * @param filteredResult the filtered result
     */
    public DiabetesEntryAdapter(Context context, List<DiabetesEntry> filteredResult) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mAdapterData = filteredResult;

    }

    /* (non-Javadoc)
     * @see android.support.v7.widget.RecyclerView.Adapter#getItemCount()
     */
    @Override
    public int getItemCount() {

        return mAdapterData.size();
        //return mAdapterData.length;
    }

    /* (non-Javadoc)
     * @see android.support.v7.widget.RecyclerView.Adapter#onCreateViewHolder(android.view.ViewGroup, int)
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.row_diabetes_record, parent,
                false);

        ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    /* (non-Javadoc)
     * @see android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder, int)
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiabetesEntry diabetesEntry = mAdapterData.get(position);

        holder.tvReading.setText(String.valueOf ((int) diabetesEntry.getGlucoseReading()) + " mmol/L");
        holder.tvDate.setText(MyValidator.getDateInddmmyyyy(diabetesEntry.getEntryDate().toString()) );
        holder.tvTime.setText(MyValidator.getTimeInAMPMFormat(diabetesEntry.getEntryTime().toString()));


        //----------------------------------
    }

}

//===End UserAdapter Class===============================================================