
package com.wade.model;

/**
 * This class models a simplistic Harbor simulation, based on an Harbor that
 * is managing Deberthing and Berthing Tanker.
 * 
 * @author Gewa
 */
public class Simulator {
    
    private Harbor harbor; 
    private int step;
    private static int numSteps = 2880; 
    private StringBuilder longReport; 
    private static Simulator sim;
    
    
    public static void main (String[] args) {
        
        try {
            if (args.length >= 1) {
                if (args[0].equals("-help")) {
                    System.out.println("Usage: Java Berthing Simulator <Sim length>");
                    System.exit(0);
                }
                else {
                    numSteps = Integer.parseInt(args[0]);
                }
            }
        }
        catch (Exception e) {
            System.err.println("An error occured!");
            System.out.println("Usage: Java Berthing Simulator <Sim length>");
            System.err.println(e);
        }
        
        sim = new Simulator();
        sim.simulate(numSteps);
    }
    
    
    
    public Simulator() {
        harbor = new Harbor();
        longReport = new StringBuilder();
        reset();
    }
    
   
    
    public void simulate(int numSteps) {
        for (step = 1; step <= numSteps; step++) {
            simulateOneStep();
        }
        harbor.getSC().finish();
        System.out.println(getResults());
    }
    
   
    
    public void simulateOneStep() {
        harbor.schedule();
        longReport.append(printLongReport());
        System.out.println(printLongReport());
    }
    
   
    
    public void reset() {
        step = 0;
        harbor.reset();
        longReport.setLength(0);
    }
    
    
    public String getResults() {
        return harbor.getSC().getCounter().toString();
    }
    
    
    
    public String printLongReport() {
        return (" Step: " + step + harbor.getSC().getCounter().longReport() + "\n");
    }
    
    
    
    public StringBuilder getLongReport() {
        return longReport;
    }
    
    
    
    public Harbor getHarbor() {
        return harbor;
    }
}
