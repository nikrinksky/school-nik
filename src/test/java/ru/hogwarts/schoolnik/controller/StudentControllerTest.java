package ru.hogwarts.schoolnik.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.schoolnik.model.Student;
import ru.hogwarts.schoolnik.repository.StudentRepository;
import ru.hogwarts.schoolnik.service.StudentServiceImpl;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = StudentController.class)
public class StudentControllerTest {
    private static final Student MOCK_STUDENT = new Student("Гарри Поттер", 18);
    private static final Object MOCK_STUDENT_NAME = "Гарри Поттер";
    private static final String MOCK_STUDENT_NEW_NAME = "Вася Пупкин";
    private static final int MOCK_STUDENT_AGE = 18;
    private static final long MOCK_STUDENT_ID = 1;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentRepository studentRepository;
    @SpyBean
    private StudentServiceImpl studentService;
    @InjectMocks
    private StudentController studentController;
    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void createStudent() throws Exception {
        when(studentRepository.save(ArgumentMatchers.any(Student.class))).thenReturn(MOCK_STUDENT);

        JSONObject createStudentRq = new JSONObject();
        createStudentRq.put("name", MOCK_STUDENT_NAME);
        createStudentRq.put("age", MOCK_STUDENT_AGE);




        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(createStudentRq.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_STUDENT_NAME))
                .andExpect(jsonPath("$.age").value(MOCK_STUDENT_AGE));
    }

    @Test
    public void getStudent() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(MOCK_STUDENT));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + MOCK_STUDENT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_STUDENT_NAME))
                .andExpect(jsonPath("$.age").value(MOCK_STUDENT_AGE));
    }

    @Test
    public void deleteStudent() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(MOCK_STUDENT));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + MOCK_STUDENT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateStudent() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(MOCK_STUDENT));

        MOCK_STUDENT.setName(MOCK_STUDENT_NEW_NAME);

        JSONObject updateStudentRq = new JSONObject();
        updateStudentRq.put("id", MOCK_STUDENT.getId());
        updateStudentRq.put("name", MOCK_STUDENT.getName());
        updateStudentRq.put("age", MOCK_STUDENT.getAge());

        when(studentRepository.save(any(Student.class))).thenReturn(MOCK_STUDENT);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(updateStudentRq.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_STUDENT.getName()))
                .andExpect(jsonPath("$.age").value(MOCK_STUDENT.getAge()));
    }


    @Test
    public void getStudentsBetweenAge() throws Exception {
        when(studentRepository.findAllByAgeBetween(anyInt(), anyInt())).thenReturn(Collections.singletonList(MOCK_STUDENT));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/filter?min=10&max=20")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(MOCK_STUDENT)));
    }

    @Test
    public void getFacultyByStudent() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(MOCK_STUDENT));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/faculty-by-id/" + MOCK_STUDENT_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
