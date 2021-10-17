package se.rich.grtiming.experience.formulator;

import se.rich.grtiming.system.entity.FinishList;
import se.rich.grtiming.system.manager.FinishListManager;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class FinishListFormulator {
    private FinishListManager manager = new FinishListManager();
    private List headers = new ArrayList();

    public Object[] getHeaders() {
        headers.add("id");
        headers.add("Finish Time");
        headers.add("Finish String");
        headers.add("Start Number");
        headers.add("Position");
        return headers.toArray();
    }

    public void setHeaders(List headers) {
        this.headers = headers;
    }

    public DefaultTableModel addRows(DefaultTableModel model) {
        List<FinishList> raceList = manager.read();
        for (FinishList r: raceList){
            List list = new ArrayList();
            list.add(r.getId());
            list.add(r.getFinishTime());
            list.add(r.getFinishString());
            list.add(r.getStartNumber());
            list.add(r.getPosition());

            model.addRow(list.toArray());
        }
        return model;
    }

    public DefaultTableModel addRows(DefaultTableModel model, int id) {
        List<FinishList> finishLists = manager.findByRaceId(FinishList.FIND_FINISH_LIST_BY_RACE_ID, id);
        System.out.println("id is " + id + " and Size of FinishList is " + finishLists.size());
        for (FinishList finishList: finishLists) {
            List list = new ArrayList();
            list.add(finishList.getId());
            list.add(finishList.getFinishTime());
            list.add(finishList.getFinishString());
            list.add(finishList.getStartNumber());
            list.add(finishList.getPosition());
            model.addRow(list.toArray());
        }
        return model;
    }
}
