package labs.KP.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import labs.KP.entity.Exam;
import labs.KP.pojo.ExamPojo;
import labs.KP.pojo.StudentPojo;
import labs.KP.repository.ExamRepository;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;
    public ExamPojo create(ExamPojo pojo) {
        Exam exam = ExamPojo.toEntity(pojo);
        Exam saved = examRepository.save(exam);
        return ExamPojo.fromEntity(saved);
    }

    public ExamPojo findById(Integer id) {
        Optional<Exam> exam = examRepository.findById(id);
        return ExamPojo.fromEntity(exam.orElse(null));
    }

    public List<ExamPojo> findAll() {
        List<Exam> exams = examRepository.findAll();
        List<ExamPojo> pojos = new ArrayList<>();
        for (Exam exam : exams) {
            pojos.add(ExamPojo.fromEntity(exam));
        }
        return pojos;
    }

    public ExamPojo update(ExamPojo pojo) {
        Exam exam = ExamPojo.toEntity(pojo);
        Exam updated = examRepository.save(exam);
        return ExamPojo.fromEntity(updated);
    }

    public void deleteById(Integer id) {
        examRepository.deleteById(id);
    }

    public List<ExamPojo> findByDate(Date date) {
        List<Exam> exams = examRepository.findByDate(date);
        List<ExamPojo> pojos = new ArrayList<>();
        for (Exam exam : exams) {
            pojos.add(ExamPojo.fromEntity(exam));
        }
        return pojos;
    }

    public List<ExamPojo> findByTypeOfExams(String typeOfExams) {
        List<Exam> exams = examRepository.findByTypeOfExams(typeOfExams);
        List<ExamPojo> pojos = new ArrayList<>();
        for (Exam exam : exams) {
            pojos.add(ExamPojo.fromEntity(exam));
        }
        return pojos;
    }

    public List<ExamPojo> findByResult(String result) {
        List<Exam> exams = examRepository.findByResult(result);
        List<ExamPojo> pojos = new ArrayList<>();
        for (Exam exam : exams) {
            pojos.add(ExamPojo.fromEntity(exam));
        }
        return pojos;
    }

    public List<ExamPojo> findByStudent(StudentPojo studentPojo) {
        List<Exam> exams = examRepository.findByStudent(StudentPojo.toEntity(studentPojo));
        List<ExamPojo> pojos = new ArrayList<>();
        for (Exam exam : exams) {
            pojos.add(ExamPojo.fromEntity(exam));
        }
        return pojos;
    }
}
