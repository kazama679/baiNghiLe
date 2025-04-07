package baiNghiLe.entity;

import baiNghiLe.util.ValidCourse;
import baiNghiLe.util.ValidStudent;
import baiNghiLe.util.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CourseRegistration implements IApp{
    private static int cridAuto=0;
    private int crid;
    private String studentId;
    private String courseId;
    private LocalDate date;
    private Status status;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getCrid() {
        return crid;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public CourseRegistration(String courseId, LocalDate date, Status status, String studentId) {
        this.courseId = courseId;
        this.crid = ++cridAuto;
        this.date = date;
        this.status = status;
        this.studentId = studentId;
    }

    public CourseRegistration() {
        this.crid = ++cridAuto;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.studentId = ValidStudent.checkSomeStudentId2(scanner, "Nhập vào mã sinh viên cần đăng kí khóa học: ");
        this.studentId = ValidCourse.checkSomeCourseId2(scanner, "Nhập vào mã khóa học cần đăng kí: ");
        this.date = LocalDate.now();
        this.status = Validator.validStatus(scanner, "Nhập vào trạng thái: ");
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = this.date.format(formatter);
        return "-----------------------------------\nMã đăng kí khóa học: "+this.crid +"\nMã sinh viên: "+this.studentId+"\nMã khóa học: "+this.courseId+"\nNgày đăng kí: "+formattedDate+"\nTrạng thái: "+(this.status==Status.PENDING ? "Chờ duyệt" : this.status==Status.ENROLLED ? "Đã duyệt" : "Đã bỏ");
    }
}
