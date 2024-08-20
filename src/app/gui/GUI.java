package app.gui;

import com.google.maps.model.AutocompletePrediction;
import data_access.AutoCompletionDAO;
import entity.ParkingLot;
import interface_adapter.ReviewViewModel;
import listeners.*;
import views.ReviewView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;

import data_access.ParkingLotDAO;
import data_access.ReviewDAO;
import interface_adapter.*;
import use_case.FilterByEOE.EOEInteractor;
import use_case.FilterByEOF.EOFInputData;
import use_case.FilterByEOF.EOFInteractor;
import entity.ParkingLot;
import use_case.FilterByPrice.FilterByPriceInputBoundary;
import use_case.FilterByPrice.FilterByPriceInputData;
import use_case.FilterByPrice.FilterByPriceInteractor;
import use_case.FilterByPrice.FilterByPriceOutputBoundary;
import interface_adapter.FilterByPriceController;
import use_case.FilterByProximity.FilterByProximityInputBoundary;
import use_case.FilterByProximity.FilterByProximityInputData;
import use_case.FilterByProximity.FilterByProximityInteractor;
import use_case.FilterByProximity.FilterByProximityOutputBoundary;
import use_case.FilterByRadius.FilterByRadiusInputBoundary;
import use_case.FilterByRadius.FilterByRadiusInputData;
import use_case.FilterByRadius.FilterByRadiusInteractor;
import use_case.FilterByRadius.FilterByRadiusOutputBoundary;
import use_case.FilterByType.FilterByTypeInputBoundary;
import use_case.FilterByType.FilterByTypeInputData;
import use_case.FilterByType.FilterByTypeInteractor;
import use_case.FilterByType.FilterByTypeOutputBoundary;
import use_case.SubmitReview.*;
import views.FilterByRadiusView;
import views.ReviewView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GUI extends JFrame {

    private JPanel GUIPanel;
    private JTextField textFieldAddress;
    private JButton radiusButton;
    private JButton availabilityButton;
    private JButton priceButton;
    private JButton easeOfEntryButton;
    private JButton proximityButton;
    private JButton typeButton;
    private JPanel buttonsPanel;
    private JScrollPane resultsScrollPane;
    private JButton ReviewButton;
    private JPanel inputPanel;
    private String selectedAddress;
    private JPanel resultsButtonPanel;
    private final AutoCompletionDAO autoCompletionDAO = new AutoCompletionDAO();
    private JButton submitReviewButton;
    private JPanel resultsTextPanel;
    private ReviewView reviewView;

    public GUI() {

        setContentPane(GUIPanel);
        setTitle("Locate Lot");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GraphicsDevice device = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();

        setSize(1200, 500);
        // set location for map here?
        setLocationRelativeTo(null);
        setVisible(true);


        // to fix a null exception caused by IntelliJ's GUI creator
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        resultsButtonPanel.setLayout(new BoxLayout(resultsButtonPanel, BoxLayout.Y_AXIS));
        // Listener to detect changes in the textFieldBox, when the user inputs an address
        textFieldAddress.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateSuggestedAddresses();
            }

            public void removeUpdate(DocumentEvent e) {
            }

            public void insertUpdate(DocumentEvent e) {
                updateSuggestedAddresses();
            }
        });

        /*
         * action performed button for proximity search
         * must return parking lots sorted by closest first
         */
        proximityButton.addActionListener(new ProximityListener(this).getActionListener()); {
            @Override
            public void actionPerformed(ActionEvent e) {
                String address = textFieldAddress.getText();
                FilterByProximityInputData inputData = new FilterByProximityInputData(address);

                // Create the presenter
                FilterByProximityOutputBoundary presenter = new FilterByProximityPresenter(GUI.this);

                // Create the interactor with the presenter
                FilterByProximityInputBoundary interactor = null;
                try {
                    interactor = new FilterByProximityInteractor(presenter);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                // Execute the interactor
                try {
                    interactor.execute(inputData);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        /*
         * radius button - opens a screen requiring user input for custom radius
         * will return parkinglots within the radius sorted by default (proximity)
         */
        radiusButton.addActionListener(new RadiusListener(this).getActionListener()); {
            @Override
            public void actionPerformed(ActionEvent e) {

                inputPanel.removeAll();
                // to fix a null exception caused by IntelliJ's GUI creator
                inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

                // Object creation encapsulated from UI and View logic using factory design pattern
                FilterByRadiusUseCaseFactory filterByRadiusUseCaseFactory = new
                        FilterByRadiusUseCaseFactory(GUI.this, textFieldAddress);
                FilterByRadiusView filterByRadiusView = filterByRadiusUseCaseFactory.createFilterByRadiusView();

                inputPanel.add(filterByRadiusView);

                inputPanel.revalidate();
            }

        });

        /*
         * action performed button for price
         * returns list of parking lots within the default radius sorted from lowest to highest price
         *
         */
        priceButton.addActionListener(new PriceListener(this).getActionListener());

        /*
         * action performed button for ease of entry reviews
         * returns list of parking lots within the default radius sorted from best to worst (+ unrated) reviews
         */
        easeOfEntryButton.addActionListener(new EOEListener(this).getActionListener());

        /*
         * action performed for availability button
         * sorts the parking lots within the default radius by availability
         */
        availabilityButton.addActionListener(new AvailabilityListener(this).getActionListener());


        /*
         * action performed for type button
         * must make user choose between "surface" or "garage"
         * final results include only the chosen option
         */
        typeButton.addActionListener(new TypeListener(this).getActionListener());

        /*
         * action performed for submit review button
         * the review view is constructed in the listener, through a factory class
         */
        ReviewListener reviewListener = new ReviewListener(inputPanel);
        submitReviewButton.addActionListener(reviewListener.getActionListener());

        reviewView = reviewListener.getReviewView();


    }

    /**
     * Update the parking lots suggested
     * @param parkingLots The list of new parking lots
     */
    public void updateParkingLotList(ParkingLot[] parkingLots) {
        // Update the GUI with the sorted parking lots
        resultsButtonPanel.removeAll();
        resultsButtonPanel.add(new JLabel("RESULTS"));
        DefaultListModel<String> listModel = new DefaultListModel<>();
        ArrayList<JButton> buttonsArrayList = new ArrayList<JButton>();

        for (ParkingLot lot : parkingLots) {
            listModel.addElement(lot.toString());
            JButton b = new JButton(lot.toString());

            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setMaximumSize(new Dimension(Integer.MAX_VALUE, b.getMinimumSize().height));
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (reviewView != null) {
                        reviewView.setParkingLot(lot);
                    }
                }
            });
            resultsButtonPanel.add(b);
            resultsButtonPanel.revalidate();
            resultsButtonPanel.repaint();

            buttonsArrayList.add(b);
        }
    }

    /**
     * Update the parking lots suggested, overloading to allow a List as a parameter
     * @param parkingLots The list of new parking lots
     */
    public void updateParkingLotList(List<ParkingLot> parkingLots) {
//        // Update the GUI with the sorted parking lots
        resultsButtonPanel.removeAll();
        resultsButtonPanel.add(new JLabel("RESULTS"));

        DefaultListModel<String> listModel = new DefaultListModel<>();
        ArrayList<JButton> buttonsArrayList = new ArrayList<JButton>();

        for (ParkingLot lot : parkingLots) {
            listModel.addElement(lot.toString());
            JButton b = new JButton(lot.toString());

            b.setAlignmentX(Component.CENTER_ALIGNMENT);
            b.setMaximumSize(new Dimension(Integer.MAX_VALUE, b.getMinimumSize().height));
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    reviewView.setParkingLot(lot);
                }
            });
            resultsButtonPanel.add(b);
            resultsButtonPanel.revalidate();
            resultsButtonPanel.repaint();

            buttonsArrayList.add(b);
        }
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {

        new GUI();
    }


    // Private void; updates the panel corresponding to suggested address buttons
    private void updateSuggestedAddresses() {

        try{
            buttonsPanel.removeAll();

            // An array to store the results of the search
            AutocompletePrediction[] results;
            results = autoCompletionDAO.getListOfPredictions(textFieldAddress.getText());

            // For later implementation when we ask for the LatLng of the place
            ArrayList<JButton> buttonsArrayList = new ArrayList<JButton>();

            for (AutocompletePrediction result : results) {
                JButton b = new JButton(result.description);

                b.setAlignmentX(Component.CENTER_ALIGNMENT);
                b.setMaximumSize(new Dimension(Integer.MAX_VALUE, b.getMinimumSize().height));
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedAddress = b.getText();
                        textFieldAddress.setText(selectedAddress);
                    }
                });

                buttonsPanel.add(b);
                buttonsPanel.revalidate(); buttonsPanel.repaint();

                buttonsArrayList.add(b);
            }
        }
        catch (Exception ex){
            buttonsPanel.removeAll();
        }

    }

    /*
    gets the entered address
     */
    public String getAddress() {
        return textFieldAddress.getText();
    }
}
