# Costco Checkout Simulation

## Project Description

This CSC 311 lab demonstrates the Producer-Consumer pattern using Java multithreading. Customers act as producers by joining a checkout queue, while cashiers act as consumers by serving customers.

The program uses `ArrayBlockingQueue` to coordinate threads and measures customer waiting times under different staffing conditions.

## Author
Ethan Mikucki

## Individual Contributions
- Implemented the CheckoutQueue, Customer, and Cashier classes.
- Created the CostcoSimulation main class.
- Added customer waiting-time measurements.
- Ran five simulation experiments.
- Analyzed staffing costs and checkout performance.

## Classes

- `CheckoutQueue.java` — Manages customers waiting for checkout using a thread-safe queue and tracks waiting times.
- `Customer.java` — Simulates customer shopping and joining the checkout line.
- `Cashier.java` — Simulates cashiers serving customers.
- `CostcoSimulation.java` — Runs the simulation using user-entered parameters and displays results.

## How to Run

1. Open the project in IntelliJ IDEA.
2. Run `CostcoSimulation.java`.
3. Enter the number of customers, number of cashiers, and checkout queue capacity.
4. View the simulation results in the console.

## Experiment Results

All five experiments used 20 customers.

| Experiment | Cashiers | Queue Size | Avg. Wait (ms) | Total Time (ms) |
|---|---:|---:|---:|---:|
| Understaffed | 1 | 10 | 4796.92 | 10446 |
| Three Cashiers | 3 | 10 | 1444.61 | 3865 |
| Overstaffed | 10 | 10 | 255.37 | 1328 |
| Small Queue | 3 | 5 | 1451.43 | 3878 |
| Large Queue | 3 | 20 | 1449.58 | 3845 |

## Staffing Cost Analysis

At $18 per hour per cashier:

- 1 cashier costs $18/hour.
- 3 cashiers cost $54/hour.
- 10 cashiers cost $180/hour.

Increasing the number of cashiers reduced customer waiting times. However, hiring additional cashiers also increased operating costs.

Changing the queue size had very little effect on waiting times in these experiments.

## Recommendation

Based on the tested configurations, three cashiers provide a reasonable balance between customer waiting times and staffing costs.

For a target of 60 customers per hour, three cashiers would cost $54 per hour. Assuming a real-world average checkout time of two minutes per customer, three cashiers could theoretically serve up to 90 customers per hour.

This is an illustrative capacity estimate. The experiments used accelerated checkout times and do not directly measure real-world hourly throughput.

## Conclusion

This simulation demonstrates how Java threads and `ArrayBlockingQueue` can model a real-world checkout system. The results show that cashier staffing has a major effect on waiting times, while changing queue capacity had little effect in these particular runs.

The experiments were run once per configuration, so additional trials would help confirm the findings.
