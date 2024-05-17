import java.util.*;

public class cw3_Restaurant {
    static Scanner input = new Scanner(System.in);

    public static void printOrderList(ArrayList<Item> orderList){ //to print Ala Carte items
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~ALA CARTE~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.printf("%-25s%-10s%-10s%-10s", "FOOD ITEM NAME", "PRICE", "QUANTITY", "NOTES");
        for (Item foodItem : orderList){
            System.out.printf("%n%-25s%-10.2f%-10d", foodItem.getName(), foodItem.getPrice(), foodItem.getQuantity());
        }
    }

    public static void printSetOrderList(ArrayList<Set> setOrderList){ //to print sets
        if (setOrderList == null){
            System.out.println("No Sets ordered.");
        } else {
            System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~SET~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.printf("%-25s%-10s", "FOOD ITEM NAME", "PRICE");
            for (Set set : setOrderList){
                System.out.printf("%n%-25s%-10.2f", set.getSetMainCourse().getName(), set.getSetMainCourse().getPrice());
                System.out.printf("%n%-25s%-10.2f", set.getSetBeverage().getName(), set.getSetBeverage().getPrice());

                if (set.getSetDessert() == null){
                    System.out.println("\nDESSERT: NONE");
                } else {
                    System.out.printf("%n%-25s%-10.2f", set.getSetDessert().getName(), set.getSetDessert().getPrice());
                }
            }
        }
    }

    public static void printCompleteDineInOrderList(DineInOrder order){ //to print dine in details
        printOrderList(order.getOrderList());
        printSetOrderList(order.getSetList());
        System.out.println("\n\nOrder number: " + order.getOrderNumber());
        System.out.println("Table number: " + order.getTableNumber());
        System.out.println("Pax: " + order.getPax());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void printTakeawayOrderList(TakeawayOrder order){ //to print takeaway details
        printOrderList(order.getOrderList());
        printSetOrderList(order.getSetList());
        System.out.println("\n\nOrder number: " + order.getOrderNumber());
        System.out.println("Cutlery: " + order.isCutlery());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    //total preparation time for the entire order
    public static int totalPrepTime(Order order){
        int totalPrepTime = 0;
        for (Item item : order.getOrderList()){
            totalPrepTime += item.preparationTime();
        }

        for (Set set : order.getSetList()){
            totalPrepTime += set.getSetMainCourse().preparationTime() + set.getSetBeverage().preparationTime();
            if (set.getSetDessert() != null){
                totalPrepTime += set.getSetDessert().preparationTime();
            }
        }

        return totalPrepTime;
    }

    public static void printAlaCarteMenu(HashMap<String, Double> dishList){
        System.out.println("");
        for (Map.Entry<String, Double> entry : dishList.entrySet()){
            System.out.printf("%-30s | %.2f%n", entry.getKey(), entry.getValue());
        }
        System.out.println("");
    }

    public static void alaCarteMenuList(HashMap<String, Double> mainCourseList, HashMap<String, Double> beverageList,
                                        HashMap<String, Double> dessertList){
        System.out.print("Main Course: ");
        printAlaCarteMenu(mainCourseList);

        System.out.print("Drink: ");
        printAlaCarteMenu(beverageList);

        System.out.print("Dessert: ");
        printAlaCarteMenu(dessertList);
    }

    public static void addMC(String foodName, HashMap<String, Double> mainCourseList, ArrayList<Item> orderList){
        System.out.println("Enter quantity: ");
        int foodQuantity = input.nextInt();
        input.nextLine();

        //add new MainCourse object to orderList
        orderList.add(new MainCourse(foodName, mainCourseList.get(foodName), foodQuantity));
    }

    public static void addBeverage(String foodName, HashMap<String, Double> beverageList, ArrayList<Item> orderList){
        //name, price, quantity, size, temperature, sugar level
        System.out.print("Enter quantity: ");
        int foodQuantity = input.nextInt();
        input.nextLine();
        System.out.print("Enter size of drink (S,M,L): ");
        String drinkSize = input.nextLine();
        System.out.print("HOT or COLD?: ");
        String drinkTemp = input.nextLine();
        System.out.print("Sugar Level (0 - No sugar ~ 3 - High sugar): ");
        int drinkSugarLvl = input.nextInt();
        input.nextLine();

        //add new Beverage object to orderList
        orderList.add(new Beverage(foodName, beverageList.get(foodName), foodQuantity, drinkSize, drinkTemp, drinkSugarLvl));
    }

    public static void addDessert(String foodName, HashMap<String, Double> dessertList, ArrayList<Item> orderList){
        //name, price, quantity, serveAfterMeal
        System.out.print("Enter quantity: ");
        int foodQuantity = input.nextInt();
        input.nextLine();
        System.out.println("Serve after meal? (Yes or No): ");
        String serveAfterMeal = input.nextLine().toUpperCase(Locale.ROOT).trim();
        boolean isServeAfterMeal;
        isServeAfterMeal = serveAfterMeal.equals("YES"); //if YES, then true, else FALSE

        //add new Dessert object to orderList
        orderList.add(new Dessert(foodName, dessertList.get(foodName), foodQuantity, isServeAfterMeal));
    }

    public static void alaCarteAdding(HashMap<String, Double> mainCourseList, HashMap<String, Double> beverageList,
                                     HashMap<String, Double> dessertList, ArrayList<Item> orderList){

        //choose either Main Course, Drink or Dessert
        System.out.println("Choose either Main Course, Drink or Dessert");
        String itemType = input.nextLine();

        //let user (cashier) enter 1 item at a time
        System.out.println("Enter food name :");
        //can check if food exists or not
        String foodName = input.nextLine().toUpperCase(Locale.ROOT).trim().replaceAll("\\s+", " ");

        if (itemType.equalsIgnoreCase("Main Course")){
            if (mainCourseList.containsKey(foodName)){
                addMC(foodName, mainCourseList, orderList);

            } else {
                System.out.println("Main Course " + foodName + " does not exist.");
            }
        }

        else if (itemType.equalsIgnoreCase("Drink")){
            if (beverageList.containsKey(foodName)) {
                addBeverage(foodName, beverageList, orderList);

            } else {
                System.out.println("Drink " + foodName + " does not exist.");
            }
        }

        else if (itemType.equalsIgnoreCase("Dessert")){
            if (dessertList.containsKey(foodName)){
                addDessert(foodName, dessertList, orderList);
            } else {
                System.out.println("Dessert " + foodName + " does not exist.");
            }
        }

        else {
            System.out.println("Wrong input. Please try again.");
        }
    }

    public static void printSet(String setName, ArrayList<String> foodNameList){ //print set menu list
        System.out.printf("%n%s >>>", setName);
        for (int i = 0; i < foodNameList.size(); i++){
            System.out.printf("%n> %s", foodNameList.get(i));
        }
        System.out.println("\nChoose one: ");
    }

    static HashMap<String, Double> breakfastSetList = new HashMap<>(); //HashMap to store food and their price (Breakfast Set)
    //assume that some Set Items are not available in Ala Carte menu (exclusive items)
    //method to add set item (1 Main Course, 1 Beverage, and 1 optional Dessert)
    //input validation for Items (Method: itemValidation) - a while loop is used and breaks until correct food name is entered
    public static void addingSetItem(ArrayList<Set> setOrderList){

        System.out.println("Choose a Set:");
        System.out.println("1 > Breakfast Set (7~10AM)");
        //System.out.println("2 > Lunch Set (12~3PM)");
        //System.out.println("3 > Dinner Set (7~10PM)");
        int setChoice = input.nextInt();
        input.nextLine();

        if (setChoice == 1){
            System.out.println("~~~~~~~~~~~~BREAKFAST SET~~~~~~~~~~~~");

            //Main Course
            List<String> mainCourseNameList = Arrays.asList("PANCAKES", "CHICKEN RICE");
            ArrayList<String> MCNameList = new ArrayList<>(mainCourseNameList);
            printSet("MAIN COURSES", MCNameList); //print the set's main courses

            String setMCChoice = input.nextLine().toUpperCase(Locale.ROOT);
            setMCChoice = itemValidation(breakfastSetList, setMCChoice); //validate input

            //Beverage
            List<String> beverageNameList = Arrays.asList("BLACK COFFEE", "ORANGE JUICE", "PLAIN WATER");
            ArrayList<String> bevNameList = new ArrayList<>(beverageNameList);
            printSet("BEVERAGES", bevNameList); //print the set's beverages

            String setBevChoice = input.nextLine().toUpperCase(Locale.ROOT);
            setBevChoice = itemValidation(breakfastSetList, setBevChoice); //validate input
            System.out.println("Size: M");
            System.out.print("HOT or COLD?: ");
            String bevTemp = input.nextLine();
            System.out.print("Sugar Level? (0 [No sugar] ~ 3 [A lot of sugar]): ");
            int bevSugarLvl = input.nextInt();
            input.nextLine();

            System.out.print("Do you want to add a Dessert? YES or NO: ");
            String optionalDessert = input.nextLine().toUpperCase(Locale.ROOT);
            boolean hasDessert = optionalDessert.equals("YES"); //if YES, the true, else false

            if (hasDessert){ //if customer wants dessert
                //display dessert menu
                List<String> dessertNameList = Arrays.asList("MILK PUDDING", "CREAM PUFF");
                ArrayList<String> desNameList = new ArrayList<>(dessertNameList);
                printSet("DESSERTS", desNameList);

                String desChoice = input.nextLine().toUpperCase(Locale.ROOT);
                desChoice = itemValidation(breakfastSetList, desChoice);
                System.out.print("Serve after Meal? YES or NO: ");
                String serveAfterMeal = input.nextLine().toUpperCase(Locale.ROOT);
                boolean isServeAfterMeal = serveAfterMeal.equals("YES");

                //finalize Set constructor with a Dessert
                Set setWithDessert = new Set(new MainCourse(setMCChoice, breakfastSetList.get(setMCChoice), 1),
                        new Beverage(setBevChoice, breakfastSetList.get(setBevChoice), 1, "M", bevTemp, bevSugarLvl),
                        new Dessert(desChoice, breakfastSetList.get(desChoice), 1, isServeAfterMeal));

                setOrderList.add(setWithDessert);

            } else {
                //finalize Set constructor without a Dessert
                Set setNoDessert = new Set(new MainCourse(setMCChoice, breakfastSetList.get(setMCChoice), 1),
                        new Beverage(setBevChoice, breakfastSetList.get(setBevChoice), 1, "M", bevTemp, bevSugarLvl));

                setOrderList.add(setNoDessert);
            }

        }
        else {
            System.out.println("Wrong input.");
        }
    }

    //method to validate input for set item
    public static String itemValidation(HashMap<String, Double> hashMap, String userInput){
        while (!hashMap.containsKey(userInput)){
            System.out.println("Item " + userInput + " does not exist. Please try again.");
            userInput = input.nextLine().toUpperCase(Locale.ROOT);
        }
        return userInput;
    }

    //method to remove an item in the order - NOTE: not implemented for Set
    public static void removingItem(ArrayList<Item> orderList){
        System.out.print("Enter name of item to remove: ");
        String foodToRemove = input.nextLine().toUpperCase(Locale.ROOT);

        for (Item item : orderList){
            if (item.getName().equals(foodToRemove)){
                System.out.println("Name: " + item.getName()+ "\t\tQuantity: "+ item.getQuantity()); //print out item and current quantity

                System.out.println("How many to remove? ");
                int quantityToRemove = input.nextInt();

                //item is removed completely if endTotal is less than or equal to zero
                int endTotal = item.getQuantity() - quantityToRemove;
                if (endTotal > 0){ //item isn't removed, quantity just decreased
                    item.setQuantity(endTotal);
                    System.out.println("Quantity for " + foodToRemove + " is now " + endTotal);

                } else { //endTotal <= 0 - item is removed completely
                    System.out.println("Item " + foodToRemove + " has been removed.");
                    orderList.remove(item);
                }

                System.out.println("");
                break;
            } //end of if else to remove a certain item
        } //end of for loop to remove an item from the order
    }

    public static void orderMenuChoices(){
        System.out.println("Starting Order ...");
        System.out.println("1. Dine In");
        System.out.println("2. Takeaway");
        System.out.println("3. Exit to Main Menu");
    }

    public static void endOfOrderChoices(){
        System.out.println("\n\nWould you like to >>");
        System.out.println("\t1. Continue order.");
        System.out.println("\t2. Remove an item from the current order.");
        System.out.println("\t3. Finish order.");
        System.out.println("\nEnter 1, 2 or 3.");
    }

    static ArrayList<Integer> dineInOrderNumber = new ArrayList<>(); //1 ~ 300
    static ArrayList<Integer> takeawayOrderNumber = new ArrayList<>(); // 500 ~ 800

    static HashMap<String, Double> mainCourseList = new HashMap<String, Double>(); //hashmap to store Main Course food items and their prices
    static HashMap<String, Double> beverageList = new HashMap<String, Double>(); //HashMap for beverages
    static HashMap<String, Double> dessertList = new HashMap<String, Double>(); //Hashmap for Desserts

    public static void orderMenu(){
        ArrayList<Item> orderList = new ArrayList<>(); //arraylist to pass in Ala Carte (1 item at a time)
        ArrayList<Set> setOrderList = new ArrayList<>(); //arraylist for Sets only

        //DineIn order number - 1 ~ 300 (inclusive)
        for (int i = 1; i <= 300; i++){
            dineInOrderNumber.add(i);
        }

        //Takeaway order number - 500 ~ 800 (inclusive)
        for (int i = 500; i <= 800; i++){
            takeawayOrderNumber.add(i);
        }

        orderMenuChoices(); //dine in, takeaway, exit to main menu

        System.out.print("Please enter your choice: ");
        int choice = input.nextInt();

        boolean stillOrdering = true; //boolean for ordering while loop

        if (choice == 1){ //dine in order
            System.out.print("Number of people (PAX)? : ");
            int pax = input.nextInt();
            System.out.print("Table Number? : ");
            int tableNumber = input.nextInt();

            while (stillOrdering){
                //choose a Set or Ala Carte
                System.out.println("\nSet (1) or Ala Carte (2)? (Any others to quit)");
                int type = input.nextInt();
                input.nextLine();

                if (type == 1){ //SET
                    addingSetItem(setOrderList);
                }

                else if (type == 2){ //ALA CARTE
                    alaCarteMenuList(mainCourseList, beverageList, dessertList); //printing all menus
                    alaCarteAdding(mainCourseList, beverageList, dessertList, orderList); //taking order (1 item at a time)
                }

                else { //if neither set nor ala carte is chosen
                    System.out.println("Wrong input. Please try again.");
                } //end of if else for adding item

                printOrderList(orderList); //print current Ala Carte list
                printSetOrderList(setOrderList); //print current set list

                endOfOrderChoices(); //continue order, remove an item, finish order
                int orderChoice = input.nextInt();
                input.nextLine();

                if (orderChoice == 1){ //add another item
                    System.out.println("Continuing order ...");
                } else if (orderChoice == 2){ //removing item
                    removingItem(orderList);
                }

                else if (orderChoice == 3){ //ending order and creating Invoice
                    //create new Order object
                    DineInOrder order = new DineInOrder(orderList, setOrderList, dineInOrderNumber.get(0), tableNumber, pax);
                    dineInOrderNumber.remove(0); //remove already used dine in order number

                    //add to total quantity sales
                    calculateTotalQuantity(order);

                    //create final invoice
                    System.out.println("Any discount? : ");
                    int discount = input.nextInt();
                    Invoice dineInInv = new Invoice(order, 5.0, discount);

                    //print complete order list and invoice
                    printCompleteDineInOrderList(order);
                    dineInInv.printInvoice();
                    //print estimated time needed to complete the order
                    System.out.println("\nTotal (estimated) preparation time: " + totalPrepTime(order));
                    System.out.println("\nTHANK YOU! PLEASE COME AGAIN!");
                    stillOrdering = false; //exit to main menu

                } else {
                    System.out.println("Wrong input. Please try again.");
                }
            } //end of while loop for dine in order

        // TAKEAWAY
        } else if (choice == 2){
            while (stillOrdering){
                System.out.println("\nSet (1) or Ala Carte (2)? (Any others to quit)");
                int type = input.nextInt();
                input.nextLine();

                if (type == 1){ //adding set
                    addingSetItem(setOrderList);
                } else if (type == 2){ //adding ala carte
                    alaCarteMenuList(mainCourseList, beverageList, dessertList);
                    alaCarteAdding(mainCourseList, beverageList, dessertList, orderList);
                } else {
                    System.out.println("Wrong input. Please try again.");
                }

                printOrderList(orderList); //printing ala carte list
                printSetOrderList(setOrderList); //printing set list
                endOfOrderChoices(); //add new item, remove an item, complete order

                int orderChoice = input.nextInt();
                input.nextLine();

                if (orderChoice == 1){
                    System.out.println("Continuing order ...");
                } else if (orderChoice == 2){
                    removingItem(orderList);
                }
                else if (orderChoice == 3){ //ending order and creating Invoice
                    //create new Order object
                    System.out.println("Is cutlery needed? YES or NO");
                    String needCutlery = input.nextLine().toUpperCase(Locale.ROOT);
                    boolean isNeedCutlery = needCutlery.equals("YES");
                    TakeawayOrder takeawayOrder = new TakeawayOrder(orderList, setOrderList, takeawayOrderNumber.get(0), isNeedCutlery);
                    takeawayOrderNumber.remove(0);

                    //add to total sales
                    calculateTotalQuantity(takeawayOrder);

                    //create final invoice
                    System.out.println("Any discount? : ");
                    int discount = input.nextInt();
                    Invoice takeawayInv = new Invoice(takeawayOrder, 5.0, discount);

                    //print final order list and invoice
                    printTakeawayOrderList(takeawayOrder);
                    takeawayInv.printInvoice();
                    System.out.println("\nTotal (estimated) preparation time: " + totalPrepTime(takeawayOrder));
                    System.out.println("\nTHANK YOU! PLEASE COME AGAIN!");
                    stillOrdering = false; //exit to main menu

                } else {
                    System.out.println("Wrong input. Please try again.");
                }
            } //end of loop for takeaway ordering

        //EXITING ORDER
        } else if (choice == 3){
            System.out.println("Returning to Main Menu.");

        //WRONG INPUT
        } else {
            System.out.println("Please choose again.");
        }
    }

    static HashMap<String, Integer> totalQuantity = new HashMap<String, Integer>(); //HashMap to keep track of an Item and their totalQuantity sold

    //method to update total quantity of an item (in either Ala Carte or Set or both)
    public static void calculateTotalQuantity(Order order){
        //update quantities to totalQuantity HashMap elements
        //loop through orderList & setOrderList (separately)
        //if Item matches key in totalQuantity, update totalQuantity with getQuantity from Item
        for (Item item : order.getOrderList()){
            if (totalQuantity.containsKey(item.getName())){
                int updatedValue = totalQuantity.get(item.getName()) + item.getQuantity();
                totalQuantity.put(item.getName(), updatedValue);
            }
        }

        for (Set set : order.getSetList()){
            //update Main Course
            int updateValMC = set.getSetMainCourse().getQuantity() + totalQuantity.get(set.getSetMainCourse().getName());
            totalQuantity.put(set.getSetMainCourse().getName(), updateValMC);

            //update Beverage
            int updateValBev = set.getSetBeverage().getQuantity() + totalQuantity.get(set.getSetBeverage().getName());
            totalQuantity.put(set.getSetBeverage().getName(), updateValBev);

            //update Dessert if not null
            if (set.getSetDessert() != null){
                int updateDesVal = set.getSetDessert().getQuantity() + totalQuantity.get(set.getSetDessert().getName());
                totalQuantity.put(set.getSetDessert().getName(), updateDesVal);
            }
        }
    }

    //print total quantity of each item
    public static void printTotalQuantitySold(){
        System.out.printf("%n%-30s%s%n", "Name", "Total");
        for (Map.Entry item : totalQuantity.entrySet()){
            System.out.printf("%n%-30s%s", item.getKey(), item.getValue());
        }
        System.out.println("");
    }

    //method to load items into the totalQuantity HashMap
    public static void loadMenuHashMap(HashMap<String, Double> menuHashMap){
        for (Map.Entry item : menuHashMap.entrySet()){
            totalQuantity.put((String) item.getKey(), 0);
        }
    }

    //method to test features of program
    public static void testing(){
        //TESTING FOR DINE IN ORDER
        ArrayList<Item> alaCarteOrderList = new ArrayList<>();
        alaCarteOrderList.add(new MainCourse("CHICKEN RICE", 10.50, 2));
        alaCarteOrderList.add(new Beverage("CAPPUCCINO",16.50, 1, "M", "HOT", 1));
        alaCarteOrderList.add(new Dessert("RED VELVET", 13.60, 2, true));

        ArrayList<Set> setOrderList = new ArrayList<>();
        setOrderList.add(new Set(new MainCourse("PANCAKES", 13.00, 1),
                new Beverage("BLACK COFFEE", 12.00, 1, "M", "HOT", 2),
                new Dessert("MILK PUDDING", 13.00, 1, false)));

        //DineInOrder dineInOrderTest = new DineInOrder(alaCarteOrderList, setOrderList, 1, 2, 3);
        //Invoice invoiceDineInTest = new Invoice(dineInOrderTest, 5, 0);
        //calculateTotalQuantity(dineInOrderTest);
        TakeawayOrder takeawayOrderTest = new TakeawayOrder(alaCarteOrderList, setOrderList, 500, true);
        Invoice invoiceTakeawayTest = new Invoice(takeawayOrderTest, 5, 0);
        calculateTotalQuantity(takeawayOrderTest);

        //printCompleteDineInOrderList(dineInOrderTest);
        //invoiceDineInTest.printInvoice();
        //System.out.println("\nTotal preparation time for order: " + totalPrepTime(dineInOrderTest));
        printTakeawayOrderList(takeawayOrderTest);
        invoiceTakeawayTest.printInvoice();
        System.out.println("\nTotal preparation time for order: " + totalPrepTime(takeawayOrderTest));

        System.out.println("THANK YOU! PLEASE COME AGAIN!");

        printTotalQuantitySold();
    }

    public static void printMainMenu(){
        System.out.println("\nWelcome Employee XXX!"); //can consider expanding class - Employee / Manager
        System.out.println("1. Start Order"); //accept only integer for this
        System.out.println("2. Print General Report"); //get total sales for each Item, quantity sold, and most and least popular item
        System.out.println("3. Exit Program");
    }

    public static void main(String[] args) {
        //ALA CARTE menu lists
        //Main Course
        mainCourseList.put("FRIED RICE", 9.00);
        mainCourseList.put("CHICKEN RICE", 10.50);
        mainCourseList.put("MEE GORENG", 9.50);
        mainCourseList.put("SPAGHETTI", 15.00);
        mainCourseList.put("FISH AND CHIPS", 17.00);

        //Beverages
        beverageList.put("CAPPUCCINO", 16.50);
        beverageList.put("GREEN TEA", 10.00);
        beverageList.put("ICED BLENDED MILKSHAKE", 17.10);

        //Desserts
        dessertList.put("MATCHA CAKE", 13.00);
        dessertList.put("CHOCOLATE ICE CREAM", 14.00);
        dessertList.put("RED VELVET", 13.60);

        //SET menu list
        //Breakfast set
        breakfastSetList.put("CHICKEN RICE", 10.50);
        breakfastSetList.put("PANCAKES", 13.00);
        breakfastSetList.put("BLACK COFFEE", 12.00);
        breakfastSetList.put("ORANGE JUICE", 14.00);
        breakfastSetList.put("PLAIN WATER", 1.00);
        breakfastSetList.put("MILK PUDDING", 13.00);
        breakfastSetList.put("CREAM PUFF", 14.50);

        loadMenuHashMap(mainCourseList);
        loadMenuHashMap(beverageList);
        loadMenuHashMap(dessertList);
        loadMenuHashMap(breakfastSetList);

        //testing(); //method to test features of program

        boolean programRun = true;
        while (programRun){
            printMainMenu(); //order, print general report, exit
            int choiceMainMenu;
            try{
                System.out.print("Please enter corresponding number (1, 2 or 3): ");
                choiceMainMenu = input.nextInt();
            } catch (Exception e){
                System.out.println("Invalid input. Please try again.");
                break;
            }

            switch (choiceMainMenu){
                case 1:
                    orderMenu(); //ordering method
                    break;
                case 2:
                    System.out.println("Printing general report ...");
                    printTotalQuantitySold(); //gets total quantity sold for all items
                    break;
                case 3:
                    System.out.println("Exiting. Thank you");
                    programRun = false;
                    break;
                default:
                    System.out.println("Error! Please try again.");
                    break;
            }
        }
    } //end of main
}
