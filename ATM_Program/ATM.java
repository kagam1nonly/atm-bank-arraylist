package ATM_Program;
/*
    Automated Teller Machine (ATM_Program.ATM) Program
    - A program where a user can register and check their details i.e. Withdraw, Deposit, Balance.
    - Only the first 10 clients are allowed to register in this program.
    - Generated by @JamesPiastro3D.
    - Don't change or edit the contents without notice.
 */
public class ATM
{
    public static void main(String[] args)
    {
        ATMSystem atm = new ATMSystem();
        atm.startATM();
    }
}

