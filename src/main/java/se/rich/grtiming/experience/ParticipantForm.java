package se.rich.grtiming.experience;

import se.rich.grtiming.experience.formulator.ParticapantFormulator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ParticipantForm {
    private JPanel bottomPanel;
    private JPanel mainPanel;
    private JTable particapantTable;
    private JScrollPane scrollPane;

    ParticapantFormulator formulator = new ParticapantFormulator();

    public ParticapantFormulator getFormulator() {
        return formulator;
    }

    public void setFormulator(ParticapantFormulator formulator) {
        this.formulator = formulator;
    }

    public JTable getParticapantTable() {
        return particapantTable;
    }

    public void setParticapantTable(JTable particapantTable) {
        this.particapantTable = particapantTable;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public ParticipantForm(int id){
        DefaultTableModel model= new DefaultTableModel(null, formulator.getHeaders());
        formulator.addRows(model, id);
        particapantTable.setModel(model);
    }

    public ParticipantForm() {
        DefaultTableModel model = new DefaultTableModel(null, formulator.getHeaders());
        formulator.addRows(model);
        particapantTable.setModel(model);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ParticipantForm");
        frame.setContentPane(new ParticipantForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
