package baiNghiLe.entity;

import baiNghiLe.util.ValidCourse;
import baiNghiLe.util.Validator;

import java.util.Scanner;

public class Course implements IApp{
    protected String courseId;
    protected String courseName;
    protected Boolean status;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Course(String courseId, String courseName, Boolean status) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.status = status;
    }

    public Course() {
    }

    @Override
    public void inputData(Scanner scanner) {
        this.courseId = ValidCourse.checkSomeCourseId(scanner, "Nhập vào id khóa học: ");
        this.courseName = ValidCourse.checkSomeCourseName(scanner, "Nhập vào tên khóa học: ", -1);
        this.status = Validator.ValidateBoolean(scanner, "Vui lòng nhập true hoặc false cho trạng thái khóa học: ");
    }

    @Override
    public String toString(){
        return "-----------------------\nId khóa học: " + this.courseId + "\nTên khóa học: " + this.courseName + "\nTrạng thái: " + this.status;
    }
}