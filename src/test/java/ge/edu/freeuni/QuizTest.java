package ge.edu.freeuni;


import ge.edu.freeuni.server.model.quiz.QuizEntity;
import ge.edu.freeuni.server.repository.quiz.QuizRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class QuizTest {


    @Autowired
    private QuizRepositoryImpl quizRepository;

    @AfterEach
    public void clearDBase(){
        quizRepository.clearDBase();
    }

    @Test
    public void addQuiz() {

        QuizEntity entity = new QuizEntity();
        entity.setName("starting quiz");
        entity.setDescription("description");
        entity.setCreatorId(1);
        entity.setCreationDate(new Date());
        Assertions.assertTrue(quizRepository.addQuiz(entity));
    }

    @Test
    public void getQuizById() {
        QuizEntity entity = quizRepository.getQuizById(1);
        Assertions.assertEquals(1, entity.getCreatorId());
        Assertions.assertEquals("starting quiz", entity.getName());
    }

    @Test
    public void getAllQuizName(){
        List<String> list = quizRepository.getAllQuizNames();

    }

}
