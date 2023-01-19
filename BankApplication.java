import java.util.Scanner;
import java.util.regex.*;
public class BankApplication{
	public static void main(String[] args){
		Login l = new Login();
		l.excus();
		l.home();
	}
}
class Customer{
	int custId;
	int accNo;
	String name;
	int balance;
	String encrypass;
	int trans=1;
	public Customer(int custId,int accNo,String name,int b,String encrypass){
		this.custId=custId;
		this.accNo=accNo;
		this.name=name;
		this.balance=b;
		this.encrypass=encrypass;
	}
}
class Login{
	Customer[] c = new Customer[10];
	Scanner scan = new Scanner(System.in);
	int index=4;
	int ID=55;
	int ac=55055;
	String aname="Nibila";
	String apass="Nibila@012";
	public void excus(){
		c[0]=new Customer(11,11011,"Kumar",10000,"ApipNbjm");
		c[1]=new Customer(22,22022,"Madhu",20000,"Cboljoh");
		c[2]=new Customer(33,33033,"Rahul",30000,"dbnqvt");
		c[3]=new Customer(44,44044,"Robin",40000,"kbwb22");
	}
	public void home(){
		System.out.println("1.Admin\n2.User\n3.quit");
		int a=scan.nextInt();
		if(a==1)
			admin();
		else if(a==2)
			user();
	}
	public void admin(){
		System.out.println("Enter the name:");
		String s = scan.next();
		System.out.println("password : ");
		String str = scan.next();
		if(str.equals(apass) && s.equals(aname)){
			ahome();
		}
		else{
			System.out.println("User name and password is wrong");
			admin();
		}
	}	
	public void ahome(){
		System.out.println("1.View\n2.Delete\n3.Exit");
			int a=scan.nextInt();
			if(a==1)
				view();
			else if(a==2)
				delete();
			else 
				home();
	}
	public void user(){
		System.out.println("\t\tZoho Bank\t\n1.Add New Account\n2.Existing Account");
		int a=scan.nextInt();
		if(a==1){
			System.out.println("Enter the Name :");
			String nam=scan.next();
			addnew(nam);
		}
		else if(a==2){
			exist();
		}
	}
	public void view(){
		topn();
		System.out.println("CustID AccountNo Name Balance EncryptedPwd");
		for(int i=0;i<index;i++)
			System.out.println(c[i].custId+" "+c[i].accNo+" "+c[i].name+" "+c[i].balance+" "+c[i].encrypass);
		ahome();
	
	}
	public void topn(){
		Customer temp;
		for(int i=0;i<index-1;i++){
			for(int j=0;j<index-i-1;j++){
				if(c[j].balance<c[j+1].balance){	
					temp=c[j];
					c[j]=c[j+1];
					c[j+1]=temp;
				}
			}
		}
	}
	public void delete(){
	
	}
	public void exist(){
		System.out.println("Customer Id:");
		int id=scan.nextInt();
		System.out.println("password :");
		String p=scan.next();
		int i,flag=0;
		String e=encryption(p);
		for(i=0;i<index;i++){	
			if(c[i].custId==id && c[i].encrypass.equals(e)){
				System.out.println("Login successfull");
				flag=1;
				homeb(i);
			}
		}
		if(flag==0){
			System.out.println("customer id or password is wrong");
			exist();
		}
	}
	public void addnew(String nam){
		System.out.println("Enter the password :");
		String p=scan.next();
		System.out.println("Re-enter the password :");
		String rp=scan.next();
		String e="";
		if(p.equals(rp)){
			System.out.println("Registered Successfully");
			e = encryption(p);
		}
		else
			addnew(nam);
		System.out.println("Customer ID : "+ID);
		System.out.println("Account Number : "+ac);
		c[index]=new Customer(ID,ac,nam,10000,e);
		index++;
		ID=ID+11;
		ac=ac+11011;
		topn();
		user();
	}
	public String encryption(String p){
		char[] ch = p.toCharArray();
		int i;
		String e="";
		for(i=0;i<ch.length;i++){
			if(ch[i]=='z'){
				ch[i]=122-25;
				e=e+ch[i];
			}
			else if(ch[i]=='Z'){
				ch[i]=90-25;
				e=e+ch[i];
			}
			else if(ch[i]==57){
				ch[i]=57-9;
				e=e+ch[i];
			}
			else{
				ch[i]=++ch[i];
				e=e+ch[i];
			}
		}
		return e;
	}
	public void changepass(int i,String e){
		String validation="^(?=.*[a-z]{2})(?=.*[A-Z]{2})(?=.*\\D{2}).{6,20}$";
		Pattern p = Pattern.compile(validation);
		String pass,rp;
			System.out.println("Enter the new Password : ");
			pass=scan.next();
			Matcher m = p.matcher(pass);
			if(m.matches()){
				System.out.println("Re-enter the new Password : ");
				rp=scan.next();
				if(rp.equals(pass)){
					c[i].encrypass=encryption(rp);
					System.out.println("Password changed successfully");
					homeb(i);
				}
				else{
					System.out.println("Password is not same");
					changepass(i,e);
				}
			}
			else{
				System.out.println("Password must be 2 Capital Letters, 2 Small letters and 2 Numbers");
				changepass(i,e);
			}	
	}
	public void cpass(int i){
		System.out.println("Enter the old password : ");
		String s = scan.next();
		String e = encryption(s);
		if(c[i].encrypass.equals(e))
			changepass(i,e);
		else{
			System.out.println("Password is wrong");
			cpass(i);
		}
	}
	public void homeb(int i){
		System.out.println("1.ATM Withdrawal\n2.Cash Deposit\n3.Account Transfer\n4.Back");
		int a = scan.nextInt();
		if(a==1)
			atm(i);
		else if(a==2)
			dep(i);
		else if(a==3)
			acctrans(i);
		else if(a==4){
			cpass(i);
		}
		else
			home();
	}
	public void atm(int i){
		System.out.println("enter the amount");
		int w = scan.nextInt();
		c[i].balance=c[i].balance-w;
		//topn();
		c[i].trans++;
		if(c[i].trans==5){
			System.out.println("For security reason set a new password for your account");
			cpass(i);
		}
		else if(c[i].trans==10 && i>2){
			c[i].balance=c[i].balance-100;
		}
		System.out.println("Balance : "+c[i].balance);
		homeb(i);
	}
	public void dep(int i){
		System.out.println("enter the amount");
		int w = scan.nextInt();
		c[i].balance=c[i].balance+w;
		//topn();
		c[i].trans++;
		if(c[i].trans==5){
			System.out.println("For security reason set a new password for your account");
			cpass(i);
		}
		else if(c[i].trans==10 && i>2){
			c[i].balance=c[i].balance-100;
		}
		System.out.println("Balance : "+c[i].balance);
		homeb(i);
	}
	public void acctrans(int i){
		System.out.println("Enter the account number");
		int acc=scan.nextInt();
		for(int j=0;j<index;j++){
			if(c[j].accNo==acc){
				System.out.println("enter the amount");
				int w = scan.nextInt();
				c[i].balance=c[i].balance-w;
				c[j].balance=c[j].balance+w;
				//topn();
				c[i].trans++;
				if(c[i].trans==5){
					System.out.println("For security reason set a new password for your account");
					cpass(i);
				}
				else if(c[i].trans==10 && i>2){
					c[i].balance=c[i].balance-100;
				}
				System.out.println("Balance : "+c[i].balance);
				homeb(i);
			}
		}
		System.out.println("account number doesnt exist");
		acctrans(i);	
	}
}