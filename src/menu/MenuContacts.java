package menu;

import model.Contacts;
import service.ContactManager;

import java.util.Scanner;

public class MenuContacts {
    private ContactManager contactManager = new ContactManager();
    private Scanner sc = new Scanner(System.in);

    public void displayMenu() {
        int choice;
        do {
            System.out.println("------ QUẢN LÝ DANH BẠ ------");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        contactManager.findAll();
                        break;
                    case 2:
                        contactManager.add(new Contacts());
                        break;
                    case 3:
                        contactManager.update();
                        break;
                    case 4:
                        System.out.println("Nhập số điện thoại cần xóa: ");
                        String phoneNumber = sc.nextLine();
                        contactManager.delete(phoneNumber);
                        break;
                    case 5:
                        search();
                        break;
                    case 0:
                        System.out.println("Thoát chương trình");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ.");
                }
            } catch (NumberFormatException e) {
                choice = -1;
            }


        } while (choice != 0);
    }
    public void search() {
        System.out.println("1. Tìm kiếm theo số điện thoại ");
        System.out.println("2. Tìm kiếm theo tên ");
        int choice;
        do {
            System.out.print("Chọn chức năng: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Nhập số điện thoại cần tìm: ");
                        String phoneNumber = sc.nextLine();
                        contactManager.finByPhongNumber(phoneNumber);
                        contactManager.findAll();
                        break;
                    case 2:
                        System.out.println("Nhập tên cần tìm: ");
                        String name = sc.nextLine();
                        contactManager.findByName(name);
                        contactManager.findAll();
                        break;
                }
            } catch (NumberFormatException e) {
                choice = -1;
            }
        } while (true);
    }

}
