import java.util.Random;
import java.util.*;
/* O kwdikas mou exei vasistei sthn lush tou Imerial College, prospathontas na to xtisw apo thn arxh 
 * monos mou, exontas thn vasikh idea.Akoma exw parei arketa stoixeia ston kwdika mou (opws o xronos) 
 * apo gnwsta site , stackoverflow,geek for geeks kai alla.
 * 
 */
public class Main {
	
	public int i;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	
		Scanner scanner = new Scanner(System.in);     

		Bridge b = null;

		System.out.println("What kind of Bridge do u want ? ");
		String input = scanner.nextLine();     

		if (input.equals("senario1"))       //mathainw poio senario epi8umei o xrhsths kai analoga 
			b = new senario1();             //8etw to thn timh b (tupou Bridge) 
		else if (input.equals("senario2"))
			b = new senario2();
		else if (input.equals("senario3"))
			b = new senario3();
		else if (input.equals("senario4"))
			b = new senario4();
		else
			System.out.println("sorry u made a mistake");

		System.out.println("How many cars do u want ? ");
		String input1 = scanner.nextLine();
		int n = Integer.valueOf(input1);   
		cars[] c = new cars[n];     //dimourgw enan pinaka tupou cars

		System.out.println("How many red cars do u want ? ");
		String inputr = scanner.nextLine();
		int nred = Integer.valueOf(inputr); //pairnw ton arithmo twn kokkinwn autokkinhtwn

		System.out.println("How many blue cars do u want ? ");
		String inputb = scanner.nextLine();
		int nblue = Integer.valueOf(inputb);  //pairnw ton arithmo ble autokinhtwn

		System.out.println("Left Side                          Bridge                       Right Side");

		for (int i = 0; i < nred; i++) {

			c[i] = new cars(i, "red", b);    //dimiourgw ta kokkina autokinhta kai amesws meta ta xekinaw
			c[i].start();
			
		}
		
		for (int i =nred; i <n; i++) {

			c[i] = new cars(i, "blue", b); //dimiourgw ta ble autokinhta kai amesws meta ta xekinaw
			c[i].start();
			
		}

		try {
			for (int i = 0; i < n; i++)
				c[i].join();             //perimenw na teleiwsoun ola ta autokinhta gia na tupwsw ENDDDDD
		} catch (InterruptedException e) {
		}

		System.out.println("ENDDDDDDDDDDDDDD");

	}

}
