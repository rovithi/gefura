import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*
 * dimiourgw mono mia class nhmatwn,thn cars,pou tha dexetai:  1) to id tou autokinhtou 2) to xrwma 
 * apo to opoio tha katalavainw se poia pleura tha vrisketai 3)thn gefura-senario pou thelei na ulopoisei o xrhsths
 */
public class cars extends Thread {
	private int id;
	public String color;
	private Bridge b;

	

	public cars(int id, String color, Bridge b) {
		this.id = id;
		this.color = color;
		this.b = b;

	}

	public long getId() {
		return id;
	}

	public String getColor() {
		return color;
	}

	public void run() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss"); // Entoles gia na krathsw thn wra pou tha
		LocalDateTime now = LocalDateTime.now();                        // ftasei kathe autokinhto (stackoverflow).

		if (this.getColor() == "red") {
			try {
				this.sleep((int) (Math.random() * 5000));             //prosomoiwnw thn kathisterisi pou erxontai ta autokinhta me to sleep
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			System.out.println("RED CAR ARRIVED " + this.getId() + " AT TIME " + dtf.format(now));  //ektupwnetai mhnuma oti eftase ena kokkino amaxi kai thn wra pou eftase
			b.redTurn(this);                 //efoson to amaxi einai kokkino kalw thn methodo redturn pou einai ulopoihmenh se kathe senario!
		} else {
			
			
			try {
				this.sleep((int) (Math.random() * 5000));  //idia logiki kai an to amaxi einai ble
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("                                                         BLUE CAR ARRIVED "+ this.getId() + " AT TIME " + dtf.format(now));
			b.blueTurn(this);                             // stelnw to nhma sto blueturn kai sto redturn ws this.

		}

	}

}
