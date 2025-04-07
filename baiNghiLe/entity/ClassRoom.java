package baiNghiLe.entity;

import baiNghiLe.util.ValidClass;
import baiNghiLe.util.ValidCourse;
import baiNghiLe.util.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ClassRoom implements IApp{
    private static int classroomIdAuto=0;
    private int classroomId;
    private String classRoomName;
    private String courseId;
    private int teacherId;
    private List<Student> listStudents;
    private LocalDate date;
    private StatusClass status;

    public ClassRoom() {
        this.classroomId = ++classroomIdAuto;
    }

    public int getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }

    public String getClassRoomName() {
        return classRoomName;
    }

    public void setClassRoomName(String classRoomName) {
        this.classRoomName = classRoomName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Student> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<Student> listStudents) {
        this.listStudents = listStudents;
    }

    public StatusClass getStatus() {
        return status;
    }

    public void setStatus(StatusClass status) {
        this.status = status;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public ClassRoom(String classRoomName, String courseId, LocalDate date, List<Student> listStudents, StatusClass status, int teacherId) {
        this.classroomId = ++classroomIdAuto;
        this.classRoomName = classRoomName;
        this.courseId = courseId;
        this.date = date;
        this.listStudents = listStudents;
        this.status = status;
        this.teacherId = teacherId;
    }

    @Override
    public void inputData(Scanner scanner) {
        this.classRoomName = ValidClass.checkSomeClassName(scanner, "Nhập vào tên lớp học: ", -1);
        this.courseId = ValidCourse.checkSomeCourseId2(scanner, "Nhập vào mã khóa học: ");
        this.date = LocalDate.now();
        this.status = Validator.validStatusClass(scanner, "Nhập vào trạng thái lớp học: ");
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = this.date.format(formatter);

        return "---------------------------\n" +
                "ID lớp học: " + classroomId + "\n" +
                "Tên lớp: " + classRoomName + "\n" +
                "Mã khóa học: " + courseId + "\n" +
                "Mã giảng viên: " + teacherId + "\n" +
                "Ngày tạo: " + formattedDate + "\n" +
                "Trạng thái: " + (status == StatusClass.PENDING ? "Đang chờ mở" :
                status == StatusClass.PROGESS ? "Đã diễn ra" : "Kết thúc");
    }

}
