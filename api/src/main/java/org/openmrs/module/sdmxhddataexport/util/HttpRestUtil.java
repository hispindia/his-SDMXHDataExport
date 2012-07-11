package org.openmrs.module.sdmxhddataexport.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * This is a http Post mehod using REST 
 * Function call has to be made with the URL of the address to send the post request along with the key and values. 
 * ParamName is the key and the 
 * @author apurv
 *
 */
public class HttpRestUtil {

/*	
	public static void main(String[] args) throws Exception{
		String[] key = new String[2];
		key[0] = "report101"; key[1] = "report202";
		String[] value = {"xml-----xml","xml2------xml"};
		
		String response = httpPost("http://httpbin.org/post", key , value);
		System.out.println(response);
//		httpPost2();
	}
	
*/	
	public static String httpRestPost(String urlStr, String[] paramName, String[] paramVal) throws Exception {
		
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setAllowUserInteraction(false);
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

			  // Create the form content
		OutputStream out = conn.getOutputStream();
		Writer writer = new OutputStreamWriter(out, "UTF-8");
		for (int i = 0; i < paramName.length; i++) {
			writer.write(paramName[i]);
			writer.write("=");
			writer.write(URLEncoder.encode(paramVal[i], "UTF-8"));
			writer.write("&");
		}
		writer.close();
		out.close();

		if (conn.getResponseCode() != 200) {
			throw new IOException(conn.getResponseMessage());
		}

		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();

		conn.disconnect();
		return sb.toString();
	}

}
