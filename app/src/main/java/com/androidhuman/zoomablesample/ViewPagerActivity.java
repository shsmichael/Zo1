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
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.samples.zoomable.ZoomableDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
//import android.support.design.widget.Snackbar;
//import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

public class ViewPagerActivity extends AppCompatActivity {
    List<String> pagesStr = new ArrayList<String>();
    private String ID = "NNL_ALEPH002609135";
    private ViewItemTask mAuthTask = null;
    int i = 0;
    int j = 0;



    ViewPager vpGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        FLog.setMinimumLoggingLevel(FLog.VERBOSE);

        setContentView(R.layout.activity_view_pager);

        Intent intent = getIntent();
        ID = intent.getStringExtra("recordID");
        mAuthTask = new ViewItemTask(ID);
        mAuthTask.execute((Void) null);
        vpGallery = (ViewPager) findViewById(R.id.vp_gallery);
        vpGallery.setAdapter(new GalleryAdapter(pagesStr));


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Fresco.shutDown();
    }

    public class ViewItemTask extends AsyncTask<Void, Void, JSONObject> {

        private final String bookId;

        ViewItemTask(String bookId) {
            this.bookId = bookId;
        }

        @Override
        protected JSONObject doInBackground(Void... params) {

            if (params.length == 0) {
                return null;
            }

            Log.v("connect", "CONNECTED");
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String serverJsonStr = null;

            try {
                final String SERVER_BASE_URL =
                        "http://52.29.110.203:8080/LibArab/signIn/doSignIn?";
                //TODO: change according to the server function format
                final String ID_PARAM = "recordID";

                Uri builtUri = Uri.parse(SERVER_BASE_URL).buildUpon()
                        .appendQueryParameter(ID_PARAM, bookId)
                        .build();

                URL url = new URL(builtUri.toString());
                Log.v("URL", builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();


                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;

                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                serverJsonStr = buffer.toString();
                Log.d("PROBLEM", serverJsonStr);

            } catch (IOException e) {
                Log.e("LOGE", "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;

            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("LOGE", "Error closing stream", e);
                    }
                }
            }

            JSONObject serverJson = null;
            try {
                serverJson = new JSONObject(serverJsonStr);
                return serverJson;

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


    }

    protected void onPostExecute(final JSONObject success) throws JSONException {

        JSONArray pages3 = success.getJSONArray("structures");
        JSONObject page2=pages3.getJSONObject(0);
       JSONArray pages=page2.getJSONArray("canvases");

       // JSONObject object = pages.getJSONObject(0);
        //JSONObject object1 = pages.getJSONObject(pages.length() - 1);


        String first = "http://iiif.nli.org.il/IIIF/";
        String last = "full/250,/0/default.jpg";
        String tmp = "";
        for (int i = 0; i < pages.length(); i++) {
            JSONObject object2 = pages.getJSONObject(i);
            tmp = first + object2.toString() + last;
            pagesStr.add(tmp);
        }
        // ViewPagerActivity.GalleryAdapter(pagesStr);

        // Intent intent=new Intent(getApplicationContext(),ViewPagerActivity.class);
        // startActivity(intent);


    }

    protected void onCancelled() {
        mAuthTask = null;
        //   showProgress(false);
    }



    class GalleryAdapter extends PagerAdapter {

       List<String> items;




      //  String[] items = new String[]{
       //         "http://iiif.nli.org.il/IIIF/FL13407243/full/full/0/default.jpg",
         //       "http://iiif.nli.org.il/IIIF/FL13407246/full/full/0/default.jpg",
           //     "http://iiif.nli.org.il/IIIF/FL13407245/full/full/0/default.jpg",
             //   "http://iiif.nli.org.il/IIIF/FL13407153/full/full/0/default.jpg",
            //    "http://iiif.nli.org.il/IIIF/FL13407216/full/full/0/default.jpg",
              //  "http://iiif.nli.org.il/IIIF/FL13407202/full/full/0/default.jpg",
                //"http://iiif.nli.org.il/IIIF/FL13407227/full/full/0/default.jpg",
       //         "http://iiif.nli.org.il/IIIF/FL13407202/full/full/0/default.jpg",
         //       "http://iiif.nli.org.il/IIIF/FL13407108/full/full/0/default.jpg",
          //      "http://iiif.nli.org.il/IIIF/FL13407109/full/full/0/default.jpg",
            //    "http://iiif.nli.org.il/IIIF/FL13407121/full/full/0/default.jpg",
             //   "http://iiif.nli.org.il/IIIF/FL13407218/full/full/0/default.jpg"

       // };
        public GalleryAdapter(List<String> pages){
            items=new ArrayList<String>();
            if(items.addAll(pagesStr)==false){
                return;
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            TextView textView1=(TextView) findViewById(R.id.textView);
            if(j==0) {
                textView1.setText(position + "/" + items.size());
                textView1.setTextSize(25);
                if(position==items.size()-1){
                    i=1;
                }
            }
            if(j==1){
                textView1.setText(items.size()-1 + "/" + items.size());
                j=0;
            }




            ZoomableDraweeView view = new ZoomableDraweeView(container.getContext());
                view.setController(
                        Fresco.newDraweeControllerBuilder()
                                .setUri(Uri.parse(items.get(position)))
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
                textView1.setText((position - 1) + "/" + items.size());

            }
           else {
                j=1;
                textView1.setText(items.size() + "/" + items.size());
                i=0;
            }

            container.removeView((View) object);


        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}


