
package com.wade.GUIview;

import com.wade.model.Simulator; // importing required resources

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.io.File;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.border.*;
/**
 * The GUI Class is used to provide the user with a graphical interface
 * so that they can interact with the program. It allows them to change the
 * various probabilities of creating tanker as well as the length of the simulation.
 * It gives them both a brief and a detailed view of the results of the simulation, and
 * also allows the user to save the detailed information to a text file in a location
 * of their choice.
 * 
 * @author Gewa
 */
public class GUI {
    
    /**
     * The Simulator to be used for the GUI.
     * @see Simulator
     */
    
    private static Simulator sim;
    private JFrame mainFrame; 
    private JTextArea results, longResults;
    private LabelledSlider commercialSlider, tugSlider, lightSlider, lengthSlider;
    private JComboBox<String> strategy; 
    private JComboBox<Integer> seed; 
    private String[] strategies = { "Waiting Time Strategy", "FuelStrategy" }; 
    private Integer[] seedSelection = new Integer[100]; 
    { for(int i = 0; i < 100; i++) { seedSelection[i] = i+1; }}; 
    private static final int padding = 5; 
    private boolean reportOpen, longReportOpen = false; 
    protected Component errorFrame; 
    
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        final GUI gui = new GUI();
    }
    
    /**
     * Creating a new <code>GUI</code>, which will give the user a graphical interface
     * with which to interact with the program. It will allow the user to adjust various variables
     * regarding the simulation, giving them full control over how it should run.
     * 
     * @see Simulator to understand the methods used
     */
    
    public GUI() {
        sim = new Simulator();
        // Step 1: create the components
        JButton runButton = new JButton("Run Simulator"); 
        JButton quitButton = new JButton("Quit");
        JLabel seedText = new JLabel("Seed: "); 
        JLabel strategyText = new JLabel("Strategy: ");
        
        lengthSlider = new LabelledSlider("Simulation Length: ", 2880, 60, 10000, 1); 
        commercialSlider = new LabelledSlider("Commercial Probability (p): ",0.1, 0, 1000, 1000); 
        tugSlider = new LabelledSlider("Tug Probability: ", 0.002, 0,1000, 1000); 
        lightSlider = new LabelledSlider("Light Probability: ", 0.005, 0, 1000, 1000); 
        
        strategy = new JComboBox<String>(strategies); 
        seed = new JComboBox<Integer>(seedSelection); 
        
        results = new JTextArea(); 
        results.setEditable(false);
        longResults = new JTextArea();
        longResults.setEditable(false);
        
        // Step 2: Set the properties of the components
        runButton.setToolTipText("Run the simulation.");
        runButton.setPreferredSize(new Dimension(140, 32));
        quitButton.setToolTipText("Exit the application."); 
        quitButton.setPreferredSize(new Dimension(100, 34));
        
        lengthSlider.setToolTipText("Used to set the simulation length in half minutes.");
        lengthSlider.setMajorTickSpacing(720); 
        lengthSlider.setPreferredSize(new Dimension(500, 80)); 
        
        commercialSlider.setToolTipText("Used to set the probability of generating a commercial tanker.");
        commercialSlider.setPreferredSize(new Dimension(275, 70)); 
        
        tugSlider.setToolTipText("Used to set the probability of generating a tug.");
        tugSlider.setPreferredSize(new Dimension(275, 70));
        
        lightSlider.setToolTipText("Used to set the probability of generating a light tanker.");
        lightSlider.setPreferredSize(new Dimension(275, 70)); 
        
        strategy.setPreferredSize(new Dimension(180, 32));
        strategy.setToolTipText("Select the scheduling strategy to be used.");
        ((JLabel) strategy.getRenderer()).setHorizontalAlignment(JLabel.CENTER); 
        seed.setPreferredSize(new Dimension(60, 32)); 
        seed.setToolTipText("Select the seed the random number generator will use."); 
        ((JLabel) seed.getRenderer()).setHorizontalAlignment(JLabel.CENTER); 
        
        // Step 3: Create containers to hold the components
        mainFrame = new JFrame("100/100 Berthing Simulator"); 
        mainFrame.getContentPane().setBackground(new Color(61, 145, 64)); 
        mainFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); 
        mainFrame.setPreferredSize(new Dimension(800, 260)); 
        mainFrame.setResizable(false); 
        
        JPanel commandBox = new JPanel();
        JPanel sliderBox = new JPanel();
        JPanel buttonBox = new JPanel(); 
        JPanel comboHolder= new JPanel(); 
        

        mainFrame.getContentPane().setLayout(new BorderLayout());
        ((JPanel) mainFrame.getContentPane()).setBorder(new EmptyBorder(padding, padding, padding, padding));
        
        commandBox.setLayout(new BorderLayout()); 
        commandBox.setBorder(new EmptyBorder(padding, padding, padding, padding));
        
        sliderBox.setLayout(new BorderLayout()); 
        sliderBox.setBorder(new EmptyBorder(padding, padding, padding, padding));
         
        buttonBox.setLayout(new BorderLayout()); 
        buttonBox.setBorder(new EmptyBorder(padding, padding, padding, padding));
        
        comboHolder.setLayout(new FlowLayout()); 
        comboHolder.setBorder(new EmptyBorder(20, padding, padding, padding));
        
        buttonBox.add(runButton, BorderLayout.WEST); 
        buttonBox.add(quitButton, BorderLayout.EAST); 
         
        comboHolder.add(strategyText);
        comboHolder.add(strategy);
        comboHolder.add(seedText); 
        comboHolder.add(seed); 
        
        commandBox.add(lengthSlider, BorderLayout.NORTH);
        commandBox.add(comboHolder, BorderLayout.CENTER);
        commandBox.add(buttonBox, BorderLayout.SOUTH); 
        
        sliderBox.add(commercialSlider, BorderLayout.NORTH); 
        sliderBox.add(tugSlider, BorderLayout.CENTER); 
        sliderBox.add(lightSlider, BorderLayout.SOUTH); 
        
        mainFrame.getContentPane().add(commandBox, BorderLayout.EAST); 
        mainFrame.getContentPane().add(sliderBox, BorderLayout.WEST); 
        
        
        runButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (reportOpen) {
                    // do nothing if the report window is already open.
                }
                else {
                    if (commercialSlider.getValue() + tugSlider.getValue() + lightSlider.getValue() > 1) {
                        JOptionPane.showMessageDialog(errorFrame, 
                                "The combined probabilities cannot be more than 1. \n Please adjust the values.",
                                "Tanker Probability Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else {
                        runSimulation(); 
                        openReport();
                    }
                }
            }
        });
        
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exitApp();
            }
        });
        
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                exitApp(); 
            }
        });
        
        strategy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
            }
        });
        
    
        mainFrame.pack();
        centreWindow(mainFrame); 
        mainFrame.setVisible(true);
    }
    
    
    
    public void openReport() {
        reportOpen = true;
        final String reportFrameString = "ReportFrame";
        
        final  JFrame reportFrame = new JFrame("Simulation Results");
        JScrollPane listScroller = new JScrollPane(results);
        listScroller.setPreferredSize(new Dimension(220, 195));
        listScroller.setMinimumSize(new Dimension(220, 150));
        
        JButton runAgainButton = new JButton("Run Again");
        JButton closeButton = new JButton("Close");
        JButton detailButton = new JButton("Details");
        
      
        closeButton.putClientProperty(reportFrameString, reportFrame);
        closeButton.setToolTipText("Close this window.");
        runAgainButton.setToolTipText("Run the simulation again.");
        detailButton.setToolTipText("Show a more detailed report.");
        
    
        reportFrame.setPreferredSize(new Dimension(240, 315));
        reportFrame.setResizable(false); 
        reportFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
        
         JPanel reportPanel = new JPanel(); 
         JPanel buttonPanel = new JPanel(); 
         
         reportFrame.getContentPane().setLayout(new BorderLayout()); 
         ((JComponent) reportFrame.getContentPane()).setBorder(new EmptyBorder (
                padding, padding, padding, padding));
         
         reportPanel.setLayout(new BorderLayout()); 
         reportPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
         
         buttonPanel.setLayout(new FlowLayout()); 
         buttonPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
         
        
         reportPanel.add(listScroller, BorderLayout.NORTH); 
         
         buttonPanel.add(detailButton); 
         buttonPanel.add(runAgainButton); 
         buttonPanel.add(closeButton);
         
         reportFrame.getContentPane().add(reportPanel, BorderLayout.NORTH); 
         reportFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
         
         
         closeButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 JComponent c = (JComponent) e.getSource();
                 JFrame f = (JFrame) c.getClientProperty(reportFrameString);
                 f.dispose();
                 reportOpen = false; 
             }
         });
         
         runAgainButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 runSimulation(); 
             }
         });
         
         detailButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 if (longReportOpen) {
                     // do nothing if the window is already open.
                 } else {
                     longResults.setText(null);
                     longResults.append(getSimulationDetails());
                     longResults.append(sim.getLongReport().toString());
                     openLongReport(); 
                 }
             }
         });
         
         reportFrame.addWindowListener(new WindowAdapter() {
             public void windowClosing(WindowEvent e) {
                 reportOpen = false; 
             }
         });
         
         
         
         reportFrame.pack(); 
         centreWindow(reportFrame); 
         reportFrame.setVisible(true); 
    }
    
    
    
    private void openLongReport() {
        longReportOpen = true;
        final JFrame longReportFrame = new JFrame("Detailed Results"); 
        
        JScrollPane listScroller = new JScrollPane(longResults); 
        
        JButton closeButton = new JButton("Close"); 
        JButton saveFileButton = new JButton("Save to file"); 
        
       
        closeButton.setToolTipText("Close this window."); 
        saveFileButton.setToolTipText("Save the results of the simulation to a text file.");
        
        listScroller.setPreferredSize(new Dimension(620, 506)); 
        
       
        longReportFrame.setPreferredSize(new Dimension(1000, 600));
        longReportFrame.setResizable(false); 
        longReportFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        JPanel reportPanel = new JPanel(); 
        JPanel buttonPanel = new JPanel(); 
        
       
        longReportFrame.getContentPane().setLayout(new BorderLayout());
        ((JComponent) longReportFrame.getContentPane()).setBorder
        (new EmptyBorder(padding, padding, padding, padding));
        
        reportPanel.setLayout(new BorderLayout()); 
        reportPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
        
        buttonPanel.setLayout(new FlowLayout()); 
        buttonPanel.setBorder(new EmptyBorder(padding, padding, padding, padding));
        
      
        reportPanel.add(listScroller, BorderLayout.NORTH); 
        buttonPanel.add(saveFileButton); 
        buttonPanel.add(closeButton); 
        longReportFrame.getContentPane().add(reportPanel, BorderLayout.NORTH); 
        longReportFrame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        
        
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                longReportFrame.dispose(); 
                longReportOpen = false; 
            }
        });
        
        saveFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser saveFile = new JFileChooser();
                saveFile.setCurrentDirectory(new File("Documents"));
                int retrieval = saveFile.showSaveDialog(null);
                if (retrieval == JFileChooser.APPROVE_OPTION) {
                     try (FileWriter fw = new FileWriter(saveFile.getSelectedFile() + ".txt")) {
                         longResults.write(fw); 
                     } catch (Exception ex) {
                         ex.printStackTrace(); 
                     }
                }
            }
        });
        
        longReportFrame.addWindowListener(new WindowAdapter () {
            public void windowClosing(WindowEvent e) {
                longReportOpen = false; 
            }
        });
        
      
        longReportFrame.pack();
        centreWindow(longReportFrame);
        longReportFrame.setVisible(true); 
    }
    
   
    private void exitApp() {
        // Display confirmation dialog before exiting application
        int response = JOptionPane.showConfirmDialog(mainFrame,
                "Do you really want to quit?", "Quit?",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        // Don`t quit
    }
    
    
    private void runSimulation() {
        resetSimulation();
        sim.simulate((int) Math.round(lengthSlider.getValue()));
        results.append(sim.getResults());
    }
    
    
    private void resetSimulation() {
        setProbabilities();
        setSeed();
        setStrategy();
        results.setText(null);
        sim.reset();
    }
    
    
    private void setProbabilities() {
        sim.getHarbor().getFacility().setProbabilities(commercialSlider.getValue(),
                tugSlider.getValue(), lightSlider.getValue());
    }
    
   
    private void setStrategy() {
        sim.getHarbor().setStrategy(strategy.getSelectedIndex());
    }
    
    
    private void setSeed() {
        sim.getHarbor().getFacility().setSeed(seed.getSelectedIndex());
        sim.getHarbor().getSC().setSeed(seed.getSelectedIndex());
    }
    
    
    private String getSimulationDetails() {
        return ("Simulation Length: " + (int) lengthSlider.getValue() + "       |       "+
                "Strategy: " + strategy.getSelectedItem() + "       |       "+
                "Seed: " + (seed.getSelectedIndex() + 1) + "        |       "+
                "Commercial Probability: " + commercialSlider.getValue() + "        |       "+
                "Tug Probability: " + tugSlider.getValue() + "      |       "+
                "Light Probability: " + lightSlider.getValue() + "\n \n");
    }
    
    
    private static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
