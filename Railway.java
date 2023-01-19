import java.util.Scanner;
public class Railway{
	public static void main(String[] args){
		Home main = new Home();
		main.home();
	}
}
class Home
{
	String name[] = new String[20];
	int age[] = new int[20];
	char gender[] = new char[20];
	char pref[] = new char[20];
	char alloc[] = new char[20];
	String place[] = new String[20];
	int pnr[] = new int[20];
	int index=0;
	int lower=2;
	int upper=2;
	int middle=2;
	int rac=2;
	int wait=2;
	int pnrr=1;
	int ticket=10;
	Scanner scan = new Scanner(System.in);
	public void home(){
		System.out.println("1.Book\n2.Available\n3.print\n4.cancel\n5.Chat");
		int in = scan.nextInt();
		if(in==1)
			booking();
		else if(in==2)
			available();
		else if(in==3)
			print();
		else if(in==4)
			cancel();
		else if(in==5)
			allticket();
	}
	public void available(){
		System.out.println("Lower Tickets : "+lower);
		System.out.println("Upper Tickets : "+upper);
		System.out.println("Middle Tickets : "+middle);
		System.out.println("RAAC Tickets : "+rac);
		System.out.println("Waiting List : "+wait);
		home();
	}
	public void booking(){
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Number of tickets");
		int n = scan.nextInt();
		if(ticket<n) {
			System.out.println("All tickets are booked");
			home();
		}
		if(lower>0 || upper>0 || middle>0 || rac>0 || wait>0 ){
		for(int i=0;i<n;i++){
			pnr[index]=pnrr;
			System.out.println("Name :");
			name[index]=scan.nextLine();
			name[index]=scan.nextLine();
			System.out.println("Age :");
			age[index]=scan.nextInt();
			System.out.println("Place:");
			place[index]=scan.next();
			do{
				System.out.println("Gender(m/f) :");
				gender[index]=scan.next().charAt(0);
			}while(gender[index]!='m' && gender[index]!='f');
			do{
				System.out.println("Berth Preference(l/u/m) :");
				pref[index]=scan.next().charAt(0);
			}while(pref[index]!='l' && pref[index]!='u' && pref[index]!='m');
			if(age[index]>=60)
				lopreference();
			else if(age[index]<=5){
				alloc[index]='-';
				index++;
				continue;
			}
			else if(pref[index]=='l')
				lopreference();
			else if(pref[index]=='m')
				mipreference();
			else if(pref[index]=='u')
				uppreference();
			index++;
		}
			System.out.println("Ticket is Booked Your pnr number is "+pnrr);
			pnrr++;
		}
		else
			System.out.println("All tickets are booked");
		home();
		
	}
	public void lopreference(){
		if(lower>0){
			alloc[index]='l';
			lower--;
		}
		else if(middle>0){
			alloc[index]='m';
			middle--;
		}
		else if(upper>0){
			alloc[index]='u';
			upper--;
		}
		else if(rac>0){
			alloc[index]='r';
			rac--;
		}
		else if(wait>0){
			alloc[index]='w';
			wait--;
		}
		else{
			System.out.println("All tickets are booked");
			home();	
		}
	}
	public void mipreference(){
		if(middle>0){
			alloc[index]='m';
			middle--;
		}
		else if(lower>0){
			alloc[index]='l';
			lower--;
		}
		else if(upper>0){
			alloc[index]='u';
			upper--;
		}
		else if(rac>0){
			alloc[index]='r';
			rac--;
		}
		else if(wait>0){
			alloc[index]='w';
			wait--;
		}
		else{
			System.out.println("All tickets are booked");
			home();	
		}
	}
	public void uppreference(){
		if(upper>0){
			alloc[index]='u';
			upper--;
		}
		else if(middle>0){
			alloc[index]='m';
			middle--;
		}
		else if(lower>0){
			alloc[index]='l';
			lower--;
		}
		else if(rac>0){
			alloc[index]='r';
			rac--;
		}
		else if(wait>0){
			alloc[index]='w';
			wait--;
		}
		else{
			System.out.println("All tickets are booked");
			home();	
		}
	}
	public void cancel(){
		int i,j,k,count=0;
		System.out.println("Enter the pnr number you want to cancel");
		int can = scan.nextInt();
		for(i=0,j=1;i<index;i++,j++){
			if(pnr[i]==can){
				System.out.println(j+".\t"+pnr[i]+"\t"+name[i]+"\t"+age[i]+"\t"+gender[i]+"\t"+place[i]+"\t"+alloc[i]);
				count++;
			}
		}
		System.out.println("Enter the name you want to delete");
		String nam=scan.next();
		//nam=scan.nextLine();
		//while(count>0){
		for(i=0;i<10;i++){
			if(nam.equals(name[i]) && age[i]>5){
				if(rac<2){
				for(j=0;j<index;j++){
					if(alloc[j]=='r'){
						pnr[i]=pnr[j];
						name[i]=name[j];
						age[i]=age[j];
						pref[i]=pref[j];
						gender[i]=gender[j];
						place[i]=place[j];
						for(k=j;k<index;k++){
							pnr[k]=pnr[k+1];
							name[k]=name[k+1];
							age[k]=age[k+1];
							pref[k]=pref[k+1];
							gender[k]=gender[k+1];
							place[k]=place[k+1];
						}
						if(wait<2)
							wait++;
						else
							rac++;
						break;
					}
								
				}
				}
				else{
					if(alloc[i]=='l')
						lower++;
					else if (alloc[i]=='m')
						middle++;
					else if(alloc[i]=='u')
						upper++;
					for(j=i;j<index;j++){
						pnr[j]=pnr[j+1];
						name[j]=name[j+1];
						age[j]=age[j+1];
						pref[j]=pref[j+1];
						gender[j]=gender[j+1];
						place[j]=place[j+1];
						alloc[j]=alloc[j+1];
					}
				}
					
			}
			else if(nam.equals(name[i]) && age[i]<=5){
				for(j=i;j<index;j++){
					pnr[j]=pnr[j+1];
					name[j]=name[j+1];
					age[j]=age[j+1];
					pref[j]=pref[j+1];
					gender[j]=gender[j+1];
					place[j]=place[j+1];
					alloc[j]=alloc[j+1];
				}
			}
		}
			//count--;
			index--;
		//}
		home();	
	}
	public void print(){
		System.out.println("Enter the pnr number to print the ticket");
		int p = scan.nextInt();
		for(int i=0;i<index;i++){
			if(pnr[i]==p){
				System.out.println("PNR Number : "+pnr[i]);
				System.out.println("Name : "+name[i]);
				System.out.println("Age : "+age[i]);
				System.out.println("Gender : "+gender[i]);
				System.out.println("Allocated Berth : "+alloc[i]);
				System.out.println("Place : "+place[i]);
				if(age[i]<=5)
					System.out.println("Fair : "+200);
				else
					System.out.println("Fair : "+400);
				System.out.println("-----X-----X-----X-----");
			}
		}
		home();
	}
	public void allticket(){
			System.out.println("S.no\tPNR\tName\tAge\tGender\tPlace\tBerth");
		for(int i=0,j=1;i<index;i++,j++)
			System.out.println(j+".\t"+pnr[i]+"\t"+name[i]+"\t"+age[i]+"\t"+gender[i]+"\t"+place[i]+"\t"+alloc[i]);
		home();
	}
}