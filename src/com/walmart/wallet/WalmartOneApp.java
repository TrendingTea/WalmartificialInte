package com.walmart.wallet;

import java.util.*;

public class WalmartOneApp {
    private static double walletBalance = 150.00;
    private static double totalCashbackEarned = 0.00;
    private static int userAge = 24;
    private static List<String> scannedCart = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==========================================================");
        System.out.println("   WALMARTIFICIAL INTE - LIVE DEBIT 'SCAN & GO' WALLET");
        System.out.println("      'The Walmart Way' - Zero In-Store Cellular Fees");
        System.out.println("==========================================================");
        System.out.printf("   Active Balance: $%.2f | 5%% Instant Cashback Active\n", walletBalance);
        System.out.println("----------------------------------------------------------");
        System.out.println("Commands: Scan UPC | Type 'EXIT' for Greeter Token | 'BALANCE'");
        System.out.println("Sample Shelf UPCs: 101 (Bread $2.50) | 102 (Beer $9.99 [21+]) | 103 (Apples $4.00)\n");

        while (true) {
            System.out.print("SHELF_SCANNER > ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("EXIT")) {
                generateFrictionlessExitPass();
                break;
            } else if (input.equals("101")) {
                processLiveDebit("Great Value Whole Wheat Bread", 2.50, false);
            } else if (input.equals("102")) {
                if (userAge < 21) {
                    System.out.println("  ❌ [AGE-GATE DENIED]: Customer is under 21. Scanner locked for alcohol item.\n");
                } else {
                    processLiveDebit("Craft IPA 6-Pack", 9.99, true);
                }
            } else if (input.equals("103")) {
                processLiveDebit("Organic Honeycrisp Apples 3lb", 4.00, false);
            } else if (input.equalsIgnoreCase("BALANCE")) {
                System.out.printf("  💰 Current Balance: $%.2f | Total Cashback: $%.2f\n\n", walletBalance, totalCashbackEarned);
            } else {
                System.out.println("  ❌ Unknown Shelf Barcode.\n");
            }
        }
    }

    private static void processLiveDebit(String itemName, double price, boolean isAgeRestricted) {
        if (walletBalance >= price) {
            double cashback = price * 0.05;
            walletBalance -= price;
            totalCashbackEarned += cashback;
            walletBalance += cashback; // 5% Instant cashback back into wallet
            scannedCart.add(itemName);

            System.out.printf("  [LIVE DEBIT] - $%.2f for %s\n", price, itemName);
            System.out.printf("  ✨ [5%% CASHBACK INSTANT]: + $%.2f deposited back to Walmart ONE\n", cashback);
            System.out.printf("  💳 Updated Balance: $%.2f\n\n", walletBalance);
        } else {
            System.out.println("  ⚠️ Insufficient Walmart ONE balance. Live reload required.\n");
        }
    }

    private static void generateFrictionlessExitPass() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("       WALMART ONE FRICTIONLESS EXIT PASS");
        System.out.println("=".repeat(50));
        System.out.printf("Items Paid: %d | Total Cashback Saved: $%.2f\n", scannedCart.size(), totalCashbackEarned);
        for (String item : scannedCart) {
            System.out.println("  ✓ " + item);
        }
        System.out.println("-".repeat(50));
        System.out.println("  [QR EXIT TOKEN]: WM-EXIT-AUTH-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        System.out.println("  🛡️  [GREETER STATUS: GREEN]: Optical sensor verified all cart items paid.");
        System.out.println("  Safe exit. No receipt check required. Have a great day!");
        System.out.println("=".repeat(50) + "\n");
    }
}
