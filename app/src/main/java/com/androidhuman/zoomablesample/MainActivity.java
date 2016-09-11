package com.androidhuman.zoomablesample;
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

/**
 * Created by Mostafa on 9/8/2016.
 */
public class MainActivity extends AppCompatActivity {


    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        ID = intent.getStringExtra("recordID");
        Intent intent1=new Intent(getApplicationContext(),ViewPagerActivity.class);
        intent.putExtra("recordID",ID);
        startActivity(intent1);


        //String jsonStr = "{\"recordID\":\"NNL_ALEPH003157499\",\"pages\":[\"http://iiif.nli.org.il/IIIF/\",\"FL15113675\",\"FL15113688\",\"FL15113673\",\"FL15113532\",\"FL15113699\",\"FL15113637\",\"FL15113638\",\"FL15113600\",\"FL15113544\",\"FL15113748\",\"FL15113587\",\"FL15113771\",\"FL15113713\",\"FL15113615\",\"FL15113619\",\"FL15113687\",\"FL15113719\",\"FL15113718\",\"FL15113650\",\"FL15113590\",\"FL15113550\",\"FL15113554\",\"FL15113592\",\"FL15113591\",\"FL15113776\",\"FL15113761\",\"FL15113777\",\"FL15113652\",\"FL15113557\",\"FL15113762\",\"FL15113694\",\"FL15113695\",\"FL15113535\",\"FL15113533\",\"FL15113784\",\"FL15113672\",\"FL15113609\",\"FL15113745\",\"FL15113536\",\"FL15113743\",\"FL15113584\",\"FL15113586\",\"FL15113674\",\"FL15113707\",\"FL15113735\",\"FL15113534\",\"FL15113668\",\"FL15113602\",\"FL15113523\",\"FL15113634\",\"FL15113765\",\"FL15113764\",\"FL15113553\",\"FL15113758\",\"FL15113653\",\"FL15113655\",\"FL15113618\",\"FL15113717\",\"FL15113756\",\"FL15113773\",\"FL15113751\",\"FL15113768\",\"FL15113685\",\"FL15113770\",\"FL15113785\",\"FL15113640\",\"FL15113733\",\"FL15113636\",\"FL15113710\",\"FL15113744\",\"FL15113724\",\"FL15113760\",\"FL15113656\",\"FL15113558\",\"FL15113524\",\"FL15113730\",\"FL15113565\",\"FL15113563\",\"FL15113782\",\"FL15113628\",\"FL15113664\",\"FL15113691\",\"FL15113625\",\"FL15113709\",\"FL15113645\",\"FL15113705\",\"FL15113578\",\"FL15113603\",\"FL15113737\",\"FL15113732\",\"FL15113569\",\"FL15113611\",\"FL15113614\",\"FL15113752\",\"FL15113772\",\"FL15113686\",\"FL15113754\",\"FL15113620\",\"FL15113755\",\"FL15113704\",\"FL15113604\",\"FL15113670\",\"FL15113572\",\"FL15113608\",\"FL15113610\",\"FL15113537\",\"FL15113708\",\"FL15113781\",\"FL15113780\",\"FL15113726\",\"FL15113560\",\"FL15113722\",\"FL15113723\",\"FL15113775\",\"FL15113556\",\"FL15113588\",\"FL15113548\",\"FL15113551\",\"FL15113721\",\"FL15113612\",\"FL15113680\",\"FL15113715\",\"FL15113545\",\"FL15113747\",\"FL15113678\",\"FL15113742\",\"FL15113644\",\"FL15113576\",\"FL15113740\",\"FL15113701\",\"FL15113671\",\"FL15113597\",\"FL15113596\",\"FL15113779\",\"FL15113662\",\"FL15113698\",\"FL15113564\",\"FL15113696\",\"FL15113629\",\"FL15113531\",\"FL15113739\",\"FL15113599\",\"FL15113526\",\"FL15113646\",\"FL15113540\",\"FL15113580\",\"FL15113581\",\"FL15113711\",\"FL15113543\",\"FL15113682\",\"FL15113616\",\"FL15113660\",\"FL15113593\",\"FL15113757\",\"FL15113623\",\"FL15113525\",\"FL15113697\",\"FL15113665\",\"FL15113631\",\"FL15113693\",\"FL15113692\",\"FL15113595\",\"FL15113725\",\"FL15113746\",\"FL15113541\",\"FL15113582\",\"FL15113766\",\"FL15113703\",\"FL15113574\",\"FL15113787\",\"FL15113641\",\"FL15113527\",\"FL15113571\",\"FL15113528\",\"FL15113716\",\"FL15113617\",\"FL15113683\",\"FL15113712\",\"FL15113681\",\"FL15113749\",\"FL15113613\",\"FL15113622\",\"FL15113621\",\"FL15113774\",\"FL15113547\",\"FL15113648\",\"FL15113720\",\"FL15113702\",\"FL15113786\",\"FL15113530\",\"FL15113689\",\"FL15113568\",\"FL15113669\",\"FL15113601\",\"FL15113539\",\"FL15113538\",\"FL15113677\",\"FL15113577\",\"FL15113789\",\"FL15113790\",\"FL15113579\",\"FL15113562\",\"FL15113627\",\"FL15113727\",\"FL15113663\",\"FL15113521\",\"FL15113661\",\"FL15113778\",\"FL15113659\",\"FL15113654\",\"FL15113555\",\"FL15113651\",\"FL15113658\",\"FL15113759\",\"FL15113552\",\"FL15113657\",\"FL15113649\",\"FL15113549\",\"FL15113589\",\"FL15113546\",\"FL15113753\",\"FL15113647\",\"FL15113684\",\"FL15113714\",\"FL15113769\",\"FL15113750\",\"FL15113679\",\"FL15113585\",\"FL15113676\",\"FL15113583\",\"FL15113542\",\"FL15113788\",\"FL15113642\",\"FL15113643\",\"FL15113738\",\"FL15113639\",\"FL15113529\",\"FL15113734\",\"FL15113731\",\"FL15113667\",\"FL15113566\",\"FL15113741\",\"FL15113605\",\"FL15113706\",\"FL15113606\",\"FL15113607\",\"FL15113767\",\"FL15113567\",\"FL15113783\",\"FL15113570\",\"FL15113700\",\"FL15113736\",\"FL15113575\",\"FL15113573\",\"FL15113598\",\"FL15113630\",\"FL15113729\",\"FL15113632\",\"FL15113633\",\"FL15113635\",\"FL15113666\",\"FL15113594\",\"FL15113690\",\"FL15113522\",\"FL15113728\",\"FL15113626\",\"FL15113561\",\"FL15113763\",\"FL15113559\",\"FL15113624\",\"/full/250,/0/default.jpg\"],\"book\":{\"webLink\":\"http://merhav.nli.org.il/primo_library/libweb/action/display.do?tabs=detailsTab&ct=display&fn=search&doc=NNL_ALEPH003157499\",\"author\":\"حافظ، 1325-1390\",\"subject\":\"Persian poetry; Poetry; Digitized manuscripts\",\"publisher\":\"JER NLI Yah. Ar. 93;The National Library of Israel Jerusalem Israel Ms. Yah. Ar. 93 Y;הספרייה הלאומית ירושלים ישראל Y;المكتبة الوطنية الاسرائيلية اورشليم إسرائيل Y;National Library of Israel, Jerusalem N;JER NLI N;Jerusalem - The National Library of Israel N;הספרייה הלאומית, ירושלים N;ירושלים - הספריה הלאומית N;ירושלים - הספרייה הלאומית N;اورشليم - المكتبة الوطنية الاسرائيلية N\",\"source\":\"The National Library of Israel\",\"title\":\"ديوان حافظ شيرازي.\"}}";


        //JSONObject json=null;
        //try{
        //  json=new JSONObject(jsonStr);
        // }catch (JSONException e){
        //   e.printStackTrace();
        // }
        // int size=json.length();


    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}



