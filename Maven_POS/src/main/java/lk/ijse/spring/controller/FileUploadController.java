package lk.ijse.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@RestController
@RequestMapping({"api/v1/upload"})
@CrossOrigin
public class FileUploadController {

    private static final ArrayList<String> allImages = new ArrayList();

    public FileUploadController() {
    }

    @PutMapping(
            consumes = {"multipart/form-data"},
            produces = {"application/json"}
    )
    public ResponseEntity uploadFile(@RequestPart("myFile") MultipartFile myFile, @RequestPart("myFile") byte[] isFile, @RequestPart("myFile") Part myPart) {
        System.out.println(isFile);
        System.out.println(myPart.getSubmittedFileName());
        System.out.println("================================");
        System.out.println(myFile.getOriginalFilename());
        System.out.println(myPart.getSubmittedFileName());

        try {
            String projectPath = (new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI())).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            uploadsDir.mkdir();
            myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));
            return new ResponseEntity("Successfully Uploaded", HttpStatus.OK);
        } catch (URISyntaxException var6) {
            var6.printStackTrace();
            return new ResponseEntity(var6.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException var7) {
            var7.printStackTrace();
            return new ResponseEntity(var7.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(
            consumes = {"multipart/form-data"},
            produces = {"application/json"}
    )
    public ResponseEntity uploadFileWithSpringWay(@RequestPart("myFile") MultipartFile myFile) {
        try {
            String projectPath = (new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI())).getParentFile().getParentFile().getAbsolutePath();
            File uploadsDir = new File(projectPath + "/uploads");
            System.out.println(projectPath);
            uploadsDir.mkdir();
            myFile.transferTo(new File(uploadsDir.getAbsolutePath() + "/" + myFile.getOriginalFilename()));
            allImages.add("uploads/" + myFile.getOriginalFilename());
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (URISyntaxException var4) {
            var4.printStackTrace();
            return new ResponseEntity(var4.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException var5) {
            var5.printStackTrace();
            return new ResponseEntity(var5.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(
            produces = {"application/json"}
    )
    public ResponseEntity getAllImagesFromDatabase() {
        return new ResponseEntity(allImages, HttpStatus.OK);
    }

}
