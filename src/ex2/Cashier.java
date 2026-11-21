
package ex2;

public class Cashier implements Runnable {

    private final int cashierId;
    private final CheckoutQueue checkoutQueue;
    private final long checkoutTime;
    private volatile int customersServed = 0;

    public Cashier(int cashierId, CheckoutQueue checkoutQueue, long checkoutTime) {
        this.cashierId = cashierId;
        this.checkoutQueue = checkoutQueue;
        this.checkoutTime = checkoutTime;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {

                // Get the next customer in line
                int customerId = checkoutQueue.getNextCustomer();

                System.out.println("Cashier " + cashierId +
                        " is serving Customer " + customerId);

                // Simulate checkout
                Thread.sleep(checkoutTime);

                customersServed++;

                System.out.println("Cashier " + cashierId +
                        " finished serving Customer " + customerId);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getCustomersServed() {
        return customersServed;
    }
}
