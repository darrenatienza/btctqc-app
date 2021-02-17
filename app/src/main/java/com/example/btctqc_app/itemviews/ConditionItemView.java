package com.example.btctqc_app.itemviews;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.btctqc_app.R;
import com.example.btctqc_app.services.models.ConditionModel;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.item_condition)
public class ConditionItemView extends RelativeLayout {

    @ViewById(R.id.id)
    TextView id;
    @ViewById(R.id.title)
    TextView title;

    public ConditionItemView(Context context) {
        super(context);
    }

    public void bind(ConditionModel model) {
        id.setText(String.valueOf(model.getCondition_id()));
        title.setText(model.getName());
    }
}