package com.microservices.questionService.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.questionService.model.Question;
import com.microservices.questionService.model.QuestionWrapper;
import com.microservices.questionService.model.Response;
import com.microservices.questionService.repository.QuestionRepo;


@Service
public class QuestionService {

	@Autowired
    private QuestionRepo questionRepository;
	
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionRepository.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<Question> addQuestion(Question question) {
		try {
			return new ResponseEntity<>(questionRepository.save(question),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new Question(),HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questionRepository.findByCategory(category),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}

	public ResponseEntity<List<String>> getQuestionForQuiz(String category, Integer numQ) {
		List<String> questions= questionRepository.findRandomQuestionByCategory(category,numQ);
		return new ResponseEntity<>(questions,HttpStatus.OK);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuestions(List<String> questionIds) {
	    try {
	        // Fetch all questions by IDs
	        List<Question> questions = questionRepository.findAllById(questionIds);

	        // Convert each Question into a QuestionWrapper
	        List<QuestionWrapper> wrappers = new ArrayList<>();
	        for (Question q : questions) {
	            wrappers.add(new QuestionWrapper(
	                    q.getId(),
	                    q.getQuestionTitle(),
	                    q.getOption1(),
	                    q.getOption2(),
	                    q.getOption3(),
	                    q.getOption4()
	            ));
	        }

	        return new ResponseEntity<>(wrappers, HttpStatus.OK);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<Integer> calculateScore(List<Response> responses) {
	    try {
	        int score = 0;

	        // Map questionId -> userAnswer
	        Map<String, String> answerMap = new HashMap<>();
	        for (Response res : responses) {
	            answerMap.put(res.getId(), res.getRightAnswer());
	        }

	        // Fetch questions
	        List<Question> questions = questionRepository.findAllById(answerMap.keySet());

	        // Compare answers
	        for (Question q : questions) {
	            if (q.getRightAnswer().equals(answerMap.get(q.getId()))) {
	                score++;
	            }
	        }

	        return new ResponseEntity<>(score, HttpStatus.OK);

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new ResponseEntity<>(0, HttpStatus.BAD_REQUEST);
	}


	
}
