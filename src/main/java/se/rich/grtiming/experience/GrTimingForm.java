package se.rich.grtiming.experience;

import se.rich.grtiming.experience.formulator.FinishListFormulator;
import se.rich.grtiming.experience.formulator.ParticapantFormulator;
import se.rich.grtiming.experience.formulator.RaceEventFormulator;
import se.rich.grtiming.experience.formulator.RaceFormulator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GrTimingForm {
    private JPanel panel1;
    private JPanel topPanel;
    private JPanel MiddlePanel;
    private JPanel BottomPanel;
    private JTabbedPane tabbedPane1;
    private JPanel raceEventTab;
    private JTable raceTable;
    private JPanel raceTab;
    private JPanel participantTab;
    private JTable raceEventTable;
    private JTable participantTable;
    private JButton participantButton;
    private JPanel finishTab;
    private JTable finishListTable;
    private JButton button2;
    private JButton finishButton;

    private RaceEventFormulator raceEventFormulator = new RaceEventFormulator();
    private RaceFormulator raceFormulator = new RaceFormulator();
    private ParticapantFormulator particapantFormulator = new ParticapantFormulator();
    private FinishListFormulator finishListFormulator = new FinishListFormulator();

    public GrTimingForm() {
        boolean debugMode = true;
        DefaultTableModel raceEventModel = new DefaultTableModel(null, raceEventFormulator.getHeaders());
        raceEventFormulator.addRows(raceEventModel);
        DefaultTableModel raceModel = new DefaultTableModel(null, raceFormulator.getHeaders());
        DefaultTableModel participantModel = new DefaultTableModel(null, particapantFormulator.getHeaders());
        DefaultTableModel finishListModel = new DefaultTableModel(null, finishListFormulator.getHeaders());


        raceEventTable.setModel(raceEventModel);
        if (!debugMode) {
            // Remove the first column (id) from the TableColumnModel
            // If you need access to the data then you use table.getModel().getValueAt(...)
            TableColumnModel tcm1 = raceEventTable.getColumnModel();
            tcm1.removeColumn(tcm1.getColumn(0));
            //
        }

        raceTable.setModel(raceModel);
        if (!debugMode) {
            // Remove the first column (id) from the TableColumnModel
            // If you need access to the data then you use table.getModel().getValueAt(...)
            TableColumnModel tcm2 = raceTable.getColumnModel();
            tcm2.removeColumn(tcm2.getColumn(0));
            //
        }

        participantTable.setModel(participantModel);
        if (!debugMode) {
            // Remove the first column (id) from the TableColumnModel
            // If you need access to the data then you use table.getModel().getValueAt(...)
            TableColumnModel tcm3 = participantTable.getColumnModel();
            tcm3.removeColumn(tcm3.getColumn(0));
            //
        }

        finishListTable.setModel(finishListModel);
        if (!debugMode) {
            // Remove the first column (id) from the TableColumnModel
            // If you need access to the data then you use table.getModel().getValueAt(...)
            TableColumnModel tcm4 = finishListTable.getColumnModel();
            tcm4.removeColumn(tcm4.getColumn(0));
            //
        }

        raceEventTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Object object = raceEventModel.getValueAt(raceEventTable.getSelectedRow(), 0);
                System.out.println(Integer.valueOf(object.toString()));
                raceFormulator.addRows(raceModel);
                tabbedPane1.setSelectedIndex(1);
                System.out.println(tabbedPane1.getSelectedIndex());
            }
        });

        raceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Object object = raceModel.getValueAt(raceTable.getSelectedRow(), 0);
                participantModel.setRowCount(0);
                particapantFormulator.addRows(participantModel, Integer.valueOf(object.toString()));
//                tabbedPane1.setSelectedIndex(2);
                System.out.println(tabbedPane1.getSelectedIndex());

            }
        });

        participantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tabbedPane1.setSelectedIndex(2);
            }
        });
        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int nr = raceTable.getSelectedRow();
                System.out.println("index is " + nr);
                Object object = raceModel.getValueAt(raceTable.getSelectedRow(), 0);
                finishListModel.setRowCount(0);
                finishListFormulator.addRows(finishListModel, Integer.valueOf(object.toString()));
                tabbedPane1.setSelectedIndex(3);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GrTimingForm");
        frame.setContentPane(new GrTimingForm().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(800, 1024);
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
