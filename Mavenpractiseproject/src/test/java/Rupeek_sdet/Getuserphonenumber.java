package Rupeek_sdet;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Getuserphonenumber
{
	@Test
	public void Getphonenumbers()
	{   
	
		RestAssured.baseURI = "";

		
		RequestSpecification httpRequest = RestAssured.given();

	
		Response response = httpRequest.request(Method.GET, "/Customers");
		String contenttype=response.getHeader("Content-Type");
		System.out.println("content type value = "+contenttype);
		String serverType =  response.header("Server");
		 System.out.println("Server value: " + serverType);
		 String acceptLanguage = response.header("Content-Encoding");
		 System.out.println("Content-Encoding: " + acceptLanguage);

		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		Headers allheaders=response.headers();
		for(Header header:allheaders)
		{
			System.out.println("Key = "+header.getName()+", Value = "+header.getValue());
		}

}
}



