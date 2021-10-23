import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class senario4 extends Bridge {

	//private boolean blueTurn = false;
	static Lock lock = new ReentrantLock(true);  //lock: xrisimopoiw kleidwma gia na pernaei enas kathe fora thn gefura
	private String color = "ok";                 //color: deixnei poia pleura exei perissoterh kinhsh
	private Condition cond = lock.newCondition();//cond : upeuthini gia to wait 
	Queue<cars> redw = new LinkedList<cars>();   // redw: oura gia ta kokkina amaxia 
	Queue<cars> bluew = new LinkedList<cars>();  // bluew: oura gia ta ble amaxia

	public void redTurn(cars t) {

		redw.add(t);                            // prosethw sthn antistoixh lista to amaxi pou molis exei ftasei
		try {
			cross(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void blueTurn(cars t) {

		bluew.add(t);                       // prosethw sthn antistoixh lista to amaxi pou molis exei ftasei
		try {
			cross(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cross(cars c) throws InterruptedException {
		lock.lock();              //kleidwnw ton koinoxrhsto poro
		
		

		try {
			if (redw.size() - bluew.size() > 1) {    //se  periptwsh pou ta kokkina amaxia einai perissotera kata 2 apo ta ble, tote color=red
				color = "red";
				while (c.getColor() != color) {      //perimene oso to amaxi exei diaforetiko xrwma apo to color
					try {
						cond.await();
					} catch (InterruptedException e) {
					}

				}
			} 
			else if (bluew.size() - redw.size() > 1) { //se  periptwsh pou ta ble amaxia einai perissotera kata 2 apo ta kokkina, tote color=blue
				color = "blue";
				while (c.getColor() != color) {        //perimene oso to amaxi exei diaforetiko xrwma apo to color
					try {
						cond.await();
					} catch (InterruptedException e) {
					}

				}

			}

			System.out.println("                 --------------------------------------");
			System.out.println("                 Now " + c.getColor() + "Car " + c.getId() + " is crossing the bridge "+ redw.size() + "-" + bluew.size());
			System.out.println("                 --------------------------------------");

			c.sleep(300);
	
			if (c.getColor().equals("red"))
				redw.remove(c);                  //afairese to amaxi apo thn lista 
			else
				bluew.remove(c);                  //afairese to amaxi apo thn lista 
			System.out.println("||Now " + c.getColor() + "Car " + c.getId() + " is OUT ||"+ redw.size() + "-" + bluew.size()); //ektupwse to amaxi pou vghke kai to size kathe listas
			
			cond.signalAll();                     //xupnhse tous olous

		} finally {
			lock.unlock();
		}

	}

}
