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
    private final String[] text;
    private String[] textNum;


    public ImageAdapter(Context c) {
        mContext = c;
        Resources res = c.getResources();
        this.text = res.getStringArray(R.array.exercises_array);
        this.textNum = res.getStringArray(R.array.exercises_num);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return text.length;
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

        if (convertView == null) {
            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
            //put in exercise name
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            //put in exercise num
            TextView textNumView = (TextView) grid.findViewById(R.id.grid_num);
            //put in image
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            textView.setText(text[position]);
            textNumView.setText(textNum[position]);
            imageView.setImageResource(mThumbIds[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }


    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5,
            R.drawable.sample_6, R.drawable.sample_7,
            R.drawable.sample_0, R.drawable.sample_1,
            R.drawable.sample_2, R.drawable.sample_3,
            R.drawable.sample_4, R.drawable.sample_5
    };
}
