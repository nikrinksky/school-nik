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
import ru.hogwarts.schoolnik.model.Faculty;
import ru.hogwarts.schoolnik.repository.FacultyRepository;
import ru.hogwarts.schoolnik.service.FacultyServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerTest {
    private static final Faculty MOCK_FACULTY = new Faculty("Факультет","красный");
    private static final Object MOCK_FACULTY_NAME = "Факультет";
    private static final Object MOCK_FACULTY_COLOR = "красный";
    private static final long  MOCK_FACULTY_ID = 1;
    private static final String MOCK_FACULTY_NEW_NAME = "зеленый";
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyRepository facultyRepository;
    @SpyBean
    private FacultyServiceImpl facultyService;
    @InjectMocks
    private FacultyController facultyController;

    private ObjectMapper mapper = new ObjectMapper();

    FacultyControllerTest() {
    }

    @Test
    public void createFaculty() throws Exception {
        when(facultyRepository.save(ArgumentMatchers.any(Faculty.class))).thenReturn(MOCK_FACULTY);

        JSONObject createFacultyRq = new JSONObject();
        createFacultyRq.put("name", MOCK_FACULTY_NAME);
        createFacultyRq.put("color", MOCK_FACULTY_COLOR);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(createFacultyRq.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(MOCK_FACULTY_COLOR));
    }

    @Test
    public void getFaculty() throws Exception {
        when(facultyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + MOCK_FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_FACULTY_NAME))
                .andExpect(jsonPath("$.color").value(MOCK_FACULTY_COLOR));
    }

    @Test
    public void deleteFaculty() throws Exception {
        when(facultyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + MOCK_FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateFaculty() throws Exception {
        when(facultyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        MOCK_FACULTY.setName(MOCK_FACULTY_NEW_NAME);

        JSONObject updateFacultyRq = new JSONObject();
        updateFacultyRq.put("id", MOCK_FACULTY.getId());
        updateFacultyRq.put("name", MOCK_FACULTY.getName());
        updateFacultyRq.put("color", MOCK_FACULTY.getColor());

        when(facultyRepository.save(ArgumentMatchers.any(Faculty.class))).thenReturn(MOCK_FACULTY);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(updateFacultyRq.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_FACULTY.getName()))
                .andExpect(jsonPath("$.color").value(MOCK_FACULTY.getColor()));
    }

//    @Test
//    public void getFacultyByNameOrByColor() throws Exception {
//        when(facultyRepository.findAllByColorOrNameIgnoreCase (anyString(), anyString())).thenReturn((Collection<Faculty>) MOCK_FACULTY);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .get("/faculty/filter?name=" + MOCK_FACULTY_NAME + "&color=" + MOCK_FACULTY_COLOR)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(MOCK_FACULTY)));
//    }

    @Test
    public void getStudentsByFaculty() throws Exception {
        when(facultyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(MOCK_FACULTY));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/students/" + MOCK_FACULTY_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
