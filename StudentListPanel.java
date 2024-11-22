package Views;
import Members.Group;
import Members.Student;
import Service.GroupService;
import Service.StudentServince;
import Uitil.Constant;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class StudentListPanel extends JPanel {
    String[] headers = {"学号", "姓名", "小组"};
    String[][] data = null;
    JTable studentTable;
    JTextField txtId = new JTextField();
    JTextField txtName = new JTextField();
    JComboBox<String> cmbGroup = new JComboBox<>();
    JButton btnEdit = new JButton("修改");
    JButton btnDelete = new JButton("删除");

    public StudentListPanel() {
        this.setBorder(new TitledBorder(new EtchedBorder(), "学生列表"));
        this.setLayout(new BorderLayout());
        // TODO 列举小组和学生构建table和combox
        try {
            new StudentServince().initStudent();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        data = new String[Constant.students.size()][3];
        for(int i = 0;i < Constant.students.size();i++){
            data[i][0] = String.valueOf(Constant.students.get(i).getId());
            data[i][1] = Constant.students.get(i).getName();
            data[i][2] = new GroupService().getGroupName(Constant.students.get(i).getGroupId());
        }

        List<Group> groups = new GroupService().readAll();
        for (Group g:groups) {
            cmbGroup.addItem(g.getGroupName());
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, headers);
        studentTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.add(txtId);
        txtId.setPreferredSize(new Dimension(100, 30));
        btnPanel.add(txtName);
        txtName.setPreferredSize(new Dimension(200, 30));
        btnPanel.add(cmbGroup);
        cmbGroup.setPreferredSize(new Dimension(100, 30));
        cmbGroup.addItem("请选择小组");

        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        this.add(btnPanel, BorderLayout.SOUTH);

        studentTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow >= 0) {
                // TODO 准备修改学生
                txtName.setText(data[selectedRow][1]);
                txtId.setText(data[selectedRow][0]);
            }
        });

        btnEdit.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "请先选择学生", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (txtId.getText() == null || txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写学号", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (txtName.getText() == null || txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写姓名", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // TODO 修改学生
            new StudentServince().changeStudent(data[selectedRow][1],txtName.getText(),txtId.getText(), String.valueOf(cmbGroup.getSelectedIndex() + 1));
            DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
            model.setValueAt(txtId.getText(),selectedRow,0);
            model.setValueAt(txtName.getText(),selectedRow,1);
            JOptionPane.showMessageDialog(this, "修改成功", "", JOptionPane.INFORMATION_MESSAGE);

        });
        btnDelete.addActionListener(e -> {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "请先选择学生", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "删除学生会删除他的考勤、成绩等，确认删除？", "", JOptionPane.YES_NO_OPTION) != 0) {
                return;
            }
            // TODO 删除学生
            new StudentServince().deleteStudent(data[selectedRow][1]);
            DefaultTableModel model = (DefaultTableModel)studentTable.getModel();
            model.removeRow(selectedRow);

            try {
                List<Student> newStudent = new StudentServince().readAll();
                String[][] newData = new String[newStudent.size()][3];
                for(int i = 0;i < newStudent.size();i++){
                    data[i][0] = String.valueOf(newStudent.get(i).getId());
                    data[i][1] = newStudent.get(i).getName();
                    data[i][2] = new GroupService().getGroupName(newStudent.get(i).getGroupId());
                }
                try {
                    new StudentServince().initStudent();
                } catch (Exception E) {
                    throw new RuntimeException(E);
                }
                DefaultTableModel Model = (DefaultTableModel)studentTable.getModel();
                Model.setDataVector(newData,headers);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }


            JOptionPane.showMessageDialog(this, "删除学生成功", "", JOptionPane.INFORMATION_MESSAGE);

        });
    }
}
