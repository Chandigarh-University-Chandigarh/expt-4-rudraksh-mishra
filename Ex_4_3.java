/*
* Develop a ticket booking system with synchronized threads to ensure no double booking of seats.
* Use thread priorities to simulate VIP bookings being processed first.
* */

import java.util.*;
import java.util.concurrent.*;

public class Ex_4_3 {
    static class Customer implements Runnable {
        private final Ticket_Booking_System booking_system;
        private final String customerName;

        public Customer(Ticket_Booking_System system, String name) {
            this.booking_system = system;
            this.customerName = name;
        }

        @Override
        public void run() {
            booking_system.book_Seat(customerName);
        }
    }

    static class Ticket_Booking_System {
        private int availableSeats;

        public Ticket_Booking_System(int seats) {
            this.availableSeats = seats;
        }

        public synchronized void book_Seat(String name) {
            if (availableSeats > 0) {
                System.out.println(name + " booked a seat. Remaining: " + (--availableSeats));
            } else {
                System.out.println(name + " failed to book. No seats available.");
            }
        }
    }

    static class Booking_Request {
        Customer customer;
        int priority;

        public Booking_Request(Customer customer, int priority) {
            this.customer = customer;
            this.priority = priority;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter total number of seats available: ");
        int total_seats = scanner.nextInt();
        scanner.nextLine(); 
        
        Ticket_Booking_System booking_system = new Ticket_Booking_System(total_seats);

        System.out.print("Enter the number of customers: ");
        int customer_count = scanner.nextInt();
        scanner.nextLine(); 

        List<Booking_Request> booking_list = new ArrayList<>();

        for (int i = 0; i < customer_count; i++) {
            System.out.print("Enter Customer Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Priority (1 = Regular, 2 = VIP): ");
            int priority = scanner.nextInt();
            scanner.nextLine(); 

            Customer customer = new Customer(booking_system, name);
            booking_list.add(new Booking_Request(customer, priority));
        }

        booking_list.sort((a, b) -> Integer.compare(b.priority, a.priority));

        int threadPoolSize = Math.min(customer_count, total_seats);
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        for (Booking_Request request : booking_list) { executor.execute(request.customer); }

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Booking Process Completed!");
    }
}
