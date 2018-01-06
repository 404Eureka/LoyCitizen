package com.example.loylogic.hackathon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CheckableImageButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.loylogic.hackathon.Model.Reaction;

import java.util.List;

/**
 * Created by loylogic on 06/01/18.
 */

public class ReactionAdapter extends RecyclerView.Adapter<ReactionAdapter.MyViewHolder> {

    private List<Reaction> ReactionsList;
    private Context context;
    int layout ;


    private static CheckableImageButton lastChecked = null;
    private static int lastCheckedPos = 0;

    public ReactionAdapter(List<Reaction> ReactionsList, Context context, int layout) {
        this.ReactionsList = ReactionsList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public ReactionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);

        return new ReactionAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReactionAdapter.MyViewHolder holder, final int position) {
        final Reaction reaction = ReactionsList.get(position);
        holder.title.setText(reaction.getName());
        int id = context.getResources().getIdentifier(reaction.getImage(), "drawable", context.getPackageName());
        holder.image.setBackgroundResource(id);
        if (reaction.isSelected()) {
            id = context.getResources().getIdentifier(reaction.getSelectedImage(), "drawable", context.getPackageName());
            holder.image.setBackgroundResource(id);
        } else {
            id = context.getResources().getIdentifier(reaction.getImage(), "drawable", context.getPackageName());
            holder.image.setBackgroundResource(id);
        }
        holder.image.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v)
            {
                CheckableImageButton cb = (CheckableImageButton)v;
                if(lastChecked != null)
                    {
                        lastChecked.setChecked(false);
                        ReactionsList.get(lastCheckedPos).setSelected(false);
                    }
                if (position == lastCheckedPos) {
                    cb.setChecked(false);
                    lastCheckedPos = -1;
                } else {
                    lastChecked = cb;
                    lastCheckedPos = position;
                    ReactionsList.get(position).setSelected(true);
                    notifyDataSetChanged();
                }


//                int clickedPos = position;
//                cb.setChecked(true);
//
//                if(cb.isChecked())
//                {
//                    if(lastChecked != null)
//                    {
//                        lastChecked.setChecked(false);
//                        ReactionsList.get(lastCheckedPos).setSelected(false);
//                    }
//
//                    lastChecked = cb;
//                    lastCheckedPos = clickedPos;
//                    reaction.setSelected(true);
//                    notifyItemChanged(position);
//                }
//                else
//                    lastChecked = null;
//
//                ReactionsList.get(clickedPos).setSelected(cb.isSelected());
//                int id = context.getResources().getIdentifier(reaction.getImage(), "drawable", context.getPackageName());
//                CheckableImageButton checkableImageButton = v.findViewById(R.id.dimage);
////
//                if (reaction.isSelected()) {
//                    id = context.getResources().getIdentifier(reaction.getSelectedImage(), "drawable", context.getPackageName());
//                    checkableImageButton.setBackgroundResource(id);
//                } else {
//                    id = context.getResources().getIdentifier(reaction.getImage(), "drawable", context.getPackageName());
//                    checkableImageButton.setBackgroundResource(id);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ReactionsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public CheckableImageButton image;
//        public RelativeLayout drlLayout;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.dname);
            image = (CheckableImageButton) view.findViewById(R.id.dimage);
//            drlLayout = (RelativeLayout) view.findViewById(R.id.drlLayout);
        }
    }



}



