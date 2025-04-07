package baiNghiLe.presentation;

import baiNghiLe.business.*;
import baiNghiLe.entity.*;
import baiNghiLe.util.Validator;

import java.util.*;
import java.util.stream.Collectors;

public class UniversityManager {
    public static final List<Course> courseList = new ArrayList<Course>();
    public static final List<Student> studentList = new ArrayList<Student>();
    public static final List<Teacher> teacherList = new ArrayList<Teacher>();
    public static final List<CourseRegistration> registerList = new ArrayList<CourseRegistration>();
    public static final List<ClassRoom> classList = new ArrayList<ClassRoom>();
    public static final List<Schedule> scheduleList = new ArrayList<Schedule>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("************************* UNIVERSITY MENU **********************\n" +
                    "1. Quản lý khóa học\n" +
                    "2. Quản lý giảng viên\n" +
                    "3. Quản lý sinh viên\n" +
                    "4. Đăng ký khóa học\n" +
                    "5. Quản lý lớp học\n" +
                    "6. Thống kê\n" +
                    "7. Thoát");
            int choice = Validator.ValidateInt(scanner, "Lựa chọn của bạn: ", 1);
            switch (choice) {
                case 1:
                    CourseBusiness.menuCourse(scanner);
                    break;
                case 2:
                    TeacherBusiness.menuTeacher(scanner);
                    break;
                case 3:
                    StudentBusiness.menuStudent(scanner);
                    break;
                case 4:
                    CourseRegistrationBusiness.menuCourseRegistration(scanner);
                    break;
                case 5:
                    ClassRoomBusiness.menuClassRoom(scanner);
                    break;
                case 6:
                    menuDashboard(scanner);
                    break;
                case 7:
                    System.out.println("Thoát chương trình!");
                    System.exit(0);
                default:
                    System.out.println("Nhập từ 1-7");
                    break;
            }
        }
    }

    public static void menuDashboard(Scanner scanner) {
        int choice;
        do{
            System.out.println("**************************DASHBOARD***********************\n" +
                    "1. Thống kê số sinh viên, giảng viên, khóa học, lớp học\n" +
                    "2. Top 3 khóa học có nhiều sinh viên nhất\n" +
                    "3. Top 3 lớp học có nhiều sinh viên nhất\n" +
                    "4. Top 3 giảng viên dạy nhiều sinh viên nhất\n" +
                    "5. Top 3 sinh viên đăng ký học nhiều nhất\n" +
                    "6. Thoát");
            choice = Validator.ValidateInt(scanner, "Lựa chọn của bạn: ",1);
            switch (choice) {
                case 1:
                    System.out.println("Tổng số sinh viên: " + UniversityManager.studentList.size());
                    System.out.println("Tổng số giảng viên: " + UniversityManager.teacherList.size());
                    System.out.println("Tổng số khóa học: " + UniversityManager.courseList.size());
                    System.out.println("Tổng số lớp học: " + UniversityManager.classList.size());
                    break;
                case 2:
                    Map<String, Long> courseStudentCount = UniversityManager.classList.stream()
                            .collect(Collectors.groupingBy(
                                    ClassRoom::getCourseId,
                                    Collectors.summingLong(c -> c.getListStudents().size())
                            ));
                    courseStudentCount.entrySet().stream()
                            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                            .limit(3)
                            .forEach(entry -> System.out.println("Mã khóa học: " + entry.getKey() + " - Số SV: " + entry.getValue()));
                    break;
                case 3:
                    UniversityManager.classList.stream()
                            .sorted((c1, c2) -> Integer.compare(
                                    c2.getListStudents().size(), c1.getListStudents().size()))
                            .limit(3)
                            .forEach(c -> System.out.println("Tên lớp: " + c.getClassRoomName() +
                                    " - Số SV: " + c.getListStudents().size()));
                    break;
                case 4:
                    Map<Integer, Integer> teacherStudentCount = new HashMap<>();
                    for (ClassRoom c : UniversityManager.classList) {
                        if (c.getTeacherId() != 0) {
                            teacherStudentCount.merge(c.getTeacherId(), c.getListStudents().size(), Integer::sum);
                        }
                    }
                    teacherStudentCount.entrySet().stream()
                            .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                            .limit(3)
                            .forEach(entry -> {
                                System.out.println("Mã GV: " + entry.getKey() + " - Số SV dạy: " + entry.getValue());
                            });
                    break;
                case 5:
                    Map<String, Integer> studentClassCount = new HashMap<>();
                    for (ClassRoom c : UniversityManager.classList) {
                        for (Student s : c.getListStudents()) {
                            studentClassCount.merge(s.getStudentId(), 1, Integer::sum);
                        }
                    }
                    studentClassCount.entrySet().stream()
                            .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                            .limit(3)
                            .forEach(entry -> {
                                System.out.println("Mã SV: " + entry.getKey() + " - Số lớp đã đăng ký: " + entry.getValue());
                            });
                    break;
                case 6:
                    System.out.println("Thoát menu Dashboard!");
                    break;
                default:
                    System.out.println("Nhập từ 1-6");
                    break;
            }
        }while (choice!=6);
    }
}