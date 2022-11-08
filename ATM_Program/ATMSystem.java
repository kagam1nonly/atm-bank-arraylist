package ATM_Program;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMSystem implements ATMSystemInfo
{
    Scanner scan = new Scanner(System.in);

    ArrayList<String> users = new ArrayList<>();
    ArrayList<Integer> pins = new ArrayList<>();
    ArrayList<Integer> deposits = new ArrayList<>();
    ArrayList<String> fullnames = new ArrayList<>();

    // This is where the ATM_Program.ATM method begins
    public void startATM()
    {
        menu: // This is an infinite loop
        while (true)
        {
            try
            {
                System.out.println("~~~~~~~~~~ATM_Program.ATM~~~~~~~~~~");
                System.out.println("[1] Register");
                System.out.println("[2] Transaction");
                System.out.println("[3] View All Users");
                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice)
                {
                    case 1: // Register
                    {
                        reg:
                        while (users.size() <  10)
                        {
                            System.out.println("~~~~~~~~~~Register~~~~~~~~~~");
                            System.out.print("Enter Full name: ");
                            String fullName = scan.nextLine();
                            System.out.print("Enter Username: ");
                            String userName = scan.nextLine();
                            // If the username already exist, it will go back to the 'reg' loop
                            if (users.contains(userName))
                            {
                                System.out.println("Username already registered.");
                                continue reg;
                            }
                            // Integer or numerical zone-only
                            try
                            {
                                System.out.print("Enter PIN: ");
                                Integer pin = scan.nextInt();
                                System.out.print("Enter Amount to Deposit: ");
                                int deposit = scan.nextInt();
                                scan.nextLine();
                                // All the details inputted will be added to their responding arraylist
                                fullnames.add(fullName);
                                users.add(userName);
                                deposits.add(deposit);
                                pins.add(pin);
                                System.out.println("Successfully Registered.\n");
                            } catch (InputMismatchException e)
                            {
                                System.out.println("Numerical Value only. Registration Failed...\n");
                            }
                            continue menu;
                        }
                        System.out.println("User limit exceeded.");
                        continue menu;
                    }
                    case 2: // Transaction
                    {
                        try
                        {
                            // User must register their pin first before they can check their balance
                            System.out.println("Enter your PIN: ");
                            int input = scan.nextInt();
                            if (pins.contains(input))
                            {
                                int index = pins.indexOf(input);
                                int depositBalance = deposits.get(index);

                                transaction:
                                while (true)
                                {
                                    System.out.println("[1] Balance Inquire");
                                    System.out.println("[2] Deposit");
                                    System.out.println("[3] Withdraw");
                                    System.out.println("[4] Back");
                                    int option = scan.nextInt();
                                    scan.nextLine();
                                    switch (option)
                                    {
                                        case 1: // Balance
                                        {
                                            System.out.println("Balance: " + depositBalance);
                                            deposits.set(index, depositBalance);
                                            continue transaction;
                                        }
                                        case 2: // Deposit
                                        {
                                            System.out.print("Amount to deposit: ");
                                            int dep = scan.nextInt();
                                            depositBalance += dep;
                                            System.out.println("Your new balance: " + depositBalance);
                                            deposits.set(index, depositBalance);
                                            continue transaction;
                                        }
                                        case 3: // Withdraw
                                        {
                                            System.out.print("Amount to Withdraw: ");
                                            int withdraw = scan.nextInt();
                                            if (withdraw < depositBalance)
                                            {
                                                depositBalance -= withdraw;
                                                System.out.println("Your new balance: " + depositBalance);
                                                deposits.set(index, depositBalance);
                                                continue transaction;
                                            } else
                                                System.out.println("You don't have enough balance.");
                                            continue transaction;
                                        }
                                        case 4: // Back
                                            System.out.println();
                                            continue menu;
                                        default: // Input mismatch, code repeats
                                            System.out.println("Invalid input. Press from 1 - 4 only.\n");
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("Invalid PIN entered.\n");
                            }
                        } catch (InputMismatchException e)
                        {
                            System.out.println("Invalid input. Please enter digital numbers only.");
                        }
                        continue menu;
                    }
                    case 3: // View all client/s
                    {
                        showAllRegistered();
                        continue menu;
                    }
                    default: // Input mismatch, code repeats
                    {
                        System.out.println("Invalid input. Press from 1 - 3 only.\n");
                    }
                }
            }
            catch (InputMismatchException e) // Input mismatch, code repeats
            {
                System.out.println("Invalid input. Please enter digital numbers only.\n");
                scan.nextLine();
            }
        }
    }

    // A method to view all registered users
    public void showAllRegistered()
    {
        int userNum = 1;
        for (int i = 0; i < fullnames.size(); i++)
        {
            System.out.println("User " + "[" + userNum + "]" );
            System.out.println("Full name: " + fullnames.get(i));
            System.out.println("Username: " + users.get(i));
            System.out.println("PIN: " + pins.get(i));
            System.out.println("Deposit: " + deposits.get(i));
            System.out.println();
            userNum++;
        }
    }
}
