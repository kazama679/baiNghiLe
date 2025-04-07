package baiNghiLe.business;

import baiNghiLe.entity.CourseRegistration;
import baiNghiLe.entity.Status;
import baiNghiLe.presentation.UniversityManager;
import baiNghiLe.util.Validator;

import java.util.Scanner;

public class CourseRegistrationBusiness {
    public static void menuCourseRegistration(Scanner scanner) {
        int choice;
        do{
            System.out.println("*******************COURSE REGISTRATION MENU*****************\n" +
                    "1. Đăng ký khóa học cho sinh viên\n" +
                    "2. Hủy đăng ký học cho sinh viên (Chỉ được hủy nếu trạng thái là PENDING)\n" +
                    "3. Duyệt đăng ký học sinh viên (PENDING → ENROLLED)\n" +
                    "4. Xem danh sách sinh viên đăng ký từng khóa học\n" +
                    "5. Thoát");
            choice = Validator.ValidateInt(scanner, "Lựa chọn của bạn: ",1);
            switch (choice) {
                case 1:
                    registerStudent(scanner);
                    break;
                case 2:
                    removeRegister(scanner);
                    break;
                case 3:
                    editRegister(scanner);
                    break;
                case 4:
                    displayRegister();
                    break;
                case 5:
                    System.out.println("Thoát menu CourseRegistration!");
                    break;
                default:
                    System.out.println("Nhập từ 1-5");
                    break;
            }
        }while (choice!=5);
    }

    public static void registerStudent(Scanner scanner) {
        int n = Validator.ValidateInt(scanner, "Nhập số lượng sinh viên đăng ký: ", 1);
        for (int i = 0; i < n; i++) {
            CourseRegistration courseRegistration = new CourseRegistration();
            courseRegistration.inputData(scanner);
            UniversityManager.registerList.add(courseRegistration);
        }
        displayRegister();
    }

    public static int indexId(int id){
        for (int i = 0; i < UniversityManager.registerList.size(); i++) {
            if(UniversityManager.registerList.get(i).getCrid() == id){
                return i;
            }
        }
        return -1;
    }

    public static void removeRegister(Scanner scanner) {
        displayRegister();
        if(UniversityManager.registerList.isEmpty()){
            System.out.println("Danh sách đăng ký rỗng");
            return;
        }
        System.out.println("Nhập vào id đăng ký muốn hủy: ");
        int id = Integer.parseInt(scanner.nextLine());
        int index = indexId(id);
        if (index == -1) {
            System.out.println("K tìm thấy id muốn hủy");
            return;
        }
        if (UniversityManager.registerList.get(index).getStatus() != Status.PENDING) {
            System.out.println("Chỉ được hủy khi trạng thái là PENDING!");
            return;
        }
        UniversityManager.registerList.get(index).setStatus(Status.DROPPED);
        System.out.println("Đã hủy thành công!");
        displayRegister();
    }

    public static void editRegister(Scanner scanner) {
        displayRegister();
        if(UniversityManager.registerList.isEmpty()){
            System.out.println("Danh sách đăng ký rỗng");
            return;
        }
        System.out.println("Nhập vào id đăng ký muốn duyệt: ");
        int id = Integer.parseInt(scanner.nextLine());
        int index = indexId(id);
        if (index == -1) {
            System.out.println("K tìm thấy id muốn duyệt");
            return;
        }
        if (UniversityManager.registerList.get(index).getStatus() != Status.PENDING) {
            System.out.println("Chỉ được duyệt khi trạng thái là PENDING!");
            return;
        }
        UniversityManager.registerList.get(index).setStatus(Status.ENROLLED);
        System.out.println("Đã duyệt thành công!");
        displayRegister();
    }

    public static void displayRegister() {
        if(UniversityManager.registerList.isEmpty()){
            System.out.println("Danh sách đăng ký rỗng");
            return;
        }
        UniversityManager.registerList.forEach(System.out::println);
    }
}
