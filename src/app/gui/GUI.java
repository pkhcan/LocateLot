package app.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import use_case.FilterByEOE.EOEInputData;
import use_case.FilterByEOE.EOEInteractor;
import entity.ParkingLot;
import interface_adapter.EOEPresenter;

public class GUI extends JFrame {

    private JPanel GUIPanel;
    private JTextField textFieldAddress;
    private JButton submitButton;
    private JButton radiusButton;
    private JButton availabilityButton;
    private JButton priceButton;
    private JButton easeOfEntryButton;
    private JButton proximityButton;
    private JButton typeButton;
    //    ^^ replace with the API search box and button ?

    public GUI() {
        setContentPane(GUIPanel);
        setTitle("LocateLot!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 400);
        // set location for map here?
        setLocationRelativeTo(null);
        setVisible(true);


        /*
         * currently returns "received." when submit is pressed
         * edit to autocomplete address
         */
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(GUI.this, "Received.");
            }
        });


        /*
         * action performed button for proximity search
         * must return parking lots sorted by closest first
         */
        proximityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO");
            }
        });


        /*
         * radius button - opens a screen requiring user input for custom radius
         * will return parkinglots within the radius sorted by default (proximity)
         */
        radiusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO");
            }
        });


        /*
         * action performed button for price
         * returns list of parking lots within the default radius sorted from lowest to highest price
         *
         */
        priceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO");
            }
        });


        /*
         * action performed button for ease of entry reviews
         * returns list of parking lots within the default radius sorted from best to worst (+ unrated) reviews
         */
        easeOfEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Step 1: Create EOEInputData with the address from the text field
                String address = textFieldAddress.getText();
                EOEInputData inputData = new EOEInputData(address);

                // Create the presenter
                EOEPresenter presenter = new EOEPresenter(GUI.this);

                // Create the interactor with the presenter
                EOEInteractor interactor = new EOEInteractor(presenter);

                // Execute the interactor
                interactor.execute(inputData);
//
//                // Get sorted ParkingLot objects from EOEOutputData
//                // Assuming the EOEOutputBoundary.present method saves the output data somewhere accessible
//                ParkingLot[] sortedParkingLots = EOEOutputData.getSortedParkingLots();
//
//                updateParkingLotList(sortedParkingLots);
            }
        });




        /*
         * action performed for availability button
         * sorts the parking lots within the default radius by availability
         */
        availabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO");
            }
        });


        /*
         * action performed for type button
         * must make user choose between "surface" or "garage"
         * final results include only the chosen option
         */
        typeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("TODO");
            }
        });
    }

    public void updateParkingLotList(ParkingLot[] parkingLots) {
        // Update the GUI with the sorted parking lots
        // For simplicity, let's print them to the console
        for (ParkingLot lot : parkingLots) {
            System.out.println(lot);
        }
    }


    public static void main(String[] args) {

        new GUI();
    }
}
