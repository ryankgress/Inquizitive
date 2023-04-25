package springexamples.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Slf4j
@Controller
public class FileUploadController {

    @GetMapping("/fileupload")
    public String fileUpload() {
        return "fileupload";
    }

//    @GetMapping("/fileupload")
//    public ModelAndView fileUpload() throws IOException {
//        ModelAndView response = new ModelAndView("fileupload");
//
//        return response;
//    }


    @PostMapping("/fileupload")
    public ModelAndView fileUpload(@RequestParam MultipartFile pic) throws IOException {
        ModelAndView response = new ModelAndView("fileupload");

        // Considers root path as Project folder (SpringBoot)
        File target = new File("./src/main/webapp/pub/images/" + pic.getOriginalFilename());
        log.debug(target.getAbsolutePath());

        // Copies the elements of the 'pic' into target file via inputstream. Uses the commons-io library
        FileUtils.copyInputStreamToFile(pic.getInputStream(), target);

        response.addObject("fileUrl", "/pub/images/" + pic.getOriginalFilename());

        return response;
    }
}
