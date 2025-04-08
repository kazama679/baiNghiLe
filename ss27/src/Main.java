import presentation.StudentUI;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("***************APPLICATION MENU**************");
            System.out.println("1. Quản lý sinh viên");
            System.out.println("2. Quản lý lớp học");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    StudentUI.displayStudentMenu(scanner);
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
            }
        } while (true);
    }
}