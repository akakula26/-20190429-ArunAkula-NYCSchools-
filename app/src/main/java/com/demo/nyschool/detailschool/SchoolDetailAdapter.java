package com.demo.nyschool.detailschool;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.nyschool.R;
import com.demo.nyschool.nysSchool.MainAdapter;
import com.demo.nyschool.pojo.NysDetail;
import com.demo.nyschool.pojo.NysSchool;

import java.util.List;

public class SchoolDetailAdapter extends RecyclerView.Adapter<SchoolDetailAdapter.ViewHolder> {
    public static final String KEY_NAME = "school_name";
    private List<NysDetail> nysSchoolList;
    private Context mContext;

    public SchoolDetailAdapter(List<NysDetail> nysSchoolList, Context mContext) {
        this.nysSchoolList = nysSchoolList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item, parent, false);
        return new SchoolDetailAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final NysDetail school = nysSchoolList.get(i);
        viewHolder.tv_dbn.setText("DBN :" + school.getDbn().trim());
        viewHolder.tv_noofSat.setText(school.getNumOfSatTestTakers().trim());
        viewHolder.tv_satCric.setText(school.getSatCriticalReadingAvgScore().trim());
        viewHolder.tv_satMat.setText(school.getSatMathAvgScore().trim());
        viewHolder.tv_sat.setText(school.getSatWritingAvgScore());
        viewHolder.tv_schoolName.setText("NAME  :" + school.getSchoolName().trim());

    }

    @Override
    public int getItemCount() {
        return nysSchoolList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_dbn;
        public TextView tv_noofSat;
        public TextView tv_satCric;
        public TextView tv_satMat;
        public TextView tv_sat;
        public TextView tv_schoolName;

        ViewHolder(View itemView) {
            super(itemView);
            this.tv_dbn = (TextView) itemView.findViewById(R.id.textView_dbn);
            this.tv_noofSat = (TextView) itemView.findViewById(R.id.textView_noOfSeat);
            this.tv_satCric = (TextView) itemView.findViewById(R.id.textView_satCritical);
            this.tv_satMat = (TextView) itemView.findViewById(R.id.textView_sat_maths);
            this.tv_sat = (TextView) itemView.findViewById(R.id.textView_sat_writing);
            this.tv_schoolName = (TextView) itemView.findViewById(R.id.textView_schoolName);
        }
    }
}
