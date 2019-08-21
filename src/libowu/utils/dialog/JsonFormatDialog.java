package libowu.utils.dialog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intellij.openapi.ui.Messages;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;

public class JsonFormatDialog extends JDialog {
    private JPanel contentPane;
    private JButton format;
    private JButton buttonCancel;
    private JButton compress;
    private JTextArea textArea1;
    private JButton copy;
    private JTextArea textArea2;

    public JsonFormatDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(format);

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

        format.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = "{\n" +
                        "    \"isShow\":true,\n" +
                        "    \"maxCount\":2,\n" +
                        "    \"data\":[\n" +
                        "        {\n" +
                        "            \"imgUrl\":\"https://ljms.ggwan.com/image/mmc-ljms/e4df6424d16579-601x699.png\",\n" +
                        "            \"actionUrl\":\"luopan\",\n" +
                        "            \"id\":\"\",\n" +
                        "            \"type\":\"2\",\n" +
                        "\t\t\t\"data\":\"isUnlockCompass\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"imgUrl\":\"https://ljms.ggwan.com/image/mmc-ljms/538ef369b20fb1-594x764.png\",\n" +
                        "            \"actionUrl\":\"caiweiluopan\",\n" +
                        "            \"id\":\"\",\n" +
                        "            \"type\":\"2\",\n" +
                        "\t\t\t\"data\":\"isUnlockJxlp\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"imgUrl\":\"https://ljms.ggwan.com/image/mmc-ljms/5d6a2d875a2558-560x663.png\",\n" +
                        "            \"actionUrl\":\"vip\",\n" +
                        "            \"id\":\"\",\n" +
                        "            \"type\":\"2\",\n" +
                        "\t\t\t\"data\":\"\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";
                try {
                    JsonParser jsonParser = new JsonParser();
                    //JsonObject jsonObject = jsonParser.parse(textArea1.getText()).getAsJsonObject();
                    JsonObject jsonObject = jsonParser.parse(string).getAsJsonObject();
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    String result = gson.toJson(jsonObject);
                    textArea2.setText(result);
                } catch (Exception j) {
                    textArea2.setText("格式有误");
                }
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        copy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable tText = new StringSelection(textArea2.getText());
                clip.setContents(tText, null);
                //Messages.showInfoMessage("复制成功", "温馨提示");
            }
        });
        compress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textArea1.getText() != null && !textArea1.getText().isEmpty()) {
                    String result = textArea1.getText().replaceAll("\n", "").replace(" ", "");
                    textArea2.setText(result);
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
        JsonFormatDialog dialog = new JsonFormatDialog();
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
        contentPane.setLayout(new GridLayoutManager(1, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(2, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(1200, 500), null, 0, false));
        format = new JButton();
        format.setText("格式化");
        panel2.add(format, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel2.add(scrollPane1, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(600, 400), null, 0, false));
        textArea1 = new JTextArea();
        scrollPane1.setViewportView(textArea1);
        compress = new JButton();
        compress.setText("压缩");
        panel2.add(compress, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        copy = new JButton();
        copy.setText("复制");
        panel2.add(copy, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("取消");
        panel2.add(buttonCancel, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel2.add(scrollPane2, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(600, 400), null, 0, false));
        textArea2 = new JTextArea();
        scrollPane2.setViewportView(textArea2);
        final JLabel label1 = new JLabel();
        label1.setText("输入");
        panel1.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("输出");
        panel1.add(label2, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
