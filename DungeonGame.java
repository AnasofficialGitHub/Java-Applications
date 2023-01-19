import java.util.Scanner;
public class DungeonGame{
	public static void main(String[] args){
		Dungeon d = new Dungeon();
		d.game();	
	}
}
class Dungeon{
	public void game(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the dimensions");
		int m=scan.nextInt();
		int n=scan.nextInt();
		String path[][]=new String[m][n];
		System.out.println("Enter the position of adventure");
		int am=scan.nextInt();
		int an=scan.nextInt();
		path[am][an]="A";
		System.out.println("Enter the position of gold");
		int gm=scan.nextInt();
		int gn=scan.nextInt();
		System.out.println("Enter the Monster position");
		int mm=scan.nextInt();
		int mn=scan.nextInt();
		path[mm][mn]="M";
		path[gm][gn]="G";
		String s;
		System.out.print("Path : "+am+","+an);
		
		
	}
	public void shortpath(int am, int an, int m, int n,String s){
		int i,j=an,count=0;
		for(i=am;i<m;){
			if(i==gm){
				for(j=an;j<n;){
					if(j==gn){
						return count;
					}
					else if(j<=gn && j<n && j>=0){
						j++;
						System.out.print(" "+i+","+j);
						count++;	
					}
					else if(j>=gn && j<n && j>=0){
						j--;
						System.out.print(" "+i+","+j);
						count++;
					}
				}
			}
			else if(i<=gm && i<m && i>=0){
				i++;
				System.out.print(" "+i+","+j);
				count++;	
			}
			else if(i>=gm && i<m && i>=0){
				i--;
				System.out.print(" "+i+","+j);	
				count++;
			}
		}
	}
}