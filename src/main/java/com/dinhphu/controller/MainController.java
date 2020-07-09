package com.dinhphu.controller;

import com.dinhphu.model.Student;
import com.dinhphu.model.StudentForm;
import com.dinhphu.services.student.IStudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
public class MainController {
    @Autowired
    private IStudentServices studentServices;

    @Autowired
    private Environment environment;

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView=new ModelAndView("index");
        modelAndView.addObject("students",studentServices.findAll());
        return modelAndView;
    }

    @GetMapping("/student/create")
    public String showCreateForm(Model theModel){
        theModel.addAttribute("studentForm",new StudentForm());
        return "create";
    }

    @PostMapping("/student/create")
    public ModelAndView createNewStudent(@ModelAttribute StudentForm studentForm){
        MultipartFile file=studentForm.getFile();
        String image=file.getOriginalFilename();

        Student student=new Student(studentForm.getName());
        student.setAvatar(image);

        String fileUpload= environment.getProperty("file_upload");

        try {
            FileCopyUtils.copy(file.getBytes(),new File(fileUpload+image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        studentServices.save(student);
        ModelAndView modelAndView=new ModelAndView("index");
        modelAndView.addObject("students",studentServices.findAll());
        return modelAndView;
    }
}
