//package com.aso.examplatform.service;
//
//import com.aso.examplatform.dto.ExamRequest;
//import com.aso.examplatform.model.Exam;
//import com.aso.examplatform.model.Question;
//import com.aso.examplatform.model.QuestionType;
//import com.aso.examplatform.repository.ExamRepository;
//import com.aso.examplatform.repository.ExamUserRepository;
//import com.aso.examplatform.repository.QuestionRepository;
//import com.aso.examplatform.repository.UserRepository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.api.function.Executable;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//@ExtendWith(MockitoExtension.class)
//class ExamServiceTest {
//
//    @Mock
//    private ExamService examService;
//    @Mock
//    private ExamUserRepository examUserRepository;
//    @Mock
//    private QuestionRepository questionRepository;
//    @Mock
//    private UserRepository userRepository;
//    @Mock
//    private ExamRepository examRepository;
//
//    ExamRequest examRequest;
//    @BeforeEach
//    void setUp(){
//        examService = new ExamService(examRepository,questionRepository,userRepository,examUserRepository);
//        List<Question> questionList = new ArrayList<>();
//        questionList.add(new Question(1L,"question test",1,true,"testpath",false,"test start","estst end",new QuestionType(1L,"MSQ"),null,null));
//        questionList.add(new Question(2L,"question test",2,true,"testpath",false,"test start","estst end",new QuestionType(1L,"MSQ"),null,null));
//        examRequest = new ExamRequest(questionList,
//        new Exam(1L,"testname","testdesc","test time","test duration",true,true,false,"test create","test create at",null));
//    }
//
//    @Test
//    void createTest() {
//        assertEquals(examRequest.getExam(),examService.create(examRequest));
//    }
//
//    @Test
//    void updateQuestionsTest() {
//        assertEquals(examRequest.getQuestions(),examService.updateQuestions(examRequest));
//        for (Question question:examService.updateQuestions(examRequest)) {
//            assertEquals(examRequest.getExam(),question.getExam());
//        }
//
//    }
//
//    @Test
//    void updateTest() throws Exception {
////        assertThrows(examRequest.getExam(),examService.update(examRequest.getExam()));
//
//    }
//
//    @Test
//    void getTest() {
//    }
//}