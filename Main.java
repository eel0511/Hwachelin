package vacationalgo;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("	"); /*파싱URL*/
        String service_key = "WBBFtMijb5OGpPE7TFNxZYLTwj%2Bc%2BdoXfjxXf7GImXSVq%2Fd0oPtZEgegKnNSFVg56JjREXbo7hKf4bYxgdMAKA%3D%3D";
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+service_key);
        urlBuilder.append("&" + URLEncoder.encode("idx","UTF-8") + "=" + URLEncoder.encode("176", "UTF-8")); /*주차장의 상세 정보 조회를 위한 주차장 고유값*/
        URL url = new URL(urlBuilder.toString());
        System.out.println(url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        
        rd.close();
        conn.disconnect();
        //이름
        String str = sb.toString();
        String target = "<parkingLot_name>";
        int target_num = str.indexOf(target); 
        String result = sb.substring(target_num,(str.substring(target_num).indexOf("</parkingLot_name>")+target_num));
        System.out.println(result); 
        //주소
        String str2 = sb.toString();
        String target2 = "<addr>";
        int target_num2 = str2.indexOf(target2); 
        String result2 = sb.substring(target_num2,(str2.substring(target_num2).indexOf("</addr>")+target_num2));
        System.out.println(result2); 
        System.out.println(sb.toString());
    }
}