package com.demo.nyschool.nysSchool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.nyschool.R;
import com.demo.nyschool.detailschool.SchoolDetailActivity;
import com.demo.nyschool.pojo.NysSchool;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    public static final String KEY_NAME = "school_name";
    private List<NysSchool> nysSchoolList;
    private Context mContext;

    public MainAdapter(List<NysSchool> nysSchoolList, Context mContext) {
        this.nysSchoolList = nysSchoolList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final NysSchool school = nysSchoolList.get(i);
        viewHolder.tv_school.setText(school.getSchoolName().trim());
        viewHolder.tv_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SchoolDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(KEY_NAME, viewHolder.tv_school.getText().toString());
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return nysSchoolList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_school;

        ViewHolder(View itemView) {
            super(itemView);
            this.tv_school = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
