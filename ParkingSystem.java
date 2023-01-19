import java.util.Scanner;
public class ParkingSystem{
	public static void main(String[] args){
		Parking park = new Parking();
		park.home();
		
	}
}
class Vehicle{
	String type;
	String regno;
	String color;
	String ticketid;
	int f,c;
	public Vehicle(String type,String regno,String color,int f,int c){
		this.type=type;
		this.regno=regno;
		this.color=color;
		this.f=f;
		this.c=c;
	}
	public String toString(){
		return color+type;
	}
}
class Parking{
	Vehicle v[][] = new Vehicle[20][5];
	Scanner scan = new Scanner(System.in);
	int m=1;
	public void home(){
		System.out.println("1.Park\n2.Unpark\n3.Display\n4.Exit");
		int no=scan.nextInt();
		switch(no){
			case 1:
				park();
				break;
			case 2:
				unpark();
				break;
			case 3:
				display();
				break;
			case 4:
				return;
		}
		home();
	}
	public void park(){
		System.out.println("Enter the vehicle Type : ");
		String s=scan.next();
		System.out.println("Enter the registration number : ");
		scan.nextLine();
		String no=scan.nextLine();
		System.out.println("Enter the colour of the vehicle : ");
		String c=scan.next();
		if(s.equals("truck")){
			for(int i=0;i<m;i++){
				if(check(i,0,s,c,no)){
					v[i][0].ticketid=tickets(i,0);
					home();
				}
				else if((i+1)==m)
					m++;
			}
		}
		else if(s.equals("bike")){
			for(int i=0;i<m;i++){
				for(int j=1;j<3;j++){
					if(check(i,j,s,c,no)){
						v[i][j].ticketid=tickets(i,j);
						home();
					}
				}
				if((i+1)==m)
					m++;
			}
		}
		else if(s.equals("car")){
			for(int i=0;i<m;i++){
				for(int j=3;j<5;j++){
					if(check(i,j,s,c,no)){
						v[i][j].ticketid=tickets(i,j);
						home();
					}
				}
				if((i+1)==m)
					m++;
			}
		}
	}
	public boolean check(int i,int j,String s,String c,String no){
			if(v[i][j]==null){
				v[i][j]=new Vehicle(s,no,c,i,j);
				return true;
			}
			return false;		
	}
	public void display(){
		System.out.println("1.Free SLot\n2.Occupied Slot\n3.exit");
		int k=scan.nextInt();
		if(k==1){
			System.out.println("Vehicle Type ");
			String s=scan.next();
			typecheck(s,k);
		}
		else if(k==2){
			System.out.println("Vehicle Type ");
			String s=scan.next();
			typecheck(s,k);		
		}
		else if(k==3)
			home();
	}
	public void typecheck(String s,int k){
		if(s.equals("truck")){
			if(k==1) 
				free(0,0,"truck") 
			else
				occ(0,0,"truck");
		}
		else if(s.equals("car")){
			if(k==1) 
				free(3,4,"car") 
			else
				occ(3,4,"car");
		}
		else if(s.equals("bike")){
			if(k==1) 
				free(1,2,"bike") 
			else
				occ(1,2,"bike");
		}
	}
	public void free(int a,int b,String vh){
		for(int i=0;i<m;i++){
			System.out.println("Free slot of "+vh+" on Floor "+(i+1)+" :");
			for(j=a;j<=b;j++){
				if(v[i][j]==null)
					System.out.print((j+1)+",");
			}
		}
		display();			
	}
	public void occ(int a,int b,String vh){
		
	}
	public String tickets(int i,int j){
		String t="PR1234";
		t+="_"+(i+1)+"_"+(j+1);
		System.out.println("Your ticket ID : "+t);
		return t;
	}
	public void unpark(){
		System.out.println("Enter the Ticket ID to unpark : ");
		String s= scan.next();
		for(int i=0;i<m;i++){
			for(int j=0;j<5;j++){
				if(v[i][j]==null)
					continue;
				else if(v[i][j].ticketid.equals(s)){
					v[i][j]=null;
					System.out.println("Unparked Successfully");
					home();
				}
			}
		}
	}
}