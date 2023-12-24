package ru.hogwarts.schoolnik.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.schoolnik.model.Student;
import ru.hogwarts.schoolnik.repository.StudentRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final  Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(String name, Integer age) {
        logger.info("вызван метод Add");
        Student newStudent = new Student(name, age);
        newStudent = studentRepository.save(newStudent);
        return newStudent;
    }

    @Override
    public Student getStudent(long id) {
        logger.info("вызван метод getStudent");
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(long id, String name, Integer age) {
        logger.info("вызван метод updateStudent");
        Student studentForUpdate = getStudent(id);
        studentForUpdate.setName(name);
        studentForUpdate.setAge(age);
        return studentRepository.save(studentForUpdate);
    }

    @Override
    public Student removeStudent(long id) {
        logger.info("вызван метод removeStudent");
        Student studentForDelete = getStudent(id);
        studentRepository.deleteById(id);
        return studentForDelete;
    }

    @Override
    public List<Student> getWhenAgeBetween(Integer min, Integer max) {
        logger.info("вызван метод getWhenAgeBetween");
        return studentRepository.findAllByAgeBetween(min, max);
    }

    @Override
    public Collection<Student> fidStudentsByFaculty(long facultyId) {
        logger.info("вызван метод fidStudentsByFaculty");
        return studentRepository.findAllByFaculty_Id(facultyId);
    }
////////////////////////////////////
    @Override
    public int getCount() {
        logger.info("вызван метод getCount");
        return studentRepository.getCount();
    }

    @Override
    public double getAvgAge() {
        logger.info("вызван метод getAvgAge");
        return studentRepository.getAvgAge();
    }

    @Override
    public  List<Student> getLastFive() {
        logger.info("вызван метод getLastFive");
        return studentRepository.getLastFiveOrderByIdDesc();

    }

    ///////////////// Stream API
    @Override
    public List<String> getAllNamesStartWithA(){
        String firstSymbol = "A";
        return studentRepository.findAll().stream()
                .map(Student::getName)
                .map(String::toUpperCase)
                .filter(name -> name.startsWith(firstSymbol))
                .sorted()
                .collect(Collectors.toList());
    }
    @Override
    public double getAvgAgeWithStream() {
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .orElse(-1);
    }
    ////////////////////////////// Threads
    @Override
    public void printStudents() {
        List<Student> students = studentRepository.findAll();

        printStudent(students.get(0));
        printStudent(students.get(1));

        Thread thread1 = new Thread(() -> {
            printStudent(students.get(2));
            printStudent(students.get(3));
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            printStudent(students.get(4));
            printStudent(students.get(5));
        });
        thread2.start();
    }
    @Override
    public void printStudentsSync() {
        List<Student> students = studentRepository.findAll();

        printStudentSync(students.get(0));
        printStudentSync(students.get(1));

        Thread thread1 = new Thread(() -> {
            printStudentSync(students.get(2));
            printStudentSync(students.get(3));
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            printStudentSync(students.get(4));
            printStudentSync(students.get(5));
        });
        thread2.start();

    }

    private void printStudent(Student student) {
        logger.info("Thread: {}. Student: {}", Thread.currentThread(), student);
    }

    private synchronized void printStudentSync(Student student) {
        printStudent(student);
    }
}
