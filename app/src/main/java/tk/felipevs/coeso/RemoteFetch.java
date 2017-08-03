package tk.felipevs.coeso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;
import android.provider.Settings;
//import android.util.Log;

/**
 * Created by felipe on 11/06/17.
 */

public class RemoteFetch {
    private static final String COESA_URL =
            "http://mapa.coesa.com/coesa.com/RESTAPI/";

    public static JSONObject getJSON(String route){
        try {
            URL url = new URL(String.format(COESA_URL, R.string.serverAPI, "/MobileActualState/Get?route=", route));
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();

            JSONObject data = new JSONObject(json.toString());

            System.out.println(String.format(COESA_URL, R.string.serverAPI, "/MobileActualState/Get?route=", route));
            System.out.println("DATA:" + data);
            // This value will be 404 if the request was not
            // successful
            if(data.getInt("cod") != 200){
                return null;
            }


            return data;
        }catch(Exception e){
            return null;
        }
    }
}
