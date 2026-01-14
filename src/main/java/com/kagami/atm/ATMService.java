package com.kagami.atm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ATMService {
    private static final String DATA_FILE = "data/users.json";
    private List<User> users;
    private final Gson gson;

    public ATMService() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.users = loadUsers();
    }

    private List<User> loadUsers() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<User>>() {
            }.getType();
            List<User> loaded = gson.fromJson(reader, listType);
            return loaded != null ? loaded : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveUsers() {
        File file = new File(DATA_FILE);
        file.getParentFile().mkdirs();
        try (Writer writer = new FileWriter(file)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User register(String fullName, String userName, int pin, double initialDeposit)
            throws IllegalArgumentException {
        if (users.stream().anyMatch(u -> u.getUserName().equalsIgnoreCase(userName))) {
            throw new IllegalArgumentException("Username already exists.");
        }
        User newUser = new User(fullName, userName, pin, initialDeposit);
        users.add(newUser);
        saveUsers();
        return newUser;
    }

    public User login(int pin) throws IllegalArgumentException {
        // Original logic: "Enter your PIN".
        // Warning: This implies PINs are unique.
        // Improvement: We will search for PIN. If multiple users have same PIN, this is
        // ambiguous.
        // But for this legacy conversion, we'll assume uniqueness or return the first
        // match.
        /*
         * Ideally, we should ask for Username AND Pin.
         * But to keep user's "Enter PIN" flow for transactions (step 2 in original),
         * we can try to support it.
         * However, standard security requires ID + Auth.
         * Refactoring note: I will enforce Username + PIN for cleaner logic if I build
         * a Login Screen.
         * User requested: "Login State (PIN verification)".
         * I'll add a method `loginMatch(pin)` but arguably we should bind to a specific
         * user.
         * The UI will likely have a "Logic" screen that asks for PIN? Or Username/PIN?
         * Let's support `login(username, pin)` as best practice, and maybe
         * `loginByPin(pin)` for backward compat/laziness
         * if the user insists, but I'll push for `login(username, pin)`.
         * The original code had [1] Register [2] Transaction (Enter PIN).
         * Since I'm making a "Login Screen", I'll ask for User + Pin.
         */

        // Actually, let's stick to the prompt's simplicity.
        // "Login State (PIN verification)" -> implied just PIN?
        // Let's overload.
        return users.stream()
                .filter(u -> u.getPin() == pin)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid PIN."));
    }

    public User login(String username, int pin) throws IllegalArgumentException {
        return users.stream()
                .filter(u -> u.getUserName().equalsIgnoreCase(username) && u.getPin() == pin)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Username or PIN."));
    }

    public void deposit(User user, double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive.");
        user.setBalance(user.getBalance() + amount);
        saveUsers();
    }

    public void withdraw(User user, double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Amount must be positive.");
        if (user.getBalance() < amount)
            throw new IllegalArgumentException("Insufficient balance.");
        user.setBalance(user.getBalance() - amount);
        saveUsers();
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
