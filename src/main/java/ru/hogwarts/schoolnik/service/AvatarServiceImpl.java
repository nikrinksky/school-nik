package ru.hogwarts.schoolnik.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.schoolnik.model.Avatar;
import ru.hogwarts.schoolnik.model.Student;
import ru.hogwarts.schoolnik.repository.AvatarRepository;
import ru.hogwarts.schoolnik.repository.StudentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
@Service
public class AvatarServiceImpl implements AvatarService{
    private final static Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    private final String avatarsDir;

    public AvatarServiceImpl(AvatarRepository avatarRepository,
                             StudentRepository studentRepository,
                             @Value("${path.to.avatars.folder}") String avatarsDir) {
        this.avatarRepository = avatarRepository;
        this.avatarsDir = avatarsDir;
        this.studentRepository = studentRepository;
    }
    @Override
    public ResponseEntity<String> uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException{
        logger.info("вызван метод uploadAvatar");
        Student student = studentRepository.findById(studentId).get();
        Path filePath = Path.of(avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
        //Path filePath = Path.of(new File("").getAbsolutePath() + avatarsDir, student + "." + getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = new Avatar();
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
        return ResponseEntity.ok().build();
    }

    @Override
    public String getExtensions(String fileName) {
        logger.info("вызван метод getExtensions");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    @Override
    public void downloadAvatar(Long id, HttpServletResponse response) throws IOException {
        logger.info("вызван метод downloadAvatar");
        Avatar avatar = avatarRepository.findById(id).get();
        Path path = Path.of(avatar.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }
    @Override
    public ResponseEntity<byte[]> downloadFromDb(Long id) {
        logger.info("вызван метод downloadFromDb");
        Avatar avatar = avatarRepository.findById(id).get();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    ////////////////////////////////
    @Override
    public List<Avatar> getAvatars(int page, int size) {
        logger.info("вызван метод getAvatars");
        Pageable pageable = PageRequest.of(page, size);
        return avatarRepository.findAll(pageable).getContent();
    }




}
