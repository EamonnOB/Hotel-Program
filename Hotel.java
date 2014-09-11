import java.io.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class Hotel {
	//name;arrival date;departure date;breakfast included(0,1);dinner included(0,1);room type(0,1,2);num of guests;price paid;bill paid(0,1)
	public static void main(String[] args) throws IOException {
		ArrayList<Guest> guests = getGuestsFromFile();
		ArrayList<Guest> pastGuests = getPastGuests(guests);
		//ArrayList<Guest> currentGuests = getCurrentGuests(guests);
		ArrayList<Guest> futureBookings = getFutureBookings(guests);
		String input = "";
		String message = "Welcome to the Imaginary Inn!\nWhat would you like to do?\n\n\n";
		Object[] menuOptions = {"View Past Guests","View Current Guests","View Future Bookings","Make Booking","Cancel Booking"};
		int choice = 0;
		while(choice != -1) {
			choice = JOptionPane.showOptionDialog(null, message, "Menu",0,3,null,menuOptions,menuOptions[0]);
			switch(choice) {
				case 0: {
					input = "";
					StringBuilder sb = new StringBuilder("Past Guests\n");
					for(int g=0; g<pastGuests.size(); g++) {
						sb.append((g+1) + " " + pastGuests.get(g).getGuestName() + "\n");
					}
					while(input != null && !(input.matches("\\d+"))) {
						input = JOptionPane.showInputDialog(null,sb.toString()+"\nEnter a number to get information on a guest","Menu",1);
					}	
				}
				break;
				case 2: {
					input = "";
					StringBuilder sb = new StringBuilder("Future Bookings\n");
					for(int g=0; g<futureBookings.size(); g++) {
						sb.append((g+1) + " " + futureBookings.get(g).getGuestName() + "\n");
					}
					while(input != null && !(input.matches("\\d+"))) {
						input = JOptionPane.showInputDialog(null,sb.toString()+"\nEnter a number to get information on a guest","Menu",1);
					}	
				}	
			}
		}
	}
	
	public static ArrayList<Guest> getGuestsFromFile() throws IOException {
		ArrayList<String> tempGuests = readEasy("guests.txt");
		ArrayList<Guest> guests = new ArrayList<Guest>(); 
		Guest tempGuest;
		String tempString = "";
		String[] tempArray;
		for(int h=0;  h<tempGuests.size(); h++) {
			tempString = tempGuests.get(h);
			tempArray = tempString.split(";");
			tempGuest = new Guest(tempArray[0],tempArray[1],tempArray[2],Integer.parseInt(tempArray[3]),Integer.parseInt(tempArray[4]),Integer.parseInt(tempArray[5]),Integer.parseInt(tempArray[6]),Double.parseDouble(tempArray[7]),Integer.parseInt(tempArray[8]));
			guests.add(tempGuest);
		}
		return guests;
	}
	
	public static ArrayList<String> readEasy(String fileName) throws IOException {
        File aFile = new File(fileName);
        Scanner in = new Scanner(aFile);
        ArrayList<String> list = new ArrayList<String>();
        while(in.hasNext()){
            list.add(in.nextLine());
        }
        in.close();
        return list;
    }
   
    public static void writeEasy(ArrayList<String> list, String fileName) throws IOException {
        File aFile = new File(fileName);
        PrintWriter out = new PrintWriter(aFile);
        for(int i=0; i<list.size(); i++) {
            out.println(list.get(i));
        }
        out.close();
    }
    
    public static String getCurrentDate() {
	    
	 	Date d = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		return df.format(d);
	       
    }
    
    public static ArrayList<Guest> getPastGuests(ArrayList<Guest> guests) {
	    ArrayList<Guest> pastGuests = new ArrayList<Guest>();
	    String currentDate = getCurrentDate();
	    String[] splitCurrentDate = currentDate.split("/");
	    for(int x=0; x<guests.size(); x++) {
		    String[] departureDate = guests.get(x).getDepartureDate().split("/");
		    if((splitCurrentDate[2].compareTo(departureDate[2])) <= 0) {
			 	if((splitCurrentDate[2].compareTo(departureDate[2])) < 0)
			 		pastGuests.add(guests.get(x));
			 	else if((splitCurrentDate[1].compareTo(departureDate[1])) <= 0) {
				 	if((splitCurrentDate[1].compareTo(departureDate[1])) < 0)
			 			pastGuests.add(guests.get(x));
			 		else if((splitCurrentDate[0].compareTo(departureDate[0])) < 0)
			 			pastGuests.add(guests.get(x));
			 	}	
	    	}
	    }
	    return pastGuests;
    }
    
    /*public static ArrayList<Guest> getCurrentGuests(ArrayList<Guest> guests) {
	    String currentDate = getCurrentDate();
	    for(int y=0; y<guests.size(); y++) {
		    
	    }
    }*/
    
    public static ArrayList<Guest> getFutureBookings(ArrayList<Guest> guests) {
	    ArrayList<Guest> futureGuests = new ArrayList<Guest>();
	    String currentDate = getCurrentDate();
	    String[] splitCurrentDate = currentDate.split("/");
	    for(int z=0; z<guests.size(); z++) {
		    String[] arrivalDate = guests.get(z).getArrivalDate().split("/");
		    if((splitCurrentDate[2].compareTo(arrivalDate[2])) >= 0) {
			 	if((splitCurrentDate[2].compareTo(arrivalDate[2])) > 0)
			 		futureGuests.add(guests.get(z));
			 	else if((splitCurrentDate[1].compareTo(arrivalDate[1])) >= 0) {
				 	if((splitCurrentDate[1].compareTo(arrivalDate[1])) > 0)
			 			futureGuests.add(guests.get(z));
			 		else if((splitCurrentDate[0].compareTo(arrivalDate[0])) > 0)
			 			futureGuests.add(guests.get(z));
			 	}	
	    	}
	    }
	    return futureGuests;
    }
	
}
