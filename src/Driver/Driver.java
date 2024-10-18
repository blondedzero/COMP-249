package Driver;
import P1.Airplane;
import P2.Helicopter;
import P2.Quadcopter;
import P3.Multirotor;
import P4.UAV;
import P5.AgriculturalDrone;
import P5.MAV;

public class Driver {

    public static void main(String[] args) {
    	
    	
        //array to store hangar aircrafts
        Airplane[] hangar = new Airplane[7];
        UAV[] hangar2 = new UAV[8];
    	
        //initializing aircraft objects
        hangar[0] = new Helicopter();
        hangar[1] = new Helicopter();
        hangar[2] = new Quadcopter();
        hangar[3] = new Multirotor();
        hangar[4] = new Helicopter();
        hangar[5] = new Quadcopter();
        hangar[6] = new Multirotor();
        
        hangar2[0] = new MAV();
        hangar2[1] = new AgriculturalDrone();
        hangar2[2] = new AgriculturalDrone();
        hangar2[3] = new MAV();
        hangar2[4] = new MAV();
        hangar2[5] = new MAV();
        hangar2[4] = new AgriculturalDrone();
        hangar2[5] = new AgriculturalDrone();
        
        //welcome message
        System.out.println("Welcome to the Hangar Aircraft Comparer Program!");
        System.out.println("Here are the following aircrafts stored in the hangar:");


     

    }

    //findLeastAndMostExpensiveUAV method
    private static String findLeastAndMostExpensiveUAV() {

        return "";
    }
}

