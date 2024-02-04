package com.quiz.quizapp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.quizapp.model.Result;

@Repository
public interface ResultDao extends JpaRepository<Result,Integer>{
	
}

