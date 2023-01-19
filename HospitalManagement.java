import java.util.Scanner;
public class HospitalManagement{
	static Hospital[] h = new Hospital[10];
	static int i;
	static int ID=100;
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args){
		System.out.println("1.Entry\n2.Waiting List\n3.Patient Details\n4.Back");
		int n=scan.nextInt();
		switch(n){
		 case 1:
			entry();
			break;
		case 2:
			waiting();
			break;
		case 3:
			patient();
			break;
		default:
			break;
		}
	}
	public static void entry(){
		System.out.println("Enter the name :");
		String nam=scan.next();
		System.out.println("Enter the phone number :");
		long phone = scan.nextLong();
		int a;
		for(a=0;a<i;a++){
			if(h[a].name.equals(nam) && h[a].phno==phone){
				assign(a);
			}
		}
		h[i]= new Hospital(nam,phone,ID);
		ID++;
		i++;
		assign(i-1);
		
	}
	public static void assign(int a){
		System.out.println("Enter the In Time");
		h[a].inTime=scan.nextFloat();
		System.out.println("Enter the doctor time");
		h[a].doctorTime=scan.nextInt();
		h[a].outTime=h[a].inTime;
		prof1(a);
	}
	public static strictfp void prof1(int a){
		float x;
		for(int j=i-1;j>0;j--){
			x=h[j].inTime-h[j-1].inTime;
			if(x<0.05){
				x=0.05f-x;
				//h[a].waitTime=x;
				h[a].outTime=h[a].outTime+x;
			}
			else 
				break;
		}
		h[a].outTime=h[a].outTime+0.10f;
		main(null);
	}	
	public static void waiting(){
	
	}
	public static void patient(){
		for(int j=0;j<i;j++)
			System.out.printf("%s  %d  %d  %.2f  %.2f  %d  %s\n",h[j].name, h[j].id, h[j].phno, h[j].inTime, h[j].outTime, h[j].doctorTime, h[j].disease);
		main(null);
	}
	
}
class Hospital{
	int id;
	String name;
	long phno;
	float inTime;
	float outTime;
	int waitTime;
	int doctorTime;
	String disease;
	public Hospital(String name,long phno,int id){
		this.name=name;
		this.phno=phno;
		this.id=id;
	}
}