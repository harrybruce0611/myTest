package hanyang.myapplication.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import hanyang.myapplication.R;
import hanyang.myapplication.models.TestData;

/**
 * Created by hanyang on 9/21/15.
 * RecyclerView adapter
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<TestData> mData = new ArrayList<TestData>();

    public TestRecyclerViewAdapter(Context context, ArrayList<TestData> _data) {
        mContext = context;
        mData = _data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.single_row_view,viewGroup,false);
        DataHolder holder = new DataHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        initView((DataHolder)viewHolder, i);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class DataHolder extends RecyclerView.ViewHolder {
        protected ImageView icon;
        protected TextView body;

        public DataHolder(View itemView) {
            super(itemView);
            icon = (ImageView)itemView.findViewById(R.id.singleRowViewImageView);
            body = (TextView)itemView.findViewById(R.id.singleRowViewTextView);
        }
    }

    private void initView(DataHolder holder, int index) {
        holder.body.setText(mData.get(index).getText());
        Picasso.with(mContext).load(mData.get(index).getImageUrl()).into(holder.icon);
    }

    /**
     * Update the RecyclerView
     * @param _data new Data
     */
    public void updateData(ArrayList<TestData> _data){
        mData = _data;
    }


}
