package ge.edu.freeuni.server.services.mail;

import ge.edu.freeuni.api.model.mail.Mail;
import ge.edu.freeuni.api.model.user.User;

import java.util.List;

public interface MailService {

    void sendMail(Mail mail);

    void removeMail(Mail mail);

    List<Mail> searchMailsByReceiver(User receiver);

    List<Mail> searchMailsBySender(User sender);

}
