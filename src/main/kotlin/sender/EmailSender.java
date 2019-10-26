package sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private MailSender mailSender;

    public void sendEmail(String title, String body){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("yurko.zavada@gmail.com");
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setFrom("mumuksiatemp@gmail.com");
        simpleMailMessage.setText(body);


        mailSender.send(simpleMailMessage);
    }
}
