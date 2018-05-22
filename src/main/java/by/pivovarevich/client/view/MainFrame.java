package by.pivovarevich.client.view;

import by.pivovarevich.client.controller.Handler;
import by.pivovarevich.client.view.family_plants.DialogChangeFamilyPlants;
import by.pivovarevich.client.view.family_plants.DialogCreateFamilyPlants;
import by.pivovarevich.model.FamilyPlants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame {
    private String title;
    private Dimension d;
    private Handler handler;
    private JFrame frame = new JFrame();

    private JToolBar tb = new JToolBar();
    private JButton addButton = new JButton("Добавить семейство");
    private JButton showButton = new JButton("Обновить семейство");
    private JButton changeButton = new JButton("Изменить описание семейства");
    private JButton deleteButton = new JButton("Удалить семейство");
    private JButton offButton = new JButton("Закрыть приложение");

    private JTabbedPane jtp = new JTabbedPane();
    private PanelInTab panelInTab;

    public MainFrame(String title, Dimension d, Handler handler) {
        this.title = title;
        this.d = d;
        this.handler = handler;
    }

    public void init() {
        frame.setTitle(title);
        frame.setSize(d);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        tb.add(addButton);
        tb.add(deleteButton);
        tb.add(changeButton);
        tb.add(showButton);
        tb.add(offButton);

        frame.getContentPane().add(tb, BorderLayout.NORTH);

        addButton.addActionListener(new createActionListener());
        changeButton.addActionListener(new changeActionListener());
        showButton.addActionListener(new showActionListener());
        deleteButton.addActionListener(new deleteActionListener());
        offButton.addActionListener(new offActionListener());

        frame.getContentPane().add(jtp);
        panelInTab = new PanelInTab(handler, MainFrame.this);

        frame.setVisible(true);
    }

    public class showActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            jtp.removeAll();

            List<FamilyPlants> list = handler.getListFamilyPlants();
            for(int i = 0; i < list.size(); i++){
                FamilyPlants familyPlants = list.get(i);
                jtp.addTab(familyPlants.getName(), panelInTab.getPanel(familyPlants.getId()));
            }
        }
    }

    public class createActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogCreateFamilyPlants dialog = new DialogCreateFamilyPlants(handler);
            dialog.create();
        }
    }

    public class deleteActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            handler.deleteFamilyPlants(String.valueOf(getSelectedIndexTab()));
        }
    }

    public class changeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DialogChangeFamilyPlants dialog = new DialogChangeFamilyPlants(handler);
            dialog.change(getSelectedIndexTab());
        }
    }

    public class offActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public int getSelectedIndexTab(){
        return jtp.getSelectedIndex();
    }
}
