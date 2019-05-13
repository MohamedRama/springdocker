package controllers;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/rest/security")
public class SecurityController {

	
	public static final String MODEL_SERVER_LOCATION = "http://localhost:9000";
	
	@PostMapping("/auth")
	public String getTokenAuth(@RequestBody LoginForm lf) {
		
//		/v1/rest/users/login
		String result = null;
		try {
			result = sendRequest(lf.getUsername(), lf.getPassword());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
	
	public static String sendRequest(String username, String password) throws JSONException, IOException {
		JSONObject json = new JSONObject();
		json.put("username", username);
		json.put("password", password);

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		try {
		    HttpPost request = new HttpPost(MODEL_SERVER_LOCATION+"/v1/rest/users/login");
		    StringEntity params = new StringEntity(json.toString());
		    request.addHeader("content-type", "application/json");
		    request.setEntity(params);
		    HttpResponse response = httpClient.execute(request);
		    HttpEntity entity = response.getEntity();
		    String serverAnswer = convertStreamToString(entity.getContent());
		    System.out.println("SERVER SAIS: " + serverAnswer);
		    return serverAnswer;
		// handle response here...
		} catch (Exception ex) {
		    // handle exception here
		} finally {
		    httpClient.close();
		}
		return null;
	}
}
