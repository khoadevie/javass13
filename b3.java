import java.util.*;

interface Manage<T> {
    void add(T item);
    void update(int index, T item);
    void delete(int index);
    void display();
}

class Invoice {
    private String code;
    private double amount;

    public Invoice(String code, double amount) {
        this.code = code;
        this.amount = amount;
    }

    public String getCode() {
        return code;
    }

    public String toString() {
        return "Ma hoa don: " + code + ", So tien: " + amount;
    }
}

class InvoiceManager implements Manage<Invoice> {
    private ArrayList<Invoice> invoices = new ArrayList<>();

    public void add(Invoice item) {
        invoices.add(item);
    }

    public void update(int index, Invoice item) {
        invoices.set(index, item);
    }

    public void delete(int index) {
        invoices.remove(index);
    }

    public void display() {
        if (invoices.isEmpty()) {
            System.out.println("Danh sach rong.");
            return;
        }
        for (int i = 0; i < invoices.size(); i++) {
            System.out.println((i + 1) + ". " + invoices.get(i));
        }
    }

    public int findByCode(String code) {
        for (int i = 0; i < invoices.size(); i++) {
            if (invoices.get(i).getCode().equals(code)) return i;
        }
        return -1;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);
    static InvoiceManager manager = new InvoiceManager();

    static String inputNotEmpty(String msg) {
        String s;
        while (true) {
            System.out.print(msg);
            s = sc.nextLine();
            if (!s.isBlank()) return s;
            System.out.println("Vui long khong de trong.");
        }
    }

    static double inputAmount() {
        while (true) {
            System.out.print("Nhap so tien: ");
            try {
                double a = Double.parseDouble(sc.nextLine());
                if (a > 0) return a;
                System.out.println("Vui long nhap so thuc > 0 !");
            } catch (Exception e) {
                System.out.println("Vui long nhap so hop le !");
            }
        }
    }

    static void addInvoice() {
        String code = inputNotEmpty("Nhap ma hoa don: ");
        double amount = inputAmount();
        manager.add(new Invoice(code, amount));
        System.out.println("Hoa don da duoc them thanh cong.");
    }

    static void updateInvoice() {
        manager.display();
        String oldCode = inputNotEmpty("Nhap ma hoa don can sua: ");
        int index = manager.findByCode(oldCode);
        if (index == -1) {
            System.out.println("Khong tim thay hoa don.");
            return;
        }
        String newCode = inputNotEmpty("Nhap ma hoa don moi: ");
        double amount = inputAmount();
        manager.update(index, new Invoice(newCode, amount));
        System.out.println("Hoa don da duoc sua thanh cong.");
    }

    static void deleteInvoice() {
        manager.display();
        String code = inputNotEmpty("Nhap ma hoa don can xoa: ");
        int index = manager.findByCode(code);
        if (index == -1) {
            System.out.println("Khong tim thay hoa don.");
            return;
        }
        manager.delete(index);
        System.out.println("Hoa don da duoc xoa thanh cong.");
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("*************** MENU QUAN LY HOA DON ***************");
            System.out.println("1. Them hoa don");
            System.out.println("2. Sua hoa don");
            System.out.println("3. Xoa hoa don");
            System.out.println("4. Hien thi danh sach hoa don");
            System.out.println("5. Thoat");
            System.out.print("Lua chon cua ban: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1 -> addInvoice();
                case 2 -> updateInvoice();
                case 3 -> deleteInvoice();
                case 4 -> manager.display();
                case 5 -> System.exit(0);
            }
        }
    }
}
