package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Base64;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGetHC4;
import org.apache.http.client.methods.HttpPostHC4;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

class RetrieveFeedTask extends AsyncTask<String, Void,String> {

    private Exception exception;

    protected String doInBackground(String... urls) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPostHC4 request = new HttpPostHC4("https://www.dbs.com/sandbox/api/sg/v1/oauth/tokens");
            String result = null;
            String auth = "Basic " + urls[0];
            auth = auth.replaceAll("\n", "");
            request.addHeader("Authorization", auth);
            request.addHeader("Content-Type", "application/x-www-form-urlencoded");

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("code", urls[1]));
            urlParameters.add(new BasicNameValuePair("redirect_uri", urls[2]));
            urlParameters.add(new BasicNameValuePair("grant_type", "token"));

            request.setEntity(new UrlEncodedFormEntity(urlParameters));
            HttpResponse response = client.execute(request);
            String json_string = EntityUtils.toString(response.getEntity());
            JSONObject json = new JSONObject(json_string);
            String accessToken = json.getString("access_token");
            String clientID = urls[3];


            String encodedclientID = new String (Base64.encode((clientID).getBytes(),Base64.DEFAULT)).replaceAll("\n","");

String decoded = JWTUtils.decoded(accessToken);
JSONObject dec = new JSONObject(decoded);
String partyID = dec.getString("cin");
            return response.toString();


        }
        catch( Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    protected void onPostExecute() {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */ );
        urlConnection.setConnectTimeout(15000 /* milliseconds */ );
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }
}