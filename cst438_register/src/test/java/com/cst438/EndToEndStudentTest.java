package com.cst438;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cst438.domain.Course;
import com.cst438.domain.CourseRepository;
import com.cst438.domain.Enrollment;
import com.cst438.domain.EnrollmentRepository;
import com.cst438.domain.Student;
import com.cst438.domain.StudentRepository;

/*
 * Create a new Java class under the src/test directory and code an end-to-end test for: 
 * (registration service) as an Admin I can add a new student to the registration service. 
 * 
 * Code walkthrough of EndToEndxxxxxxxxxTest.java 
 * 
 * - We use @SpringBootTest and Junit @Test to write the test code.
 * - The @SpringBootTest annotation allows us to use Spring repositories to interact with the database from the test environment.   We are not using mocks, we are using the real database.
 * - The actual testing consists of code to locate an element (either a button or an input field) in the browser's DOM and clicking on the button or entering text into the input field. 
 * - Then verify using assert statements that elements in the DOM exist and have the expected text. 
 * - There are multiple way to locate a DOM element, by tag name, by id, by CSS class name and by XPATH. 
 * - If you are not familiar with XPATH, you may want to read this tutorial about XPATH  https://www.w3schools.com/xml/xpath_intro.asp
 */

@SpringBootTest
public class EndToEndStudentTest {

	public static final String CHROME_DRIVER_FILE_LOCATION = "C:/chromedriver_win32/chromedriver.exe";

	public static final String URL = "http://localhost:3000";

	public static final String TEST_USER_NAME = "Test";
	
	public static final String TEST_USER_EMAIL = "test@csumb.edu";
	
	public static final String TEST_USER_STATUS = "No Hold";
	
	public static final int TEST_USER_STATUS_CODE = 0;
	
	public static final int SLEEP_DURATION = 1000; // 1 second.
	
	@Autowired
	StudentRepository studentRepository;
	
	@Test
	public void addStudentTest() throws Exception {
		
		// delete student if already added 
		Student x = null;
		do {
			x = studentRepository.findByEmail(TEST_USER_EMAIL);
			if (x != null)
				studentRepository.delete(x);
		} while (x != null);
		
		System.setProperty("webdriver.chrome.driver",CHROME_DRIVER_FILE_LOCATION);
		
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--remote-allow-origins=*");	 
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			driver.get(URL);
			Thread.sleep(SLEEP_DURATION);
			
			WebElement we = driver.findElement(By.xpath("//button[@id='addNewStudentButton']"));
			we.click();

			Thread.sleep(SLEEP_DURATION);

			driver.findElement(By.xpath("//input[@name='name']")).sendKeys(TEST_USER_NAME);
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(TEST_USER_EMAIL);
			driver.findElement(By.xpath("//button[@id='AddS']")).click(); 
			Thread.sleep(SLEEP_DURATION);

			Student s = studentRepository.findByEmail(TEST_USER_EMAIL);
			assertNotNull(s, "Student not found in database.");
			assertNotNull(s.getStudent_id(), "Student ID not given");
			assertEquals(s.getName(), TEST_USER_NAME);
			assertEquals(s.getEmail(), TEST_USER_EMAIL);
			assertEquals(s.getStatus(), TEST_USER_STATUS);
			assertEquals(s.getStatusCode(), TEST_USER_STATUS_CODE);
			
		} catch (Exception ex) {
			throw ex;
			
		} finally {
			// clean up 
			Student s = studentRepository.findByEmail(TEST_USER_EMAIL);
			if (s != null)
				studentRepository.delete(s);
			
			driver.close();
			driver.quit();
			
		}
	}
}
