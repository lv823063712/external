package com.example.external.ui.view;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.external.R;
import com.example.external.ui.view.picker.SinglePicker;

import razerdp.basepopup.BasePopupWindow;
import razerdp.util.animation.AnimationHelper;
import razerdp.util.animation.TranslationConfig;

/**
 * @ClassName: EducationPop
 * @Description:
 * @CreateDate: 2020/11/16 10:48
 * @Creator: lf
 */
public class EducationPop extends BasePopupWindow implements View.OnClickListener {
    private Activity mContext;
    private TextView accomplish, cancle_pop;
    private FrameLayout wheelview_single;
    private SexPicker.selectAll selectAll;
    private String e1ducation = "Other";

    public EducationPop(Activity context) {
        super(context);
        setPopupGravity(Gravity.BOTTOM);
        int width = context.getWindowManager().getDefaultDisplay().getWidth();
        setWidth(width);
        this.mContext = context;
        init(mContext);
    }


    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.pop_layout);
    }

    private void init(Activity context) {
        accomplish = findViewById(R.id.accomplish);
        cancle_pop = findViewById(R.id.cancle_pop);
        wheelview_single = findViewById(R.id.wheelview_single);

        wheelview_single.addView(onSinglePicker());
        accomplish.setOnClickListener(this);
        cancle_pop.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.accomplish:
                selectAll.selectAll(e1ducation);
                dismiss();
            case R.id.cancle_pop:
                dismiss();
                break;
        }
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return AnimationHelper.asAnimation()
                .withTranslation(TranslationConfig.FROM_BOTTOM)
                .toShow();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return AnimationHelper.asAnimation()
                .withTranslation(TranslationConfig.TO_BOTTOM)
                .toDismiss();
    }

    public void mySelectAll(SexPicker.selectAll onClickListener) {
        this.selectAll = onClickListener;
    }

    public interface selectAll {
        void selectAll(String nameAb);
    }

    public void selectAll(String nameAb) {
        selectAll.selectAll(nameAb);
    }

    private View onSinglePicker() {
//        String[] ss = (String[]) list.toArray();
        SinglePicker<String> picker = new SinglePicker<>(mContext, new String[]{"Other", "High School Diploma",
                "Bachelor degree","Master Diploma","Doctoral Diploma"});
        picker.setCanLoop(false);
        picker.setLineVisible(true);
        picker.setLineColor(ContextCompat.getColor(mContext, R.color.gray_cc));
        picker.setSelectedIndex(0);
        picker.setItemWidth(500);
        picker.setTextSize(22);
        picker.setOuterLabelEnable(true);
        picker.setSelectedTextColor(ContextCompat.getColor(mContext, R.color.black_333333));
        picker.setUnSelectedTextColor(ContextCompat.getColor(mContext, R.color.black_66333333));
        picker.setOnSingleWheelListener((index, item) -> {
            e1ducation = item;
        });
        picker.setOnItemPickListener((index, item) -> {
            e1ducation = item;
        });

        return picker.getContentView();
    }
}
