package com.example.glassesshop.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView Adapter for DataBinding Layout.
 */
public class DataBindingListAdapter<T, T2 extends ViewDataBinding> extends BaseAdapter {

    /**
     * Model List
     */
    private List<T> dataList;

    /**
     *  DataBindingUtil.inflate attachParent setting flag.
     */
    private boolean attachParent;

    /**
     * Generated ViewDataBinding Object
     */
    private T2 binding;

    /**
     * ListView Layout Row ID
     */
    private int layoutId;

    /**
     * Generated BR ID.
     */
    private int variableId;

    /**
     * Context LayoutInflater
     */
    private LayoutInflater inflater;

    /**
     * Constructor
     *
     * @param context    context
     * @param layoutId   layout id
     * @param variableId generated databinding id
     * @param dataList   model list
     */
    public DataBindingListAdapter(@NonNull Context context, @LayoutRes int layoutId, int variableId, @Nullable List<T> dataList) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layoutId = layoutId;
        this.variableId = variableId;
        this.setDataList((dataList != null) ? dataList : createDefaultDataList());
        this.setAttachParent(false);
    }

    /**
     * @see #DataBindingListAdapter(Context, int, int, List)
     */
    public DataBindingListAdapter(@NonNull Context context, @LayoutRes int layoutId, int variableId) {
        this(context, layoutId, variableId, null);
    }

    /**
     * @return Empty ArrayList
     */
    protected List<T> createDefaultDataList() {
        return new ArrayList<T>();
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        // FIXME 楽観的実装
        return dataList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public T getItem(int position) {
        // FIXME 楽観的実装
        return getDataList().get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        // FIXME 楽観的実装
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            binding = DataBindingUtil.inflate(inflater, layoutId, parent, isAttachParent());
            convertView = binding.getRoot();
            convertView.setTag(binding);
        } else {
            binding = (T2) convertView.getTag();
        }

        binding.setVariable(variableId, getItem(position));
        return convertView;
    }

    /**
     *
     * @return Data List
     */
    public List<T> getDataList() {
        return dataList;
    }

    /**
     *
     * @param dataList Data List
     */
    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    /**
     *
     * @return DataBindingUtil.inflate attachParent setting.
     */
    public boolean isAttachParent() {
        return attachParent;
    }

    /**
     *
     * @param attachParent DataBindingUtil.inflate attachParent setting.
     */
    public void setAttachParent(boolean attachParent) {
        this.attachParent = attachParent;
    }
}
