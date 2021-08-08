import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.*;

public class App {

 public static void main(String[] args) throws IOException  {
    App app = new App();
    ArrayList list = app.GetData("https://jsonplaceholder.typicode.com/todos/");
  

    // Get All Data
    System.out.println("");
    System.out.println(list);

    // get data by index
    System.out.println("");
    System.out.println(list.get(9));

    // get value from key by index
    System.out.println("");
    JSONObject result = new JSONObject(list.get(9).toString());
    System.out.println("TITLE :"+result.get("title"));

    
 }

// Return BufferedReader
 ArrayList GetData(String url) throws IOException {
    HttpClient client = new DefaultHttpClient();
    HttpGet request = new HttpGet(url);
    HttpResponse response = client.execute(request);
    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
    String line="";
    String lines="";
    while ((line=rd.readLine()) != null) {
      lines+=line;
    }
    rd.close();
    JSONArray payload = new JSONArray(lines);
    ArrayList<String> list = new ArrayList<String>();     
    if (payload != null) { 
      for (int i=0;i<payload.length();i++){ 
        list.add(payload.get(i).toString());
      } 
    } 
   return list;
 }


}