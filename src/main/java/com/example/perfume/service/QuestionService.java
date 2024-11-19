package com.example.perfume.service;

import com.example.perfume.entity.Answer;
import com.example.perfume.Message.DataNotFoundException;
import com.example.perfume.entity.SiteUser;
import com.example.perfume.entity.Question;
import com.example.perfume.repository.QuestionRepository;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }
    public Question getQuestion(Integer id){
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent()){
            return question.get();
        }else{
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject, String content, SiteUser user){
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(user);
        this.questionRepository.save(q);
    }

    public void creates(String subject, String content){
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q);
    }
    public Page<Question> getList(int page, String kw, boolean titleOnly, boolean contentOnly) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate")); // 최신순 정렬
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Question> spec = search(kw, titleOnly, contentOnly);
        return this.questionRepository.findAll(spec, pageable);
    }

    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public void delete(Question question) {
        this.questionRepository.delete(question);
    }

    private Specification<Question> search(String kw, boolean titleOnly, boolean contentOnly) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복 제거
                Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);

                Predicate predicate;

                if (titleOnly) {
                    // 제목만 검색
                    predicate = cb.like(q.get("subject"), "%" + kw + "%");
                } else if (contentOnly) {
                    // 내용만 검색
                    predicate = cb.like(q.get("content"), "%" + kw + "%");
                } else {
                    // 제목과 내용 모두 검색
                    predicate = cb.or(
                            cb.like(q.get("subject"), "%" + kw + "%"), // 제목
                            cb.like(q.get("content"), "%" + kw + "%"), // 내용
                            cb.like(u1.get("username"), "%" + kw + "%"), // 질문 작성자
                            cb.like(a.get("content"), "%" + kw + "%"), // 답변 내용
                            cb.like(u2.get("username"), "%" + kw + "%") // 답변 작성자
                    );
                }

                return predicate;
            }
        };
    }

}
