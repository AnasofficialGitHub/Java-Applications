import java.util.Scanner;
public class TollgateApplication{
	public static void main(String[] args){
		home();
	}
	public static void home(){
		Scanner scan = new Scanner(System.in);
		Route r[] = new Route[10];
		int i=0;
		while(true){
		System.out.println("1.Journey\n2.Toll Details\n3.Vehicle Details");
		int n=scan.nextInt();
		switch(n){
		case 1:
			tollpay(r,i);
			break;
		case 2:
			tolldetails();
			break;
		case 3:
			vehicledetails();
			break;
		default:
			home();
		}
		}
	}
	public static void tollpay(Route r[],int i){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Starting place");
		String s =scan.next();
		System.out.println("Enter the destination");
		String e=scan.next();
		r[i]=new Route(s,e);
		System.out.println("Enter the Vehicle Type");
		r[i].type=scan.next();
		System.out.println("Enter the Id");
		r[i].id=scan.nextInt();
		System.out.println("Vip user or not(y/n)");
		char c = scan.next().charAt(0);
		if(c=='y' || c=='Y')
			r[i].vip=true;
		else
			r[i].vip=false;
		while(true){
			if(r[i].travel())
				continue;
			else 
				break;
		}
		System.out.println(r[i].tollpay);
		i++;
	}
	public static void tolldetails(){

	}
	public static void vehicledetails(){

	}
	public static void findroute(){
		
	}
}

class Route{
	String start;
	String end;
	String type;
	int id;
	boolean vip;
	int numtoll;
	int tollpay=0;
	Toll toll;
	public Route(String start,String end){
		this.start=start;
		this.end=end;
		if(start.equals("Chennai"))
			toll=new Chennai();
		else if(start.equals("Chengalpattu"))
			toll=new Chengalpattu();
		else if(start.equals("Vellore"))
			toll=new Vellore();
		else if(start.equals("Thiruvannamalai"))
			toll=new Thiruvannamalai();
		else if(start.equals("Theni"))
			toll=new Theni();
			
	}
	public boolean travel(){
		return toll.pay(this);
	}	
}
abstract class Toll{
	public abstract boolean pay(Route route);
}
class Chennai extends Toll{
	public boolean pay(Route route){
		route.tollpay+=50;
		if(route.end.equals("Chennai"))
			return false;
		else{
			route.toll=new Chengalpattu();
			return true;
		}
	}
}
class Chengalpattu extends Toll{
	public boolean pay(Route route){
		route.tollpay+=40;
		if(route.end.equals("Chengalpattu"))
			return false;
		else{
			route.toll=new Vellore();
			return true;
		}
	}
}
class Vellore extends Toll{
	public boolean pay(Route route){
		route.tollpay+=30;
		if(route.end.equals("Vellore"))
			return false;
		else{
			route.toll=new Thiruvannamalai();
			return true;
		}
	}
}
class Thiruvannamalai extends Toll{
	public boolean pay(Route route){
		route.tollpay+=30;
		if(route.end.equals("Thiruvannamalai"))
			return false;
		else{
			route.toll=new Theni();
			return true;
		}
	}
}
class Theni extends Toll{
	public boolean pay(Route route){
		route.tollpay+=20;
		if(route.end.equals("Theni"))
			return false;
		else{
			route.toll=new Chennai();
			return true;
		}
	}
}