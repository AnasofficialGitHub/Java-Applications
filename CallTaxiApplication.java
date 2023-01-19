import java.util.Scanner;
public class CallTaxiApplication{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of taxi :");
		int n = scan.nextInt();
		Point obj = new Point(n);
		obj.home();
		
	}
}
class Customer{
	int cid;
	char p;
	char d;
	int pt;
	int dt;
	int taxi;
	int price=100;
	public Customer(int cid){
		this.cid=cid;
	}
}

class Point{
	int n;
	int cn=0;
	char taxi[] = new char[10];
	Customer c[] = new Customer[10];
	Scanner scan = new Scanner(System.in);
	public Point(int n){
		this.n=n;
		int i;
		for(i=0;i<n;i++)
			taxi[i]='A';
	}
	public void home(){
		System.out.println("1.Taxi Booking\n2.Taxi Details");
		int b=scan.nextInt();
		if(b==1)
			travel();
		else if(b==2)
			taxidetails();
	}
	public void travel(){
		int i,j;
		System.out.println("Customer ID : ");
		int id=scan.nextInt();
		c[cn]=new Customer(id);
		System.out.println("Pickup Point : ");
		c[cn].p = scan.next().charAt(0);	
		System.out.println("Drop Point : ");
		c[cn].d = scan.next().charAt(0);	
		System.out.println("Pickup Time : ");
		c[cn].pt = scan.nextInt();	
		for(j=0;j<6;j++){
			for(i=0;i<n;i++){
			if(taxi[i]==c[cn].p+j || taxi[i]==c[cn].p-j){
				if(time(i)){
				System.out.println("taxi "+(i+1)+" is allocated");
				c[cn].taxi=i;
				calc();
				taxi[i]=c[cn].d;
				cn++;
				home();
				}
			}
			}
		}
		System.out.println("No taxi available");
		home();
	}
	public void calc(){
		int count=0,i;
		if(c[cn].p-c[cn].d>=0)
			count=c[cn].p-c[cn].d;
		else if(c[cn].d-c[cn].p>=0)
			count=c[cn].d-c[cn].p;
		c[cn].dt=c[cn].pt+count;
		for(i=6;i<=15*count;i++)
			c[cn].price=c[cn].price+10;	
		System.out.println("Price : "+c[cn].price);
	}
	public void taxidetails(){
		int i,j,earn;
		System.out.println("BookingID CustomerID From To PickupTime DropTime Amount");
		for(i=0;i<n;i++){
		earn=0;
		for(j=0;j<cn;j++){
			if(c[j].taxi==i){
				System.out.println(c[j].cid+"\t"+c[j].cid+"\t"+c[j].p+"\t"+c[j].d+"\t"+c[j].pt+"\t"+c[j].dt+"\t"+c[j].price);
				earn=earn+c[j].price;
			}
		}
		System.out.println("\nTaxi-"+(i+1)+"\t\t"+"Total Earnings: Rs."+earn);
		}
		home();
	}
	public boolean time(int i){
		int j,flag=0;
		for(j=cn-1;j>=0;j--){
			if(c[j].taxi==i){
				flag=1;
				if(c[j].dt<=c[cn].pt){
					return true;
				}
				break;
			}
		}  
		if(flag==0)
			return true;
		return false;
	}
}