
package ex2;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CheckoutQueue {

    private static class WaitingCustomer {
        int customerId;
        long arrivalTime;

        WaitingCustomer(int customerId) {
            this.customerId = customerId;
            this.arrivalTime = System.nanoTime();
        }
    }

    private final ArrayBlockingQueue<WaitingCustomer> customers;

    private final AtomicLong totalWaitingTime = new AtomicLong(0);
    private final AtomicInteger customersStarted = new AtomicInteger(0);

    public CheckoutQueue(int capacity) {
        customers = new ArrayBlockingQueue<>(capacity);
    }

    // Customer joins the checkout line
    public void joinQueue(int customerId) throws InterruptedException {
        customers.put(new WaitingCustomer(customerId));
    }

    // Cashier takes the next customer
    public int getNextCustomer() throws InterruptedException {
        WaitingCustomer customer = customers.take();

        long waitTime = System.nanoTime() - customer.arrivalTime;

        totalWaitingTime.addAndGet(waitTime);
        customersStarted.incrementAndGet();

        return customer.customerId;
    }

    // Returns the number of customers waiting
    public int getQueueSize() {
        return customers.size();
    }

    // Returns the average customer wait in milliseconds
    public double getAverageWaitTime() {
        if (customersStarted.get() == 0) {
            return 0;
        }

        return totalWaitingTime.get() / 1_000_000.0 / customersStarted.get();
    }
}
