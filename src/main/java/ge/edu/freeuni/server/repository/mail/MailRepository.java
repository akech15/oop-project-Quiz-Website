package ge.edu.freeuni.server.repository.mail;

import ge.edu.freeuni.server.model.mail.MailEntity;
import ge.edu.freeuni.server.model.user.UserEntity;

import java.util.List;

public interface MailRepository {

    MailEntity getMailById(long id);

    void addMail(MailEntity mail);

    void removeMail(MailEntity mail);

    List<MailEntity> searchMailsByReceiver(UserEntity receiver);

    List<MailEntity> searchMailsBySender(UserEntity sender);

}
