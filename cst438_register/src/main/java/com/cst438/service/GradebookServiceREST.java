package com.cst438.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.cst438.domain.EnrollmentDTO;


public class GradebookServiceREST extends GradebookService {

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${gradebook.url}")
	String gradebook_url;
	
	public GradebookServiceREST() {
		System.out.println("REST grade book service");
	}

	@Override
	public void enrollStudent(String student_email, String student_name, int course_id) {
		
		//TODO  complete this method in homeworks 4
		
		// When a student add a class, send POST message to Gradebook backend using EnrollmentDTO.
		
		EnrollmentDTO enrollment = new EnrollmentDTO();
		enrollment.course_id = course_id;
		enrollment.studentEmail=student_email;
		enrollment.studentEmail=student_name;

		// Use the postForObject  method on the restTemplate.
		// restTemplate.postForObject( URL, Object,  EnrollmentDTO.class);
		System.out.println("Post to gradebook... " + enrollment);
		EnrollmentDTO response = restTemplate.postForObject(gradebook_url + "/enrollment", enrollment, EnrollmentDTO.class);
		System.out.println("Response from gradebook... " + response);
		
	}

}
