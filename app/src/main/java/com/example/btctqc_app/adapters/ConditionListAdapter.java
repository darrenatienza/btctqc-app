package com.example.btctqc_app.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.example.btctqc_app.itemviews.ConditionItemView;
import com.example.btctqc_app.itemviews.ConditionItemView_;
import com.example.btctqc_app.services.models.ConditionModel;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;


@EBean
public class ConditionListAdapter extends RecyclerViewAdapterBase<ConditionModel, ConditionItemView> {

    @RootContext
    Context context;

    public void setList(List<ConditionModel> list) {
        items = list;
    }
    @Override
    protected ConditionItemView onCreateItemView(ViewGroup parent, int viewType) {
        return ConditionItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<ConditionItemView> viewHolder, int position) {
        ConditionItemView view = viewHolder.getView();
        ConditionModel model = items.get(position);
        view.bind(model);
    }
}
