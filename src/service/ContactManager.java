package service;

import file.FileHandler;
import file.TxtFileHandler;
import model.Contacts;
import util.EmailValidator;
import util.PhoneValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManager implements Manager<Contacts> {
    private FileHandler txtFileHandler = new TxtFileHandler();
    private List<Contacts> contacts;
    private PhoneValidator phoneValidator = new PhoneValidator();
    private EmailValidator emailValidator = new EmailValidator();

    private static final String FILE_PATH = "src/data/contacs.txt";
    Scanner sc = new Scanner(System.in);

    public ContactManager () {
        contacts = txtFileHandler.read(FILE_PATH);
    }


    // kiểm tra danh bạ trống
    public boolean checkContacts () {
        if (contacts.isEmpty()) {
            System.out.println("Danh bạ trống");
            return true;
        } else {
            return false;
        }
    }

    // kiểm tra số điện thoại trùng lặp
    public boolean checkPhoneNumber(String phoneNumber) {
        for (Contacts contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void add(Contacts contacts) {
        System.out.println("------ Thêm danh bạ mới ------");
        // thêm số điện thoại
        String phoneNumber;
        while (true) {
            System.out.println("Nhập số điện thoại: ");
            phoneNumber = sc.nextLine();

            if (!phoneValidator.checkPhone(phoneNumber)) {
                System.out.println("Số điện thoại không hợp lệ.");
                return;
            }

            if (checkPhoneNumber(phoneNumber)) {
                System.out.println("Số điện thoại đã tồn tại");
                return;
            }
            break;
        }

        // thêm tên
        System.out.println("Nhập tên: ");
        String name = sc.nextLine();

        // thêm ngày sinh
        System.out.println("Nhập ngày sinh: ");
        String bithDate = sc.nextLine();

        // thêm nhóm
        System.out.println("Nhập nhóm: ");
        String group = sc.nextLine();

        // thêm địa chỉ
        System.out.println("Nhập địa chỉ: ");
        String address = sc.nextLine();

        // thêm giới tính
        System.out.println("Nhập giới tính: ");
        String gender = sc.nextLine();

        // thêm email
        String email = "";
        while (true) {
            System.out.println("Nhập địa chỉ email: ");
            email = sc.nextLine();

            if (email.isEmpty()) {
                break; // Cho phép bỏ qua email
            }

            if (!emailValidator.checkEmail(email)) {
                System.out.println("Địa chỉ email không hợp lệ.");
                continue;
            }
            break;
        }

        // lưu lại vào danh bạ
        Contacts newContact = new Contacts(phoneNumber, name, gender, bithDate, group, address, email);
        this.contacts.add(newContact);
        txtFileHandler.write(FILE_PATH, newContact);
    }

    @Override
    public void update() {

        System.out.println("------ Cập nhật danh bạ ------");
        System.out.println("Để trống nếu muốn giữ thông tin cũ");

        // số điện thoại
        while (true) {
            System.out.println("Nhập số điện thoại: ");
            String phoneNumber = sc.nextLine();
            checkPhoneNumber(phoneNumber);

            if (!phoneValidator.checkPhone(phoneNumber)) {
                System.out.println("Số điện thoại không hợp lệ.");
                return;
            }

            if (checkPhoneNumber(phoneNumber)) {
                System.out.println("Số điện thoại đã tồn tại");
                return;
            }
            break;
        }

        // tên
        System.out.println("Nhập tên: ");
        String name = sc.nextLine();

        // giới tính
        System.out.print("Nhập giới tính: ");
        String gender = sc.nextLine();

        // nhóm
        System.out.print("Nhập nhóm: ");
        String group = sc.nextLine();

        // địa chỉ
        System.out.print("Nhập địa chỉ: ");
        String address = sc.nextLine();

        // ngày sinh
        System.out.print("Nhập ngày sinh: ");
        String bithDate = sc.nextLine();

        // email
        while (true) {
            System.out.println("Nhập địa chỉ email: ");
            String email = sc.nextLine();

            if (!emailValidator.checkEmail(email)) {
                System.out.println("Địa chỉ email không hợp lệ.");
            }
            break;
        }

        txtFileHandler.save(FILE_PATH, contacts);

        System.out.println("Cập nhật thành công");
    }

    @Override
    public void delete(String phoneNumber) {
        Contacts contact = finByPhongNumber(phoneNumber);
        contacts.remove(contact);
        txtFileHandler.save(FILE_PATH, contacts);
    }

    @Override
    public Contacts finByPhongNumber(String phoneNumber) {
        checkContacts();
        for (Contacts contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                return contact;
            }
        }
        System.out.println("Không tìm thấy danh bạ");
        return null;
    }

    // tìm kiếm theo tên
    public List<Contacts> findByName(String name) {
        List<Contacts> result = new ArrayList<>();
        for (Contacts contact : contacts) {
            if (contact.getName().contains(name)) {
                result.add(contact);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy danh bạ");
        }
        return result;
    }

    @Override
    public List<Contacts> findAll() {
        checkContacts();
        System.out.println("------ Danh sách danh bạ ------");
        System.out.printf("%-15s %-20s %-15s %-10s %-15s %-15s %-15s\n",
                "Số điện thoại", "Họ tên", "Giới tính", "Nhóm", "Địa chỉ", "Ngày sinh", "Email");
        for (Contacts contact : contacts) {
            System.out.printf("%-15s %-20s %-15s %-10s %-15s %-15s %-15s\n",
                    contact.getPhoneNumber(), contact.getName(), contact.getGender(), contact.getGroup(), contact.getAddress(), contact.getBithDate(), contact.getEmail());
        }
        return contacts;
    }
}
