package Rupeek_sdet;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Getcustomer 
{
	@Test
	public void Getcustomersdetails()
	{
		RestAssured.baseURI="";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/Customers");
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		
	}

}
