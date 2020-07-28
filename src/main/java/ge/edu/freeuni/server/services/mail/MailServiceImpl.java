package ge.edu.freeuni.server.services.mail;

import ge.edu.freeuni.api.converter.mail.MailConverter;
import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.mail.Mail;
import ge.edu.freeuni.api.model.user.User;
import ge.edu.freeuni.server.repository.mail.MailRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MailServiceImpl implements MailService {

    @Autowired
    private MailRepositoryImpl mailRepository;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Override
    public void sendMail(Mail mail) {
        mailRepository.addMail(MailConverter.mailToEntity(mail, userRepository));
    }

    @Override
    public void removeMail(Mail mail) {
        mailRepository.removeMail(MailConverter.mailToEntity(mail, userRepository));
    }

    @Override
    public List<Mail> searchMailsByReceiver(User receiver) {
        return MailConverter.
                entityToMailList(userRepository, mailRepository.
                                searchMailsByReceiver(UserConverter.
                                                      userToEntity(receiver)));
    }

    @Override
    public List<Mail> searchMailsBySender(User sender) {
        return MailConverter.
                entityToMailList(userRepository, mailRepository.
                        searchMailsBySender(UserConverter.
                                userToEntity(sender)));
    }
}
