package com.infobox.introslide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class intropageradapter extends PagerAdapter {

    Context context;
    ArrayList<ScreenItem> screenItemslist;

    public intropageradapter(Context context, ArrayList<ScreenItem> screenItemslist) {
        this.context = context;
        this.screenItemslist = screenItemslist;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View layoutView = layoutInflater.inflate(R.layout.screen_layout, null);


        ImageView imageView = layoutView.findViewById(R.id.imageViewid);

        TextView title = layoutView.findViewById(R.id.titletextviewid);
        TextView description = layoutView.findViewById(R.id.descriptextviewid);

        imageView.setImageResource(screenItemslist.get(position).getImage());
        title.setText(screenItemslist.get(position).getTitle());
        description.setText(screenItemslist.get(position).getDescription());

       container.addView(layoutView);
       return layoutView;
    }

    @Override
    public int getCount() {
        return screenItemslist.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
