package com.example.proxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
@RestController
public class ProxyController {
    private static int count = 0;
    private static final String USER_AGENT = "Mozilla/5.0";

    @GetMapping("/consultar")
    public static String main(String numero) throws IOException {
        String geturl;
        if(count%2== 0) {
            geturl = "http://localhost:8080/collatzsequence?" + numero;
            System.out.println("se fue al primero"+ count);
        }
        else{
            geturl = "http://localhost:8080/collatzsequence?" + numero;
            System.out.println("se fue al segundo"+ count);
        }
        URL obj = new URL(geturl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        count++;
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

                // print result
            System.out.println(response.toString());
            return response.toString();
        } else {
                System.out.println("GET request not worked");
                return "GET request not worked";
            }
    }


}
