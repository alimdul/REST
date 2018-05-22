package by.pivovarevich.client.view.family_plants;

import by.pivovarevich.client.controller.Handler;
import by.pivovarevich.model.FamilyPlants;
import by.pivovarevich.model.FloweringTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DialogCreateFamilyPlants {

    private JDialog dialog = new JDialog();
    private Handler handler;

    public DialogCreateFamilyPlants(Handler handler) {
        this.handler = handler;
    }

    public void create(){

        dialog.setSize(400,500);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);

        String[] numberMonth = {
                "январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"
        };
        JComboBox comboBoxFirstMonth = new JComboBox(numberMonth);
        JComboBox comboBoxLastMonth = new JComboBox(numberMonth);

        JLabel nameFamilyPlants = new JLabel("Название семейства цветов: ");
        JTextField nameFamilyPlantsText = new JTextField(10);

        dialog.add(nameFamilyPlants, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(nameFamilyPlantsText, new GridBagConstraints(1, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel firstMonth = new JLabel("Первый месяц цветения: ");
       // JTextField firstMonthText = new JTextField(10);

        dialog.add(firstMonth, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(comboBoxFirstMonth, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JLabel lastMonth = new JLabel("Последний месяц цветения: ");
        //JTextField lastMonthText = new JTextField(10);

        dialog.add(lastMonth, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(comboBoxLastMonth, new GridBagConstraints(1, 2, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        JButton addNotation = new JButton("Добавить запись");

        dialog.add(addNotation, new GridBagConstraints(0, 6, 2, 1, 2, 2,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.pack();
        dialog.setVisible(true);

        addNotation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((nameFamilyPlantsText.getText()).trim().isEmpty()){
                    JOptionPane.showMessageDialog(new JFrame(), "Одно или несколько полей не заполнены");
                    return;
                } else {
                        FamilyPlants familyPlants = new FamilyPlants(1, nameFamilyPlantsText.getText(),
                                new FloweringTime(comboBoxFirstMonth.getSelectedItem().toString(),
                                        comboBoxLastMonth.getSelectedItem().toString()), new ArrayList<>());

                        handler.addFamilyPlants(familyPlants);
                        dialog.dispose();
                }
            }
        });
    }
}
