
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MembershipManagementGUI extends JFrame {
    private JTextField idField, nameField;
    private JComboBox<String> statusField;
    private DefaultTableModel tableModel;

    public MembershipManagementGUI() {
        // Fonts and Colors
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color backgroundColor = new Color(240, 248, 255);
        Color buttonColor = new Color(70, 130, 180);
        Color buttonTextColor = Color.WHITE;

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Add Member"));
        inputPanel.setBackground(backgroundColor);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(labelFont);
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(labelFont);

        idField = new JTextField();
        nameField = new JTextField();
        statusField = new JComboBox<>(new String[]{"Active", "Inactive"});

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(statusLabel);
        inputPanel.add(statusField);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(backgroundColor);

        JButton addButton = new JButton("Add Member");
        JButton deleteButton = new JButton("Delete Member");
        JButton showButton = new JButton("Show Members");

        styleButton(addButton, buttonColor, buttonTextColor);
        styleButton(deleteButton, buttonColor, buttonTextColor);
        styleButton(showButton, buttonColor, buttonTextColor);

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(showButton);

        // Members Table
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Status"}, 0);
        JTable memberTable = new JTable(tableModel);
        memberTable.setRowHeight(25);
        memberTable.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane tableScrollPane = new JScrollPane(memberTable);

        // Main Layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(backgroundColor);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(tableScrollPane, BorderLayout.SOUTH);

        add(mainPanel);

        // Button Actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText().trim();
                String name = nameField.getText().trim();
                String status = statusField.getSelectedItem().toString();

                if (id.isEmpty() || name.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ID and Name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    tableModel.addRow(new Object[]{id, name, status});
                    idField.setText("");
                    nameField.setText("");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = memberTable.getSelectedRow();
                if (selectedRow >= 0) {
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder members = new StringBuilder("Members List:\n");
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    members.append("ID: ").append(tableModel.getValueAt(i, 0))
                            .append(", Name: ").append(tableModel.getValueAt(i, 1))
                            .append(", Status: ").append(tableModel.getValueAt(i, 2))
                            .append("\n");
                }
                JOptionPane.showMessageDialog(null, members.toString(), "Members", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void styleButton(JButton button, Color bgColor, Color textColor) {
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
    }
}
