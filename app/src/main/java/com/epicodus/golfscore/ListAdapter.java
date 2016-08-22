package com.epicodus.golfscore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ListAdapter extends BaseAdapter {

    private final Context mContext;
    private final Hole[] mHoles;


    public ListAdapter (Context context, Hole[] holes) {
        mContext = context;
        mHoles = holes;
    }


    @Override
    public int getCount() {
        return mHoles.length;
    }

    @Override
    public Object getItem(int position) {
        return mHoles[position];
    }

    @Override
    public long getItemId(int position) {
        return 0; //not implemented
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.holeLabelTextView = (TextView) convertView.findViewById(R.id.holeLabelTextView);
            holder.strokeCountTextView = (TextView) convertView.findViewById(R.id.strokeCountTextView);
            holder.removeStrokeButton = (Button) convertView.findViewById(R.id.removeStrokeButton);
            holder.addStrokeButton = (Button) convertView.findViewById(R.id.addStrokeButton);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.holeLabelTextView.setText(mHoles[position].getmLabel());
        holder.strokeCountTextView.setText(mHoles[position].getmStrokeCount());
        holder.removeStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int upDatedStrokeCount = mHoles[position].getmStrokeCount() - 1;
                if(upDatedStrokeCount < 0) {
                    upDatedStrokeCount = 0;
                }
                mHoles[position].setmStrokeCount(upDatedStrokeCount);
                holder.strokeCountTextView.setText(upDatedStrokeCount + "");
            }
        });
        holder.addStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int upDatedStrokeCount = mHoles[position].getmStrokeCount() + 1;
                mHoles[position].setmStrokeCount(upDatedStrokeCount);
                holder.strokeCountTextView.setText(upDatedStrokeCount + "");

            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView holeLabelTextView;
        TextView strokeCountTextView;
        Button removeStrokeButton;
        Button addStrokeButton;

    }
}
