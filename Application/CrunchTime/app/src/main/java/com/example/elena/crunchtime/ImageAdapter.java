package com.example.elena.crunchtime;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by elena on 2/4/16.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private final String[] mtext;
    private String[] mtextNum;
    private String[] mtextType;

    public ImageAdapter(Context c) {
        mContext = c;
        Resources res = c.getResources();
        this.mtext = res.getStringArray(R.array.exercises_array);
        this.mtextType = res.getStringArray(R.array.exercises_types2);
        this.mtextNum = res.getStringArray(R.array.exercises_num);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mtext.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
            //put in exercise name
            TextView typeView = (TextView) grid.findViewById(R.id.grid_type);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            //put in exercise num
            TextView textNumView = (TextView) grid.findViewById(R.id.grid_num);
            //put in image
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            typeView.setText(mtextType[position]);
            textView.setText(mtext[position]);
            textNumView.setText(mtextNum[position]);
            imageView.setImageResource(mThumbIds[position]);
//        } else {
//            grid = (View) convertView;
//        }

        return grid;
    }


    public void updateNums(int pos, int cals){
        Resources res = mContext.getResources();
        int[] conversion = res.getIntArray(R.array.exercises_conversion);
        mtextNum[pos] = Integer.toString((cals*conversion[pos])/100);
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.pushup, R.drawable.situp,
            R.drawable.squat, R.drawable.leglift,
            R.drawable.pushup, R.drawable.jumpingjack,
            R.drawable.pullup, R.drawable.cycle,
            R.drawable.walking, R.drawable.run,
            R.drawable.swimming, R.drawable.stairs
    };
}
