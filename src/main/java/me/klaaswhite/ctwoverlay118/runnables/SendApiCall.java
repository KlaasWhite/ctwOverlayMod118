package me.klaaswhite.ctwoverlay118.runnables;

import me.klaaswhite.ctwoverlay118.client.CtwOverlay118Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SendApiCall implements Runnable{

    String endPoint;
    String data;

    public SendApiCall(String endPoint, String data){
        this.endPoint = endPoint;
        this.data = data;
    }

    @Override
    public void run() {
        System.out.println("Send api call as runnable called");
        try {
            URL url = new URL(String.format(CtwOverlay118Client.url + "/api/mc/%s", endPoint));
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.setDoInput(true);
            try(OutputStream os = con.getOutputStream()) {
                byte[] input = data.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
//                System.out.println(response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
