import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
    TransaktionHandler th = new TransaktionHandler();
    Database database = new Database(Main.DBUSER, Main.DBPASS, Main.DBURL);
    DBController dbc = new DBController(database);

    public void mainMenu() {

        boolean running = true;

        while (running) {
            System.out.println("Ohøj landkrappe, og velkommen til banken over dem alle.");
            System.out.println("Du har nu følgende muligheder.");
            System.out.println("Tryk 1) for at logge ind som bruger.");
            System.out.println("Tryk 2) for at logge ind som adminstator.");
            System.out.println("Eller tryk 'q' for at stoppe programmet");

            String cmd = sc.nextLine();

            if (cmd.equals("1")) {
                costumerLogInMenu();
            } else if (cmd.equals("2")) {
                adminLoginMenu();
            } else if (cmd.equals("q")) {
                running = false;
            } else {
                System.out.println("Indtast et korrekt input (mainMenu)");
            }
        }
    }

    public void costumerLogInMenu() {
        boolean running = true;
        System.out.println("Du har valgt at logge ind som bruger");
        System.out.println("Tryk 1) for at logge ind");
        System.out.println("Tryk 2) for at gå tilbage");

        String cmd = sc.nextLine();
        while (running) {
            if (cmd.equals("1")) {
                System.out.println("Indtast dit brugernavn og afslut med enter: ");
                int customerID = Integer.parseInt(sc.nextLine());
                if (dbc.getCustomerAccountDetails(customerID)) {
                    System.out.println("indtast det konto nummer du gerne vil administrer");
                    int accountId = Integer.parseInt(sc.nextLine());
                    if (dbc.checkAccountToCustomer(accountId,customerID)){
                        costumerMenu(accountId);
                        running = false;
                    } else {
                        System.out.println("Du mangler at indtaste et korrekt Id");
                    }
                }
            } else if (cmd.equals("2")) {
                running = false;
            } else {
                System.out.println("Du har indtastet forkert prøv igen\n");
                System.out.println("Indtast dit brugernavn og afslut med enter: ");
            }
        }
    }

    public void costumerMenu(int i) {
        boolean running = true;
        System.out.println("Du er nu logget ind som: ");
        System.out.println("Din saldo er: " + dbc.returnCurrentAccountAmount(i));
        System.out.println("Tryk 1) for at indsætte penge ind på din konto");
        System.out.println("Tryk 2) for at trække penge ud af din konto");
        System.out.println("Tryk 3) for at se alle dine transaktioner");
        System.out.println("Eller tryk 'q' for at logge ud");

        String cmd = sc.nextLine();

        while (running) {
            if (cmd.equals("1")) {
                System.out.println("Hvor mange penge vil du gerne indsætte på din konto? ");
                double input = Double.parseDouble(sc.nextLine());
                th.deposit(i, input);
                System.out.println("Din nye er saldo er nu: " + dbc.returnCurrentAccountAmount(i));
                costumerMenu(i);
            } else if (cmd.equals("2")) {
                System.out.println("Indtast beløb du gerne vil hæve på din konto");
                double input = Double.parseDouble(sc.nextLine());
                th.withdraw(i, input);
                System.out.println("Din nye er saldo er nu: " + dbc.returnCurrentAccountAmount(i));
                costumerMenu(i);
            } else if (cmd.equals("3")){
                System.out.println(dbc.showAllTransactions(i));
                costumerMenu(i);
            }
            else if (cmd.equals("q")) {
                System.out.println("Du er nu logget ud!\n");
                running = false;
            } else {
                System.out.println("Indtast et korrekt input (costumerMenu)");
            }
            running = false;
        }
    }

    public void adminLoginMenu() {
        boolean running = true;
        System.out.println("Du har valgt at logge ind som admin");
        System.out.println("Tryk 1) for at logge ind");
        System.out.println("Tryk 2) for at gå tilbage");
        String cmd = sc.nextLine();

        while (running) {
            if (cmd.equals("1")) {
                System.out.println("Indtast dit brugernavn og afslut med enter: ");
                cmd = sc.nextLine();
                adminMenu();
                running = false;
            } else if (cmd.equals("2")) {
                running = false;
            } else {
                System.out.println("Hov hov du! Indtast rigtig input (adminLoginMenu)");
            }
        }
    }

    public void adminMenu() {
        boolean running = true;

        System.out.println("Velkommen til Admin menu, hvad vil du foretage dig?");
        System.out.println("Tryk 1) for at administrere en kunde");
        System.out.println("Tryk 2) for at flytte midler mellem to kunder");
        System.out.println("Tryk 3) for at oprette en ny kunde");
        System.out.println("Tryk 4) test af transaktioner");
        System.out.println("Eller tryk 'q' for at logge ud!");

        String cmd = sc.nextLine();

        while (running) {
            if (cmd.equals("1")) {
                dbc.getCustomersInfo();
                System.out.println("Vælg et kunde id du gerne vil administrer");
                int customerID = Integer.parseInt(sc.nextLine());
                if (dbc.getCustomerAccountDetails(customerID)) {
                    System.out.println("Vælg hvilket konto nummer du gerne vil administrer");
                    int accountID = Integer.parseInt(sc.nextLine());
                    if (dbc.checkAccountToCustomer(accountID,customerID)) {
                        costumerMenuForAdmin(accountID);
                        running = false;
                    } else {
                        System.out.println("Du mangler at indtaste et korrekt Id");
                    }
                } else {
                    System.out.println("Hov hov du, tast lige rigtigt (adminMenu)");
                }
            } else if (cmd.equals("2")) {
                int fromAccountID;
                int toAccountID;
                dbc.getCustomersInfo();
                System.out.println("Vælg hvilket kunde id du gerne vil hæve fra");
                int input = Integer.parseInt(sc.nextLine());
                if (dbc.getCustomerAccountDetails(input)) {
                    System.out.println("Vælg hvilket konto nr du gerne vil hæve fra");
                    fromAccountID = Integer.parseInt(sc.nextLine());

                    dbc.getCustomersInfo();
                    System.out.println("Vælg hvilket kunde id der skal modtage pengene");
                    input = Integer.parseInt(sc.nextLine());
                    if (dbc.getCustomerAccountDetails(input)) {
                        System.out.println("Vælg hvilket konto nr der skal modtage pengene");
                        toAccountID = Integer.parseInt(sc.nextLine());
                        System.out.println("Vælg hvor meget du gerne vil overføre");
                        double amount = Double.parseDouble(sc.nextLine());
                        th.transferBetweenAccount(fromAccountID, toAccountID, amount);
                        running = false;
                        adminMenu();

                    } else {
                        System.out.println("Noget gik galt");
                    }
                } else {
                    System.out.println("Noget gik galt manner");
                }
            } else if (cmd.equals("3")) {
                running = false;
                adduser();
            } else if (cmd.equals("4")) {
                System.out.println("Indtast den konto du vil se alle transaktioner på!");
                dbc.getCustomersInfo();
                int input = Integer.parseInt(sc.nextLine());
                System.out.println(dbc.showAllTransactions(input));
                running = false;
            } else if (cmd.equals("q")) {
                running = false;
            }
        }

    }

    public void adduser() {
        boolean running = true;
        while(running) {
            System.out.println("---Indsæt ny kunde---");
            System.out.println("indtast kunder navn");
            String customer_name = sc.nextLine();
            System.out.println("indtast kundens by");
            String customer_city = sc.nextLine();
            int result = dbc.createCustomer(customer_name, customer_city);
            if (result != 0) {
                System.out.println("Kunde nr " + result + " er nu blevet tilføjet til DB");
                running = false;
                adminMenu();
            } else {
                System.out.println("Kunde kunne ikke tilføjes! Kunde ID: " + result + " bruges allerede!");
            }
        }
    }

    public void costumerMenuForAdmin(int i) {
        boolean running = true;
        System.out.println("Du er nu logget ind som: ");
        System.out.println("Din saldo er: " + dbc.returnCurrentAccountAmount(i));
        System.out.println("Tryk 1) for at indsætte penge ind på din konto");
        System.out.println("Tryk 2) for at trække penge ud af din konto");
        System.out.println("Tryk 3) for at se alle dine transaktioner");
        System.out.println("Eller tryk 'q' for at logge ud");

        String cmd = sc.nextLine();

        while (running) {
            if (cmd.equals("1")) {
                System.out.println("Hvor mange penge vil du gerne indsætte på din konto? ");
                double input = Double.parseDouble(sc.nextLine());
                th.deposit(i, input);
                System.out.println("Din nye er saldo er nu: " + dbc.returnCurrentAccountAmount(i));
                costumerMenu(i);
            } else if (cmd.equals("2")) {
                System.out.println("Indtast beløb du gerne vil hæve på din konto");
                double input = Double.parseDouble(sc.nextLine());
                th.withdraw(i, input);
                System.out.println("Din nye er saldo er nu: " + dbc.returnCurrentAccountAmount(i));
                costumerMenu(i);
            } else if (cmd.equals("3")){
                System.out.println(dbc.showAllTransactions(i));
                costumerMenu(i);
            }
            else if (cmd.equals("q")) {
                System.out.println("Du er nu logget ud!\n");
                adminMenu();
                running = false;
            } else {
                System.out.println("Indtast et korrekt input (costumerMenu)");
            }
            running = false;
        }
    }
}