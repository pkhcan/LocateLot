package app.gui;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public GUI () {
        setTitle("LocateLot!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        // set location for map here?
        setLocationRelativeTo(null);
        setVisible(true);
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
