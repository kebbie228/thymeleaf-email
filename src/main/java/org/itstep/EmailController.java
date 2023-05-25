package org.itstep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailController {
@Autowired
    EmailService emailService;

@RequestMapping("/sendtext")
public String sendText(){
    Email email=new Email();
    email.from="anton.konovalchenko@gmail.com";
    email.to="anton.konovalchenko@gmail.com";
    email.subject="Тема";
    email.text="ifeskhravkooxgmv";
    emailService.sentTextEmail(email);
    return "success.html";
}
}
