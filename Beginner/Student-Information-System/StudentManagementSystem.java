import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Student class
class Student {
    String name;
    int age;
    String course;

    Student(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }
}

public class StudentManagementSystem {

    private JFrame frame;
    private JTextField nameField, ageField, courseField;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<Student> students = new ArrayList<>();

    public StudentManagementSystem() {
        frame = new JFrame("Student Information System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // ===== INPUT PANEL =====
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);

        panel.add(new JLabel("Course:"));
        courseField = new JTextField();
        panel.add(courseField);

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        panel.add(addBtn);
        panel.add(updateBtn);

        frame.add(panel, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel(new String[]{"Name", "Age", "Course"}, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(deleteBtn, BorderLayout.SOUTH);

        // ===== BUTTON ACTIONS =====

        // ADD
        addBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String course = courseField.getText();

                students.add(new Student(name, age, course));
                model.addRow(new Object[]{name, age, course});

                clearFields();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Enter valid age!");
            }
        });

        // UPDATE
        updateBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow >= 0) {
                try {
                    String name = nameField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String course = courseField.getText();

                    students.set(selectedRow, new Student(name, age, course));

                    model.setValueAt(name, selectedRow, 0);
                    model.setValueAt(age, selectedRow, 1);
                    model.setValueAt(course, selectedRow, 2);

                    clearFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Enter valid age!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Select a row to update!");
            }
        });

        // DELETE
        deleteBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();

            if (selectedRow >= 0) {
                students.remove(selectedRow);
                model.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Select a row to delete!");
            }
        });

        // CLICK TABLE ROW → FILL INPUT FIELDS
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                nameField.setText(model.getValueAt(row, 0).toString());
                ageField.setText(model.getValueAt(row, 1).toString());
                courseField.setText(model.getValueAt(row, 2).toString());
            }
        });

        frame.setVisible(true);
    }

    private void clearFields() {
        nameField.setText("");
        ageField.setText("");
        courseField.setText("");
    }

    public static void main(String[] args) {
        new StudentManagementSystem();
    }
}