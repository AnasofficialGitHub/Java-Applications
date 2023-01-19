import java.util.Scanner;
public class LiftManagement{
	static int arr[] = new int[5];
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args){
		display();
	}
	public static void home(){
		int cf,gf,mm;
		System.out.println("Current Floor : ");
		cf=scan.nextInt();
		System.out.println("Going Floor : ");
		gf=scan.nextInt();
		System.out.println("Members : ");
		mm=scan.nextInt();
		assign2(cf,gf,mm);
			
	}
	public static void display(){
		System.out.println("Lift  : L1 L2 L3 L4 L5");
		System.out.print("Floor : ");
		for(int i=0;i<5;i++)
			System.out.print(arr[i]+"  ");
		System.out.println();
		home();
	} 
	public static void assign(int a, int b, int c){
		int i,j,flag=0;
		for(j=0;j<=10;j++){
			if(a>=0 && a<=5 && b>=0 && b<=5){
			for(i=0;i<=1;i++){
				if(a+j==arr[i] || a-j==arr[i] && arr[i] != -1){
					if(i==0 && c<=5)
						floor(i,b);
					else if(i==1 && c<=10)
						floor(i,b);
				}
			}
			//i=4;
			//fifthfloor(i,j,a,b);
			}	
			else if(a>=6 && a<=10 && b>=6 && b<=10 || a==0 || b==0){
				for(i=2;i<=3;i++){
					if(a+j==arr[i] || a-j==arr[i] && arr[i] != -1)
						if(i==2 && c<=5)
							floor(i,b);
						else if(i==3 && c<=10)
							floor(i,b);
				}
				//fifthfloor(i,j,a,b);
			}
			else
				fifthfloor(4,j,a,b);
		}
	}
	public static void fifthfloor(int i, int j, int a, int b){
		for(i=4;i<=4;i++){
			if(a+j==arr[i] || a-j==arr[i] && arr[i] != -1){
				floor(i,b);
			}
		}
	}
	public static void floor(int i, int b){
		arr[i]=b;
		System.out.println("*****L"+(i+1)+" assigned*****");
		display();
	}
	public static void assign2(int a, int b, int c){
		int i,j,flag=0;
		for(j=0;j<=10;j++){
			if(a>=0 && a<=5 && b>=0 && b<=5){
			for(i=0;i<=1;i++){
				if(a+j==arr[i] || a-j==arr[i] && arr[i] != -1){
					if(i==0 && c<=5)
						floor(i,b);
					else if(i==1 && c<=10)
						floor(i,b);
				}
			}
			i=4;
			fifthfloor(i,j,a,b);
			}	
			else if(a>=6 && a<=10 && b>=6 && b<=10 || a==0 || b==0){
				for(i=2;i<=3;i++){
					if(a+j==arr[i] || a-j==arr[i] && arr[i] != -1)
						if(i==2 && c<=5)
							floor(i,b);
						else if(i==3 && c<=10)
							floor(i,b);
				}
				fifthfloor(i,j,a,b);
			}
			else
				fifthfloor(4,j,a,b);
		}
	}
}
		