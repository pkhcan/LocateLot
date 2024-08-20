package views;

import entity.ParkingLot;
import interface_adapter.ReviewState;
import interface_adapter.ReviewViewModel;
import interface_adapter.SubmitReviewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class ReviewView extends JPanel implements PropertyChangeListener {
    private final SubmitReviewController controller;
    private final ReviewViewModel viewModel;
    private ParkingLot selectedParkingLot;
    private JPanel inputPanel;
    private JLabel messageLabel;
    private JButton submitButton;

    /**
     * Construct a ReviewView to interact with the user and invoke the controller.
     * @param controller The associated controller with the use case
     * @param viewModel The associated view model
     */
    public ReviewView(SubmitReviewController controller, ReviewViewModel viewModel) {

        this.controller = controller;
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());
        this.inputPanel = new JPanel();
        this.add(inputPanel, BorderLayout.NORTH);

        showAskForRating();

        this.selectedParkingLot = null;
        messageLabel = new JLabel();

        this.add(messageLabel, BorderLayout.CENTER);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.revalidate();
    }

    /**
     * Decouples the controller dependency for testing in isolation purposes, shall not be used otherwise.
     * @param viewModel
     */
    public ReviewView(ReviewViewModel viewModel) {
        controller = null;
        this.viewModel = viewModel;
        viewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());
        this.inputPanel = new JPanel();
        this.add(inputPanel, BorderLayout.NORTH);

        showAskForRating();

        this.selectedParkingLot = null;
        messageLabel = new JLabel();

        this.add(messageLabel, BorderLayout.CENTER);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        this.revalidate();
    }

    /**
     * Let the view know that there has been changes in the state
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ReviewState state = (ReviewState) evt.getNewValue();
        if (Objects.equals(state.getState(), "AskForRating")){
            showAskForRating();
        }
        else if (Objects.equals(state.getState(), "Submitted")){
            showSubmitted(state.getParkingLot(), state.getRating());
        }
        else if (Objects.equals(state.getState(), "Failed")){
            showFailed();
        }
        else if (Objects.equals(state.getState(), "Empty")){
            showEmpty();
        }
    }

    /*
     * Private Method, to ask the user for input.
     */
    private void showAskForRating() {
        // Prepare the review panel
        inputPanel.removeAll();
        inputPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel enterRatingLabel = new JLabel("Enter your rating:");
        inputPanel.add(enterRatingLabel);

        // Create a slider
        JSlider ratingSlider = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);

        // Customize the slider
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintTrack(true);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setSnapToTicks(true);
        ratingSlider.setPaintLabels(true);


        inputPanel.add(ratingSlider);

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.submit(selectedParkingLot, ratingSlider.getValue());
            }
        });

        inputPanel.add(submitButton);
        this.submitButton = submitButton;

        this.revalidate();


        }

    /*
     * Private Method, to show the user that submission was successful
     */
    private void showSubmitted(String parkingLot, int rating){
        messageLabel.setText("Your review of " + rating +
                "/5 for parking lot " + parkingLot + " was submitted");

        this.revalidate();
    }

    /*
     * Private Method, to let the user know that submission failed.
     */
    private void showFailed(){
        messageLabel.setText("Something went wrong, your review was not submitted.");
        this.revalidate();
    }

    /*
     * Private Method, to let the user know that they have not selected a parking lot yet.
     */
    private void showEmpty(){
        messageLabel.setText("You have not selected a parking lot yet.");

        this.revalidate();

    }

    /**
     * Update the parking lot selected by the user, to be used in the GUI.
     * @param parkingLot Current selected parking lot.
     */
    public void setParkingLot(ParkingLot parkingLot) {
        this.selectedParkingLot = parkingLot;
    }



}
