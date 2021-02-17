package com.example.btctqc_app.adapters;

import android.content.Context;
import android.view.ViewGroup;

import com.example.btctqc_app.itemviews.ConditionItemView;
import com.example.btctqc_app.itemviews.ConditionItemView_;
import com.example.btctqc_app.itemviews.ResponseItemView;
import com.example.btctqc_app.itemviews.ResponseItemView_;
import com.example.btctqc_app.services.models.ConditionModel;
import com.example.btctqc_app.services.models.ResponseModel;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;


@EBean
public class ResponseListAdapter extends RecyclerViewAdapterBase<ResponseModel, ResponseItemView> {

    @RootContext
    Context context;

    public void setList(List<ResponseModel> list) {
        items = list;
    }
    @Override
    protected ResponseItemView onCreateItemView(ViewGroup parent, int viewType) {
        return ResponseItemView_.build(context);
    }

    @Override
    public void onBindViewHolder(ViewWrapper<ResponseItemView> viewHolder, int position) {
        ResponseItemView view = viewHolder.getView();
        ResponseModel model = items.get(position);
        view.bind(model);
    }
}
