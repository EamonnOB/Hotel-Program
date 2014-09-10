public class Guest {

	private String name;
	private String arrivalDate;
	private String departureDate;
	private int nights;
	private boolean breakfastIncluded = false;
	private boolean dinnerIncluded = false;
	private int roomType;
	private int numOfGuests;
	private double price;
	private boolean paidBill = false;
	
	Guest(String name, String arrivalDate, String departureDate, int breakfast, int dinner, int roomType, int numOfGuests, double price, int paid) {
		this.name = name;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		nights = getNumberOfNights();
		if(breakfast == 1)
			breakfastIncluded = true;
		if(dinner == 1)
			dinnerIncluded = true;
		this.roomType = roomType;
		this.numOfGuests = numOfGuests;
		this.price = price;
		if(paid == 1)
			paidBill = true;
	}

	public String getGuestInfoForDisplay() {
		StringBuilder sb = new StringBuilder("Guest Name: "+name+"\nArriving: "+arrivalDate+"\nDeparting: "+departureDate+"\nNumber of Nights: "+nights+"\nBreakfast Included: ");
		if(breakfastIncluded)
			sb.append("Yes");
		else
			sb.append("No");
		sb.append("\nDinner Included: ");
		if(dinnerIncluded)
			sb.append("Yes");
		else
			sb.append("No");
		sb.append("\nRoom Type ");
		if(roomType==0)
			sb.append("Single");
		else if(roomType==1)
			sb.append("Twin");
		else
			sb.append("Double");
		sb.append("\nNumber Of Guests: "+numOfGuests+"\nCost Of Stay: "+price+"\nBill Paid: ");
		if(paidBill)
			sb.append("Yes");
		else
			sb.append("No");
		return sb.toString();
	}
	
	public String getGuestInfoForFile() {
		StringBuilder sb = new StringBuilder(name+";"+arrivalDate+";"+departureDate+";");
		if(breakfastIncluded)
			sb.append("1");
		else
			sb.append("0");
		if(dinnerIncluded)
			sb.append("1");
		else
			sb.append("0");
		sb.append(";"+roomType+";"+numOfGuests+";"+price+";");
		if(paidBill)
			sb.append("1");
		else
			sb.append("0");
		return sb.toString();
	}
	
	public String getGuestName() {
		return name;	
	}
	
	private int getNumberOfNights() {
		int numOfNights = 0;
		int month1=0, month2=0;
		String[] arrival = arrivalDate.split("/");
		month1 = Integer.parseInt(arrival[1]);
		String[] departure = departureDate.split("/");
		month2 = Integer.parseInt(departure[1]);
		if(month1 == month2)
			numOfNights = Integer.parseInt(departure[0]) - Integer.parseInt(arrival[0]);
		else if((month2-1) == month1 || month2 == 1)
			numOfNights = Integer.parseInt(departure[0]) + (getDaysInMonth(month1) - Integer.parseInt(arrival[0]));
		return numOfNights;
	}
		
	private int getDaysInMonth(int month) {
		int numOfDays = 0;
		switch(month) {
			case 1: numOfDays = 31; break;
			case 2: numOfDays = 28; break;
			case 3: numOfDays = 31; break;
			case 4: numOfDays = 30; break;
			case 5: numOfDays = 31; break;
			case 6: numOfDays = 30; break;
			case 7: numOfDays = 31; break;		
			case 8: numOfDays = 31; break;
			case 9: numOfDays = 30; break;
			case 10: numOfDays = 31; break;
			case 11: numOfDays = 30; break;
			case 12: numOfDays = 31; break;		
		}
		return numOfDays;	
	}

}
