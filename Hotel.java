import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Hotel {
	//name;arrival date;departure date;breakfast included(0,1);dinner included(0,1);room type(0,1,2);num of guests;price paid;bill paid(0,1)
	public static void main(String[] args) throws IOException {
			
		String message = "Welcome to the Imaginary Inn!\nWhat would you like to do?";
		Object[] menuOptions = {"View Guests"};
		int choice = 0;
		while(choice != -1) {
			choice = JOptionPane.showOptionDialog(null, message, "Menu",0,3,null,menuOptions,menuOptions[0]);
			if(choice == 0) {
				StringBuilder sb = new StringBuilder("Guests\n");
				ArrayList<Guest> guests = getGuestsFromFile();
				for(int g=0; g<guests.size(); g++) {
					sb.append((g+1) + guests.get(g).getGuestName() + "\n");
				}
				String input = "";
				while(input != null && !(input.matches("[0-9]{2}"))) {
					input = JOptionPane.showInputDialog(null,sb.toString()+"\nEnter a number to get information on guest","Menu",1);
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
	
}
