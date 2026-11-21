
package ex2;

import java.util.ArrayList;

public class CostcoSimulation {

    public static void main(String[] args) throws InterruptedException {

        int numberOfCustomers = 20;
        int numberOfCashiers = 3;
        int queueCapacity = 10;

        CheckoutQueue queue = new CheckoutQueue(queueCapacity);

        ArrayList<Thread> customerThreads = new ArrayList<>();
        ArrayList<Thread> cashierThreads = new ArrayList<>();
        ArrayList<Cashier> cashiers = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        // Create and start cashiers
        for (int i = 1; i <= numberOfCashiers; i++) {
            Cashier cashier = new Cashier(i, queue, 500);
            Thread thread = new Thread(cashier);

            cashiers.add(cashier);
            cashierThreads.add(thread);
            thread.start();
        }

        // Create and start customers
        for (int i = 1; i <= numberOfCustomers; i++) {
            Customer customer = new Customer(i, queue, 200);
            Thread thread = new Thread(customer);

            customerThreads.add(thread);
            thread.start();
        }

        // Wait for all customers to finish shopping
        for (Thread thread : customerThreads) {
            thread.join();
        }

        // Wait until every customer is served
        int totalServed = 0;

        while (totalServed < numberOfCustomers) {
            totalServed = 0;

            for (Cashier cashier : cashiers) {
                totalServed += cashier.getCustomersServed();
            }

            Thread.sleep(50);
        }

        // Stop the cashiers
        for (Thread thread : cashierThreads) {
            thread.interrupt();
            thread.join();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("\n===== COSTCO SIMULATION RESULTS =====");
        System.out.println("Total Customers: " + numberOfCustomers);
        System.out.println("Total Cashiers: " + numberOfCashiers);
        System.out.println("Customers Served: " + totalServed);
        System.out.println("Simulation Time: " + (endTime - startTime) + " ms");
        System.out.printf("Average Wait Time: %.2f ms%n",
                queue.getAverageWaitTime());
    }
}
