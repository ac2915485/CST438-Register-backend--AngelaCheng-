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
public class EndToEndScheduleTest {

	public static final String CHROME_DRIVER_FILE_LOCATION = "C:/chromedriver_win32/chromedriver.exe";

	public static final String URL = "http://localhost:3000";

	public static final String TEST_USER_NAME = "Test";
	
	public static final String TEST_USER_EMAIL = "test@csumb.edu";
	
	public static final int SLEEP_DURATION = 1000; // 1 second.
	
	
}
