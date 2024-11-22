package Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame{
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame() {
        JFrame jFrame = new JFrame();

        // 设置窗口标题
        jFrame.setTitle("用户登录");

        // 设置窗口大小
        jFrame.setSize(400, 200);

        jFrame.setVisible(true);

        // 设置窗口居中显示
        jFrame.setLocationRelativeTo(null);

        // 设置关闭窗口时退出程序
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建面板
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));//将面板划分成三行两列，
        // 水平方向上相邻网格单元格之间的间距为hgap, 垂直方向上相邻网格单元格之间的间距为vgap
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));//上下左右留白

        // 添加用户名标签和输入框
        panel.add(new JLabel("用户名:"));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 30));
        panel.add(usernameField);

        // 添加密码标签和输入框
        panel.add(new JLabel("密码:"));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));
        panel.add(passwordField);

        // 添加空白标签和登录按钮
        panel.add(new JLabel(""));
        loginButton = new JButton("登录");
        panel.add(loginButton);

        // 添加登录按钮的点击事件
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // 这里添加登录验证逻辑
                if (username.equals("admin") && password.equals("123456")) {
                    JOptionPane.showMessageDialog(jFrame,
                            "登录成功！", "提示",
                            JOptionPane.INFORMATION_MESSAGE);
                    jFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(jFrame,
                            "用户名或密码错误！", "错误",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // 将面板添加到窗口
        jFrame.add(panel);
        jFrame.pack();
    }
}
