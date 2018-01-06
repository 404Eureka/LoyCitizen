package com.example.loylogic.hackathon;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.loylogic.hackathon.Model.Department;

import java.util.List;

/**
 * Created by loylogic on 06/01/18.
 */

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.MyViewHolder> {

    private List<Department> departmentsList;
    private Context context;
    int layout ;
    public DepartmentAdapter(List<Department> departmentsList, Context context, int layout) {
        this.departmentsList = departmentsList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Department department = departmentsList.get(position);
        holder.title.setText(department.getName());
        int id = context.getResources().getIdentifier(department.getImage(), "drawable", context.getPackageName());
        holder.image.setBackgroundResource(id);
        holder.drlLayout.setBackgroundColor(Color.parseColor(department.getColor()));

    }

    @Override
    public int getItemCount() {
        return departmentsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        public RelativeLayout drlLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.dname);
            image = (ImageView) view.findViewById(R.id.dimage);
            drlLayout = (RelativeLayout) view.findViewById(R.id.drlLayout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Redraw the old selection and the new
                    Intent intent = new Intent(context,FeedbackActivity.class);
                    intent.putExtra("title",title.getText());
                    context.startActivity(intent);
                }
            });
        }
    }



}


