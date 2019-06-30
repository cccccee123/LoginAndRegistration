package com.androiddeft.loginandregistration;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Handler;

public class Getpost extends AppCompatActivity {

    private EditText topic, content;
    private Button post,view,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getpost);

        post = findViewById(R.id.post);


        topic = findViewById(R.id.topic);
        content = findViewById(R.id.content);
        view=findViewById(R.id.view);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Getpost.this, DashboardActivity.class);
                startActivity(i);
                finish();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData();

                Intent intent = new Intent(Getpost.this, Show.class);
                startActivity(intent);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Getpost.this, Show.class);
                startActivity(intent);
            }
        });
    }



    private void postData() {
        String postParams =
                "topic=" + topic.getText().toString() + "&" +
                        "content=" + content.getText().toString();

        System.out.println(postParams);

        String urlString = "http://192.168.130.2/feedback/add.php";

        MyAsynTask newTask = new MyAsynTask();
        newTask.execute(urlString, postParams);
    }

    private class MyAsynTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String retStr = postHttpURLConnection(params[0], params[1]);
            return retStr;
        }

        @Override
        protected void onPostExecute(String retStr) {
            super.onPostExecute(retStr);

            System.out.println("====================================");
            System.out.println(retStr);


            Toast.makeText(getApplicationContext(), retStr, Toast.LENGTH_LONG).show();
        }

        private String postHttpURLConnection(String urlStr, String postParams) {

            String response = "";

            InputStream inputStream = null;
            HttpURLConnection urlConnection = null;

            try {
                URL url = new URL(urlStr);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.connect();

                OutputStream outputStream = urlConnection.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeBytes(postParams);

                dataOutputStream.flush();
                dataOutputStream.close();

                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader bufferedReader =
                            new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }

                } else {
                    response = "";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response;
        }


    }
}
