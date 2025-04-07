package baiNghiLe.entity;

import baiNghiLe.business.ClassRoomBusiness;
import baiNghiLe.util.ValidClass;
import baiNghiLe.util.ValidCourse;
import baiNghiLe.util.Validator;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Schedule implements IApp{
    private static int autoId = 0;
    private int scheduleId;
    private int classroomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public int getClassroomId() {
        return classroomId;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Schedule() {
        this.scheduleId = ++autoId;
    }

    public Schedule(int classroomId, LocalDateTime endTime, LocalDateTime startTime) {
        this.classroomId = classroomId;
        this.endTime = endTime;
        this.scheduleId = ++autoId;
        this.startTime = startTime;
    }

    @Override
    public void inputData(Scanner scanner) {
        ClassRoomBusiness.displayClass();
        this.classroomId = ValidClass.checkSomeClassId(scanner, "Nhập vào id lớp muốn thêm lịch học: ");
        this.startTime = Validator.validDateTime(scanner, "Nhập vào thời gian bắt đầu học (dd/MM/yyyy HH:mm:ss): ");
        while (true) {
            this.endTime = Validator.validDateTime(scanner, "Nhập vào thời gian kết thúc học (dd/MM/yyyy HH:mm:ss): ");
            if (endTime.isAfter(startTime)) {
                break;
            } else {
                System.out.println("Thời gian kết thúc phải sau thời gian bắt đầu. Vui lòng nhập lại.");
            }
        }
    }
}
