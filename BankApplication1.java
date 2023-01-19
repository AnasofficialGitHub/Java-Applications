import java.util.*;

import java.util.regex.*;
public class BankApplication1{
	public static void main(String[] args){
		Login l = new Admin();
		l.home();
	}
}
class CustomerDetails{
	int cid,accno;
	String name;
	long balance;
	String enpwd;
	int trans=0;
	ArrayList<ArrayList> transaction= new ArrayList<ArrayList>();
	public CustomerDetails(String name){
		this.name=name;
	}
	public String toString(){
		return cid+" "+accno+" "+name+" "+balance+" "+enpwd+" ";
	}
}
abstract class Login{
	Scanner scan = new Scanner(System.in);
	CustomerDetails cus[] = new CustomerDetails[10];
	int i=4;
	int acc=55055;
	int ci=55;
	static Admin al=new Admin();
	static User ul=new User();
	public abstract void signin();
	public Login(){
		cus[0]=new CustomerDetails("Kumar");
		cus[0].cid=11;cus[0].accno=11011;cus[0].balance=10000;cus[0].enpwd="ApipNbjm";
		cus[1]=new CustomerDetails("Madhu");
		cus[1].cid=22;cus[1].accno=22022;cus[1].balance=20000;cus[1].enpwd="Cboljoh";
		cus[2]=new CustomerDetails("Rahul");
		cus[2].cid=33;cus[2].accno=33033;cus[2].balance=30000;cus[2].enpwd="dbnqvt";
		cus[3]=new CustomerDetails("Robin");
		cus[3].cid=44;cus[3].accno=44044;cus[3].balance=40000;cus[3].enpwd="kbwb22";
	}
	public void home(){
		System.out.println("1.Admin\n2.User");
		int n=scan.nextInt();
		switch(n){
			case 1:
				al.signin();
				break;
			case 2:
				System.out.println("1.Signup\n2.Signin");
				n=scan.nextInt();
				switch(n){
				case 1:
					ul.signup();
					break;
				case 2:
					ul.signin();
					break;
				}
				break;
		}	
	}
	public final String encryption(String pass){
		char ch[]=pass.toCharArray();
		pass="";
		for(int a=0;a<ch.length;a++){
			if(ch[a]==57)
				ch[a]='0';
			else if(ch[a]=='z')
				ch[a]='a';
			else if(ch[a]=='Z')
				ch[a]='A';
			else
				ch[a]++;
		}
		for(int a=0;a<ch.length;a++)
			pass=pass+ch[a];
		return pass;
	}
	public final String decrypt(String pass){
		char ch[]=pass.toCharArray();
		pass="";
		for(int a=0;a<ch.length;a++){
			if(ch[a]==48)
				ch[a]='9';
			else if(ch[a]=='a' )
				ch[a]='z';
			else if(ch[a]=='A')
				ch[a]='Z';
			else
				ch[a]--;
		}
		for(int a=0;a<ch.length;a++)
			pass=pass+ch[a];;
		return pass;
	}
}
class Admin extends Login{
	String aname="Nibila";
	String apass="Nibila@012";
	public void signin(){
		System.out.println("Enter the Admin Name :");
		String name=scan.next();
		if(name.equals(aname)){
			System.out.println("Enter the password");
			String pass=scan.next();
			if(pass.equals(apass)){
				System.out.println("Successfully login");
				ahome();
			}
			else{
				System.out.println("Password is wrong");
				signin();
			}
		}
		else{
			System.out.println("Admin name is wrong");
			signin();
		}
	}
	public void ahome(){
		System.out.println("1.View\n2.back");
		int n=scan.nextInt();
		switch(n){
		case 1:
			view();
			break;
		default:
			home();
			break;
		}
	}
	public void view(){
		for(int a=0;a<i-1;a++){
			for(int b=0;b<i-1;b++){
				if(cus[b].balance<cus[b+1].balance){
					CustomerDetails temp=cus[b];
					cus[b]=cus[b+1];
					cus[b+1]=temp;
				}
			}
		}
		System.out.println("Custid AccNo. Name Balance EncryptedPwd");
		for(int a=0;a<i;a++)
			System.out.println(cus[a]);
		ahome();
	}
}
class User extends Login{
	public void signup(){
		System.out.println("Enter the Name");
		String name=scan.next();
		cus[i]=new CustomerDetails(name);
		System.out.println("Enter the password");
		String pass=scan.next();
		System.out.println("Re-Enter the password");
		String rpass=scan.next();
		if(pass.equals(rpass))
			cus[i].enpwd=encryption(pass);
		else
			signup();
		System.out.println("Your Customer ID is "+ci);
		cus[i].cid=ci;
		cus[i].accno=acc;
		cus[i].balance=10000;
		ci=ci+11;
		acc=acc+11011;
		i++;
		home();
	}
	public void signin(){
		System.out.println("Customer ID :");
		int id=scan.nextInt();
		for(int a=0;a<i;a++){
			if(id==cus[a].cid){
				System.out.println("Password");
				String pass=scan.next();
				if(cus[a].enpwd.equals(encryption(pass))){
					System.out.println(" Login Successfull");
					uhome(a);
				}
				else{
					System.out.println("Password is wrong");
					signin();
				}
			}
		}
		System.out.println("Id is wrong");
		signin();	
	}
	public void uhome(int a){
		System.out.println("1.ATM Withdrawl\n2.Cash Deposit\n3.Account Transfer\n4.Change Password\n5.Transaction History\n5.Back");
		int n=scan.nextInt();
		switch(n){
		case 1:
			atm(a);
			break;
		case 2:
			cashdep(a);
			break;
		case 3:
			transfer(a);
			break;
		case 4:
			changepass(a);
			break;
		case 5:
			trans(a);
			break;
		default:
			home();
		}
		
	}
	public void atm(int a){
		System.out.println("Enter the cash to withdraw");
		long cash = scan.nextLong();
		lowbalance(a,cash);
		cus[a].balance-=cash;
		System.out.println("Cash Withdrawn successfully");
		if(cash>5000)
			cus[a].balance-=10;
		fee(a,cash);
		System.out.println("Current Balance : "+cus[a].balance);
		uhome(a);
	}
	public void cashdep(int a){
		System.out.println("Enter the cash to deposit");
		long cash = scan.nextLong();
		cus[a].balance+=cash;
		ArrayList arr=new ArrayList();
		arr.add(cus[a].trans);
		arr.add("Cash Deposit");
		arr.add(cash);
		arr.add(cus[a].balance);
		cus[a].transaction.add(arr);
		System.out.println("Cash Added successfully");
		fee(a,cash);
		System.out.println("Current Balance : "+cus[a].balance);
		uhome(a);
	}
	public void transfer(int a){
		System.out.println("Enter the account number");
		int accc= scan.nextInt();
		for(int j=0;j<i;j++){
			if(accc==cus[j].accno){
				System.out.println("Enter the cash");
				long cash = scan.nextLong();
				lowbalance(a,cash);
				cus[j].balance+=cash;
				cus[a].balance-=cash;
				System.out.println("Cash trasfered successfully to "+cus[j].name);
				fee(a,cash);
				System.out.println("Current Balance : "+cus[a].balance);
				uhome(a);
			}
		}
		System.out.println("Wrong account number");
		uhome(a);
	}
	public void changepass(int a){
		System.out.println("\tChange Password\t");
		String val="^(?=.*?[A-Z].*?[A-Z])(?=.*?[a-z].*?[a-z])(?=.*?[0-9].*?[0-9]).{6,20}$";
		Pattern p = Pattern.compile(val);
		String pass,rp;
		System.out.println("Enter the new Password : ");
		pass=scan.next();
		Matcher m = p.matcher(pass);
		if(m.matches()){
			System.out.println("Re-enter the new Password : ");
			rp=scan.next();
			if(rp.equals(pass)){
				cus[a].enpwd=encryption(rp);
				System.out.println("Password changed successfully");
				uhome(a);
			}
			else{
				System.out.println("Password is not same");
				changepass(a);
			}
		}
		else{
			System.out.println("Password must be 2 Capital Letters, 2 Small letters and 2 Numbers");
			changepass(a);
		}
		uhome(a);	
	}
	public void fee(int a,long cash){
		cus[a].trans+=1;
		if(cus[a].trans%5==0)
			changepass(a);
		if(cus[a].trans%10==0){
			cus[a].balance-=100;
			
		}
		if(cash>5000)
			cus[a].balance-=10;
	}
	public void lowbalance(int a,long cash){
		if(cus[a].balance-cash<1000){
			System.out.println("Balance is low add cash and then do your Transaction");
			uhome(a);
		}
	}
	public void trans(int a){
		System.out.println(cus[a].transaction);
	}
}