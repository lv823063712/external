package com.example.external.ui.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.example.external.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import razerdp.basepopup.BasePopupWindow;
import razerdp.util.animation.AnimationHelper;
import razerdp.util.animation.TranslationConfig;

/**
 * @ClassName: BillTimerPop
 * @Description:
 * @CreateDate: 2020/11/16 9:50
 * @Creator: lf
 */
public class BillTimerPop extends BasePopupWindow implements View.OnClickListener {
    Date startData;
    SelectTimeListener mSelectTimeListener;
    private FragmentActivity mContext;
    private FrameLayout mFrameLayout;
    private TextView mTvOk;
    private TextView tv_no;
    private TimePickerBuilder mStartTimerBuilder;
    private TimePickerView mStartTimer;

    public BillTimerPop(FragmentActivity context) {
        super(context);
        mContext = context;
        setAlignBackground(true);
        setPopupGravity(Gravity.BOTTOM | Gravity.FILL_HORIZONTAL);
        setShowAnimation(AnimationHelper.asAnimation().withTranslation(TranslationConfig.FROM_BOTTOM).toShow());
        setDismissAnimation(AnimationHelper.asAnimation().withTranslation(TranslationConfig.TO_BOTTOM).toDismiss());
        initView();
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.pop_billtimer);
    }

    private void initView() {
        mFrameLayout = findViewById(R.id.fragmen_fragment);
        mTvOk = findViewById(R.id.tv_ok);
        tv_no = findViewById(R.id.tv_no);
        mTvOk.setOnClickListener(this);
        tv_no.setOnClickListener(this);
        initStart();

    }

    private void initStart() {
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.setTimeInMillis(selectedDate.getTimeInMillis());
        selectedDate.set(Calendar.HOUR_OF_DAY, 0);
        selectedDate.set(Calendar.MINUTE, 0);
        selectedDate.set(Calendar.SECOND, 0);
        selectedDate.set(Calendar.MILLISECOND, 0);
        startData = new Date(selectedDate.getTimeInMillis());

        Calendar startDate = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("yyyy");
        String format = df.format(new Date());
        int i = Integer.parseInt(format) - 100;
        //正确设置方式 原因：注意事项有说明   BasePopWindow
        startDate.set(i, 0, 1);
        mStartTimerBuilder = new TimePickerBuilder(mContext, (date, v) -> {

        })
                .setTimeSelectChangeListener(date -> {
                    startData = date;
                    LogUtils.e("Data", getTime(date), TimeUtils.date2Millis(date));
                    Calendar startDate1 = Calendar.getInstance();
                    startDate1.setTime(date);
                    startDate1.set(Calendar.HOUR_OF_DAY, 0);
                    startDate1.set(Calendar.MINUTE, 0);
                    startDate1.set(Calendar.SECOND, 0);
                    startDate1.set(Calendar.MILLISECOND, 0);

                }).setLayoutRes(R.layout.pickerview_custom_time, v -> {

                })
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("", "", "", "", "", "") //设置空字符串以隐藏单位提示   hide label
                .setDividerColor(Color.DKGRAY)
                .setContentTextSize(20)
                .setDate(selectedDate)
                .setRangDate(startDate, selectedDate)
                .setDecorView(mFrameLayout)//非dialog模式下,设置ViewGroup, pickerView将会添加到这个ViewGroup中
                .setOutSideColor(0x00000000)
                .setOutSideCancelable(false);
        mStartTimer = mStartTimerBuilder.build();
        mStartTimer.show(null, false);
    }

    //转换时间戳
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_ok:
                darkenBackground(1f);
                //接口回调,拿到选择日期
                mSelectTimeListener.selectModel(getTime(startData));
                dismiss();
                break;
            case R.id.tv_no:
                darkenBackground(1f);
                dismiss();
                break;
        }
    }

    /**
     * 接口回调
     *
     * @param mSelectTimeListener
     */
    public void setSelectTimeListener(SelectTimeListener mSelectTimeListener) {
        this.mSelectTimeListener = mSelectTimeListener;
    }

    public interface SelectTimeListener {
        void selectModel(String start);

    }

    public void darkenBackground(Float bgcolor) {
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = bgcolor;
        mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mContext.getWindow().setAttributes(lp);

    }

}
