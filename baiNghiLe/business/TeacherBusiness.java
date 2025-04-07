package baiNghiLe.business;

import baiNghiLe.entity.Student;
import baiNghiLe.entity.Teacher;
import baiNghiLe.presentation.UniversityManager;
import baiNghiLe.util.ValidStudent;
import baiNghiLe.util.Validator;

import java.util.Comparator;
import java.util.Scanner;

public class TeacherBusiness {
    public static void menuTeacher(Scanner scanner) {
        int choice;
        do{
            System.out.println("*************************TEACHER MENU**************************\n" +
                    "1. Danh sách giảng viên sắp xếp theo mã giảm dần\n" +
                    "2. Thêm mới giảng viên\n" +
                    "3. Cập nhật giảng viên\n" +
                    "4. Xóa giảng viên (chỉ xóa được nếu giảng viên chưa được xếp lớp)\n" +
                    "5. Thoát");
            choice = Validator.ValidateInt(scanner, "Lựa chọn của bạn: ",1);
            switch (choice) {
                case 1:
                    displayListTeacher();
                    break;
                case 2:
                    addTeacher(scanner);
                    break;
                case 3:
                    updateTeacher(scanner);
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println("Thoát menu Teacher!");
                    break;
                default:
                    System.out.println("Nhập từ 1-5");
                    break;
            }
        }while (choice!=5);
    }

    public static void displayListTeacher() {
        if(UniversityManager.teacherList.isEmpty()){
            System.out.println("Danh sách giảng viên rỗng!");
            return;
        }
        UniversityManager.teacherList.sort(Comparator.comparing(Teacher::getName).reversed());
        UniversityManager.teacherList.forEach(System.out::println);
    }

    public static void addTeacher(Scanner scanner) {
        int n = Validator.ValidateInt(scanner, "Nhập vào số lượng giảng viên muốn thêm: ", 1);
        for (int i = 0; i < n; i++) {
            Teacher t = new Teacher();
            t.inputData(scanner);
            UniversityManager.teacherList.add(t);
        }
    }

    public static int indexId(int id) {
        for (int i = 0; i < UniversityManager.teacherList.size(); i++) {
            if (id == UniversityManager.teacherList.get(i).getTeacherId()) {
                return i;
            }
        }
        return -1;
    }

    public static void updateTeacher(Scanner scanner) {
        if(UniversityManager.teacherList.isEmpty()){
            return;
        }
        int id = Validator.ValidateInt(scanner, "Nhập vào id giảng viên cập nhập: ", 1);
        int index = indexId(id);
        if(index == -1){
            System.out.println("Không tìm thấy id nào!");
            return;
        }
        int choice;
        do{
            System.out.println("1. Sửa tên giảng viên");
            System.out.println("2. Sửa tuổi giảng viên");
            System.out.println("3. Sửa địa chỉ");
            System.out.println("4. Sửa số điện thoại");
            System.out.println("5. Sửa email");
            System.out.println("6. Sửa giới tính");
            System.out.println("7. Sửa chuyên môn");
            System.out.println("8. Thoát");
            choice = Validator.ValidateInt(scanner, "Lựa chọn của bạn: ", 1);
            switch (choice) {
                case 1:
                    System.out.println("Nhập tên cần sửa: ");
                    String name = Validator.ValidateString(scanner, 1, 150);
                    UniversityManager.teacherList.get(index).setName(name);
                    break;
                case 2:
                    int age = Validator.ValidateInt(scanner, "Nhập tuổi: ", 18);
                    UniversityManager.teacherList.get(index).setAge(age);
                    break;
                case 3:
                    System.out.println("Nhập địa chỉ cần sửa: ");
                    UniversityManager.teacherList.get(index).setAddress(Validator.ValidateString(scanner, 1, 255));
                    break;
                case 4:
                    UniversityManager.teacherList.get(index).setPhone(Validator.ValidatePhone(scanner, "Nhập vào số điện thoại: ",index));
                    break;
                case 5:
                    UniversityManager.teacherList.get(index).setEmail(Validator.ValidateEmail(scanner, "Nhập vào email: ", index));
                    break;
                case 6:
                    UniversityManager.teacherList.get(index).setSex(Validator.validSex(scanner, "Nhập vào giới tính: "));
                    break;
                case 7:
                    System.out.println("Nhập chuyên môn cần sửa: ");
                    UniversityManager.teacherList.get(index).setSubject(Validator.ValidateString(scanner, 1, 255));
                    break;
                case 8:
                    System.out.println("Sửa thành công");
                    break;
                default:
                    System.out.println("Chọn từ 1-8");
                    break;
            }
        }while (choice!=8);
        displayListTeacher();
    }
}
