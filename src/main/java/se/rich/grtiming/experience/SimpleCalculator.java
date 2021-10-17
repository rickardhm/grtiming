package se.rich.grtiming.experience;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator {
    private JPanel mainPanel;
    private JPanel bottomPanel;
    private JButton addButton;
    private JButton clearButton;
    private JTabbedPane jTabbedPane;

    public SimpleCalculator() {
        RaceForm raceForm = new RaceForm();
        ParticipantForm participantForm = new ParticipantForm();

        JPanel firstPanel = raceForm.getMainPanel();
        JPanel secondPanel = participantForm.getMainPanel();

        jTabbedPane.add("Race", firstPanel);
        jTabbedPane.add("Participants", secondPanel);

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jTabbedPane.remove(1);
                DefaultTableModel model = new DefaultTableModel(null, participantForm.getFormulator().getHeaders());
//                participantForm.getFormulator().addRows(model);
                jTabbedPane.add("Participants", new ParticipantForm(16659).getMainPanel());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SimpleCaslculator");
        frame.setContentPane(new SimpleCalculator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
