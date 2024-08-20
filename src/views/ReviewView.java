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

    private void showSubmitted(String parkingLot, int rating){
        messageLabel.setText("Your review of " + rating +
                "/5 for parking lot " + parkingLot + " was submitted");

        this.revalidate();
    }

    private void showFailed(){
        messageLabel.setText("Something went wrong, your review was not submitted.");
        this.revalidate();
    }

    private void showEmpty(){
        messageLabel.setText("You have not selected a parking lot yet.");

        this.revalidate();

    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.selectedParkingLot = parkingLot;
    }



}
