package ru.hogwarts.schoolnik.TestRestTemplate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import ru.hogwarts.schoolnik.controller.StudentController;
import ru.hogwarts.schoolnik.model.Student;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTestRestTemplate {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void contextLoads() throws Exception {
        Assertions.assertThat(studentController).isNotNull();
    }

    @Test
    public void testGetStudentById() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotNull();
    }

    @Test
    public void testPostStudent() throws Exception {
        Student student = new Student();
        student.setId(5L);
        student.setName("Гарри Поттер");
        student.setAge(18);

        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    public void testGetStudentByAgeBetween() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student" + "/By-Age-Between?min=" + 20 + "&max=" + 30, String.class))
                .isNotNull();
    }

    @Test
    public void testGetFacultyByStudentId() throws Exception {
        Assertions
                .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/student" + "/faculty-by-student-id", String.class))
                .isNotNull();
    }

    @Test
    public void testDeleteStudent() throws Exception {
        ResponseEntity<Void> resp = testRestTemplate.exchange("http://localhost:" + port + "/student", HttpMethod.DELETE, null, Void.class);
    }

    @Test
    public void testPutStudent() throws Exception {
        Student student = new Student();
        student.setId(5L);
        student.setName("Гарри Поттер");
        student.setAge(21);

        Assertions
                .assertThat(this.testRestTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }
}
