package by.pivovarevich.client.view.plant;

import by.pivovarevich.client.controller.Handler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogDeletePlant {
    private JDialog dialog = new JDialog();
    private Handler handler;

    public DialogDeletePlant(Handler handler) {
        this.handler = handler;
    }

    public void delete(int index) {
        dialog.setSize(500, 500);
        dialog.setLayout(new GridBagLayout());
        dialog.setLocationRelativeTo(null);

        JLabel numberLabel = new JLabel("Номер записи: ");
        JTextField numberTextField = new JTextField(10);

        JButton deleteButton = new JButton("Удалить");

        JPanel panelDeleteButton = new JPanel();
        panelDeleteButton.setLayout(new GridBagLayout());

        panelDeleteButton.add(numberLabel, new GridBagConstraints(0, 0, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));


        panelDeleteButton.add(numberTextField, new GridBagConstraints(0, 1, 2, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));


        dialog.add(panelDeleteButton, new GridBagConstraints(0, 0, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.add(deleteButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));

        dialog.pack();
        dialog.setVisible(true);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if ((numberTextField.getText()).trim().isEmpty()) {
                    JOptionPane.showMessageDialog(new JFrame(), "Поле не заполнено");
                    return;
                } else {
                    handler.deletePlant(String.valueOf(index), String.valueOf(Integer.parseInt(numberTextField.getText())-1));
                    dialog.dispose();
                }
            }
        });
    }
}
