
package ex2;

import java.util.concurrent.ArrayBlockingQueue;

public class CheckoutQueue {

    private final ArrayBlockingQueue<Integer> customers;

    public CheckoutQueue(int capacity) {
        customers = new ArrayBlockingQueue<>(capacity);
    }

    // Customer joins the checkout line
    public void joinQueue(int customerId) throws InterruptedException {
        customers.put(customerId);
    }

    // Cashier takes the next customer
    public int getNextCustomer() throws InterruptedException {
        return customers.take();
    }

    // Returns the number of customers waiting
    public int getQueueSize() {
        return customers.size();
    }
}
