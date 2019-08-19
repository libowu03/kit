package libowu.utils.dialog;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class LinghitUtil extends JDialog {
    private JPanel contentPane;
    private JButton changeLanguage;
    private JButton timeStamp;
    private JButton changeHex;
    private JButton colorPick;
    private JButton jsonForm;
    private JButton encodeBtn;
    private JButton postAndGet;
    private JButton fileUtil;
    private boolean isColorPicker = false;
    private Robot r;

    public LinghitUtil() {
        setContentPane(contentPane);
        setModal(true);
//        getRootPane().setDefaultButton(changeLanguage);
        try {
            r = new Robot();
        } catch (AWTException a) {
            System.out.println("错误" + ":" + a.getMessage());
        }

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
        changeLanguage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomDialog dialog = new CustomDialog();
                dialog.setSize(500, 350);
                setPosition(dialog, 500, 350);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        timeStamp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TimeStameDialog timeStameDialog = new TimeStameDialog();
                timeStameDialog.setSize(500, 500);
                setPosition(timeStameDialog, 500, 500);
                timeStameDialog.pack();
                timeStameDialog.setVisible(true);
            }
        });
        changeHex.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HexDialog dialog = new HexDialog();
                dialog.setSize(500, 400);
                setPosition(dialog, 500, 400);
                dialog.setLocationRelativeTo(null);//居中
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        colorPick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //isColorPicker = true;
               /* ColorPickDialog dialog = new ColorPickDialog();
                dialog.setLocationRelativeTo(dialog);//居中
                dialog.pack();
                dialog.setVisible(true);*/
                JColorChooser.showDialog(new JPanel(), "请选择颜色", Color.CYAN);
            }
        });

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isColorPicker = false;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (isColorPicker) {
                    Point mousepoint = MouseInfo.getPointerInfo().getLocation();
                    if (r == null) {
                        return;
                    }
                    System.out.println(r.getPixelColor(mousepoint.x, mousepoint.y));
                }

            }
        });
        jsonForm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JsonFormatDialog dialog = new JsonFormatDialog();
                dialog.setSize(1200, 600);
                setPosition(dialog, 1200, 600);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        encodeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EncodeDialog dialog = new EncodeDialog();
                dialog.setSize(1200, 700);
                setPosition(dialog, 1200, 700);
                dialog.setLocationRelativeTo(null);//居中
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        postAndGet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PostAndGetDialog dialog = new PostAndGetDialog();
                dialog.setSize(1200, 500);
                setPosition(dialog, 1200, 900);
                dialog.setLocationRelativeTo(null);//居中
                dialog.pack();
                dialog.setVisible(true);
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
        LinghitUtil dialog = new LinghitUtil();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    public void setPosition(JDialog jDialog, int w, int h) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        jDialog.setLocation(width / 2 - w / 2, height / 2 - h / 2);//居中
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
        contentPane.setToolTipText("");
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(5, 2, new Insets(10, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(500, 300), null, 0, true));
        changeLanguage = new JButton();
        changeLanguage.setText("简繁体转换");
        panel1.add(changeLanguage, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        timeStamp = new JButton();
        timeStamp.setText("时间工具");
        panel1.add(timeStamp, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        changeHex = new JButton();
        changeHex.setText("进制转换");
        panel1.add(changeHex, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        colorPick = new JButton();
        colorPick.setText("调色板");
        panel1.add(colorPick, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jsonForm = new JButton();
        jsonForm.setText("JSON格式化");
        panel1.add(jsonForm, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        encodeBtn = new JButton();
        encodeBtn.setText("编码工具");
        panel1.add(encodeBtn, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        postAndGet = new JButton();
        postAndGet.setText("在线请求");
        panel1.add(postAndGet, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fileUtil = new JButton();
        fileUtil.setText("文本工具");
        panel1.add(fileUtil, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("工具集合");
        contentPane.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
