package se.rich.grtiming.experience;

import se.rich.grtiming.experience.formulator.RaceFormulator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RaceForm {
    private JPanel racePanel;
    private JTable raceTable;
    private JScrollPane raceScrollPane;
    private RaceFormulator formulator = new RaceFormulator();

    public JTable getRaceTable() {
        return raceTable;
    }

    public void setRaceTable(JTable raceTable) {
        this.raceTable = raceTable;
    }

    public RaceFormulator getFormulator() {
        return formulator;
    }

    public void setFormulator(RaceFormulator formulator) {
        this.formulator = formulator;
    }

    public JPanel getMainPanel() {
        return racePanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.racePanel = mainPanel;
    }

    public RaceForm() {
        DefaultTableModel model = new DefaultTableModel(null, formulator.getHeaders());
        formulator.addRows(model);
        raceTable.setModel(model);
        // Remove the first column (id) from the TableColumnModel
        // If you need access to the data then you use table.getModel().getValueAt(...)
        TableColumnModel tcm = raceTable.getColumnModel();
        tcm.removeColumn(tcm.getColumn(0));
        //
        raceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Object modelValueAt = model.getValueAt(raceTable.getSelectedRow(), 0);
                int id = Integer.valueOf(modelValueAt.toString());
                System.out.println(id);
//                ParticipantManager manager = new ParticipantManager();
//                Object o = model.getValueAt(table1.getSelectedRow(), 0);
//                List<Participant> list = manager.findByRaceId(Integer.valueOf(o.toString()));
//                System.out.println(list.size());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RaceForm");
        frame.setContentPane(new RaceForm().racePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
