/*
 * Copyright (C) 2015 Taeho Kim <jyte82@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.androidhuman.zoomablesample;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.samples.zoomable.ZoomableDraweeView;

public class ViewPagerActivity extends AppCompatActivity {

      int i=0;
    int j=0;



    ViewPager vpGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        FLog.setMinimumLoggingLevel(FLog.VERBOSE);

        setContentView(R.layout.activity_view_pager);


        vpGallery = (ViewPager) findViewById(R.id.vp_gallery);
        vpGallery.setAdapter(new GalleryAdapter());


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Fresco.shutDown();
    }




    class GalleryAdapter extends PagerAdapter {

        String[] items = new String[]{
                "http://iiif.nli.org.il/IIIF/FL13407243/full/full/0/default.jpg",
                "http://iiif.nli.org.il/IIIF/FL13407246/full/full/0/default.jpg",
                "http://iiif.nli.org.il/IIIF/FL13407245/full/full/0/default.jpg",
                "http://iiif.nli.org.il/IIIF/FL13407153/full/full/0/default.jpg",
                "http://iiif.nli.org.il/IIIF/FL13407216/full/full/0/default.jpg",
                "http://iiif.nli.org.il/IIIF/FL13407202/full/full/0/default.jpg",
                "http://iiif.nli.org.il/IIIF/FL13407227/full/full/0/default.jpg",
                "http://iiif.nli.org.il/IIIF/FL13407202/full/full/0/default.jpg",
                "http://iiif.nli.org.il/IIIF/FL13407108/full/full/0/default.jpg",
                "http://iiif.nli.org.il/IIIF/FL13407109/full/full/0/default.jpg",
                "http://iiif.nli.org.il/IIIF/FL13407121/full/full/0/default.jpg",
                "http://iiif.nli.org.il/IIIF/FL13407218/full/full/0/default.jpg"

        };

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            TextView textView1=(TextView) findViewById(R.id.textView);
            if(j==0) {
                textView1.setText(position + "/" + items.length);
                textView1.setTextSize(25);
                if(position==items.length-1){
                    i=1;
                }
            }
            if(j==1){
                textView1.setText(items.length-1 + "/" + items.length);
                j=0;
            }




            ZoomableDraweeView view = new ZoomableDraweeView(container.getContext());
                view.setController(
                        Fresco.newDraweeControllerBuilder()
                                .setUri(Uri.parse(items[position]))
                                .build());




            GenericDraweeHierarchy hierarchy =
                    new GenericDraweeHierarchyBuilder(container.getResources())
                            .setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER)
                            .setProgressBarImage(new ProgressBarDrawable())
                            .build();

            view.setHierarchy(hierarchy);


            container.addView(view,
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            TextView textView1=(TextView) findViewById(R.id.textView);
            if(i==0){
                textView1.setText((position - 1) + "/" + items.length);

            }
           else {
                j=1;
                textView1.setText(items.length + "/" + items.length);
                i=0;
            }

            container.removeView((View) object);


        }

        @Override
        public int getCount() {
            return items.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}

