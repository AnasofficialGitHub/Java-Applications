import java.util.Scanner;
public class BallBrick{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the size of N x N matrix");
		int n=scan.nextInt();
		Game g = new Game(n);
		g.home();
	}
	
}
class Game{
	int n,count,counter;
	String table[][]= new String[10][10];
	public Game(int n){
		this.n=n;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i==0 || j==0 || j==n-1)
					table[i][j]="W";
				else if(i==n-1 && j==n/2)
					table[i][j]="0";
				else if(i==n-1)
					table[i][j]="G";
				else
					table[i][j]=" ";
			}
		}
	}
	public void home(){
		Scanner scan=new Scanner(System.in);
		int r,c;
		String brick;
		char exit='Y';
		while(exit=='Y' || exit=='y'){
			System.out.println("Enter the brick position and Brick Type : ");
			r=scan.nextInt();
			c=scan.nextInt();
			brick = scan.next();
			if(table[r][c]==" ")
				table[r][c]=brick;
			else{
				System.out.println("Position invalid");
				continue;
			}
			counter++;
			System.out.println("Do you want to continue(Y/N) : ");
			exit = scan.next().charAt(0);
		}
		System.out.println("enter the ball count");
		count=scan.nextInt();
		view();
		play();
	}
	public void view(){
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				System.out.print(table[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("ball count is "+count);
	}
	public void play(){
		Scanner scan = new Scanner(System.in);
		String dir;
		System.out.println("Enter the direction : ");
		dir=scan.next();
		int i,j;
		for(i=n-1,j=1;j<n;j++){
			if(table[i][j]=="0"){
				break;
			}
		}
		if(dir.equals("ST"))
			straight(i,j);
		else if(dir.equals("LD"))
			left(i,j);
		else if(dir.equals("RD"))
			right(i,j);
		view();
		if(counter==0){
			System.out.println("Hurray !!! Your the winner");
			return;
		}
		else if(count==0){
			System.out.println("Game Over");
			return;
		}
		else 
			play();
	}
	public void straight(int i, int j){
		i--;
		while(i<n && i>=0){
			if(table[i][j].equals(" "))
				i--;
			else if(table[i][j].charAt(0)>47 && table[i][j].charAt(0)<=57){
				attack(i,j);
				break;
			}
			else if(table[i][j].equals("W"))
				break;
		}	
	}
	public void right(int i,int j){
		--i;++j;
		while(j<n && j>=0){
			if(j==n-1){
				j--;
				while(j>=0){
					if(table[i][j].equals(" "))
						j--;
					else if(table[i][j].charAt(0)>47 && table[i][j].charAt(0)<=57){
						attack(i,j);
						break;
					}
					else if(j==0){
						count--;
						break;
					}
				}
				break;
			}
			else if(table[i][j].equals(" ")){
				i--;j++;
			}
			else if(table[i][j].charAt(0)>47 && table[i][j].charAt(0)<=57){
				attack(i,j);
				break;
			}
		}
	}
	public void left(int i, int j){
		--i;--j;
		while(j<n && j>=0){
			if(j==0){
				j++;
				while(j<n){
					if(table[i][j].equals(" "))
						j++;
					else if(table[i][j].charAt(0)>47 && table[i][j].charAt(0)<=57){
						attack(i,j);
						break;
					}
					else if(table[i][j].charAt(0)=='D' && table[i][j].charAt(1)=='E'){
						
					}
					else if(j==n-1){
						count--;
						break;
					}
				}
				break;
			}
			else if(table[i][j].equals(" ")){
				i--;j--;
			}
			else if(table[i][j].charAt(0)>47 && table[i][j].charAt(0)<=57){
				attack(i,j);
				break;
			}
		}
	}
	public void attack(int i, int j){
		char c;
		c=table[i][j].charAt(0);
		--c;
		table[i][j]="";
		table[i][j]+=c;
		if(table[i][j].charAt(0)=='0'){
			table[i][j]=" ";
			counter--;
		}
		if(table[n-1][j].equals("G")){
			count--;
			for(int a=1;a<n-1;a++)
				table[n-1][a]="G";
			table[n-1][j]="0";
		}
	}
}