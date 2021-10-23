package com.company;
import java.util.Scanner;

class Seats {
    private int seatNo;

    //Constructors
    public  Seats() {
        seatNo=0;
    }
    public Seats(int seatNo){
        setSeatNo(seatNo);
    }
    //toString
    public String toString() {
        return "Seat: " + this.getSeatNo();
    }


    //getters and setters
    public int getSeatNo() {
        // TODO Auto-generated method stub
        return seatNo;
    }
    public void setSeatNo(int seatNo) {
        // TODO Auto-generated method stub
        this.seatNo= seatNo;
    }

}


class Passengers {
    private String name;

    //constructors
    public Passengers(){
        name = "Unknown";
    }
    public  Passengers( String name) {
        setName(name);
    }
    //getters and setters
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name= newName;
    }
    //toString
    public String toString() {
        return "Passenger: " + this.getName();
    }
}


class Flight {

    // Fields
    private String departureCity;
    private String arrivalCity;
    private int flightNo;

    // Constructor
    public  Flight() {
        departureCity = "Unknown Departure City";
        arrivalCity = "Unknown Arrival City";
        flightNo=100;
    }
    public  Flight( int flightNo, String departureCity, String  arricalCity) {
        setDepartureCity(departureCity);
        setArrivalCity(arricalCity);
        setFlightNo(flightNo);

    }
    //toString
    public String toString() {
        return "Flight: " + this.getFlightNo() + " " + this.getDepartureCity() + " - " + this.getArrivalCity();

    }

    //getters and setters
    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public int getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(int flightNo) {
        this.flightNo = flightNo;
    }

}

class Tickets {
    private LocalDate departureDate;
    private Flight flight;
    private Passengers passenger;
    private Seats seat;

    //constructor
    public  Tickets() {
        departureDate= LocalDate.now();

    }


    //getters and setters
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public Passengers getPassenger() {
        return passenger;
    }
    public void setPassenger(Passengers passenger) {
        this.passenger = passenger;
    }
    public Seats getSeat() {
        return seat;
    }
    public void setSeat(Seats seat) {
        this.seat = seat;
    }
    public String toString() {
        return "Ticket: " + this.getPassenger().getName() + " departing on flight " + this.getFlight().getFlightNo() + " from " + this.getFlight().getDepartureCity() + " to " + this.getFlight().getArrivalCity() + " on " + this.getDepartureDate() + " in seat " + this.getSeat().getSeatNo();
    }
}

class Control {

    private ArrayList<Seats> seats;
    private ArrayList<Passengers> passengers;
    private ArrayList<Flight> flight;
    private ArrayList<Tickets> ticket;

    //constructor
    public Control() {
        seats=new ArrayList<Seats>();
        passengers=new ArrayList<Passengers>();
        flight=new ArrayList<Flight>();
        ticket=new ArrayList<Tickets>();

    }

//getters and setters

    public ArrayList<Seats> getSeats() {
        return seats;
    }

    public ArrayList<Passengers> getPassengers() {
        // TODO Auto-generated method stub
        return passengers;
    }

    public ArrayList<Flight> getFlight() {
        // TODO Auto-generated method stub
        return flight;
    }

    public ArrayList<Tickets> getTicket() {
        // TODO Auto-generated method stub
        return ticket;
    }

    public void addSeat(int seatNumber) {

        seats.add(new Seats(seatNumber));
    }

    public void addFlight(int flightNo, String departureCity, String arrivalCity) {
        flight.add(new Flight(flightNo, departureCity, arrivalCity));

    }

    public boolean addPassenger(String passengerName) {
        boolean passengerExists = false;

        for (Passengers item : getPassengers())
        {
            if(passengerName.equals(item.getName())){
                passengerExists = true;
            }

        }
        if(passengerExists==false){
            passengers.add(new Passengers(passengerName));
        }
        return passengerExists;
    }

    public String addTicket(LocalDate departureDate, String passengerName, int flightNumber, int seatNumber)
    {
        //find the passenger object
        Passengers ticketPassenger = null;
        for(Passengers item: getPassengers())
        {
            if(passengerName.equals(item.getName())){
                ticketPassenger = item;
            }
        }
        // Find the flight object
        Flight ticketFlight = null;
        for(Flight item: getFlight())
        {
            if(flightNumber == item.getFlightNo()){
                ticketFlight= item;
            }
        }

        // Find the seat object
        Seats ticketSeat = null;
        for(Seats item: getSeats())
        {
            if(seatNumber == item.getSeatNo()){
                ticketSeat= item;
            }
        }
        // combining information on ticket object
        Tickets 	tmpTicket = new Tickets();
        tmpTicket.setDepartureDate(departureDate);
        tmpTicket.setPassenger(ticketPassenger);
        tmpTicket.setFlight(ticketFlight);
        tmpTicket.setSeat(ticketSeat);

        //adding ticket object to Ticket arraylist
        ticket.add(tmpTicket);
        return tmpTicket.toString();
    }

    //Finding out available seats for a particular date and flight no
    public ArrayList<Seats> getOpenSeats( LocalDate departureDate, int flightNumber ){
        ArrayList<Seats> openSeats = getSeats();
        for(Tickets item: getTicket()){
            if(departureDate.equals(item.getDepartureDate()) && flightNumber == item.getFlight().getFlightNo()){
                openSeats.remove(item.getSeat());
            }
        }
        return openSeats;
    }

    //Bootstrap method to add fix data
    public void bootstrap() {
        addSeat(1);
        addSeat(2);
        addSeat(3);
        addSeat(4);
        addSeat(5);
        addSeat(6);
        addFlight(1000, "Los Angeles", "Chicago");
        addFlight(1001, "Chicago", "New York");
        addFlight(2000, "New York", "Chicago");
        addFlight(2001, "Chicago", "Los Angeles");
    }

}
public class Main {

    public static void main(String[] args) {
	// write your code here
        // Initialize database
        Control prodDB = new Control();
        prodDB.bootstrap();
        //Initialize console
        boolean always = true;
        String passengerName = null;
        int flightNumber = 0;
        int seatNumber = 0;
        LocalDate departureDate = LocalDate.now();
        //reading input from screen;
        BufferedReader screenInput = new BufferedReader(new InputStreamReader(System.in));

        while(always)
        {
            // ask for passenger name and add
            System.out.println("Enter Passenger: ");

            try {
                passengerName = screenInput.readLine();
            } catch (IOException e) {
                System.out.println("Sorry, i dont understand.");
            }
            // checking whether an existing passenger or new one
            boolean result = prodDB.addPassenger(passengerName);

            // welcoming existing passenger or new one

            if(result)
            {
                System.out.println("Welcome back: " +  passengerName);
            }
            else{
                System.out.println("Welcome: " +  passengerName);
            }
            // show flights and ask for flights
            System.out.println("\nHere are available flights: ");
            for(Flight item: prodDB.getFlight()){
                System.out.println(item);
            }
            System.out.println("Enter the flight no to book");
            try{
                flightNumber = Integer.parseInt(screenInput.readLine());
            } catch (IOException e) {
                System.out.println("Please enter flight no");
            }
            catch (NumberFormatException e) {
                System.out.println("That was not a number");
            }

            // Show available seats and asks

            System.out.println("\nAssuming you are flying today,");
            System.out.println("Here are available seats on that flight: ");
            ArrayList<Seats> openSeats = prodDB.getOpenSeats(departureDate, flightNumber);
            for(Seats item: openSeats){
                System.out.print(item.getSeatNo() + ",");
            }
            System.out.println("\nEnter the seat you want: ");
            try{
                seatNumber = Integer.parseInt(screenInput.readLine());

            }
            catch (IOException e) {
                System.out.println("Please enter a seat no");
            }
            String ticketInfo = prodDB.addTicket(departureDate, passengerName, flightNumber, seatNumber);
            System.out.println("\nReservations Successful!! Here are your ticket details");
            System.out.println(ticketInfo + "\n");
        }



    }

}
}
