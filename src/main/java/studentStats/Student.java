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
        this.passedExams = new ArrayList<>();
    }

    public Student(String name, String surname, String yob, String residence){
        this.name = name;
        this.surname = surname;
        this.yob = yob;
        this.residence = residence;
        this.passedExams = new ArrayList<>();
    }

    @Override
    public String toString(){
        return String.format("student: %s %s", name, surname);
    }

    public void addExam(Exam exam){
        passedExams.add(exam);
    }

    public void removeLastExam(){
        passedExams.remove(passedExams.size()-1);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getYob() {
        return yob;
    }

    public String getResidence() {
        return residence;
    }

    public List<Exam> getPassedExams() {
        return passedExams;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setYob(String yob) {
        this.yob = yob;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

}
