package org.itstep;
import org.itstep.Email;
import org.itstep.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
public class EmailController {
    @Autowired
    EmailService emailService;
    String generatedString;
    User user;

    @RequestMapping("/sendtext")
    public String sendText() {
        Email email = new Email();
        email.from = "anton.konovalchenko@gmail.com";
        email.to ="anton.konovalchenko@gmail.com";
        email.subject = "Тема";
        email.text = "Текст сообщения";
        emailService.sendTextEmail(email);
        return "success.html";
    }

    @RequestMapping("/sendhtml")
    public String sendHtml() {
        Email email = new Email();
        email.from = "anton.konovalchenko@gmail.com";
        email.to = "anton.konovalchenko@gmail.com";
        email.subject = "Тема";
        email.template = "template.html";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", "Имя");
        properties.put("subscriptionDate", LocalDate.now().toString());
        properties.put("technologies", Arrays.asList("Java", "Python", "JavaScript"));
        email.setProperties(properties);
        emailService.sendHtmlEmail(email);
        return "success.html";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        user = new User();
        model.addAttribute("user", user);
        return "signup.html";
    }

    @PostMapping("/register")
    public String register(User user) {

//System.out.println(user.getUsername());
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
         generatedString = new String(array, Charset.forName("UTF-8"));

        Email email = new Email();
        email.from = "anton.konovalchenko@gmail.com";

        email.to = "anton.konovalchenko@gmail.com";
        email.subject = "Регистрация";
        email.template = "template.html";
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", "Имя");
        properties.put("subscriptionDate", LocalDate.now().toString());
        properties.put("technologies", Arrays.asList("Java", "Python", "JavaScript"));
        properties.put("hash", generatedString);
        email.setProperties(properties);
        emailService.sendHtmlEmail(email);

        return "success.html";
    }

    @GetMapping("/enable")
    public String enable(Model model, @RequestParam(name="hash")String hash) {
        System.out.println(generatedString);
        System.out.println(hash);
        if (hash.equals(generatedString))
            System.out.println("success");

        return "success.html";
    }

} 