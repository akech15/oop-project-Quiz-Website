package ge.edu.freeuni.api.converter.mail;

import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.mail.Mail;
import ge.edu.freeuni.server.model.mail.MailEntity;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public final class MailConverter {

    public static Mail entityToMail(MailEntity from, UserRepositoryImpl userRepository) {
        return Mail.builder().
                id(from.getId())
                .context(from.getContext())
                .sender(UserConverter.entityToUser(userRepository.getUserById(from.getSenderId())))
                .receiver(UserConverter.entityToUser(userRepository.getUserById(from.getReceiverId())))
                .sent_date(from.getSent_date())
                .build();
    }

    public static MailEntity mailToEntity(Mail mail, UserRepositoryImpl userRepository) {
        return MailEntity.builder()
                .id(mail.getId())
                .context(mail.getContext())
                .senderId(mail.getSender().getId())
                .receiverId(mail.getReceiver().getId())
                .sent_date(mail.getSent_date())
                .build();
    }

    public static List<Mail> entityToMailList(UserRepositoryImpl user,
                                              List<MailEntity> list) {

        List<Mail> res = new ArrayList<>();
        for (MailEntity mailEntity :
                list) {
            res.add(MailConverter.entityToMail(mailEntity, user));
        }
        return res;

    }

}
