import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
public class Mail{
	public static void main(String[] args){
		Amail a = new Amail();
		a.login();
	}
}
class User{
	String uname;
	String mail;
	String pass;
	File f;
	int s=0;
	int r=0;
	File sent[] = new File[100];
	File rec[] = new File[100];
	File group[] = new File[100];
	public User(String uname, String mail, String pass){
		this.uname=uname;
		this.mail=mail;
		this.pass=pass;
		f = new File("C:\\Users\\Administrator\\Desktop\\Mail\\"+uname);
		f.mkdir();
	}
	public void send(String sub,String con){
		try{
			sent[s]=new File("C:\\Users\\Administrator\\Desktop\\Mail\\"+uname+"\\sentmail"+s+".txt");
			sent[s].createNewFile();
			FileWriter w = new FileWriter(sent[s]);
			w.write(sub+con);
			s++;
			w.close();
		}
		catch(IOException e){}
	}
	public void recieve(String sub,String con){
		try{
			rec[r]=new File("C:\\Users\\Administrator\\Desktop\\Mail\\"+uname+"\\recievedmail"+r+".txt");
			rec[r].createNewFile();
			FileWriter w = new FileWriter(rec[r]);
			w.write(sub+con);
			r++;
			w.close();
		}
		catch(IOException e){}
	}
}
class Amail{
	User u[] = new User[100];
	int index=0;
	Scanner scan = new Scanner(System.in);
	public void login(){
		System.out.println("1.Signup\n2.Signin\n3.exit");
		int n=scan.nextInt();
		switch(n){
		case 1: signup(); break;
		case 2: signin(); break;
		default: return;
		}
		login();		
	}
	public void signup(){
		System.out.println("Enter the name : ");
		String name=scan.next();
		System.out.println("Enter the mail :");
		String pass="",rpass="";
		String mail=scan.next();
		do{
			System.out.println("Enter the password :");
			pass=scan.next();
			System.out.println("Re-enter the password :");
			rpass=scan.next();
		}while(!pass.equals(rpass));
		u[index]=new User(name,mail,pass);
		index++;	
	}	
	public void signin(){
		System.out.println("Enter the Mail :");
		String mail=scan.next();
		boolean b=true;
		for(int i=0;i<index;i++){
			if(u[i].mail.equals(mail)){
				b=false;
				System.out.println("Enter the password :");
				String pass=scan.next();
				if(u[i].pass.equals(pass))
					home(i);
				else{
					System.out.println("Password is wrong ");
					signin();
					break;
				}
			}
		}
		if(b)
			System.out.println("User not found");
	}
	public void home(int a){
		System.out.println("\n1.Create Group\n2.Group Assignment\n3.Compose Mail\n4.Inbox\n5.Sent Mail\n6.Delete Mail\n7.Recall\n8.Share Inbox\n9.Exit");
		int n=scan.nextInt();
		switch(n){
		case 1: break;
		case 2:	break;
		case 3:	compose(a); break;
		case 4: inbox(a); break;
		case 5:
		case 6:
		case 7:
		case 8:
		default: login(); break;
		}
		home(a);
	}
	public void compose(int a){
		System.out.println("To :");
		String mail=scan.next();
		for(int i=0;i<index;i++){
			if(u[i].mail.equals(mail)){
				System.out.println("Subject : ");
				scan.nextLine();
				String s=scan.nextLine();
				System.out.println("Content : ");
				String str=scan.nextLine();
				u[a].send(s,str);
				u[i].recieve(s,str);
			}
		}
	}
	public void inbox(int a){
		for(int i=u[a].r-1;i>=0;i--){
			System.out.println(u[a].rec[i]);
		}
	}
}