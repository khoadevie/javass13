import java.util.*;

class Person {
    private String name;
    private String email;
    private String phone;

    public Person(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Phone: " + phone;
    }
}

public class Main {
    static ArrayList<Person> list = new ArrayList<>();

    static String inputNotEmpty(Scanner sc, String msg) {
        String s;
        while (true) {
            System.out.print(msg);
            s = sc.nextLine();
            if (!s.isBlank()) return s;
            System.out.println("Vui long ko de trong !");
        }
    }

    static void addPerson(Scanner sc) {
        String name = inputNotEmpty(sc, "Nhap ten nguoi dung: ");
        String email = inputNotEmpty(sc, "Nhap email nguoi dung: ");
        String phone = inputNotEmpty(sc, "Nhap so dien thoai nguoi dung: ");
        list.add(new Person(name, email, phone));
        System.out.println("Nguoi dung da duoc them thanh cong.");
    }

    static void deletePerson(Scanner sc) {
        System.out.print("Nhap email nguoi dung de xoa: ");
        String email = sc.nextLine();
        Optional<Person> p = list.stream()
                .filter(x -> x.getEmail().equalsIgnoreCase(email))
                .findFirst();
        p.ifPresentOrElse(x -> {
            list.remove(x);
            System.out.println("Nguoi dung da duoc xoa thanh cong.");
        }, () -> System.out.println("Khong tim thay nguoi dung."));
    }

    static void showPersons() {
        if (list.isEmpty()) {
            System.out.println("Danh sach rong.");
            return;
        }
        System.out.println("Danh sach nguoi dung:");
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("*************** MENU QUAN LY NGUOI DUNG ***************");
            System.out.println("1. Them nguoi dung");
            System.out.println("2. Xoa nguoi dung");
            System.out.println("3. Hien thi danh sach nguoi dung");
            System.out.println("4. Thoat");
            System.out.print("Lua chon cua ban: ");
            int c = Integer.parseInt(sc.nextLine());
            switch (c) {
                case 1 -> addPerson(sc);
                case 2 -> deletePerson(sc);
                case 3 -> showPersons();
                case 4 -> System.exit(0);
            }
        }
    }
}
