package hust.ltu14.networksecurity.Digital_Signature;

import java.util.Scanner;

public class App 
{	
	
    public static void main( String[] args ){
    	Scanner sc = new Scanner(System.in);
		System.out.println("\n**********Digital Signature App v1.0**********");
		while (true) {
			System.out.println("\n1.Create signature for text file");
			System.out.println("\n2.Verify signature");
			System.out.println("\n3.Exit");
			System.out.println("\nPlease make your choice: ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				try {
					SignatureMaker sigmaker = new SignatureMaker();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				break;
			case 2:
				try {
					Verifier verifier = new Verifier();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				break;
			case 3:
				System.out.println("Do you want to quit?(Y/N) ");
				sc.nextLine();
				String choose = sc.nextLine();
				if(choose=="Y"|choose=="y") {
					sc.close();
					return;
				}
				else break;
			
			default:
				System.out.println("Do you want to quit?(Y/N) ");
				choose = sc.nextLine();
				if(choose=="Y"|choose=="y") {
					sc.close();
					return;
				}
				else break;
			}
		}
    }
        
}
