package com.example.btctqc_app.itemviews;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.btctqc_app.R;
import com.example.btctqc_app.services.models.ResponseModel;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.item_response)
public class SurveyItemView extends RelativeLayout {

    @ViewById(R.id.id)
    TextView id;
    @ViewById(R.id.condition)
    TextView title;

    public SurveyItemView(Context context) {
        super(context);
    }

    public void bind(ResponseModel model) {
        id.setText(String.valueOf(model.getCondition_id()));
        title.setText(model.getCondition_name());
    }
}