package com.pofsoft.t1b1client;
import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
 
import java.util.ArrayList;
 
/**
 * Created with IntelliJ IDEA.
 * User: zizi
 * Date: 8/10/12
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyCustomAdapter extends BaseExpandableListAdapter {
 
	private static final String TAG_STARTNUMBER = "startnumber";
	private static final String TAG_POLEPOSITION = "poleposition";
	private static final String TAG_FIRSTNAME = "firstname";
	private static final String TAG_LASTNAME = "lastname";
	
    private LayoutInflater inflater;
    private ArrayList<Parent> mParent;
 
    public MyCustomAdapter(Context context, ArrayList<Parent> parent){
        mParent = parent;
        inflater = LayoutInflater.from(context);
    }
 
 
    @Override
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return mParent.size();
    }
 
    @Override
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return mParent.get(i).getArrayChildren().size();
    }
 
    @Override
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return mParent.get(i).getTitle();
    }
 
    @Override
    //gets the name of each item
    public Object getChild(int i, int i1) {
        return mParent.get(i).getArrayChildren().get(i1);
    }
 
    @Override
    public long getGroupId(int i) {
        return i;
    }
 
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }
 
    @Override
    public boolean hasStableIds() {
        return true;
    }
 
    @Override
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
 
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_parent, viewGroup,false);
        }
 
        TextView textView = (TextView) view.findViewById(R.id.list_item_text_view);
        //"i" is the position of the parent/group in the list
        textView.setText(getGroup(i).toString());
 
        //return the entire view
        return view;
    }
 
    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = inflater.inflate(R.layout.list_item_child, viewGroup,false);
        }
 
        TextView textView = (TextView) view.findViewById(R.id.lastName);
        textView.setText(mParent.get(i).getArrayChildren().get(i1).get(TAG_LASTNAME));

        textView = (TextView) view.findViewById(R.id.firstName);
        textView.setText(mParent.get(i).getArrayChildren().get(i1).get(TAG_FIRSTNAME));
        
        textView = (TextView) view.findViewById(R.id.startnumber);
        textView.setText(mParent.get(i).getArrayChildren().get(i1).get(TAG_STARTNUMBER));

        textView = (TextView) view.findViewById(R.id.poleposition);
        textView.setText(mParent.get(i).getArrayChildren().get(i1).get(TAG_POLEPOSITION));
        
        return view;
    }
 
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
 
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        /* used to make the notifyDataSetChanged() method work */
        super.registerDataSetObserver(observer);
    }
}