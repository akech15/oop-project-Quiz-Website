package ge.edu.freeuni;


import ge.edu.freeuni.server.model.quiz.QuizEntity;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class QuizTest {


    @Autowired
    private QuizRepositoryImpl quizRepository;


    @Test
    public void addQuiz() {

        QuizEntity entity = new QuizEntity();
        entity.setName("starting quiz");
        entity.setDescription("description");
        entity.setCreatorId(1);
        entity.setCreationDate(new Date());
        Assertions.assertTrue(quizRepository.addQuiz(entity));
    }

//    @Test
//    public void getQuizById() {
//        QuizEntity entity = quizRepository.getQuizByname();
//        Assertions.assertEquals(1, entity.getCreatorId());
//        Assertions.assertEquals("starting quiz", entity.getName());
//    }

    @Test
    public void getAllQuizName() {
        QuizEntity entity = new QuizEntity();
        entity.setName("starting quiz");
        entity.setDescription("description");
        entity.setCreatorId(1);
        entity.setCreationDate(new Date());
        quizRepository.addQuiz(entity);
        List<String> list = quizRepository.getAllQuizNames();
        Assertions.assertEquals(5, list.size());
        Assertions.assertEquals("starting quiz", list.get(0));
    }

}
