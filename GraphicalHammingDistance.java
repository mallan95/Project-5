import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GraphicalHammingDistance extends JFrame implements ActionListener, ChangeListener
{
    private JFormattedTextField hDist;
    private JFormattedTextField d0Field;
    private JFormattedTextField d1Field;
    private JFormattedTextField d2Field;
    private JFormattedTextField d3Field;
    private JFormattedTextField d4Field;
    private JFormattedTextField newStation;
    private JLabel hDistDisplay;
    private JLabel d0Display;
    private JLabel d1Display;
    private JLabel d2Display;
    private JLabel d3Display;
    private JLabel d4Display;
    private JLabel compare;
    private JLabel LabelFREE;
    private JButton showButton;
    private JButton calcButton;
    private JButton addButton;
    private JButton tableButton;
    private JSlider hDistSlider;
    private JComboBox stations;
    private String currCity;
    String[] Dist0 = new String[120];
    String[] Dist1 = new String[120];
    String[] Dist2 = new String[120];
    String[] Dist3 = new String[120];
    String[] Dist4 = new String[120];
    int d0count = 0;
    int d1count = 0;
    int d2count = 0;
    int d3count = 0;
    int d4count = 0;
    String[] fullList = null;
    private JTextArea outputArea;
    private JTextArea outputAreaTable;
    
    GraphicalHammingDistance() {
        setTitle("Hamming Distance");
        GridBagConstraints layoutConst = null; // Used to specify GUI component layout
        JScrollPane scrollPane = null;         // Container that adds a scroll bar
        JScrollPane scrollPane21 = null;
        
        //Creating labels
        hDistDisplay = new JLabel("Enter Hamming Dist:");
        d0Display = new JLabel("Distance 0:");
        d1Display = new JLabel("Distance 1:");
        d2Display = new JLabel("Distance 2:");
        d3Display = new JLabel("Distance 3:");
        d4Display = new JLabel("Distance 4:");
        compare = new JLabel("Compare with:");
        LabelFREE = new JLabel("FREE ZONE:");
        
        //Creating Text Fields
        hDist = new JFormattedTextField(1);
        hDist.setEditable(false);
        hDist.setText("2");
        hDist.setColumns(10); // Initial width of 10 units
        
        d0Field = new JFormattedTextField();
        d0Field.setEditable(false);
        d0Field.setColumns(10); // Initial width of 10 units
        
        d1Field = new JFormattedTextField();
        d1Field.setEditable(false);
        d1Field.setColumns(10); // Initial width of 10 units
        
        d2Field = new JFormattedTextField();
        d2Field.setEditable(false);
        d2Field.setColumns(10); // Initial width of 10 units
        
        d3Field = new JFormattedTextField();
        d3Field.setEditable(false);
        d3Field.setColumns(10); // Initial width of 10 units
        
        d4Field = new JFormattedTextField();
        d4Field.setEditable(false);
        d4Field.setColumns(10); // Initial width of 10 units
        
        newStation = new JFormattedTextField(1);
        newStation.setEditable(true);
        newStation.setText("ZERO");
        newStation.setColumns(10); // Initial width of 10 units
        
        
        //Creating dropdown box filled w/ Mesonet values
        try
        {
          fullList = reader();
        } catch (IOException e) {
          e.printStackTrace();
        }
        stations = new JComboBox(fullList);
        stations.setSelectedIndex(76);
        
        
        String[] columnNames2 = {"Distance", "Station Name"};
        //String testing = Dist1[1].
        
        Object[][] data2 =    {
                            {"1", Dist1[1]},
                            {"2", Dist2[1]},
                            {"2", Dist1[2]},
                            {"3", Dist3[1]},
                            {"3", Dist3[2]},
                            {"3", Dist3[3]},
                            {"4", Dist4[1]},
                            {"4", Dist4[2]},
                            {"4", Dist4[3]},
                            {"4", Dist4[4]},
                            };
                            
                            
        
        //Create Buttons
        showButton = new JButton("Show Station");
        showButton.addActionListener(this);
        
        calcButton = new JButton("Calculate HD");
        calcButton.addActionListener(this);
        
        addButton = new JButton("Add Station");
        addButton.addActionListener(this);
        
        tableButton = new JButton("Update the table");
        tableButton.addActionListener(this);
        
        //Create Slider
        hDistSlider = new JSlider(1, 4, 1);
        hDistSlider.addChangeListener(this);
        hDistSlider.setMajorTickSpacing(1);
        hDistSlider.setMinorTickSpacing(1);
        hDistSlider.setValue(2);
        hDistSlider.setPaintTicks(true);
        hDistSlider.setPaintLabels(true); 
        
        //Add the Text Area as a scrollPane
        outputArea = new JTextArea(20, 15);
        outputAreaTable = new JTextArea(10, 15);
        scrollPane = new JScrollPane(outputArea);
        outputArea.setEditable(false);
        
       // scrollPane21 = new JScrollPane(outputAreaTable);
        //outputAreaTable.setEditable(false);
        
        setLayout(new GridBagLayout());
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 1, 1);
        layoutConst.anchor = 21;
        layoutConst.gridx = 0;
        layoutConst.gridy = 0;
        layoutConst.gridwidth = 1;
        add(hDistDisplay, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 1, 1, 10);
        layoutConst.fill = 2;
        layoutConst.gridx = 1;
        layoutConst.gridy = 0;
        layoutConst.gridwidth = 1;
        add(hDist, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(1, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 0;
        layoutConst.gridy = 1;
        layoutConst.gridwidth = 2;
        add(hDistSlider, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 5);
        layoutConst.anchor = 21;
        layoutConst.gridx = 0;
        layoutConst.gridy = 2;
        layoutConst.gridwidth = 1;
        add(showButton, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(1, 10, 20, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 0;
        layoutConst.gridy = 3;
        layoutConst.gridwidth = 2;
        add(scrollPane, layoutConst);
        
        JTable table2 = new JTable(data2, columnNames2);
        
        JScrollPane scrollPane211 = new JScrollPane(table2);
        outputAreaTable.setEditable(false);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(1, 10, 20, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 3;
        layoutConst.gridy = 3;
        layoutConst.gridwidth = 2;
        add(scrollPane211, layoutConst);
        
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 2;
        layoutConst.gridx = 0;
        layoutConst.gridy = 4;
        layoutConst.gridwidth = 1;
        add(compare, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 1;
        layoutConst.gridy = 4;
        layoutConst.gridwidth = 1;
        add(stations, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 1;
        layoutConst.gridy = 15;
        layoutConst.gridwidth = 1;
        add(newStation, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 0;
        layoutConst.gridy = 9;
        layoutConst.gridwidth = 1;
        add(calcButton, layoutConst);

        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 0;
        layoutConst.gridy = 10;
        layoutConst.gridwidth = 1;
        add(d0Display, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 0;
        layoutConst.gridy = 11;
        layoutConst.gridwidth = 1;
        add(d1Display, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 0;
        layoutConst.gridy = 12;
        layoutConst.gridwidth = 1;
        add(d2Display, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 0;
        layoutConst.gridy = 13;
        layoutConst.gridwidth = 1;
        add(d3Display, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 0;
        layoutConst.gridy = 14;
        layoutConst.gridwidth = 1;
        add(d4Display, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 1;
        layoutConst.gridy = 10;
        layoutConst.gridwidth = 1;
        add(d0Field, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 1;
        layoutConst.gridy = 11;
        layoutConst.gridwidth = 1;
        add(d1Field, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 1;
        layoutConst.gridy = 12;
        layoutConst.gridwidth = 1;
        add(d2Field, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 1;
        layoutConst.gridy = 13;
        layoutConst.gridwidth = 1;
        add(d3Field, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 2;
        layoutConst.gridx = 1;
        layoutConst.gridy = 14;
        layoutConst.gridwidth = 1;
        add(d4Field, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 22;
        layoutConst.gridx = 3;
        layoutConst.gridy = 0;
        layoutConst.gridwidth = 5;
        add(LabelFREE, layoutConst);
        
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 0;
        layoutConst.gridy = 15;
        layoutConst.gridwidth = 1;
        add(addButton, layoutConst);
        
        layoutConst = new GridBagConstraints();
        layoutConst.insets = new Insets(10, 10, 10, 10);
        layoutConst.fill = 21;
        layoutConst.gridx = 3;
        layoutConst.gridy = 2;
        layoutConst.gridwidth = 1;
        add(tableButton, layoutConst);
      }
        
    public void stateChanged(ChangeEvent event)
    {
      JSlider sourceEvent = (JSlider)event.getSource();
      
      if (sourceEvent == hDistSlider) {
        //int sliderVal = hDistSlider.getValue();
        String hDistNumber = Integer.toString(hDistSlider.getValue());
        hDist.setText(hDistNumber);
      }
    }
        
        
    
    
    
    String[] reader() throws IOException {
        
        String[] nodes = new String[120];
        
        String filename = "Mesonet.txt";
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String lineOfData = br.readLine().substring(0, 4);  // String containing the first line of data from the file
        
        int k = 0;
        while(lineOfData != null) // TESTER of first 5
        {
            nodes[k] = lineOfData.substring(0, 4);
            lineOfData = br.readLine();
            String TESTER = nodes[k]; //TESTER
         // System.out.println(TESTER); //TESTER
          k++;
            
            
        }
     //return dataArray;
      
      String[] newDataArray = new String[k];
      for (int i = 0; i < k; i++)
      {
        newDataArray[i] = nodes[i];
       // System.out.println(i); //TESTER
      }
      return newDataArray;
       
      
    }
    
    void hDistCounter(String city) throws IOException {
        //System.out.println("Tester2");
        currCity = city;
        //TESTER
        //currCity = "WEST";
        fullList = reader();
        
        for (int i = 0; i < fullList.length ; i++) {
            int counter = 0;

            for (int j = 0; j < 4; j++ ) {
                if (currCity.charAt(j) == fullList[i].charAt(j)) {
                    counter++;
                }
            }
            if (counter == 0) {
                Dist4[d4count] = fullList[i];
                d4count++;
                
            }
            if (counter == 1) {
                Dist3[d3count] = fullList[i];
                d3count++;
            }
            if (counter == 2) {
                Dist2[d2count] = fullList[i];
                d2count++;
            }
            if (counter == 3) {
                Dist1[d1count] = fullList[i];
                d1count++;
            }
            if (counter == 4) {
                Dist0[d0count] = fullList[i];
                d0count++;
            }
        }
    }
    
    public void actionPerformed(ActionEvent event){
        
      outputArea.setText("");
      
      String userCity = "";
      
      userCity = (String) stations.getSelectedItem();
      
      try
    {
        hDistCounter(userCity);
    }
    catch (IOException e1)
    {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }
      
      JButton sourceEvent = (JButton)event.getSource();
      if (sourceEvent == showButton)
      {
          
          
          
        if (hDistSlider.getValue() == 1) {
          for (int j = 0; j < d1count; j++) {
            outputArea.append(Dist1[j] + "\n");
          }
        }
          
        else if (hDistSlider.getValue() == 2) {
          for (int j = 0; j < d2count; j++) {
            outputArea.append(Dist2[j] + "\n");
          }
          }

          else if (hDistSlider.getValue() == 3) {
          for (int j = 0; j < d3count; j++) {
            outputArea.append(Dist3[j] + "\n");
          }
          }

          else if (hDistSlider.getValue() == 4) {
          for (int j = 0; j < d4count; j++) {
            outputArea.append(Dist4[j] + "\n");
          }
          }
          
          else {
          System.out.println("Error.");
        }
        
      }

      //DISPLAYING HOW MANY HAVE ARE AT EACH H DISTANCE
      if (sourceEvent == calcButton)
      {
          //System.out.println("Tester1");
          
          
          d0Field.setText(String.valueOf(d0count));
          d1Field.setText(String.valueOf(d1count));
          d2Field.setText(String.valueOf(d2count));
          d3Field.setText(String.valueOf(d3count));
          d4Field.setText(String.valueOf(d4count));
      }
      
      d0count = 0;
      d1count = 0;
      d2count = 0;
      d3count = 0;
      d4count = 0;

      //ADDING A NEW STATION
      if (sourceEvent == addButton)
      {
          OUTER_LOOP:
          for (int j = 0; j < fullList.length; j++) 
          {
              if (newStation.getText() == fullList[j]) {
                  break OUTER_LOOP;
              }
              else if (j == fullList.length -1  && newStation.getText() != fullList[fullList.length-1]) 
              {
                  int newStationCount = fullList.length + 1;
                  
                  String[] newDataArray = new String[newStationCount];
                  
                  for (int i = 0; i < fullList.length; i++)
                  {
                    newDataArray[i] = fullList[i];
                  }
                  newDataArray[fullList.length] = newStation.getText();
                  
                  stations.addItem(newStation.getText());
              }
          }

        

      }
      
    //Updating the table
      if (sourceEvent == tableButton)
      {
          JFrame topFrame = null;        // Application window
          JScrollPane scrollPane21 = null;
          
          topFrame = new JFrame("Table Time!");


          String[] columnNames = {"Distance", "Station Name"};
          //String testing = Dist1[1].
          
          Object[][] data =    {
                              {"1", Dist1[1]},
                              {"2", Dist2[1]},
                              {"2", Dist1[2]},
                              {"3", Dist3[1]},
                              {"3", Dist3[2]},
                              {"3", Dist3[3]},
                              {"4", Dist4[1]},
                              {"4", Dist4[2]},
                              {"4", Dist4[3]},
                              {"4", Dist4[4]},
                              };
          
          JTable table = new JTable(data, columnNames);
          
          scrollPane21 = new JScrollPane(table);
          
          
          GridBagConstraints layoutConst = new GridBagConstraints();
          layoutConst.insets = new Insets(1, 10, 20, 10);
          layoutConst.fill = 21;
          layoutConst.gridx = 0;
          layoutConst.gridy = 0;
          layoutConst.gridwidth = 2;
          add(scrollPane21, layoutConst);
          
       // Add table header
          layoutConst = new GridBagConstraints();
          layoutConst.insets = new Insets(10, 10, 0, 0);
          layoutConst.fill = GridBagConstraints.HORIZONTAL;
          layoutConst.gridx = 0;
          layoutConst.gridy = 0;
          layoutConst.gridwidth = 2;
          add(table.getTableHeader(), layoutConst);

          // Add table itself
          layoutConst = new GridBagConstraints();
          layoutConst.insets = new Insets(0, 10, 10, 0);
          layoutConst.fill = GridBagConstraints.HORIZONTAL;
          layoutConst.gridx = 0;
          layoutConst.gridy = 1;
          layoutConst.gridwidth = 2;
          add(table, layoutConst);
          
          // Resize window to fit components 
          topFrame.pack();

          // Set program to terminate when window is closed
          topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          // Display window
          topFrame.setVisible(true);

      }
      
    }

                        
    /* Creates a GraphicalHammingDistanceFrame and makes it visible */
    public static void main(String[] args) {
       // Creates GraphicalHammingDistanceFrame and its components
        GraphicalHammingDistance myFrame = new GraphicalHammingDistance();

       myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       myFrame.pack();
       myFrame.setVisible(true);
       
     //TEST
       
       //System.out.println(Tester);
    }
}
