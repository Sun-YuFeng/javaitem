package Views;

import Members.Group;
import Service.GroupService;
import Uitil.Constant;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class GroupListPanel extends JPanel {
    String[] headers = {"序号", "小组名称", "分数"};
    String[][] data = null;
    JTable classTable;
    JTextField txtName = new JTextField();
    JTextField txtScore = new JTextField();
    JButton btnEdit = new JButton("修改");
    JButton btnDelete = new JButton("删除");

    public GroupListPanel() {
        this.setBorder(new TitledBorder(new EtchedBorder(), "小组列表"));
        this.setLayout(new BorderLayout());
        // TODO 列举小组
        List<Group> groups = new GroupService().readAll();

        this.data = new String[groups.size()][3];
        for(int i = 0;i < groups.size();i++){
            data[i][0] = String.valueOf(i+1);
            data[i][1] = groups.get(i).getGroupName();
            data[i][2] = String.valueOf(groups.get(i).getScore());
        }
        DefaultTableModel tableModel = new DefaultTableModel(data, headers);
        classTable = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        classTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(classTable);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        btnPanel.add(txtName);
        txtName.setPreferredSize(new Dimension(200, 30));
        btnPanel.add(txtScore);
        txtScore.setPreferredSize(new Dimension(100, 30));
        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        this.add(btnPanel, BorderLayout.SOUTH);

        classTable.getSelectionModel().addListSelectionListener(e -> {
            // TODO 获取选中小组
            int selectRow = classTable.getSelectedRow();
            if(selectRow >= 0){
                txtName.setText(data[selectRow][1]);
                txtScore.setText(String.valueOf(data[selectRow][2]));
            }
        });

        btnEdit.addActionListener(e -> {
            // TODO 修改小组
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "请先选择小组", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (txtName.getText() == null || txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "请填写小组名称", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            new GroupService().changeGroup(data[selectedRow][1],txtName.getText(),Integer.parseInt(txtScore.getText()));
            DefaultTableModel model = (DefaultTableModel)classTable.getModel();
            model.setValueAt(txtName.getText(), selectedRow, 1);
            model.setValueAt(txtScore.getText(),selectedRow,2);
            JOptionPane.showMessageDialog(this, "修改成功", "", JOptionPane.INFORMATION_MESSAGE);

        });
        btnDelete.addActionListener(e -> {
            // TODO 删除小组
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "请先选择小组", "", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            new GroupService().deleteGroup(data[selectedRow][1]);
            DefaultTableModel model = (DefaultTableModel) classTable.getModel();
            model.removeRow(selectedRow);

            List<Group> updateData = new GroupService().readAll();
            String[][] newData = new String[updateData.size()][3];
            for(int i = 0;i < updateData.size();i++){
                newData[i][0] = String.valueOf(i+1);
                newData[i][1] = updateData.get(i).getGroupName();
                newData[i][2] = String.valueOf(updateData.get(i).getScore());
            }
            model.setDataVector(newData,headers);
            txtName.setText("");
            txtScore.setText("");
            JOptionPane.showMessageDialog(this, "删除小组成功", "", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
