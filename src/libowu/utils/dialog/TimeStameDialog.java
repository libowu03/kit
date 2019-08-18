package libowu.utils.dialog;

import com.intellij.openapi.ui.Messages;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import libowu.utils.utils.TimeUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimeStameDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField7;
    private JTextField textField6;
    private int whoFouse;

    public TimeStameDialog() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonOK);

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        textField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                whoFouse = 0;
            }
        });
        textField2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                whoFouse = 1;
            }
        });
        textField3.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                whoFouse = 2;
            }
        });
        textField4.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                whoFouse = 3;
            }
        });
        textField5.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                whoFouse = 4;
            }
        });
        textField7.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                whoFouse = 6;
            }
        });
        textField6.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                whoFouse = 5;
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (whoFouse == 0) {
                    if (textField1.getText() == null || textField1.getText().isEmpty()) {
                        Messages.showInfoMessage("请输入要转换的值", "温馨提示");
                        return;
                    }
                    //毫秒转其他
                    textField2.setText(TimeUtils.haomiaoTomiao(textField1.getText()));
                    textField3.setText(TimeUtils.haomiaoTomin(textField1.getText()));
                    textField4.setText(TimeUtils.haomiaoToHouse(textField1.getText()));
                    textField5.setText(TimeUtils.haomiaoToDay(textField1.getText()));
                    textField6.setText(TimeUtils.haomiaoToMonth(textField1.getText()));
                    textField7.setText(TimeUtils.haomiaoToYear(textField1.getText()));
                } else if (whoFouse == 1) {
                    if (textField2.getText() == null || textField2.getText().isEmpty()) {
                        Messages.showInfoMessage("请输入要转换的值", "温馨提示");
                        return;
                    }
                    //秒转其他
                    textField1.setText(TimeUtils.secondToOther(textField2.getText(), TimeUtils.MILLSECOND));
                    textField3.setText(TimeUtils.secondToOther(textField2.getText(), TimeUtils.MINUES));
                    textField4.setText(TimeUtils.secondToOther(textField2.getText(), TimeUtils.HOUSE));
                    textField5.setText(TimeUtils.secondToOther(textField2.getText(), TimeUtils.DAYS));
                    textField6.setText(TimeUtils.secondToOther(textField2.getText(), TimeUtils.MONTH));
                    textField7.setText(TimeUtils.secondToOther(textField2.getText(), TimeUtils.YEAR));
                } else if (whoFouse == 2) {
                    if (textField3.getText() == null || textField3.getText().isEmpty()) {
                        Messages.showInfoMessage("请输入要转换的值", "温馨提示");
                        return;
                    }
                    //分钟转其他
                    textField1.setText(TimeUtils.minToOther(textField3.getText(), TimeUtils.MILLSECOND));
                    textField2.setText(TimeUtils.minToOther(textField3.getText(), TimeUtils.SECOND));
                    textField4.setText(TimeUtils.minToOther(textField3.getText(), TimeUtils.HOUSE));
                    textField5.setText(TimeUtils.minToOther(textField3.getText(), TimeUtils.DAYS));
                    textField6.setText(TimeUtils.minToOther(textField3.getText(), TimeUtils.MONTH));
                    textField7.setText(TimeUtils.minToOther(textField3.getText(), TimeUtils.YEAR));
                } else if (whoFouse == 3) {
                    if (textField4.getText() == null || textField4.getText().isEmpty()) {
                        Messages.showInfoMessage("请输入要转换的值", "温馨提示");
                        return;
                    }
                    //小时转其他
                    textField1.setText(TimeUtils.houseToOther(textField4.getText(), TimeUtils.MILLSECOND));
                    textField2.setText(TimeUtils.houseToOther(textField4.getText(), TimeUtils.SECOND));
                    textField3.setText(TimeUtils.houseToOther(textField4.getText(), TimeUtils.MINUES));
                    textField5.setText(TimeUtils.houseToOther(textField4.getText(), TimeUtils.DAYS));
                    textField6.setText(TimeUtils.houseToOther(textField4.getText(), TimeUtils.MONTH));
                    textField7.setText(TimeUtils.houseToOther(textField4.getText(), TimeUtils.YEAR));
                } else if (whoFouse == 4) {
                    if (textField5.getText() == null || textField5.getText().isEmpty()) {
                        Messages.showInfoMessage("请输入要转换的值", "温馨提示");
                        return;
                    }
                    //日转其他
                    textField1.setText(TimeUtils.dayToOther(textField5.getText(), TimeUtils.MILLSECOND));
                    textField2.setText(TimeUtils.dayToOther(textField5.getText(), TimeUtils.SECOND));
                    textField3.setText(TimeUtils.dayToOther(textField5.getText(), TimeUtils.MINUES));
                    textField4.setText(TimeUtils.dayToOther(textField5.getText(), TimeUtils.HOUSE));
                    textField6.setText(TimeUtils.dayToOther(textField5.getText(), TimeUtils.MONTH));
                    textField7.setText(TimeUtils.dayToOther(textField5.getText(), TimeUtils.YEAR));
                } else if (whoFouse == 5) {
                    if (textField6.getText() == null || textField6.getText().isEmpty()) {
                        Messages.showInfoMessage("请输入要转换的值", "温馨提示");
                        return;
                    }
                    //月转其他
                    textField1.setText(TimeUtils.monthToOther(textField6.getText(), TimeUtils.MILLSECOND));
                    textField2.setText(TimeUtils.monthToOther(textField6.getText(), TimeUtils.SECOND));
                    textField3.setText(TimeUtils.monthToOther(textField6.getText(), TimeUtils.MINUES));
                    textField4.setText(TimeUtils.monthToOther(textField6.getText(), TimeUtils.HOUSE));
                    textField5.setText(TimeUtils.monthToOther(textField6.getText(), TimeUtils.DAYS));
                    textField7.setText(TimeUtils.monthToOther(textField6.getText(), TimeUtils.YEAR));
                } else if (whoFouse == 6) {
                    if (textField7.getText() == null || textField7.getText().isEmpty()) {
                        Messages.showInfoMessage("请输入要转换的值", "温馨提示");
                        return;
                    }
                    //年转其他
                    textField1.setText(TimeUtils.yearToOther(textField7.getText(), TimeUtils.MILLSECOND));
                    textField2.setText(TimeUtils.yearToOther(textField7.getText(), TimeUtils.SECOND));
                    textField3.setText(TimeUtils.yearToOther(textField7.getText(), TimeUtils.MINUES));
                    textField4.setText(TimeUtils.yearToOther(textField7.getText(), TimeUtils.HOUSE));
                    textField5.setText(TimeUtils.yearToOther(textField7.getText(), TimeUtils.DAYS));
                    textField6.setText(TimeUtils.yearToOther(textField7.getText(), TimeUtils.MONTH));
                }
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        TimeStameDialog dialog = new TimeStameDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("转换");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("取消");
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(7, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(500, 400), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("毫秒");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        panel3.add(textField1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("秒");
        panel3.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        panel3.add(textField2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("分");
        panel3.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        panel3.add(textField3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("时");
        panel3.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField4 = new JTextField();
        panel3.add(textField4, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("年");
        panel3.add(label5, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField7 = new JTextField();
        panel3.add(textField7, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("日");
        panel3.add(label6, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField5 = new JTextField();
        panel3.add(textField5, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("月");
        panel3.add(label7, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField6 = new JTextField();
        panel3.add(textField6, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
