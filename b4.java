import java.util.*;

interface Manage<T> {
    void add(T item);
    void update(int index, T item);
    void delete(int index);
    void display();
}

class Order {
    private String code;
    private String customer;

    public Order(String code, String customer) {
        this.code = code;
        this.customer = customer;
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return "Ma don hang: " + code + ", Ten khach hang: " + customer;
    }
}

class OrderManager implements Manage<Order> {
    private ArrayList<Order> orders = new ArrayList<>();

    public void add(Order item) {
        orders.add(item);
    }

    public void update(int index, Order item) {
        orders.set(index, item);
    }

    public void delete(int index) {
        orders.remove(index);
    }

    public void display() {
        if (orders.isEmpty()) {
            System.out.println("Danh sach rong.");
            return;
        }
        for (int i = 0; i < orders.size(); i++) {
            System.out.println((i + 1) + ". " + orders.get(i));
        }
    }

    public int findByCode(String code) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getCode().equals(code)) return i;
        }
        return -1;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static OrderManager manager = new OrderManager();

    static String inputNotEmpty(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine();
            if (!s.isBlank()) return s;
            System.out.println("Vui long khong de trong !");
        }
    }

    static void addOrder() {
        String code = inputNotEmpty("Nhap ma don hang: ");
        String customer = inputNotEmpty("Nhap ten khach hang: ");
        manager.add(new Order(code, customer));
        System.out.println("Don hang da duoc them thanh cong.");
    }

    static void updateOrder() {
        manager.display();
        String oldCode = inputNotEmpty("Nhap ma don hang can sua: ");
        int index = manager.findByCode(oldCode);
        if (index == -1) {
            System.out.println("Khong tim thay don hang.");
            return;
        }
        String newCode = inputNotEmpty("Nhap ma don hang moi: ");
        String customer = inputNotEmpty("Nhap ten khach hang moi: ");
        manager.update(index, new Order(newCode, customer));
        System.out.println("Don hang da duoc sua thanh cong.");
    }

    static void deleteOrder() {
        manager.display();
        String code = inputNotEmpty("Nhap ma don hang can xoa: ");
        int index = manager.findByCode(code);
        if (index == -1) {
            System.out.println("Khong tim thay don hang.");
            return;
        }
        manager.delete(index);
        System.out.println("Don hang da duoc xoa thanh cong.");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("*************** MENU QUAN LY DON HANG ***************");
            System.out.println("1. Them don hang");
            System.out.println("2. Sua don hang");
            System.out.println("3. Xoa don hang");
            System.out.println("4. Hien thi danh sach don hang");
            System.out.println("5. Thoat");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> addOrder();
                case 2 -> updateOrder();
                case 3 -> deleteOrder();
                case 4 -> manager.display();
                case 5 -> System.exit(0);
            }
        }
    }
}
