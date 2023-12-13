import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Expense {
    String description;
    double amount;
    String category;

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }
}

class ExpenseTracker {
    ArrayList<Expense> expenses;
    Map<String, Double> categoryBudgets;

    public ExpenseTracker() {
        this.expenses = new ArrayList<>();
        this.categoryBudgets = new HashMap<>();
    }

    public void addExpense(String description, double amount, String category) {
        Expense expense = new Expense(description, amount, category);
        expenses.add(expense);
        System.out.println("Expense added successfully!");
    }

    public void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded yet.");
        } else {
            System.out.println("Expenses:");
            for (Expense expense : expenses) {
                System.out.println("Description: " + expense.description + ", Amount: " + expense.amount +
                        ", Category: " + expense.category);
            }
        }
    }

    public void setBudget(String category, double budget) {
        categoryBudgets.put(category, budget);
        System.out.println("Budget set successfully for category: " + category);
    }

    public void viewBudgets() {
        if (categoryBudgets.isEmpty()) {
            System.out.println("No budgets set yet.");
        } else {
            System.out.println("Budgets:");
            for (Map.Entry<String, Double> entry : categoryBudgets.entrySet()) {
                System.out.println("Category: " + entry.getKey() + ", Budget: " + entry.getValue());
            }
        }
    }

    public void checkOverspending() {
        for (Map.Entry<String, Double> entry : categoryBudgets.entrySet()) {
            String category = entry.getKey();
            double budget = entry.getValue();
            double spent = expenses.stream()
                    .filter(e -> e.category.equals(category))
                    .mapToDouble(e -> e.amount)
                    .sum();

            if (spent > budget) {
                System.out.println("Overspending alert for category " + category + "! You've exceeded the budget.");
            }
        }
    }
}

public class ExpenseManager {
    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Set Budget");
            System.out.println("4. View Budgets");
            System.out.println("5. Check Overspending");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter expense description: ");
                    String description = scanner.next();
                    System.out.print("Enter expense amount: ");
                    double amount = scanner.nextDouble();
                    System.out.print("Enter expense category: ");
                    String category = scanner.next();
                    expenseTracker.addExpense(description, amount, category);
                    break;
                case 2:
                    expenseTracker.viewExpenses();
                    break;
                case 3:
                    System.out.print("Enter category for budget: ");
                    String budgetCategory = scanner.next();
                    System.out.print("Enter budget amount: ");
                    double budgetAmount = scanner.nextDouble();
                    expenseTracker.setBudget(budgetCategory, budgetAmount);
                    break;
                case 4:
                    expenseTracker.viewBudgets();
                    break;
                case 5:
                    expenseTracker.checkOverspending();
                    break;
                case 6:
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
