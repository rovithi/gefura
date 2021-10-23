import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
 * senario 3 : ta autokinhta pernane en allax.
 */
public class senario3 extends Bridge {

    private boolean isemptyr = false; //isepmptyred=true den uparxei kanena amaxi apo thn kokkinh pleura
	private boolean isemptyb = false; //isemptyblue=true den uparxei kanena amaxi apo thn ble pleura

	static Lock lock = new ReentrantLock(true);      //lock : xreiazomai to kleidwma gia na pernaei enas kathe fora.
	private int arrived = 0;                        //arrived: einai gia to 1o amaxi pou tha perasei.Autos 8a orisei kai thn seira pou tha perasoun ta amaxia
	private String color;                          //color : to kathe amaxi pou egkatalupei thn gefura apo apothikeuei to xrwma tou sthn metavlhth color.
	private Condition cond = lock.newCondition(); //cond: prokeimenou ta amaxia na perimenoun an den einai h seira tous
	Queue<cars> redw = new LinkedList<cars>();   //redw: oura twn kokkinwn autokinhtwn 
	Queue<cars> bluew = new LinkedList<cars>(); //blew: oura twn ble autokinhtwn

	public void redTurn(cars t) {
		
       redw.add(t);                            // opoio kokkino amaxi erxetai bainei sthn oura 
		try {
			cross(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void blueTurn(cars t) {
        bluew.add(t);                          // opoio ble amaxi erxetai bainei sthn oura 
		try {
			cross(t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void cross(cars c) throws InterruptedException {

		lock.lock();                            
		try {
			arrived++;                //metrhths tou posoi pernane thn gefura
			if (arrived == 1)         //an einai arrived= 1 einai o prwtos ara ton afhnoyme na perasei 
				color = c.getColor(); 
			
			
			while (arrived > 1 && c.getColor().equals(color) /*(!isemptyr && !isemptyb)*/ ) { 
				/*
				 *  oso to amaxi pou ftanei: 1) den einai to prwto 
				 *  2) exei to idio xrwma me to prohgoumeno
				 * 
				 *  WAIT!
				 */
				
				try {
					cond.await();
				} catch (InterruptedException e) {}
			}

			System.out.println("                 --------------------------------------");
			System.out.println("                 Now " + c.getColor() + "Car " + c.getId() + " is crossing the bridge ");
			System.out.println("                 --------------------------------------");

			c.sleep(300);
			if (c.getColor().equals("red"))
				redw.remove(c);                     //afairese to amaxi pou perase  apo thn lista
			else
				bluew.remove(c);
			
			System.out.println("||Now " + c.getColor() + "Car " + c.getId() + " is OUT ");
			color = c.getColor();     // apothikeuse sto color to xrwma tou autokinhtou pou perase
			
			/*if(redw.size()==0)
				isemptyr=true;      //ean h kokkinh oura einai adia, tote na perasoun oloi oi ble !
			else
				isemptyr=false;
			if(bluew.size()==0)
				isemptyb=true;     // ean h ble oura einai adia , tote na perasoun oloi oi kokkinoi!
			else 
				isemptyb=false;
			*/
			cond.signalAll();                   // xupna tous olous 
			
			

		} finally {
			lock.unlock();
		}

	}
}
