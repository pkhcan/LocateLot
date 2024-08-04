package app.gui;

import com.google.maps.errors.ApiException;
import com.google.maps.model.AutocompletePrediction;
import data_access.AutoCompletionDAO;

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
import data_access.ReviewDataAccessInterface;
import interface_adapter.*;
import use_case.FilterByEOE.EOEInputData;
import use_case.FilterByEOE.EOEInteractor;
import use_case.FilterByEOF.EOFInputData;
import use_case.FilterByEOF.EOFInteractor;
import entity.ParkingLot;
import use_case.FilterByPrice.FilterByPriceInputBoundary;
import use_case.FilterByPrice.FilterByPriceInputData;
import use_case.FilterByPrice.FilterByPriceInteractor;
import use_case.FilterByPrice.FilterByPriceOutputBoundary;
import use_case.FilterByProximity.FilterByProximityInputBoundary;
import use_case.FilterByProximity.FilterByProximityInputData;
import use_case.FilterByProximity.FilterByProximityInteractor;
import use_case.FilterByProximity.FilterByProximityOutputBoundary;
import use_case.FilterByRadius.FilterByRadiusInputBoundary;
import use_case.FilterByRadius.FilterByRadiusInputData;
import use_case.FilterByRadius.FilterByRadiusInteractor;
import use_case.FilterByRadius.FilterByRadiusOutputBoundary;
import use_case.SubmitReview.*;
import views.ReviewView;
//import interface_adapter.EOFPresenter;

import java.io.IOException;
import java.util.ArrayList;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;


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
    private JButton ReviewButton;
    private JPanel ReviewPanel;
    //    ^^ replace with the API search box and button ?
    private String selectedAddress;
//    private JScrollPane resultsScrollPane;
    private JPanel resultsButtonPanel;
    private final AutoCompletionDAO autoCompletionDAO = new AutoCompletionDAO();


    public GUI() {

        setContentPane(GUIPanel);
        setTitle("Locate Lot");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        GraphicsDevice device = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();

        setSize(900, 400);
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

        // Set up the panel for submit review use case
        ReviewViewModel reviewViewModel = new ReviewViewModel();
        // to fix a null exception caused by IntelliJ's GUI creator
        ReviewPanel.setLayout(new BoxLayout(ReviewPanel, BoxLayout.Y_AXIS));

        ReviewPanel.add(SubmitReviewUseCaseFactory.create(reviewViewModel));
        ReviewPanel.revalidate();


        /*
         * action performed button for proximity search
         * must return parking lots sorted by closest first
         */
        proximityButton.addActionListener(new ActionListener() {
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
        radiusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String address = textFieldAddress.getText();
                double radius = 3.0;
                FilterByRadiusInputData inputData = new FilterByRadiusInputData(radius, address);

                // Create the presenter
                FilterByRadiusOutputBoundary presenter = new FilterByRadiusPresenter(GUI.this);

                // Create the interactor with the presenter
                FilterByRadiusInputBoundary interactor = null;
                try {
                    interactor = new FilterByRadiusInteractor(presenter);
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
         * action performed button for price
         * returns list of parking lots within the default radius sorted from lowest to highest price
         *
         */
        priceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String address = textFieldAddress.getText();
                double radius = 3.0;
                ParkingLotDAO radiusSortedList = null;
                LocalTime currentTime = LocalTime.now();

                try {
                    radiusSortedList = new ParkingLotDAO();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                FilterByPriceInputData inputData = new FilterByPriceInputData(radiusSortedList, currentTime);

                // Create the presenter
                FilterByPriceOutputBoundary presenter = new FilterByPricePresenter(GUI.this);

                // Create the interactor with the presenter
                FilterByPriceInputBoundary interactor = null;
                interactor = new FilterByPriceInteractor(presenter, radiusSortedList);

                // Execute the interactor
                try {
                    interactor.execute(inputData);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        easeOfEntryButton.addActionListener(new ActionListener() {
            /**
             * action performed button for ease of entry reviews
             * returns list of parking lots within the default radius sorted from best to worst (+ unrated) reviews
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create EOEInputData with the address from the text field
                String address = textFieldAddress.getText();

                // Create the presenter
                EOEPresenter presenter = new EOEPresenter(GUI.this);

                // Create the interactor with the presenter
                EOEInteractor interactor = new EOEInteractor(presenter);

                // Create the controller with the interactor
                EOEController controller = new EOEController(interactor);

                // Execute the interactor via the controller - handle EOE request
                try {
                    controller.handleEOE(address);
                } catch (IOException | InterruptedException | ApiException ex) {
                    showError(ex.getMessage());
                }

            }
        });




        /*
         * action performed for availability button
         * sorts the parking lots within the default radius by availability
         */
        availabilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String address = textFieldAddress.getText();
                EOFInputData inputData = new EOFInputData(address);

                // Create the presenter
                EOFPresenter presenter = new EOFPresenter(GUI.this);

                // Create the interactor with the presenter
                EOFInteractor interactor = new EOFInteractor(presenter);

                // Execute the interactor
                try {
                    interactor.execute(inputData);
                } catch (IOException | InterruptedException | ApiException ex) {
                    throw new RuntimeException(ex);
                }
//            }
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
//        for (ParkingLot lot : parkingLots) {
//            System.out.println(lot);
//        }
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
                    // add code here
                }
            });
            resultsButtonPanel.add(b);
            resultsButtonPanel.revalidate();
            resultsButtonPanel.repaint();

            buttonsArrayList.add(b);
        }
    }

    public void updateParkingLotList(List<ParkingLot> parkingLots) {
//        // Update the GUI with the sorted parking lots
//        // For simplicity, let's print them to the console
//        for (ParkingLot lot : parkingLots) {
//            System.out.println(lot);
//        }
//    }
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
                    // add code here
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




    private void createUIComponents() {
        // TODO: place custom component creation code here
    }



//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("LocateLot GUI App");
//            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            frame.setSize(850, 300);
//
//            CardLayout cardLayout = new CardLayout();
//            JPanel cardPanel = new JPanel(cardLayout);

//            JPanel defaultCard = createDefaultCard();
//            JPanel getGradeCard = createGetGradeCard(frame, getGradeUseCase);
//            JPanel logGradeCard = createLogGradeCard(frame, logGradeUseCase);
//            JPanel formTeamCard = createFormTeamCard(frame, formTeamUseCase);
//            JPanel joinTeamCard = createJoinTeamCard(frame, joinTeamUseCase);
//            JPanel manageTeamCard = createManageTeamCard(frame, leaveTeamUseCase, getAverageGradeUseCase);

//            cardPanel.add(defaultCard, "DefaultCard");
//            cardPanel.add(getGradeCard, "GetGradeCard");
//            cardPanel.add(logGradeCard, "LogGradeCard");
//            cardPanel.add(formTeamCard, "FormTeamCard");
//            cardPanel.add(joinTeamCard, "JoinTeamCard");
//            cardPanel.add(manageTeamCard, "ManageTeamCard");

//            JButton getGradeButton = new JButton("Get Grade");
//            getGradeButton.addActionListener(e -> cardLayout.show(cardPanel, "GetGradeCard"));
//
//            JButton logGradeButton = new JButton("Log Grade");
//            logGradeButton.addActionListener(e -> cardLayout.show(cardPanel, "LogGradeCard"));
//
//            JButton formTeamButton = new JButton("Form a team");
//            formTeamButton.addActionListener(e -> cardLayout.show(cardPanel, "FormTeamCard"));
//
//            JButton joinTeamButton = new JButton("Join a team");
//            joinTeamButton.addActionListener(e -> cardLayout.show(cardPanel, "JoinTeamCard"));
//
//            JButton manageTeamButton = new JButton("My Team");
//            manageTeamButton.addActionListener(e -> cardLayout.show(cardPanel, "ManageTeamCard"));

    // MARK HERE
//            JButton filterButton = new JButton("Filter by");
//            filterButton.addActionListener(e -> cardLayout.show(cardPanel, "filterOptions"));
//
//            JPanel buttonPanel = new JPanel();
//            buttonPanel.add(filterButton);

//            buttonPanel.add(getGradeButton);
//            buttonPanel.add(logGradeButton);
//            buttonPanel.add(formTeamButton);
//            buttonPanel.add(joinTeamButton);
//            buttonPanel.add(manageTeamButton);

    // MARK HERE TOO
//            frame.getContentPane().add(cardPanel, BorderLayout.CENTER);
//            frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
//
//            frame.setVisible(true);

//        });
//    }
//
//    }
}
