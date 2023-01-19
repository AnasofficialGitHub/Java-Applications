import java.util.Scanner;
public class Library
{	
	public static void main(String[] args){
		Login obj = new Login();
		obj.home();	
	}
}
class Login
{
	static String username = new String();
	static String userpwd = new String();
	public static void home(){
		Scanner scan = new Scanner(System.in);
		System.out.println("---------------Home---------------");
		System.out.println("\nAdmin\nUser\n");
		int i;
		String s = scan.next();
		Admin ad = new Admin();
		User us = new User();
		switch(s){
		case "Admin":
			String adminname = "Nibila";
			String adminpwd = "Nibila@012";
			System.out.println("1.Signin");
			i=scan.nextInt();
			if(i==1){
				signin(adminname, adminpwd);
				ad.adminhome();
			}
			else
				System.out.println("Enter the correct input");
			break;
		case "User":
			System.out.println("1.Signup\n2.Signin");
			i=scan.nextInt();
			if(i==1)
				signup(username, userpwd);
			else if(i==2)
				signin(username, userpwd);
			else
				System.out.println("Enter the correct input");
			us.userhome();
			break;
		default:
			System.out.println("Invalid input!!!");
			break;	
		}
	}
	public static void signin(String name, String pass){
		System.out.println("\n**********Signin Page**********\n");
		Scanner scan = new Scanner(System.in);
		System.out.println("User Name :");
		String a = scan.nextLine();
		if(a.equals(name)){
			System.out.println("Password : ");
			String b = scan.next();
			if(b.equals(pass)){
				System.out.println("\nSignin successfully\n");
				System.out.print("Welcome "+a+"\n");
			}
			else {
				System.out.println("Password is Wrong");
				signin(name, pass);
			}			
		}
		else{
			System.out.println("User name is wrong");
			signin(name,pass);
		}	
	}
	public static void signup(String name, String pass){
		System.out.println("\n***Signup Page**");
		Scanner scan = new Scanner(System.in);
		System.out.println("User Name : ");
		name=scan.nextLine();
		System.out.println("Note:minimum 1 small letter, 1 capital letter, 1 special character and 1 number");
		int n=0;
		while(n==0){
			System.out.println("\nPassword :");
			pass= scan.next();
			n=validator(pass);	
		}
		System.out.println("\nSignup Successfull\n");
		signin(name, pass);				
	}
	public static int validator(String p){
   		int special=0,number=0,lower=0,upper=0;
 		char a[]=p.toCharArray();
            	if(a.length>=8){
       			for(int i=0;i<a.length;i++){
    				 if(a[i]>=35 && a[i]<=46 || a[i]==64)
				 	special++;
     				 else if(a[i]>=48 && a[i]<=57)
					number++;
     			 	 else if(a[i]>=65 && a[i]<=90)
					upper++;
    				 else if(a[i]>=97 && a[i]<=122)
					lower++;
		        }
		}
    		else{
      			System.out.println("Password Must be minimum 8 characters");
         		return 0;	
                }
		if(special>=1 && number>=1 && upper>=1 && lower>=1){
			return 1;
     		}
		else{
			System.out.println("Note:minimum 1 small letter, 1 capital letter, 1 special character and 1 number");
			return 0;
		}
	}	
}
class Admin extends Login
{
	Scanner scan = new Scanner(System.in);
	static String book[] = new String[50];
	static int id[] = new int[50];
	static int price[] = new int[50];
	static int stock[] = new int[50];
	static int n=0;
	public void adminhome(){
		System.out.println("-----------Admin Home----------\n");
		Login log = new Login();
		System.out.println("1.View\n2.Add\n3.Update\n4.Delete\n5.back");
		int i = scan.nextInt();
		switch(i){
		case 1:
			System.out.println("---------------Books---------------");
			view();		
			System.out.println("Press any number to exit");
			int b= scan.nextInt();
			if(b>=0)
				adminhome();
			break;
		case 2:
			add();
			break;
		case 3:
			update();
			break;
		case 4:
			delete();
			break;
		case 5:
			log.home();
			break;
		default:
			System.out.println("Enter the correct number ");
			adminhome();
		}
	}
	public void view(){
		System.out.println("S.no\tBook Name\tBook ID\tPrice\tStock");
		for(int a=0;a<n;a++)
			System.out.println((a+1)+".\t"+book[a]+"\t"+id[a]+"\t"+price[a]+"\t"+stock[a]);	
	}
	private void add(){
		System.out.println("---------------Add Books---------------");
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Enter the number of books ");
		int num=scan.nextInt();
		for(int i=n;i<n+num;i++){
			System.out.println("Enter the book id");
			id[i]=scan.nextInt();
			System.out.println("Enter the book");
			book[i]=scan1.nextLine();
			System.out.println("Enter the book price ");
			price[i]=scan.nextInt();
			System.out.println("Enter the stock ");
			stock[i]=scan.nextInt();
			
		}
		n=n+num; 
		adminhome();
	}
	private void delete(){
		System.out.println("---------------Delete Books---------------");
		view();	
		Admin obj = new Admin();
		System.out.println("Press the number of the book to delete");
		int del=scan.nextInt();
		del=del-1;
		String s = book[del];
		for(int a=0;a<n-1;a++){
			if(book[a] == s){
				String temp = book[a];
				book[a]=book[a+1];
				book[a+1]=temp;
				//obj.swap(id[a],id[a+1]);
				//obj.swap(price[a],price[a+1]);
				//obj.swap(stock[a],stock[a+1]);
				int temp1 = id[a];
				id[a]=id[a+1];
				id[a+1]=temp1;
				temp1 = price[a];
				price[a]=price[a+1];
				price[a+1]=temp1;
				temp1 = stock[a];
				stock[a]=stock[a+1];
				stock[a+1]=temp1;
			}
		}
		book[n-1]="";
		n--;
		System.out.println("Deleted Successfully\n");
		adminhome();
	}
	void swap(int x, int y){
		int temp=x;
		x=y;
		y=temp;
	}
	private void update(){
		System.out.println("---------------Update---------------");
		view();
		Scanner scan1 = new Scanner(System.in);
		System.out.println("1.Edit ID\n2.Change Book Name\n3.Change stock\n4.Change Price\n5.Back");
		int x=scan.nextInt();
		if(x==5)
			adminhome();
		System.out.println("Enter the number of book you want to edit");
		int edit = scan.nextInt();
		edit = edit-1;
		switch(x){
		case 1:
			System.out.println("Enter the new id");
			id[edit]=scan.nextInt();
			break;
		case 2:	
			System.out.println("Enter the book name");
			book[edit]=scan1.nextLine();
			break;
		case 3:
			System.out.println("Add the new stock");
			int add = scan.nextInt();
			stock[edit]=stock[edit]+add;
			break;
		case 4:
			System.out.println("Enter the new price");
			price[edit]=scan.nextInt();
			break;
		default:
			System.out.println("Enter the correct Number");
			break;
		}
		System.out.println("Updated Successfully");
		update();	
	}	
}
class User extends Login
{
	Scanner scan = new Scanner(System.in);
	Admin obj = new Admin();
	public void userhome(){
		System.out.println("-----------User Home----------");
		Login log = new Login();
		System.out.println("1.View\n2.Buy\n3.Read\n4.Back");
		int i = scan.nextInt();
		switch(i){
		case 1:
			System.out.println("---------------Books---------------");
			obj.view();		
			System.out.println("Press any number to exit");
			int z= scan.nextInt();
			if(z>=0)
				userhome();
			break;
		case 2:
			buy();
			break;
		case 3:
			read();
			break;
		case 4:
			log.home();
			break;
		default:
			System.out.println("Enter the correct number ");
			userhome();
		}
	}
	static String userbook[] = new String[50];
	static int num=0;
	public void buy(){
		System.out.println("---------------Buy Books---------------");
		obj.view();
		System.out.println("Enter the number you want to buy");
		int i = scan.nextInt();
		i = i-1;
		obj.stock[i] = obj.stock[i] - 1;
		userbook[num]=obj.book[i];
		num++;
		userhome();	
	}
	private void read(){
		System.out.println("---------------Your Books---------------");
		for(int j=0;j<num;j++)
			System.out.println((j+1)+"."+userbook[j]);
		System.out.println("Enter the book you want to Read\nPress 0 to exit");
		int k = scan.nextInt();
		if(k==0)
			userhome();
		k=k-1;
		if(k<num){
			System.out.println("**********"+userbook[k]+"*********");
			System.out.println("This book is writen by Mohamed Anas. He is future Zoho employee. This whole application is developed by him.\n");
			System.out.println("Press any number to exit");
			int y= scan.nextInt();
			if(y>=0)
				read();
		}
		else{
			System.out.println("You have only "+(num-1)+" Books");
			read();
		}	
	}
}