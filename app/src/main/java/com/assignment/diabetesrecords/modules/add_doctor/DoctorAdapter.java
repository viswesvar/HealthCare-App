package com.assignment.diabetesrecords.modules.add_doctor;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.assignment.diabetesrecords.R;
import com.assignment.diabetesrecords.modules.app_appointment.AddAppointmentActivity;
import com.assignment.diabetesrecords.entity.Doctor;

import java.util.List;

//==Start UserAdapter Class==========================================================
public class DoctorAdapter extends
        RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    /**
     * The Class ViewHolder.
     */
    public  class ViewHolder extends RecyclerView.ViewHolder {



        /** The title. */
        private TextView tvName, tvPhone, tvEmail, tvDoctorId;
        private Button bAppointment;


        /**
         * Instantiates a new view holder.
         *
         * @param itemView the item view
         */
        public ViewHolder(View itemView) {
            super(itemView);


            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvPhone = (TextView) itemView.findViewById(R.id.tvPhone);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
            tvDoctorId = (TextView) itemView.findViewById(R.id.tvDoctorId);
            bAppointment = (Button) itemView.findViewById(R.id.bAppointment);


        }
    }

    /** The Context. */
    private Context mContext;

    /** The Inflater. */
    private LayoutInflater mInflater;

    /** The Adapter data. */
    private List<Doctor> mAdapterData;
    int selected_position = 0;

    /**
     * Instantiates a new filter adapter.
     *
     * @param context the context
     * @param filteredResult the filtered result
     */
    public DoctorAdapter(Context context, List<Doctor> filteredResult) {
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
        View convertView = mInflater.inflate(R.layout.row_doctor, parent,
                false);

        ViewHolder holder = new ViewHolder(convertView);

        return holder;
    }

    /* (non-Javadoc)
     * @see android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(android.support.v7.widget.RecyclerView.ViewHolder, int)
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Doctor record = mAdapterData.get(position);

        holder.tvName.setText(record.getName().toString() );
        holder.tvPhone.setText(record.getPhone());
        holder.tvEmail.setText(record.getEmailid());
        holder.tvDoctorId.setText(String.valueOf(record.getId()));

        holder.bAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in= new Intent(mContext, AddAppointmentActivity.class);
                in.putExtra("DoctorId", holder.tvDoctorId.getText());
                in.putExtra("DoctorName", holder.tvName.getText());
                in.putExtra("DoctorPhone", holder.tvPhone.getText());
                in.putExtra("DoctorEmail", holder.tvEmail.getText());
                mContext.startActivity(in);
            }
        });

        //----------------------------------
    }

}

//===End UserAdapter Class===============================================================