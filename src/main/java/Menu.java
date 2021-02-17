import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
    Account account;
    TransaktionHandler th;
    Database database = new Database(Main.DBUSER,Main.DBPASS,Main.DBURL);
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
            }
        }
    }

    public void costumerLogInMenu(){
        boolean running = true;
        System.out.println("Du har valgt at logge ind som bruger");
        System.out.println("Tryk 1) for at logge ind");
        System.out.println("Tryk 2) for at gå tilbage");

        String cmd = sc.nextLine();
        while(running){
            if(cmd.equals("1")){
                System.out.println("Indtast dit brugernavn og afslut med enter: ");
                int input = Integer.parseInt(sc.nextLine());

               if(dbc.getAccountDetails(input)){
                   System.out.println("Hvilken konto vil du gerne administrer");
                   int input1 = Integer.parseInt(sc.nextLine());
                   costumerMenu(input1);
               }

            } else if(cmd.equals("2")){
                running = false;
            }
        }
    }

    public void costumerMenu(int i){
        boolean running = true;
        System.out.println("Du er nu logget ind som: ");
        System.out.println("Din saldo er: " + dbc.returnCurrentAccountAmount(i));
        System.out.println("Tryk 1) for at indsætte penge ind på din konto");
        System.out.println("Tryk 2) for at trække penge ud af din konto");
        System.out.println("Tryk 3) for at gå tilbage til oversigten over dine konti");
        System.out.println("Eller tryk 'q' for at logge ud");

        String cmd = sc.nextLine();

        while(running){
            if(cmd.equals("1")){
                System.out.println("Hvor mange penge vil du gerne indsætte på din konto? ");
                double input = Double.parseDouble(sc.nextLine());
                th.deposit(i, input);
                System.out.println("Din nye er saldo er nu: " + dbc.returnCurrentAccountAmount(i));
            }else if (cmd.equals("2")){
                System.out.println("Indtast beløb du gerne vil hæve på din konto");
                double input = Double.parseDouble(sc.nextLine());
                th.withdraw(i,input);
            }else if (cmd.equals("3")){
                running = false;
            }
            else if(cmd.equals("q")){
                running = false;
            }
        }
    }

    public void adminLoginMenu(){
        boolean running = true;
        System.out.println("Du har valgt at logge ind som admin");
        System.out.println("Tryk 1) for at logge ind");
        System.out.println("Tryk 2) for at gå tilbage");

        String cmd = sc.nextLine();

        while(running){
            if(cmd.equals("1")){
                System.out.println("Indtast dit brugernavn og afslut med enter: ");
                cmd = sc.nextLine();
                adminMenu();
            }else if(cmd.equals("2")) {
                running = false;
            }
        }
    }

    public void adminMenu(){
        boolean running = true;

        System.out.println("Velkommen til Admin menu, hvad vil du foretage dig?");
        System.out.println("Tryk 1) for at administrere en kunde");
        System.out.println("Tryk 2) for at flytte midler mellem to kunder");
        System.out.println("Eller tryk 'q' for at lukke ud!");

        String cmd = sc.nextLine();

        while(running) {
            if (cmd.equals("1")) {
                dbc.getCustomersInfo();
                System.out.println("Vælg en konto at administrere");
                int input = Integer.parseInt(sc.nextLine());
                dbc.getAccountDetails(input);
                costumerLogInMenu();
            }else if (cmd.equals("2")) {
                dbc.getCustomersInfo();
                System.out.println("hvilkent konto vil du hæve fra?");
                int fromAccountID = Integer.parseInt(sc.nextLine());
                Account fromAcount = new Account(fromAccountID,dbc.returnCurrentAccountAmount(fromAccountID));

                dbc.getCustomersInfo();
                System.out.println("Hvilken konto skal modtage");
                int toAccountID = Integer.parseInt(sc.nextLine());
                Account toAccount = new Account(toAccountID, dbc.returnCurrentAccountAmount(toAccountID));

                System.out.println("Vælg beløb");
                double amount = Double.parseDouble(sc.nextLine());
                th.transferBetweenAccount(fromAcount,toAccount,amount);
            }else if (cmd.equals("q")){
                running = false;
            }
        }

    }
}
