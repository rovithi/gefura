import java.util.Timer;
/*
 * senario 2 : ta amaxia pernane me asfaleia . 
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class senario2 extends Bridge {

	
	static Lock lock = new ReentrantLock(true);  //xreiazomai to kleidwma gia na pernaei enas kathe fora apo thn gefura
                                                 // iso me true gia na einai dikaih h dieleush.	
	                                             // (to prosthesa epeita apo thn suzhthsh sto ergasthrio, den to brhka se kapoio site)

	public void redTurn(cars t) {

		try {

			cross(t);                       //kalw thn methodo cross ( ton koinoxrhsto poro)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void blueTurn(cars t) {

		try {

			cross(t);                      //kalw thn methodo cross ( ton koinoxrhsto poro)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void cross(cars t) throws InterruptedException {
		lock.lock();                   //kathe fora enas katalamvanei to kleidwma 
		                               // epeidh exw thesei true to kleidwma, tha perasoun me thn seira pou tha ftasoun.
		try {
			System.out.println("                 --------------------------------------");
			System.out.println("                 Now " + t.getColor() + "Car " + t.getId() + " is crossing the bridge "); //ektupwnw otan ena amaxi pernaei thn gefura 
			System.out.println("                 --------------------------------------");
			
			t.sleep(300);              //opws fainetai kai sto applet tou Imerial, ola ta amaxia diasxuzoun me thn idia taxuthta thn gefura.

		} finally {

			
			System.out.println("||Now " + t.getColor() + "Car " + t.getId() + " is OUT ||"); // ligo prin to unlock ektupwnw oti to amaxi perase thn gefura. 
			lock.unlock();

		}
	}

}
