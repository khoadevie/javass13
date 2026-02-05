import java.util.*;

interface Manage<T> {
    void add(T item);
    void update(int index, T item);
    void delete(int index);
    void display();
}

class Student {
    private String id;
    private String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "ID: " + id + ", Ten sinh vien: " + name;
    }
}

class AttendanceManager implements Manage<Student> {
    private ArrayList<Student> students = new ArrayList<>();

    public void add(Student item) {
        students.add(item);
    }

    public void update(int index, Student item) {
        students.set(index, item);
    }

    public void delete(int index) {
        students.remove(index);
    }

    public void display() {
        if (students.isEmpty()) {
            System.out.println("Danh sach rong.");
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }

    public int findById(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) return i;
        }
        return -1;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static AttendanceManager manager = new AttendanceManager();

    static String inputNotEmpty(String msg) {
        String s;
        while (true) {
            System.out.print(msg);
            s = sc.nextLine();
            if (!s.isBlank()) return s;
            System.out.println("Vui long khong de trong.");
        }
    }

    static void addStudent() {
        String id = inputNotEmpty("Nhap id sinh vien: ");
        String name = inputNotEmpty("Nhap ten sinh vien: ");
        manager.add(new Student(id, name));
        System.out.println("Sinh vien da duoc them thanh cong.");
    }

    static void updateStudent() {
        manager.display();
        String id = inputNotEmpty("Nhap id sinh vien can sua: ");
        int index = manager.findById(id);
        if (index == -1) {
            System.out.println("Khong tim thay sinh vien.");
            return;
        }
        String name = inputNotEmpty("Nhap ten moi sinh vien: ");
        manager.update(index, new Student(id, name));
        System.out.println("Sinh vien da duoc sua thanh cong.");
    }

    static void deleteStudent() {
        manager.display();
        String id = inputNotEmpty("Nhap id sinh vien can xoa: ");
        int index = manager.findById(id);
        if (index == -1) {
            System.out.println("Khong tim thay sinh vien.");
            return;
        }
        manager.delete(index);
        System.out.println("Sinh vien da duoc xoa thanh cong.");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("*************** MENU QUAN LY DIEM DANH ***************");
            System.out.println("1. Them sinh vien");
            System.out.println("2. Sua sinh vien");
            System.out.println("3. Xoa sinh vien");
            System.out.println("4. Hien thi danh sach sinh vien");
            System.out.println("5. Thoat");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateStudent();
                case 3 -> deleteStudent();
                case 4 -> manager.display();
                case 5 -> System.exit(0);
            }
        }
    }
}
