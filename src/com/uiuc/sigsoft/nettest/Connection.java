package com.uiuc.sigsoft.nettest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.DataOutputStream;

public class Connection {

    private final String USER_AGENT = "SigSoftBot/1.0"; // Identify ourselves to websites
    private URL url;
    private HttpURLConnection connection;
    public String responseBody = "";
    public int responseStatus;

    public Connection (String url) {
        try {
            this.url = new URL(url);
            this.connection = (HttpURLConnection) this.url.openConnection(); // We want to specifically deal with an HTTP connection
            connection.setRequestProperty("User-Agent", USER_AGENT); // Set our User-Agent header (google it if you want to learn more)
        } catch (MalformedURLException urlEx) {
            System.err.println("The entered URL was invalid.");
            System.err.println(urlEx.toString());
        } catch (IOException ioEx) {
            System.err.println("Error initializing connection to server.");
            System.err.println(ioEx.toString());
        }
    }

    public void get() {
        try {
            connection.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseLine;

            do {
                responseLine = br.readLine();
                if (responseLine != null) {
                    responseBody += responseLine+"\n";
                }
            } while (responseLine != null);
            br.close();

            //responseBody = response.toString();
            responseStatus = connection.getResponseCode();
        } catch (IOException ioEx) {
            System.err.println("IO error communicating with server.");
        }
    }


    public void post(long data)
    {

        try{
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
            wr.writeChars ("data=0");
            wr.flush ();
            wr.close ();
        }

        catch (IOException ioEx) {
            System.err.println("IO error communicating with server.");
            System.err.println(ioEx.toString());
        }

        try {
            responseStatus = connection.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String responseLine;

            do {
                responseLine = br.readLine();
                if (responseLine != null) {
                    responseBody += responseLine+"\n";
                }
            } while (responseLine != null);
            br.close();

            //responseBody = response.toString();

        } catch (IOException ioEx) {
            System.err.println("IO error communicating with server.");
            System.err.println(ioEx.toString());
        }
        System.out.println(responseBody);
    }
}