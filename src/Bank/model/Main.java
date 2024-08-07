package Bank.model;

import Bank.Service.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();

        Scanner scanner = new Scanner(System.in);
        int input ;
        System.out.println("*********welcome*********");
        do {
            System.out.println("1.create account\n2.create user\n3.find by firstName\n" +
                    "4.find by lastName\n5.find by cardNumber\n6.withdrow\n7.deposit" +
                    "\n8.Print last 3 Transactions \n9.exit");
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    System.out.println("please enter nationalCode");
                    User user = service.findByNationalCode(scanner.nextInt());
                    System.out.println();
                    System.out.println("please enter a" +
                            "accountNumber& cardNumber& accountType & balance& cvv & expire");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    calendar.add(Calendar.YEAR, 5);
                    Date date = calendar.getTime();
                    service.createAccount(user, scanner.nextInt(), scanner.nextInt()
                            , service.findAccountType(scanner.next().toLowerCase()),
                            scanner.nextInt(), scanner.nextInt(),
                            date);
                    break;
                case 2:
                    System.out.println("please enter firstName&  lastName & " +
                            "nationalCode &userType ");
                    service.createUser(scanner.next(), scanner.next(), scanner.nextInt(),
                            User.UserType.NOHISTORY);
                    break;
                case 3:
                    System.out.println("please enter firstName");
                    String name = scanner.next();
                    service.findByFirstName(name);
                    break;
                case 4:
                    System.out.println("please enter lastName");
                    String family = scanner.next();
                    service.findByLastName(family);
                    break;
                case 5:
                    System.out.println("please enter cardNumber");
                    int number = scanner.nextInt();
                    service.findByCardNumber(number);
                    break;
                case 6:
                    System.out.println("please enter cardNumber &fee");
                    int cardNumber = scanner.nextInt();
                    int fee = scanner.nextInt();
                    service.withdraw(cardNumber, fee);
                    break;
                case 7:
                    System.out.println("please enter cardNumber &fee");
                    cardNumber = scanner.nextInt();
                    fee = scanner.nextInt();
                    service.deposit(cardNumber, fee);
                    break;
                case 8:
                    System.out.println("Please Enter card number");
                    Account account = service.findAccountByCardNumber(scanner.nextInt());
                    if (account.getTransactionList().size()<=3){
                        account.getTransactionList().forEach(System.out::println);
                    }else {
                        account.getTransactionList().stream().
                                skip(account.getTransactionList().size()-3).
                                forEach(System.out::println);
                    }

            }


        } while (input != 9);
        service.close();
    }

}