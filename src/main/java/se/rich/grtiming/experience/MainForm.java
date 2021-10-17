package se.rich.grtiming.experience;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainForm {
    private JTabbedPane tabbedPane;
    private JPanel mainPanel;

    public MainForm() {
        RaceForm raceForm = new RaceForm();
        ParticipantForm participantForm = new ParticipantForm();

        JPanel firstPanel = raceForm.getMainPanel();
        JPanel secondPanel = participantForm.getMainPanel();

        tabbedPane.add("Race", firstPanel);
        tabbedPane.add("Participants", secondPanel);

        /*raceForm.getRaceTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTable raceTable = raceForm.getRaceTable();
                int i = raceTable.getSelectedRow();
                System.out.println(i + "_" + raceTable.getColumnCount());
                Object modelValueAt = model.getValueAt(i, 0);
                int id = Integer.valueOf(modelValueAt.toString());
//                participantForm.getFormulator().addRows(model, id);
                System.out.println(id);
            }
        });*/

    }

    public JTabbedPane getTabbedPane() {
        return this.tabbedPane;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainForm");
        frame.setContentPane(new MainForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800,1024);
        frame.setVisible(true);
    }
}
