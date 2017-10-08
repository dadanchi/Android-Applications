package com.dadanchi.e_meal.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.dadanchi.e_meal.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dadanchi on 08/10/2017.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {
    // TODO -> DI
    private HashMap<String, ArrayList<String>> mChildren;
    private ArrayList<String> mHeadings;
    private Context mContext;

    public ExpandableListAdapter(HashMap<String, ArrayList<String>> hashMap,
                                 ArrayList<String> list, Context context) {
        mChildren = hashMap;
        mHeadings = list;
        mContext = context;

    }

    @Override
    public int getGroupCount() {
        return mHeadings.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String currentHeading = mHeadings.get(groupPosition);

        return mChildren.get(currentHeading).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mHeadings.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String currentHeading = mHeadings.get(groupPosition);

        return mChildren.get(currentHeading).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        txtListChild.setText(childText);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void clear() {
        mHeadings = new ArrayList<>();
        mChildren = new HashMap<>();
    }

    public void addAll(HashMap<String, ArrayList<String>> map, ArrayList<String> list) {
        mChildren = map;
        mHeadings = list;
    }
}
