import java.util.Scanner;
import java.util.regex.*;
import java.util.List;
import java.util.ArrayList;
public class ZcoinApplication{
	public static void main(String[] args){
		Zcoin z = new ZE();
		z.met();
		
	}
}
class Info{
	String name;
	String mail;
	String mobile;
	int H_ID;
	String pass;
	float RC;
	float zcoin;
	int zid;
	List trans=new ArrayList();
	public Info(String name,String mail,String mobile,int H_ID,String pass,float RC,int zid){
		this.name=name;
		this.mail=mail;
		this.mobile=mobile;
		this.H_ID=H_ID;
		this.pass=pass;
		this.RC=RC;
		this.zid=zid;
	}
	public String toString(){
		return name+" "+mail+" "+mobile+" "+H_ID+" "+RC+" "+zid;
	}
}
class Approval{
	String name;
	String mail;
	String mobile;
	int H_ID;
	String pass;
	int RC;
	public Approval(String name,String mail,String mobile,int H_ID,String pass,int RC){
		this.name=name;
		this.mail=mail;
		this.mobile=mobile;
		this.H_ID=H_ID;
		this.pass=pass;
		this.RC=RC;

	}
	public String toString(){
		return name+" "+mail+" "+mobile+" "+H_ID+" "+RC;
	}
	
}
abstract class Zcoin{
	Scanner scan = new Scanner(System.in);
	static Approval[] app = new Approval[100];
	static Info[] info = new Info[100];
	static int i=0,j=0;
	static float value=2;
	public abstract void signin();
	public abstract void trans(int x);
	public void met(){
		home();
	}
	public void home(){
		ZE a = new ZE();
		ZU u = new ZU();
		System.out.println("1.Agent Login\n2.User Login");
		int n=scan.nextInt();
		if(n==1)
			a.signin();
		else{
			System.out.println("1.Signup\n2.Signin");
			n=scan.nextInt();
			if(n==1)
				u.signup();
			else
				u.signin();
		}	
	}
	public boolean validator(String pass){
		String val="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?).{8,20}$";
		Pattern p = Pattern.compile(val);
		Matcher m = p.matcher(pass);
		if(m.matches()){
			return false;
		}
		else{
			System.out.println("Password must be 2 Capital Letters, 2 Small letters and 2 Numbers");
			return true;
		}
			
	}
}
class ZE extends Zcoin{
	static int zid=58001;
	private String mail = "agentnibila@zohomail.com";
	private String pass = "Nibila@012";
	public void signin(){
		System.out.println("Agent Mail : ");
		String s = scan.next();
		if(s.equals(mail)){
			System.out.println("Password : ");
			s=scan.next();
			if(s.equals(pass)){
				homee();
			}
			else{
				System.out.println("Password is wrong");
				signin();
			}
		}
		else{
			System.out.println("Mail is wrong");
			signin();
		}
	}
	public void homee(){
		System.out.println("1.Approvals\n2.Exit");
		int n=scan.nextInt();
		if(n==1)
			approval();
		else {
			home();
		}
			
	}
	public void trans(int x){
		System.out.println("Name Mail Ph.no H_ID RC");
		for(int a=0;a<j;a++)
			System.out.println((a+1)+" "+info[a]);
		System.out.println("Enter the number to see transaction ");
		int n=scan.nextInt();
		
		
	}
	public void approval(){
		for(int a=0;a<i;a++)
			System.out.println((a+1)+" "+app[a]);
		System.out.println("Enter the number to approve(Enter 0 to exit) : ");
		int n=scan.nextInt();
		if(n==0)
			homee();
		n=n-1;
		info[j]=new Info(app[n].name,app[n].mail,app[n].mobile,app[n].H_ID,app[n].pass,(float)app[n].RC,zid);
		app[n]=null;
		for(int a=n;a<i-1;a++)
			app[a]=app[a+1];
		info[j].zcoin=info[j].RC/value;
		value*=0.1;
		i--;
		j++;
		zid++;
		approval();	
	}
	
}
class ZU extends Zcoin{
	public void signup(){
		System.out.println("Name : ");
		String name=scan.nextLine();
		System.out.println("Email ID : ");
		String mail=scan.next();
		System.out.println("Mobile Number : ");
		String mob=scan.next();
		System.out.println("H_ID : ");
		int id=scan.nextInt();
		boolean b=true;
		String pass="";
		while(b){
		System.out.println("Enter the password : ");
		pass=scan.next();
		if(pass.equals(name))		
			System.out.println("Password should not be same as name");
		else if(pass.equals(mail))
			System.out.println("Password should not be same as mail");
		else if(pass.equals(mob))
			System.out.println("Password should not be same as Mobile");
		else{
			b=validator(pass);
		}
		}
		System.out.println("RC : ");
		int rc=scan.nextInt();
		System.out.println("Signup successfull!! Wait for the approval from the agent ");
		app[i]=new Approval(name,mail,mob,id,pass,rc);
		i++;
		home();
	}
	public void signin(){
		System.out.println("Enter the email");
		String mail=scan.next();
		Label1:
		for(int a=0;a<j;a++){
			if(info[a].mail.equals(mail)){
				System.out.println("Password : ");
				String pass=scan.next();
				if(info[a].pass.equals(pass)){
					homeu(a);
				}
				else{
					System.out.println("password is wrong");
					continue Label1;
				}
			}
			
		}
		for(int a=0;a<i;a++){
			if(app[a].mail.equals(mail)){
				System.out.println("Your status not approved yet!!!");
				break;
			}
		}
		System.out.println("User not found");
		signin();
	}
	public void homeu(int x){
		System.out.println("1.Account Details\n2.Balance\n3.Transaction History\n4.Change Password\n5.RC Transaction\n6.Zcoin Transaction");
		int n=scan.nextInt();
		switch(n){
			case 1:
				
				break;
			case 2:
			
				break;
			case 3:
				trans(x);
				break;
			case 4:
			
				break;
			case 5:
			
				break;
			case 6:	
				
				break;
			default:
				home();
		}
	}
	public void trans(int x){
		
	}
	public void balance(int x){
		System.out.println("RC : "+info[x].RC);
		System.out.println("ZCOIN : "+info[x].zcoin);
	}
}