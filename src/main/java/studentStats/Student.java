package studentStats;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String surname;
    private String yob;
    private String residence;
    private List<Exam> passedExams;
    public Student(){

    }

    public Student(String name, String surname, String yob, String residence){
        this.name = name;
        this.surname = surname;
        this.yob = yob;
        this.residence = residence;
        this.passedExams = new ArrayList<>();
    }

    public void addExam(Exam exam){
        passedExams.add(exam);
    }

    @Override
    public String toString(){
        return String.format("student: %s %s", name, surname);
    }
}
