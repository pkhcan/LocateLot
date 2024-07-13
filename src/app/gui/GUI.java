package app.gui;

import com.google.maps.FindPlaceFromTextRequest;
import com.google.maps.model.AutocompletePrediction;
import com.google.maps.model.FindPlaceFromText;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.PlacesSearchResult;
import data_access.AutoCompletionObject;
import data_access.GeoApiDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GUI extends JFrame {

    private JPanel GUIPanel;
    private JTextField textFieldAddress;
    private JButton buttonSubmitAddress;
    private JButton noiceButton;
    private JScrollPane buttonsScrollPane;
    private JPanel buttonsPanel;
//    ^^ replace with the API search box and button ?

    public GUI () {
        setContentPane(GUIPanel);
        setTitle("LocateLot!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        // set location for map here?
        setLocationRelativeTo(null);
        setVisible(true);

        // to fix a null exception caused by IntelliJ's GUI creator
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));



        /*
        * edit to display list of options or to display filter choices
         */

        buttonSubmitAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(GUI.this, "Received.");

            }
        });

        textFieldAddress.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//                    GeocodingResult[] results;
                    try{
//                        results = GeoApiDAO.getLatitudeLongitude(textFieldAddress.getText());
//                        textFieldAddress.setText(results[0].formattedAddress);


                        AutoCompletionObject autoCompletionObject = new AutoCompletionObject();

                        AutocompletePrediction[] results;
                        results = autoCompletionObject.getListOfPredictions(textFieldAddress.getText());
                        ArrayList<JButton> buttonsArrayList = new ArrayList<JButton>();

                        for (AutocompletePrediction result : results) {
                            System.out.println("--  ");
                            JButton b = new JButton(result.description);

                            b.setAlignmentX(Component.CENTER_ALIGNMENT);
                            b.setMaximumSize(new Dimension(Integer.MAX_VALUE, b.getMinimumSize().height));
                            buttonsPanel.add(b);
                            buttonsPanel.revalidate(); buttonsPanel.repaint();

                            buttonsArrayList.add(b);
                        }
                    }
                    catch (Exception ex){
                        textFieldAddress.setText("err");
                        System.out.println(ex.getMessage());
                    }

                }
            }
        });

        noiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = new JButton("1");
                b.setAlignmentX(Component.CENTER_ALIGNMENT);
                b.setMaximumSize(new Dimension(Integer.MAX_VALUE, b.getMinimumSize().height));
                buttonsPanel.add(b);
                buttonsPanel.revalidate(); buttonsPanel.repaint();
            }
        });
    }

    public static void main(String[] args) {

        new GUI();

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
