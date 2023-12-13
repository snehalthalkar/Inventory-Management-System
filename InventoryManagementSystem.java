import java.util.ArrayList;
import java.util.Scanner;

class Item {
    String itemName;
    int quantity;

    public Item(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Inventory {
    ArrayList<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(String itemName, int quantity) {
        for (Item item : items) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new Item(itemName, quantity));
    }

    public void removeItem(String itemName, int quantity) {
        for (Item item : items) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                item.setQuantity(item.getQuantity() - quantity);
                if (item.getQuantity() <= 0) {
                    items.remove(item);
                }
                return;
            }
        }
        System.out.println("Item not found in inventory");
    }

    public void displayInventory() {
        System.out.println("Inventory:");
        for (Item item : items) {
            System.out.println(item.getItemName() + ": " + item.getQuantity());
        }
    }
}

public class InventoryManagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Display Inventory");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String itemName = scanner.next();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    inventory.addItem(itemName, quantity);
                    break;
                case 2:
                    System.out.print("Enter item name: ");
                    itemName = scanner.next();
                    System.out.print("Enter quantity: ");
                    quantity = scanner.nextInt();
                    inventory.removeItem(itemName, quantity);
                    break;
                case 3:
                    inventory.displayInventory();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        }
    }
}