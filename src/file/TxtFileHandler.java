package file;

import model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtFileHandler implements FileHandler{
    public TxtFileHandler() {

    }


    @Override
    public ArrayList<Contacts> read(String path) {
        ArrayList<Contacts> contacts = new ArrayList<>();
        File file = new File(path);

        if(!file.exists()) {
            System.out.println("File không tồn tại.");
            return contacts;
        }

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNumber = 0;
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length != 7) {
                    System.out.println("Cảnh báo: Dòng " + lineNumber + " không đúng định dạng (cần 7 trường, có " + parts.length + " trường). Bỏ qua.");
                    continue;
                }

                Contacts contact = new Contacts(parts[0].trim(),
                                                parts[1].trim(),
                                                parts[2].trim(),
                                                parts[5].trim(),
                                                parts[3].trim(),
                                                parts[4].trim(),
                                                parts[6].trim());
                contacts.add(contact);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    @Override
    public void write(String path, Contacts contacts) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))) {
            String data = contacts.getPhoneNumber() + "," +
                    contacts.getName() + "," +
                    contacts.getGender() + "," +
                    contacts.getGroup() + "," +
                    contacts.getAddress() + "," +
                    contacts.getBithDate() + "," +
                    contacts.getEmail();
            bufferedWriter.write(data);
            bufferedWriter.newLine();
            System.out.println("Lưu thông tin thành công");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(String path, List<Contacts> contacts) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path))) {
            for (Contacts contact : contacts) {
                String data = contact.getPhoneNumber() + "," +
                        contact.getName() + "," +
                        contact.getGender() + "," +
                        contact.getGroup() + "," +
                        contact.getAddress() + "," +
                        contact.getBithDate() + "," +
                        contact.getEmail();
                bufferedWriter.write(data);
                bufferedWriter.newLine();
            }
            System.out.println("Lưu thông tin thành công");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
