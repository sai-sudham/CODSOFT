import java.util.Scanner;

public class ATM 
{
    private static final int MAX_ATTEMPTS = 3;
    private static final int CORRECT_PIN = 1000;
    private static final float INIT_BALANCE = 3000;

    public static void main(String[] args) 
	{
        int attempts = 0;
        boolean cardBlocked = false;
        float balance = INIT_BALANCE;

        while (!cardBlocked) 
		{
            System.out.print("Please Enter Your PIN Number: ");
            Scanner scanner = new Scanner(System.in);
            int enteredPin = scanner.nextInt();

            if (enteredPin == CORRECT_PIN) 
			{
                System.out.println("PIN is correct");
                Transactions(balance);

                break;
            } else if (isReverseOfPIN(enteredPin)) 
			{
                cardBlocked = true;
                System.out.println("Your Card is blocked. Please contact your Bank Manager!!!!!!");
            } else 
			{
                attempts++;
                System.out.println("Incorrect PIN. Attempts remaining: " + (MAX_ATTEMPTS - attempts));

                if (attempts == MAX_ATTEMPTS) 
				{
                    cardBlocked = true;
                    System.out.println("Your Card is blocked. Please contact your respective bank Manager....");
                }
            }
        }
    }

    private static void Transactions(float balance) 
	{
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) 
		{
            System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t****Choose an option****");
            System.out.println("\t\t\t\t\t\t\t1. Check Balance \t\t\t\t\t\t\t 2.Withdraw\n");
			
            System.out.println("\t\t\t\t\t\t\t3. Deposit \t\t\t\t\t\t\t\t 4.Exit");
            System.out.print("\nEnter your choice: ");
			

            int choice = scanner.nextInt();

            switch (choice) 
			{
                case 1:
                    System.out.println("\nYour current balance is: " + balance);
                    break;
                case 2:
                    System.out.println("\nEnter the amount to withdraw: ");
                    float withdrawAmount = scanner.nextFloat();

                    // Check if the withdrawAmount is a multiple of 500
                    if (withdrawAmount % 500 == 0) {
                         if (withdrawAmount <= balance) {
                             balance -= withdrawAmount;
                         System.out.print("Withdrawal successful.\nRemaining balance: " + balance);
                         } else {
                            System.out.println("\nInsufficient funds.");
                        }
                    } else {
                       System.out.println("\nWithdrawal amount must be in multiples of 500.");
                    }
                    break;

                case 3:
                    System.out.print("\nEnter the amount to deposit: ");
                    float depositAmount = scanner.nextFloat();
                    balance += depositAmount;
                    System.out.println("\nDeposit successful.\nUpdated balance: " + balance);
                    break;
                case 4:
                    exit = true;
                    System.out.println("\n\t\t\t\t\t\t\t\t\t<<=========Thank you for using the ATM==========>>");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again!!!!!!");
            }
        }
    }

    private static boolean isReverseOfPIN(int enteredPin) 
	{
        int reversePin = reverseNumber(enteredPin);
        return reversePin == CORRECT_PIN;
    }

    private static int reverseNumber(int number) 
	{
        int reverse = 0;

        while (number != 0) {
            int digit = number % 10;
            reverse = reverse * 10 + digit;
            number /= 10;
        }

        return reverse;
    }
}
