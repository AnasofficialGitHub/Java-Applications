import java.util.Scanner;
public class TicTacToe{
	static Scanner scan = new Scanner(System.in);
	static char xo[][] = new char[3][3];
	static char u1,u2;
	public static void main(String[] args){
		int i,j;
		char k=49;
		for(i=0;i<3;i++)
			for(j=0;j<3;j++,k++)
				xo[i][j]=k;
		System.out.println("\t\tWelcome to Tic-Tac-Toe");
		System.out.println("Choose X or O");
		u1 = scan.next().charAt(0);
		if(u1=='X')
			u2='O';
		else
			u2='X';
		home();

	}
	public static void home(){
		int i,j,slot;
		view();
		if(check(u2)){
			System.out.println(u2+" is winner\n Thanks for playing");
			main(null);
		}
		else if(tie()){
			System.out.println(" Match Tied \n Thanks for playing");
			main(null);
		}
		do{
		System.out.println(u1+"'s Turn : ");
		slot=scan.nextInt();
		}while(slot(slot+48,u1));
		view();
		if(check(u1)){
			System.out.println(u1+" is winner\n Thanks for playing");
			main(null);
		}
		else if(tie()){
			System.out.println(" Match Tied \n Thanks for playing");
			main(null);
		}	
		do{
		System.out.println(u2+"'s Turn : ");
		slot=scan.nextInt();
		}while(slot(slot+48,u2));
		home();
		
	}
	public static boolean slot(int s,char u){
		int i,j,flag=0;
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				if(xo[i][j]==s){
					xo[i][j]=u;
					flag=1;
					return false;
				}
			}
		}
		if(flag==0){
			System.out.println("Slot is occupied enter again ");
			return true;
		}
		else 
			return false;
	}
	public static void view(){
		int i,j;
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				System.out.print(xo[i][j]+" ");
			}
			System.out.println();
		}
	}
	public static boolean check(char u){
		int i,j,count;
		for(i=0;i<3;i++){
			count=0;
			for(j=0;j<3;j++){
				if(xo[i][j]==u)
					count++;
			}
			if(count==3)
				return true;
		}
		for(j=0;j<3;j++){
			count=0;
			for(i=0;i<3;i++){
				if(xo[i][j]==u)
					count++;
			}
			if(count==3)
				return true;
		}
		count=0;
		for(i=0,j=0;i<3 && j<3;i++,j++)
			if(xo[i][j]==u)
				count++;
		if(count==3)
			return true;
		count=0;
		for(i=0,j=2;i<3 && j>=0;i++,j--)
			if(xo[i][j]==u)
				count++;
		if(count==3)
			return true;
		return false;
	}
	public static boolean tie(){
		int i,j,flag=0;
		for(i=0;i<3;i++){
			for(j=0;j<3;j++){
				if(xo[i][j]>=49 && xo[i][j]<=57)
					return false;
			}
		}
		return true;
	}
}