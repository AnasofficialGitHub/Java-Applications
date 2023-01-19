import java.util.Scanner;
public class BankGiftCard{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		Login a = new Admin();
		Login b = new User();
		User u = new User();
		Login g = new Giftlogin();
		System.out.println("1.Account Login(AL)\n2.Purchase(UL)");
		int m=scan.nextInt();
		if(m==1){
			System.out.println("1.Admin\n2.User");
			int n=scan.nextInt();
			if(n==1)
				a.login();
			else if(n==2){
				System.out.println("1.Signup\n2.Signin");
				n=scan.nextInt();
				if(n==1)
					u.signup();
				else
					b.login();
			}
			else{
				System.out.println("Enter the correct input");
				main(args);
			}
		}
		else if(m==2){
			g.signin();
		}
			
	}
}
abstract class Login{
	String aname = "Nibila";
	String apass = "Nibila@012";
	static String[] uname = new String[3];
	static String[] upass = new String[3];
	static int i;
	static int[] deposit = new int[3];
	Scanner scan = new Scanner(System.in);
	Accountdetails ad = new Accountdetails();
	public abstract void signin();
	public final void login(){
		signin();		
	}
}
class Admin extends Login{
	public void signin(){
		System.out.println("*****Sign In*****");
		System.out.println("Admin User Name :");
		String name = scan.next();
		System.out.println("Password : ");
		String pass = scan.next();
		if(name.equals(aname) && pass.equals(apass))
			System.out.println("Signin Successfull");
		else{
			System.out.println("Admin name or password is wrong");
			signin();
		}
	}
}
class User extends Login implements Signup{
	public void signup(){
		Accountdetails ac = new Accountdetails();
		System.out.println("User Name:");
		uname[i]=scan.next();
		System.out.println("Password:");
		upass[i]=scan.next();
		System.out.println("Enter the deposit amount");
		deposit[i]=scan.nextInt();
		ac.dep[i]=deposit[i];
		i++;
		signin();
	}
	public void signin(){
		System.out.println(" User Name :");
		String name = scan.next();
		System.out.println("Password : ");
		String pass = scan.next();
		int flag=0;
		for(int j=0;j<3;j++){
			if(name.equals(uname[j]) && pass.equals(upass[j])){
				flag=1;
				ad.menu(j);
			}
		}
		if(flag==0){
			System.out.println("Username or password is wrong");
			signin();
		}
	}
}
interface Signup{
	public void signup();
}
class Giftlogin extends Login{
	public void signin(){
		System.out.println("Card Number:");
		int c = scan.nextInt();
		System.out.println("Pin : ");
		int p = scan.nextInt();
		int flag1=0;
		Giftcard gif = new Giftcard();
		for(int k=0;k<ad.i;k++){
			if(c==ad.cardno[k] && p==ad.pin[k]){
				gif.show(k);
				break;
			}
		}
		if(flag1==0){
			System.out.println("Card number or pin is wrong");
			signin();
		}
	}
}

class Accountdetails {
	static int[] dep = new int[3];
	static int[] cardno = new int[10];
	static int[] pin = new int[10];
 	static int card=58054;
	static int PIN=1406;
	static int i;
	static int acc[] = new int[10];
	static int giftid[] = new int[10];
	Scanner scan = new Scanner(System.in);
	BankGiftCard b = new BankGiftCard();
	public void menu(int j){
		System.out.println("1.Create Giftcard\n2.Topup\n3.Transaction History\n4.Block\n5.Logout");
		int n=scan.nextInt();
		switch(n){
		case 1:
			create(j);
			break;
		case 2:
			topup(j);
			break;
		case 3:
			history(j);
			break;
		case 4:
			block(j);
			break;
		case 5:
			logout();
			break;
		}
	}

	public void create(int j){
		System.out.println("Your Card Number : "+card);
		System.out.println("Your Pin : "+PIN);
		cardno[i]=card;
		pin[i]=PIN;
		giftid[i]=j+1;
		i++;
		card++;
		PIN=PIN+666;
		menu(j);
	}
	public void topup(int j){
		view(j);
		System.out.println("Enter the Card Number to topup");
		int c = scan.nextInt();
		int flag=0;
		for(int k=0;k<i;k++){
			if(c==cardno[k] && giftid[k]==j+1){
				flag=1;
				System.out.println("Enter the amount ");
				int amount = scan.nextInt();
				acc[k]=acc[k]+amount;
				break;
			}
		}
		if(flag==0){
			System.out.println("Card number not found");
		}
		menu(j);
	}
	public void history(int j){
	
	}
	public void block(int j){
		view(j);
		System.out.println("Enter the Card Number to Block :");
		int c = scan.nextInt();
		int flag1=0;
		for(int k=0;k<i;k++){
			if(c==cardno[k] && giftid[k]==j+1){
				dep[k]=dep[k]+acc[k];
				cardno[k]=0;
				pin[k]=0;
				acc[k]=0;
				for(int z=0;j<z-1;z++){
					if(cardno[z]==0){
						int temp=cardno[z];
						cardno[z]=cardno[z+1];
						cardno[z+1]=temp;
						temp=pin[z];
						pin[z]=pin[z+1];
						pin[z+1]=temp;
					 	temp=acc[z];
						acc[z]=acc[z+1];
						acc[z+1]=temp;
						temp=giftid[z];
						giftid[z]=giftid[z+1];
						giftid[z+1]=temp;
					}
				}
				i--;
				menu(j);
			}
		}
		if(flag1==0){
			System.out.println("Card number or pin is wrong");
			block(j);
		}
	}
	public void logout(){
		BankGiftCard.main(null);
	}
	public void view(int j){
		System.out.println("S.no ID Card Number Balance");
		for(int x=0;x<i;x++)
			if(giftid[x]==j+1)
				System.out.println((x+1)+".  "+giftid[x]+"    "+cardno[x]+"   "+acc[x]);
	}
}
class Adminacc extends Accountdetails{
	public void adminmenu(){
		
	}		
}
class Giftcard extends Accountdetails{
	public void show(int k){
		System.out.println("1.Purchase\n2.Balance\n3.Logout");
		int sc = scan.nextInt();
		if(sc==1)
			product(k);
		else if(sc==2){
			System.out.println("Gift Card Balance "+acc[k]);
			show(k);
		}
		else
			BankGiftCard.main(null);		
	}
	public void product(int k){
		System.out.println("1.Pen - Rs.10\n2.Headset - Rs.100\n3.Soap - Rs.40\n4.Bag - Rs.500\n5.Toy - Rs300");
		int p = scan.nextInt();
		switch(p){
			case 1:
				if(acc[k]>=10)
					acc[k]=acc[k]-10;
				else {
					System.out.println("Money is low");
					show(k);
				}
				break;
			case 2:
				if(acc[k]>=100)
					acc[k]=acc[k]-100;
				else {
					System.out.println("Money is low");
					show(k);
				}
				break;
			case 3:
				if(acc[k]>=40)
					acc[k]=acc[k]-40;
				else {
					System.out.println("Money is low");
					show(k);
				}
				break;
			case 4:
				if(acc[k]>=500)
					acc[k]=acc[k]-500;
				else {
					System.out.println("Money is low");
					show(k);
				}
				break;
			case 5:
				if(acc[k]>=300)
					acc[k]=acc[k]-300;
				else {
					System.out.println("Money is low");
					show(k);
				}
				break;
		}
	}
}
