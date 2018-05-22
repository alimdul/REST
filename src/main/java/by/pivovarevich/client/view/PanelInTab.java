package by.pivovarevich.client.view;

import by.pivovarevich.client.controller.Handler;
import by.pivovarevich.client.view.plant.DialogChangePlant;
import by.pivovarevich.client.view.plant.DialogCreatePlant;
import by.pivovarevich.client.view.plant.DialogDeletePlant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PanelInTab {

    private ModelTable model;
    private JTable table;
    private JScrollPane jsp;
    private JLabel labelMonths;
    private int index;
    private Handler handler;
    private MainFrame frame;
    private Map mapTableModel = new HashMap();

    public PanelInTab(Handler handler, MainFrame frame){
        this.handler = handler;
        this.frame = frame;
    }

    public JPanel getPanel(int i) {
        index = i;
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        model = new ModelTable();
        table = new JTable(model);
        jsp = new JScrollPane(table);

        mapTableModel.put(index-1, model);

        JPanel panel1 = new JPanel();

        JLabel labelTime = new JLabel("Время цветения(месяц): ");
        labelMonths = new JLabel(handler.getListFamilyPlants().get(index-1).getFloweringTime().getFirstMonthFlowering()+" - "+
                handler.getListFamilyPlants().get(index-1).getFloweringTime().getLastMonthFlowering());
        JToolBar toolBar = new JToolBar();
        JButton buttonAdd = new JButton("Добавить цветок");
        JButton buttonDelete = new JButton("Удалить цветок");
        JButton buttonChange = new JButton("Изменить описание цветка");
        JButton buttonShow = new JButton("Посмотреть таблицу");

        toolBar.add(buttonAdd);
        toolBar.add(buttonDelete);
        toolBar.add(buttonChange);
        toolBar.add(buttonShow);
        toolBar.add(labelTime);
        toolBar.add(labelMonths);

        panel1.add(toolBar);

        panel.add(panel1, BorderLayout.NORTH);

        buttonAdd.addActionListener(new addActionListener());
        buttonDelete.addActionListener(new deleteActionListener());
        buttonChange.addActionListener(new changeActionListener());
        buttonShow.addActionListener(new showActionListener());

        jsp.setPreferredSize(new Dimension(700, 500));
        panel.add(jsp, BorderLayout.CENTER);

        return panel;
    }

    public class showActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ModelTable modelTable = (ModelTable) mapTableModel.get(frame.getSelectedIndexTab());
            modelTable.addNotation(handler.getListPlants(String.valueOf(frame.getSelectedIndexTab())));
        }
    }

    public class addActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogCreatePlant dialogCreatePlant = new DialogCreatePlant(handler);
            dialogCreatePlant.create(frame.getSelectedIndexTab());
        }
    }

    public class deleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogDeletePlant dialogDeletePlant = new DialogDeletePlant(handler);
            dialogDeletePlant.delete(frame.getSelectedIndexTab());
        }
    }

    public class changeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogChangePlant dialogChangePlant = new DialogChangePlant(handler);
            dialogChangePlant.change(frame.getSelectedIndexTab());
        }
    }
}
