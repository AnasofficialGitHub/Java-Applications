import java.util.Scanner;
public class Baloon{
	static int r,c;
	static char[][] ch = new char[r][c];
	static Scanner scan = new Scanner(System.in);
	public static void main(String[] args){
		int r,c,i,j;
		System.out.println("Enter the rows and column");
		r=scan.nextInt();
		c=scan.nextInt();
		char yn;
		char[][] ch = new char[r][c];
		for(i=0;i<r;i++)
			for(j=0;j<c;j++)
				ch[i][j]='-';
		while(true){
			fill(ch,r,c);
			show(ch,r,c);
			System.out.println("Do you want to continue(Y/N) :");
			yn = scan.next().charAt(0);
			if(yn=='y' || yn=='Y')
				continue;
			else
				break;
		}	
	}
	public static void show(char ch[][],int r,int c){
		System.out.println("Contents of the matrix : ");
		for(int i=0;i<r;i++){
			for(int j=0;j<c;j++){
				System.out.print(ch[i][j]+" ");
			}
			System.out.println();
		}		
	}
	public static void fill(char ch[][],int r,int c){
		char clr;
		int flag=0;
		System.out.println("Enter the color of the ballon :");
		clr=scan.next().charAt(0);
		System.out.println("Enter the column number :");
		int x=scan.nextInt();
		for(int i=r-1;i>=0;i--){
			for(int j=0;j<c;j++){
				if(x-j>=0 && ch[i][x-j]=='-'){
					ch[i][x-j]=clr;
					flag=1;
					break;
				}
				else if(x+j<c && ch[i][x+j]=='-'){
					ch[i][x+j]=clr;
					flag=1;
					break;
				}
			}
			if(flag==1)
				break;
		}	 
	}
}
