import java.util.Scanner;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
public class BusReservation{
	public static void main(String[] args){
		Admin ad = new Admin(4);
	}	
}
class Ticket implements Serializable{
	String name;
	int age;
	String gender;
	long phno;
	String mail;
	char berth;
	int seat;
	String from;
	String to;
	public Ticket(String name,int age,String gender,long phno,String mail,char berth,int seat,String from,String to){
		this.name=name;
		this.age=age;
		this.gender=gender;
		this.phno=phno;
		this.mail=mail;
		this.berth=berth;
		this.seat=seat;
		this.from=from;
		this.to=to;
	}
	public String toString(){
		return berth+" "+seat+" "+name+" "+age+" "+gender+" "+phno+" "+mail+" "+from+" "+to;
	}
}
abstract class Login implements Serializable{
	static String[] uname=new String[10];
	static String[] upass=new String[10];
	static int i=0;
	static int n=0,b=0;
	static Ticket[] t = new Ticket[18];
	Scanner scan = new Scanner(System.in);
	public abstract void signin();
	public void home() {
		Admin a = new Admin();
		User u = new User();
		System.out.println("1.Admin\n2.User\n3.exit");
		int n=scan.nextInt();
		if(n==1)
			a.signin();
		else if(n==2){
			u.signup();
		}
		else{
			try{
			FileOutputStream f = new FileOutputStream("bus.txt");
			ObjectOutputStream o = new ObjectOutputStream(f);
			for(int b=0;b<t.length;b++)
				o.writeObject(t[b]);
			f.close();
			o.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			System.exit(0);
		}
	
	}
	public void view(){
		System.out.println("1.Normal\n2.Berth");
		int n=scan.nextInt();
		if(n==1){
			for(int a=0;a<t.length-6;a++){
				if(t[a]==null)
					System.out.print((a+1)+" ");
				else 
					System.out.print("  ");
				if(a%2!=0)
					System.out.println();
			}
		}
		else if(n==2){
			for(int a=t.length-6,b=1;a<t.length;a++,b++){
				if(t[a]==null)
					System.out.print("u"+b+" ");
				else 
					System.out.print("   ");
				if(a%2!=0)
					System.out.println();
			}
		}
	}
}
class Admin extends Login{
	String aname="Nibilamam";
	String apass="Nibila@012";
	public Admin(){}
	public Admin(int a){
		super.home();
	}
	public void signin(){
		System.out.println("Name : ");
		String s=scan.next();
		if(s.equals(aname)){
			System.out.println("Password : ");
			String p=scan.next();
			if(p.equals(apass)){
				home();
			}
			else{
				System.out.println("Password is wrong ");
				signin();
			}
		}
		else{
			System.out.println("Admin name is wrong ");
			signin();
		}
	}
	public void home(){
		System.out.println("1.View\n2.User Details\n3.ticket Details\n4.Restore\n5.Exit");
		int n=scan.nextInt();
		switch(n){
		case 1:
			view();
			break;
		case 2:
			userDet();
			break;
		case 3:
			ticketDet();
			break;
		case 4:
			restore();
			break;
		default:
			super.home();
		}
		home();
	}
	public void userDet(){
		for(int a=0;a<i;a++)
			System.out.println(a+1+". "+uname[a]);
	}
	public void ticketDet(){
		for(int a=0;a<t.length-6;a++)
			if(t[a]!=null)
				System.out.println(t[a]);
		for(int a=12;a<t.length;a++)
			if(t[a]!=null)
				System.out.println(t[a]);
	}
	public void restore() {
		try{
		FileInputStream f = new FileInputStream("bus.txt");
		ObjectInputStream o = new ObjectInputStream(f);
		for(int a=0;a<t.length;a++)
			t[a]=(Ticket)o.readObject();
			//o.readObject((Ticket)t[a]);
		o.close();
		f.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
class User extends Login{
	public void signup(){
		System.out.println("1.Signup\n2.Signin\n3.Exit");
		int n=scan.nextInt();
		if(n==2)
			signin();
		else if(n==3)
			super.home();
		System.out.println("Name : ");
		uname[i]=scan.next();
		String p="",rp="";
		do{
			System.out.println("Password");
			p=scan.next();
			System.out.println("Re-Enter Password");
			rp=scan.next();
		}while(!p.equals(rp));
		upass[i]=rp;
		i++;
		signup();
	}
	public void signin(){
		System.out.println("Name : ");
		String n=scan.next();
		for(int a=0;a<i;a++){
			if(uname[a].equals(n)){
				System.out.println("Password ");
				String p=scan.next();
				if(upass[a].equals(p))
					home();
			}
		}
		System.out.println("Username is wrong");
		signin();
	}
	public void home(){
		System.out.println("1.Book Ticket\n2.Cancel Ticket\n3.Available Ticket\n4.Exit");
		int n=scan.nextInt();
		if(n==1)
			book();
		else if(n==2)
			cancel();
		else if(n==3)
			view();
		else 
			signup();
		home();
	}
	public void book(){
		if(b==6 && n==12){
			System.out.println("Seats are filled");
			return;
		}
		System.out.println("Normal or Berth (n/b) ");
		char c=scan.next().charAt(0);
		int a=0;
			if(c=='b'){
				a=12;
				if(b>6){
					System.out.println("berth seats are filled");
					return;
				}
			}
			else{
				if(n>12){
					System.out.println("Normal seats are filled");
					return;
				}
			}
			System.out.println("Name : ");
			String name=scan.next();
			System.out.println("Age:");
			int age=scan.nextInt();
			System.out.println("Gender :");
			String m=scan.next();
			System.out.println("Phone number :");
			Long p=scan.nextLong();
			System.out.println("Mail id:");
			String mail=scan.next();
			System.out.println("Pickup:");
			String pick = scan.next();
			System.out.println("Drop:");
			String drop = scan.next();
			System.out.println("Seat Number :");
			int s=scan.nextInt();
			if(s>18 || a+s>18){
				System.out.println("Seat number invalid");
			}
			else if(t[a+s-1]==null){
				t[a+s-1]=new Ticket(name,age,m,p,mail,c,s,pick,drop);
					
			}
			else{
				System.out.println("the seat is booked");
			}
		
	}
	public void cancel(){
		System.out.println("Normal or Berth (n/b) ");
		char c=scan.next().charAt(0);
		System.out.println("Entet the ticket number");
		int s=scan.nextInt();
		if(c=='b')
			t[12+s-1]=null;
		else
			t[s-1]=null;
		System.out.println("Your money is refunded");
		
	}
}