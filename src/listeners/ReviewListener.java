package listeners;

import app.gui.SubmitReviewUseCaseFactory;
import views.ReviewView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviewListener {
    private JPanel inputPanel;
    private ReviewView reviewView;

    /**
     * In order to adhere to the Single Responsibility Principle;
     * Construct an object that will take care of listener creation and the associated reviewView
     * @param inputPanel the input panel of the GUI which will be updated.
     */
    public ReviewListener(JPanel inputPanel) {
        this.inputPanel = inputPanel;
        this.reviewView = SubmitReviewUseCaseFactory.create();
    }

    /**
     * Get the action listener
     * @return ActionListener for the button which will open the submit review panel.
     */
    public ActionListener getActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear the previous Panel
                inputPanel.removeAll();
                // to fix a null exception caused by IntelliJ's GUI creator
                inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
                inputPanel.add(reviewView);
                inputPanel.revalidate();
            }
        };
    }

    /**
     * Retrieve the ReviewPanel that will be launched with this action listener
     * @return ReviewPanel
     */
    public ReviewView getReviewView() {
        return reviewView;
    }

}
