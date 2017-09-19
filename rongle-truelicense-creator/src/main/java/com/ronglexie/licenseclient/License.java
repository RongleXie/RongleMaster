package com.ronglexie.licenseclient;


import com.alibaba.druid.util.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;

/**
 * @author wxt.xqr
 * @version 2017-8-22
 */
public class License {
    public final String LIC_NAME = "rongle.lic";
    public JPanel Licence;
    private JTextField IPTextField;
    private JTextField MACTextField;
    private JTextField expireTimeTextField;
    private JTextField FileTextField;
    private JButton LicenceButton;
    final ObservingTextField observingTextField;

    public class ObservingTextField extends JTextField implements Observer {
        public void update(Observable o, Object arg) {
            Calendar calendar = (Calendar) arg;
            com.qt.datapicker.DatePicker dp = (com.qt.datapicker.DatePicker) o;
            expireTimeTextField.setText(dp.formatDate(calendar));
        }
    }

    public License() {

        observingTextField = new ObservingTextField();
        expireTimeTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                com.qt.datapicker.DatePicker dp = new com.qt.datapicker.DatePicker(observingTextField, Locale.CHINA);
                // previously selected date
                Date selectedDate = dp.parseDate(expireTimeTextField.getText());
                dp.getScreen().setTitle("有效时间");
                dp.setSelectedDate(selectedDate);
                dp.start(expireTimeTextField);
            }
        });
        FileTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                jfc.showDialog(new JLabel(), "选择");
                File file = jfc.getSelectedFile();
                if (file.isDirectory()) {
                    System.out.println("文件夹:" + file.getAbsolutePath());
                } else if (file.isFile()) {
                    System.out.println("文件:" + file.getAbsolutePath());
                }
                System.out.println(jfc.getSelectedFile().getName());
                FileTextField.setText(file.getAbsolutePath());
            }
        });
        LicenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StringUtils.isEmpty(IPTextField.getText())){
                    JOptionPane.showMessageDialog(Licence, "请输入IP地址！！！", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (StringUtils.isEmpty(MACTextField.getText())){
                    JOptionPane.showMessageDialog(Licence, "请输入MAC地址！！！", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (StringUtils.isEmpty(expireTimeTextField.getText())){
                    JOptionPane.showMessageDialog(Licence, "请选择有效时间！！！", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (StringUtils.isEmpty(FileTextField.getText())){
                    JOptionPane.showMessageDialog(Licence, "请选择保存路径！！！", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(!ipCheck(IPTextField.getText())){
                    JOptionPane.showMessageDialog(Licence, "IP地址输入不正确，请重新输入！！！", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if(!macCheck(MACTextField.getText())){
                    JOptionPane.showMessageDialog(Licence, "MAC地址输入不正确，请重新输入！！！", "警告", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    CreateLicense createLicense;
                    createLicense = new CreateLicense();
                    createLicense.setCustomParam(IPTextField.getText(), MACTextField.getText(), expireTimeTextField.getText(), FileTextField.getText() + File.separator + LIC_NAME);
                    createLicense.create();
                    JOptionPane.showMessageDialog(Licence, "保存成功！请到指定目录查看！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(Licence, "保存失败！内部错误！", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    /**
     * 判断IP地址的合法性，这里采用了正则表达式的方法来判断
     * return true，合法
     * */
    public static boolean ipCheck(String text) {
        if (text != null && !text.isEmpty()) {
            // 定义正则表达式
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."+
            "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+
            "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."+
            "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            // 判断ip地址是否与正则表达式匹配
            if (text.matches(regex)) {
                // 返回判断信息
                return true;
            } else {
                // 返回判断信息
                return false;
            }
        }
        return false;
    }
    /**
     * 判断MAC地址的合法性，这里采用了正则表达式的方法来判断
     * return true，合法
     * */
    public static boolean macCheck(String text) {
        if (text != null && !text.isEmpty()) {
            // 定义正则表达式
            String regex = "^[A-F0-9]{2}(-[A-F0-9]{2}){5}$";
            // 判断mac址是否与正则表达式匹配
            if (text.matches(regex)) {
                // 返回判断信息
                return true;
            } else {
                // 返回判断信息
                return false;
            }
        }
        return false;
    }
}
