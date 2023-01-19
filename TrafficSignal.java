import java.util.Scanner;
public class TrafficSignal{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		Go g = new Go();
		while(true){
			g.go("Green","Red",1);
			scan.next();
			g.go("Red","Green",2);
			scan.next();
		}
	}
}
class Go{
	String str="Red";
	String str1="Green";
	Signal s = new Signal1(str,str1);
	public void go(String str,String str1,int i){
		this.str=str;
		this.str1=str1;
		s.travel(this,i);
	}
}
abstract class Signal{
	public abstract void travel(Go go,int i);
}
class Signal1 extends Signal{
	String NS;
	String EW;
	public Signal1(String NS,String EW){
		this.NS=NS;
		this.EW=EW;
	}
	public void travel(Go go,int i){
		System.out.println("Signal 1 : "+NS+" "+EW);
		if(i==1)
			go.s=new Signal2("Green","Red");
		else
			go.s=new Signal2("Red","Green");
		go.s.travel(go,i);
	}
}
class Signal2 extends Signal{
	String NS;
	String EW;
	public Signal2(String NS, String EW){
		this.NS=NS;
		this.EW=EW;
	}
	public void travel(Go go,int i){
		if(i==1)
			go.s=new Signal3("Red","Green");
		else
			go.s=new Signal3("Green","Red");
		System.out.println("Signal 2 : "+NS+" "+EW);
		go.s.travel(go,i);
	}
}
class Signal3 extends Signal{
	String NS;
	String EW;
	public Signal3(String NS,String EW){
		this.NS=NS;
		this.EW=EW;
	}
	public void travel(Go go,int i){
		if(i==1)
			go.s=new Signal4("Green","Red");
			
		else
			go.s=new Signal4("Red","Green");
		System.out.println("Signal 3 : "+NS+" "+EW);
		go.s.travel(go,i);
	}
}
class Signal4 extends Signal{
	String NS;
	String EW;
	public Signal4(String NS,String EW){
		this.NS=NS;
		this.EW=EW;
	}
	public void travel(Go go,int i){
		if(i==1)
			go.s=new Signal5("Red","Green");
		else 
			go.s=new Signal5("Green","Red");
		System.out.println("Signal 4 : "+NS+" "+EW);
		go.s.travel(go,i);
	}
}
class Signal5 extends Signal{
	String NS;
	String EW;
	public Signal5(String NS,String EW){
		this.NS=NS;
		this.EW=EW;
	}
	public void travel(Go go,int i){
		System.out.println("Signal 5 : "+NS+" "+EW);
		if(i==1)
			go.s=new Signal1("Green","Red");
		else
			go.s=new Signal1("Red","Green");
		
	}
}