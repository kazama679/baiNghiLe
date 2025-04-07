package baiNghiLe.business;

import baiNghiLe.entity.ClassRoom;
import baiNghiLe.entity.Schedule;
import baiNghiLe.entity.StatusClass;
import baiNghiLe.entity.Student;
import baiNghiLe.presentation.UniversityManager;
import baiNghiLe.util.ValidClass;
import baiNghiLe.util.Validator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ClassRoomBusiness {
    public static void menuClassRoom(Scanner scanner) {
        int choice;
        do{
            System.out.println("********************** CLASS ROOM MENU **********************\n" +
                    "1. Danh sách lớp học sắp xếp theo ngày tạo giảm dần\n" +
                    "2. Thêm mới lớp học\n" +
                    "3. Cập nhật thông tin lớp học (Chỉ cập nhật được thông tin nếu trạng thái khác CLOSE)\n" +
                    "4. Xóa lớp học (Chỉ xóa được nếu lớp học chưa có sinh viên và giảng viên)\n" +
                    "5. Phân công giảng viên cho lớp \n" +
                    "6. Thêm sinh viên vào cho lớp\n" +
                    "7. Tạo lịch học cho lớp\n" +
                    "8. Cập nhật trạng thái lớp (PENDING → PROGESS → CLOSE)\n" +
                    "9. Thoát");
            choice = Validator.ValidateInt(scanner, "Lựa chọn của bạn: ",1);
            switch (choice) {
                case 1:
                    displayClass();
                    break;
                case 2:
                    addClass(scanner);
                    break;
                case 3:
                    editClass(scanner);
                    break;
                case 4:
                    delete(scanner);
                    break;
                case 5:
                    addTeacher(scanner);
                    break;
                case 6:
                    addStudent(scanner);
                    break;
                case 7:
                    addSchedule(scanner);
                    break;
                case 8:
                    updateStatus(scanner);
                    break;
                case 9:
                    System.out.println("Thoát menu CLASS ROOM MENU!");
                    break;
                default:
                    System.out.println("Nhập từ 1-9");
                    break;
            }
        }while (choice!=9);
    }

    public static void displayClass() {
        if(UniversityManager.classList.isEmpty()){
            System.out.println("Danh sách lớp rỗng");
            return;
        }
        UniversityManager.classList.sort(Comparator.comparing(ClassRoom::getDate).reversed());
        UniversityManager.classList.forEach(System.out::println);
    }

    public static void addClass(Scanner scanner) {
        int n  = Validator.ValidateInt(scanner, "Nhập số lượng lớp muốn thêm: ", 1);
        for (int i = 0; i < n; i++) {
            ClassRoom classRoom = new ClassRoom();
            classRoom.inputData(scanner);
            UniversityManager.classList.add(classRoom);
        }
    }

    public static int indexId(int id){
        for (int i = 0; i < UniversityManager.classList.size(); i++) {
            if(UniversityManager.classList.get(i).getClassroomId() == id){
                return i;
            }
        }
        return -1;
    }

    public static void editClass(Scanner scanner) {
        int id = Validator.ValidateInt(scanner, "nhập vào id class cần cập nhập: ", 1);
        int index = indexId(id);
        if(index == -1){
            System.out.println("Id k chính xác");
            return;
        }
        System.out.println("Nhập vào tên lớp học cần cập nhập: ");
        UniversityManager.classList.get(index).setClassRoomName(ValidClass.checkSomeClassName(scanner, "Nhập vào tên lớp học: ", index));
    }

    public static void delete(Scanner scanner) {
        int id = Validator.ValidateInt(scanner, "nhập vào id class cần xóa: ", 1);
        int index = indexId(id);
        if(index == -1){
            System.out.println("Id k chính xác");
            return;
        }
        if(UniversityManager.classList.get(index).getTeacherId() == 0 && UniversityManager.classList.get(index).getListStudents().isEmpty()){
            UniversityManager.classList.remove(index);
            System.out.println("Đã xóa lớp học");
            return;
        }
        System.out.println("Lớp học có giảng viên hoặc học sinh nên k thể xóa!");
    }

    public static void addTeacher(Scanner scanner) {
        int idClass = Validator.ValidateInt(scanner, "Nhập vào id lớp: ", 1);
        int indexClass = indexId(idClass);
        if(indexClass == -1){
            System.out.println("K tìm thấy lớp");
            return;
        }
        TeacherBusiness.displayListTeacher();
        int id = Validator.ValidateInt(scanner, "Nhập vào id giảng viên muốn phân công: ", 1);
        int index = TeacherBusiness.indexId(id);
        if(index == -1){
            System.out.println("K tìm thấy giảng viên");
            return;
        }
        UniversityManager.classList.get(indexClass).setTeacherId(id);
    }

    public static void updateStatus(Scanner scanner) {
        int id = Validator.ValidateInt(scanner, "nhập vào id class cần cập nhập trạng thái: ", 1);
        int index = indexId(id);
        if(index == -1){
            System.out.println("Id k chính xác");
            return;
        }
        if(UniversityManager.classList.get(index).getStatus() == StatusClass.PENDING){
            UniversityManager.classList.get(index).setStatus(StatusClass.PROGESS);
        }else if(UniversityManager.classList.get(index).getStatus() == StatusClass.PROGESS){
            UniversityManager.classList.get(index).setStatus(StatusClass.CLOSE);
        }else if(UniversityManager.classList.get(index).getStatus() == StatusClass.CLOSE){
            System.out.println("Trạng thái lớp học đã là CLOSE k thể cập nhập nữa!");
            return;
        }
        System.out.println("Cập nhập trạng thái lớp học thành công!");
    }

    public static void addStudent(Scanner scanner) {
        int id = Validator.ValidateInt(scanner, "nhập vào id class cần thêm sinh viên: ", 1);
        int index = indexId(id);
        if(index == -1){
            System.out.println("Id k chính xác");
            return;
        }
        StudentBusiness.displayListStudent();
        System.out.println("Nhập id sinh viên cần thêm vào lớp học: ");
        String idStudent = scanner.nextLine();
        int indexStudent = StudentBusiness.indexId(idStudent);
        if(indexStudent == -1){
            System.out.println("K tìm thấy id sinh viên cần thêm");
            return;
        }
        List<Student> list = UniversityManager.classList.get(index).getListStudents();

        if (list == null) {
            list = new ArrayList<>();
        }

        Student studentToAdd = UniversityManager.studentList.get(indexStudent);
        if (list.stream().anyMatch(s -> s.getStudentId().equals(studentToAdd.getStudentId()))) {
            System.out.println("Sinh viên đã có trong lớp này rồi!");
            return;
        }

        list.add(studentToAdd);
        UniversityManager.classList.get(index).setListStudents(list);
        System.out.println("Thêm sinh viên vào lớp thành công!");
    }

    public static void addSchedule(Scanner scanner) {
        Schedule schedule = new Schedule();
        schedule.inputData(scanner);
        UniversityManager.scheduleList.add(schedule);
    }
}
