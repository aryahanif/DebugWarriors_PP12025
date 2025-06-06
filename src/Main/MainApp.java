package Main;

import Entities.PreOrder;
import Services.PreOrderQueueService;
import Utils.KeyboardInput;
import Utils.MenuDisplay;

public class MainApp {
    public static void main(String[] args) {
        PreOrderQueueService queue = new PreOrderQueueService();
        
        while (true) {
            MenuDisplay.showMainMenu();
            int choice = Integer.parseInt(KeyboardInput.getString(""));
            
            switch (choice) {
                case 1: // Add Preorder
                    String name = KeyboardInput.getString("Customer name: ");
                    String contact = KeyboardInput.getString("Contact number: ");
                    PreOrder newOrder = new PreOrder(name, contact);
                    queue.enqueue(newOrder);
                    System.out.println("✅ Preorder added successfully");
                    break;
                    
                case 2: // Process Preorder
                    if (queue.isEmpty()) {
                        System.out.println("🚫 Queue is empty");
                    } else {
                        PreOrder processed = queue.dequeue();
                        System.out.println("Processed: " + 
                            processed.getCustomerName() + " | " + 
                            processed.getContactNumber());
                    }
                    break;
                    
                case 3: // View Front
                    PreOrder front = queue.peekFront();
                    if (front == null) {
                        System.out.println("🚫 No preorders in queue");
                    } else {
                        System.out.println("Current front: " + 
                            front.getCustomerName() + " | " + 
                            front.getContactNumber());
                    }
                    break;
                    
                case 4: // Exit
                    System.out.println("Exiting system...");
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}
