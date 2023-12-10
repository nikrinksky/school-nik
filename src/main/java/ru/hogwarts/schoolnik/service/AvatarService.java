package ru.hogwarts.schoolnik.service;



import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.schoolnik.model.Avatar;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


public interface AvatarService {
    public ResponseEntity<String> uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException;

    String getExtensions(String fileName);

    void downloadAvatar(Long id, HttpServletResponse response) throws IOException;



    ResponseEntity<byte[]> downloadFromDb(Long id);

    ////////////////////////////////
    Collection<Avatar> find(int page, int pageSize);
}
