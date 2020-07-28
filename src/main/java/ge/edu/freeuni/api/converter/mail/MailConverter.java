package ge.edu.freeuni.api.converter.mail;

import ge.edu.freeuni.api.converter.answer.AnswerConverter;
import ge.edu.freeuni.api.converter.user.UserConverter;
import ge.edu.freeuni.api.model.answer.Answer;
import ge.edu.freeuni.api.model.mail.Mail;
import ge.edu.freeuni.server.model.answer.AnswerEntity;
import ge.edu.freeuni.server.model.mail.MailEntity;
import ge.edu.freeuni.server.repository.passedQuiz.PassedQuizRepositoryImpl;
import ge.edu.freeuni.server.repository.question.QuestionRepositoryImpl;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import ge.edu.freeuni.server.repository.user.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public final class MailConverter {

    public static Mail entityToMail(MailEntity from, UserRepositoryImpl userRepository) {
        return Mail.builder().
                id(from.getId()).
                context(from.getContext()).
                sender(UserConverter.entityToUser(userRepository.getUserById(from.getSenderId()))).
                receiver(UserConverter.entityToUser(userRepository.getUserById(from.getReceiverId()))).
                build();
    }

    public static MailEntity mailToEntity(Mail from, UserRepositoryImpl userRepository) {
        return MailEntity.builder().
                id(from.getId()).
                context(from.getContext()).
                senderId(userRepository.getIdByUsername(from.getSender().getUsername())).
                receiverId(userRepository.getIdByUsername(from.getReceiver().getUsername())).
                build();
    }

    public static List<Mail> entityToMailList(UserRepositoryImpl user,
                                              List<MailEntity> list){

        List<Mail> res = new ArrayList<>();
        for (MailEntity mailEntity:
                list) {
            res.add(MailConverter.entityToMail(mailEntity, user));
        }
        return res;

    }

}
