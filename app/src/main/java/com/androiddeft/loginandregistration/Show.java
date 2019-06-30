 package com.androiddeft.loginandregistration;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.androiddeft.loginandregistration.model.Feedback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

 public class Show extends AppCompatActivity {
     private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        listView = (ListView) findViewById(R.id.listView);

        String Str_feedback = "http://192.168.130.2/feedback/show.php";
        System.out.println(Str_feedback);

        MyAsynTask newTask = new MyAsynTask();
        newTask.execute(Str_feedback);
    }

     private String getHttpURLConnection (String urlStr) {

         System.out.println (".............." + urlStr);

         HttpURLConnection urlConnection = null;
         BufferedReader bufferedReader = null;

         String returnStr = null;

         try {
             URL url = new URL(urlStr);

             urlConnection = (HttpURLConnection) url.openConnection();
             urlConnection.setRequestMethod("GET");
             urlConnection.connect();

             InputStream inputSteam = urlConnection.getInputStream();
             StringBuffer stringBuffer = new StringBuffer();

             if (inputSteam == null) {
                 // Nothing to do
                 return null;
             }

             bufferedReader = new BufferedReader( new InputStreamReader(inputSteam) );

             String line;
             while ( (line = bufferedReader.readLine()) != null) {
                 stringBuffer.append(line + "\n");
             }

             returnStr = stringBuffer.toString();

         } catch (MalformedURLException e) {
             e.printStackTrace();
         } catch (ProtocolException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         return returnStr;
     }

     private class MyAsynTask extends AsyncTask<String, Void, String> {


         @Override
         protected String doInBackground(String... params) {
             String retStr = getHttpURLConnection(params[0]);
             return retStr;
         }

         protected void onPostExecute(String retStr)  {
             super.onPostExecute(retStr);

             System.out.println("====================================");
             System.out.println(retStr);

             ArrayList<Feedback> feedbacks = jsonToArrayList_feedbacks(retStr);
             System.out.println(feedbacks);

             ArrayAdapter<Feedback> arrayAdapter = new ArrayAdapter<Feedback>(
                     Show.this,
                     android.R.layout.simple_list_item_1,
                     feedbacks
             );

             listView = (ListView) findViewById(R.id.listView);
             listView.setAdapter(arrayAdapter);

             for (int i=0; i<feedbacks.size(); i++) {
                 System.out.println( i + "> " + feedbacks.get(i) );
             }

             Toast.makeText(Show.this,retStr, Toast.LENGTH_LONG).show();

         }

         private ArrayList<Feedback> jsonToArrayList_feedbacks (String jsonStr_feedbacks) {
             ArrayList<Feedback> feedbackArrayList = new ArrayList<Feedback>();

             try {
                 System.out.println("//////////////////////////////////////////////////////////");
                 System.out.println(jsonStr_feedbacks);
                 JSONObject root = new JSONObject(jsonStr_feedbacks);

                 JSONArray jsonArray = root.getJSONArray("data");

                 for (int i = 0; i < jsonArray.length(); i++) {
                     JSONObject jsonObject = jsonArray.getJSONObject(i);


                     String topic = jsonObject.getString("topic");
                     String content = jsonObject.getString("content");

                     Feedback feedback = new Feedback(topic,content);

                     feedbackArrayList.add(feedback);
                 }
             } catch (JSONException e) {
                 e.printStackTrace();

                 return null;
             }
             return feedbackArrayList;
         }

     }
 }
