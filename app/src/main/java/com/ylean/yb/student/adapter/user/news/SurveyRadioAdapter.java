package com.ylean.yb.student.adapter.user.news;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ylean.yb.student.R;
import com.zxdc.utils.library.bean.SurveyDetails;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyRadioAdapter extends BaseAdapter {

    private Activity activity;
    private SurveyDetails.Ques ques;
    private List<SurveyDetails.Ans> list;

    public SurveyRadioAdapter(Activity activity, SurveyDetails.Ques ques) {
        super();
        this.activity = activity;
        this.ques=ques;
        this.list = ques.getAnslist();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder holder = null;
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.item_survey_radio, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        final SurveyDetails.Ans ans = list.get(position);
        holder.tvTitle.setText(ans.getContent());
        if(ques.getSelectValue().equals(ans.getOptionvalue())){
            holder.imgRedio.setImageResource(R.mipmap.radio_yes);
        }else{
            holder.imgRedio.setImageResource(R.mipmap.radio_no);
        }

        holder.linClick.setTag(ans.getOptionvalue());
        holder.linClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ques.setSelectValue((String) v.getTag());
                notifyDataSetChanged();
            }
        });
        return view;
    }


    static
    class ViewHolder {
        @BindView(R.id.img_redio)
        ImageView imgRedio;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.lin_click)
        LinearLayout linClick;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
