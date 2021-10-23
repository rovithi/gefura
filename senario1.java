/*
 * senario1 : ta autokinhta mh asfalhs dieuleush me sugrouseis.
 */
public class senario1 extends Bridge {
	private int bluein = 0;  // an bluein>0  ble amaxi einai panw sthn gefura 
	private int redin = 0;   //an redin>0  kokkino amaxi einai panw sthn gefura .
	                        // an redin>0 && bluein>0 tote exoume sigrousi 

	public void redTurn(cars t) {

		try {
			redin++;           //ftanei kokkino amaxi kai xwris na perimenei kanei cross thn gefura 
			cross(t);          //ulopoiw thn methodo cross pou 8a einai o koinoxrhstos poros gia to kokkino kai to ble amaxi
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void blueTurn(cars t) {

		try {
			bluein++;        //omoiws gia to ble
			cross(t);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void cross(cars t) throws InterruptedException {
		System.out.println("                 --------------------------------------");
		System.out.println("                 Now " + t.getColor() + "Car " + t.getId() + " is crossing the bridge "); // ektupwsei mhnumatos  oti to amaxi me xrwma t.getcolor 
		System.out.println("                 --------------------------------------");
		if (redin != 0 && bluein != 0) {
			System.out.println(" {  THREADS ARE CRUSHED } " + redin + "--" + bluein); //an redin>0 && bluein>0  tote sigroush
			redin = 0;
			bluein = 0;

		} else {
			t.sleep(300);
			System.out.println("Car " + t.getId() + " OUT ");  //alliws to amaxi mporese kai ktevhke apo thn gefura xwris na prolavei 
			                                                   // na bei kapoio allo amaxi apo apenanti.
			if (t.getColor() == "red")
				redin = 0;                // efoson vgei apo thn gefura to amaxi kane ton metrhth tou 0
			else
				bluein = 0;

		}

	}

}
