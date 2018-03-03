package com.ryzen.ryan.carbonzero;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;

public class LandingPage extends AppCompatActivity {

    TextView quoteText;
    RelativeLayout landingLayout;
    final String quoteAPI = "http://api.forismatic.com/api/1.0/?method=getQuote&format=text&lang=en";
    final String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/start";

    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        new getAPI().execute(quoteAPI);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        quoteText = findViewById(R.id.quoteText);
        landingLayout = findViewById(R.id.landingLayout);

        File dir = new File(path);
        dir.mkdir();

        landingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                file = new File(path+"/prelim_data.txt");
                if(file.isFile()) {
                    initialFile(file);
                }
                String [] data = Load(file);
                if (data[0].equals("false")){
                    Toast.makeText(getApplicationContext(), "It is false!", Toast.LENGTH_SHORT).show();
                    Intent questionIntent = new Intent(LandingPage.this, QuestionsActivity.class);
                    startActivity(questionIntent);
                }else{
                    Intent earthIntent = new Intent(LandingPage.this, EarthActivity.class);
                    startActivity(earthIntent);
                }
//                try{
//                    Intent questionIntent = new Intent(LandingPage.this, QuestionsActivity.class);
//                    startActivity(questionIntent);
//                }catch(Exception e){
//                    Intent earthIntent = new Intent(LandingPage.this, EarthActivity.class);
//                    startActivity(earthIntent);
//                }
            }
        });
    }

    private void initialFile(File file) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("prelim_data.txt", Context.MODE_PRIVATE);;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            try {
                fos.write("false".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private String[] Load(File file){
        FileInputStream fis = null;
        try{
            fis = openFileInput("prelim_data.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String test;
        int count = 0;

        try{
            while((test=br.readLine()) != null){
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fis.getChannel().position(0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] array = new String[count];
        String line;
        int i = 0;
        try{
            while((line = br.readLine()) != null){
                array[i] = line;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return array;
    }


    private final String USER_AGENT = "Mozilla/5.0";
    class getAPI extends AsyncTask<String, Void, String> {

        private Exception exception;

        @Override
        protected String doInBackground(String... url) {

            try {
                URL obj = new URL(url[0]);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //add request header
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                // Send post request
                con.setDoOutput(true);

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Response Code : " + responseCode);

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                return response.toString();

            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(String response) {

            if (exception != null)
                quoteText.setText(exception.toString());
            else
                quoteText.setText(response.toString());
        }
    }
}
