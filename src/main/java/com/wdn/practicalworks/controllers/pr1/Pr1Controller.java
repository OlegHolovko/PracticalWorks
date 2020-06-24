package com.wdn.practicalworks.controllers.pr1;

import com.wdn.practicalworks.models.pr1.XmlDOMParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class Pr1Controller extends ServiceController {
    @GetMapping("/pr1/upload")
    public String upload(Model model) {
        model.addAttribute("hasParent", "1");
        model.addAttribute("activeMenu", "pr1_upload");
        return "pr1/upload";
    }

    @GetMapping("/pr1/processing")
    public String processing(Model model) {
        final File folder = new File(UPLOADED_FOLDER);
        model.addAttribute("files", listFilesForFolder(folder));
        model.addAttribute("hasParent", "1");
        model.addAttribute("activeMenu", "pr1_processing");
        return "pr1/processing";
    }

    @RequestMapping(value = "/pr1/delete/{filename}", method = { RequestMethod.GET })
    public String deleteFile(@PathVariable("filename") String filename, Model model) {
        File file = new File(UPLOADED_FOLDER + filename);
        if (file.exists()){
            file.delete();
        }
        File file2 = new File(UPLOADED_FOLDER + PROCESSED + filename);
        if (file2.exists()){
            file2.delete();
        }
        final File folder = new File(UPLOADED_FOLDER);
        model.addAttribute("success_msg", "Вы удалили файл " + filename);
        model.addAttribute("files", listFilesForFolder(folder));
        model.addAttribute("hasParent", "1");
        model.addAttribute("activeMenu", "pr1_processing");
        return "pr1/processing";
    }

    @RequestMapping(value = "/pr1/delete-result/{filename}", method = { RequestMethod.GET })
    public String deleteResult(@PathVariable("filename") String filename, Model model) {
        File file = new File(UPLOADED_FOLDER + PROCESSED + filename);
        if (file.exists()){
            file.delete();
        }
        final File folder = new File(UPLOADED_FOLDER);
        model.addAttribute("success_msg", "Вы удалили обработанный файл " + "processed-" + filename);
        model.addAttribute("files", listFilesForFolder(folder));
        model.addAttribute("hasParent", "1");
        model.addAttribute("activeMenu", "pr1_processing");
        return "pr1/processing";
    }

    @RequestMapping(value="/upload", method= RequestMethod.POST)
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model){
        if (!file.isEmpty()) {
            if(!file.getContentType().equals("text/xml")){
                model.addAttribute("error_msg", "Вы пытаетесь загрузить не XML-файл!");
                model.addAttribute("hasParent", "1");
                model.addAttribute("activeMenu", "pr1_upload");
                return "pr1/upload";
            }
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(UPLOADED_FOLDER + file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();

                final File folder = new File(UPLOADED_FOLDER);
                model.addAttribute("files", listFilesForFolder(folder));
                model.addAttribute("success_msg", "Вы успешно загрузили " + file.getOriginalFilename());
                model.addAttribute("hasParent", "1");
                model.addAttribute("activeMenu", "pr1_processing");
                return "pr1/processing";

            } catch (Exception e) {
                model.addAttribute("error_msg", "Не удалось загрузить файл" + e.getMessage());
                model.addAttribute("hasParent", "1");
                model.addAttribute("activeMenu", "pr1_upload");
                return "pr1/upload";
            }
        } else {
            model.addAttribute("error_msg", "Не удалось загрузить файл: он пустой");
            model.addAttribute("hasParent", "1");
            model.addAttribute("activeMenu", "pr1_upload");
            return "pr1/upload";
        }
    }

    @RequestMapping(value = "/pr1/parse-dom/{filename}", method = { RequestMethod.GET })
    public String parseDOM(@PathVariable("filename") String filename, Model model) throws IOException, ParserConfigurationException {
        try {
            XmlDOMParser.validate(UPLOADED_FOLDER + filename);
            try {
                XmlDOMParser.create(UPLOADED_FOLDER + PROCESSED + filename, XmlDOMParser.parse(UPLOADED_FOLDER + filename));
                model.addAttribute("success_msg", "Файл " + filename + " успешно обработан.");
            } catch (JAXBException e){
                model.addAttribute("error_msg", "Ошибка записи файла: " + e.getMessage());
            }
        } catch (ParserConfigurationException | IOException | SAXException e){
            model.addAttribute("error_msg", "Ошибка валидации файла: " + e.getMessage());
        }

        final File folder = new File(UPLOADED_FOLDER);
        model.addAttribute("files", listFilesForFolder(folder));
        model.addAttribute("hasParent", "1");
        model.addAttribute("activeMenu", "pr1_processing");
        return "pr1/processing";
    }

}
