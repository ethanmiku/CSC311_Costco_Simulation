
package ex2;

public class Customer implements Runnable {

    private final int customerId;
    private final CheckoutQueue checkoutQueue;
    private final long shoppingTime;

    public Customer(int customerId, CheckoutQueue checkoutQueue, long shoppingTime) {
        this.customerId = customerId;
        this.checkoutQueue = checkoutQueue;
        this.shoppingTime = shoppingTime;
    }

    @Override
    public void run() {
        try {
            // Simulate the customer shopping
            Thread.sleep(shoppingTime);

            System.out.println("Customer " + customerId + " finished shopping.");

            // Join the checkout line
            checkoutQueue.joinQueue(customerId);

            System.out.println("Customer " + customerId + " joined the checkout line.");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
