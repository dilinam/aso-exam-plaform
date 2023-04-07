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
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@ExtendWith(MockitoExtension.class)
//@ExtendWith(SpringExtension.class)
//class ExamServiceTest {
//
//    @InjectMocks
//    private ExamService examService;
//    @MockBean
//    private ExamUserRepository examUserRepository;
//    @MockBean
//    private QuestionRepository questionRepository;
//    @MockBean
//    private UserRepository userRepository;
//    @MockBean
//    private ExamRepository examRepository;
//
//    ExamRequest examRequest;
////    @Test
////    void createTest() {
////        assertEquals(examRequest.getExam(),examService.create(examRequest));
////    }
////
////    @Test
////    void updateQuestionsTest() {
////        assertEquals(examRequest.getQuestions(),examService.updateQuestions(examRequest));
////        for (Question question:examService.updateQuestions(examRequest)) {
////            assertEquals(examRequest.getExam(),question.getExam());
////        }
////
////    }
////
////    @Test
////    void updateTest() throws Exception {
//////        assertThrows(examRequest.getExam(),examService.update(examRequest.getExam()));
////
////    }
//
//    @Test
//    void getTest() {
//        List<Question> questionList = new ArrayList<>();
//        List<Exam> examList =new ArrayList<>();
//        questionList.add(new Question(1L,"question test",1,true,"testpath",false,"test start","estst end",new QuestionType(1L,"MSQ"),null,null));
//        questionList.add(new Question(2L,"question test",2,true,"testpath",false,"test start","estst end",new QuestionType(1L,"MSQ"),null,null));
//        Exam exam = new Exam(1L,"testname","testdesc","test time","test duration",true,true,false,"test create","test create at");
//        examList.add(exam);
//        examRequest = new ExamRequest(questionList,exam);
//
//        Mockito.when(examRepository.findById(1L)).thenReturn(Optional.of((exam)));
//
//        Exam results = null;
//        try {
//            results = examService.get(1L);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        assertNotNull(results);
//    }
//}