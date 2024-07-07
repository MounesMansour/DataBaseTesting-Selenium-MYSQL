import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class MyTestCases {

	Connection con;
	Statement stmt;
	ResultSet rs;
	String URL="https://magento.softwaretestingboard.com/customer/account/create/";
	WebDriver driver=new ChromeDriver();
	@BeforeTest
	public void setUp() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root", "1234");

	}
	@Test(priority = 1)
	public void addData() throws SQLException {
		stmt =con.createStatement();
		String Query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city,country )"
				+ "VALUES (77, 'Mounes', 'Mounes', 'Mansour', '123-456-7890', 'Amman', 'Amman' ,'jordan')";
		int insertedRow =stmt.executeUpdate(Query);
		Assert.assertEquals(insertedRow>0, true);
		 
	}
	
	@Test(priority = 2)
	public void updateData() throws SQLException {
		stmt=con.createStatement();
		String Query = "update customers set customerName = 'AutomationGroups' where customerNumber = 77 ";
		int insertedRow =stmt.executeUpdate(Query);
		Assert.assertEquals(insertedRow>0, true);
	}

	@Test(priority = 3)
	public void getData() throws SQLException {
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM customers where customerNumber=77");
		String customerName=null;
		while(rs.next()) {
			 customerName=rs.getString("customerName");
		}
		driver.get(URL);
		driver.findElement(By.id("firstname")).sendKeys(customerName);
	}

	@Test(priority = 4)
	public void deleteData() throws SQLException {
		stmt=con.createStatement();
		String Query = "delete from customers where customerNumber = 77";
		int insertedRow =stmt.executeUpdate(Query);
		Assert.assertEquals(insertedRow>0, true);
	}
}
