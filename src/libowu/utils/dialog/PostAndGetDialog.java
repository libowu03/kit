package libowu.utils.dialog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intellij.openapi.ui.Messages;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import okhttp3.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;

public class PostAndGetDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField url;
    private JComboBox comboBox1;
    private JTabbedPane tabbedPane1;
    private JTabbedPane tabbedPane2;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JTextArea textArea5;
    private JTextArea textArea6;
    private JButton copyResult;
    private String requestType = "GET";
    private String urlPar;

    public PostAndGetDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //执行请求操作
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder build = new Request.Builder();
                if (requestType.equals("GET")) {
                    build.get().url(url.getText());
                    if (textArea1.getText() == null || textArea1.getText().isEmpty()) {
                        build.build();
                    } else {
                        //设置请求头
                        String[] headValue = textArea2.getText().split("\n");
                        if (headValue != null && headValue.length != 0) {
                            for (int i = 0; i < headValue.length; i++) {
                                try {
                                    String[] paraTemp = headValue[i].split(":");
                                    build.addHeader(paraTemp[0], paraTemp[1]);
                                } catch (Exception p) {

                                }
                            }
                        }
                    }
                } else if (requestType.equals("POST")) {
                    //设置请求参数
                    FormBody.Builder form = new FormBody.Builder();
                    String[] value = textArea1.getText().split("\n");
                    if (value != null && value.length != 0) {
                        for (int i = 0; i < value.length; i++) {
                            try {
                                String[] paraTemp = value[i].split(":");
                                form.add(paraTemp[0], paraTemp[1]);
                            } catch (Exception p) {

                            }
                        }
                    }
                    System.out.println("执行post请求");
                    build.post(form.build());

                    //设置请求头
                    String[] headValue = textArea2.getText().split("\n");
                    if (headValue != null && headValue.length != 0) {
                        for (int i = 0; i < headValue.length; i++) {
                            String[] paraTemp = headValue[i].split(":");
                            build.addHeader(paraTemp[0], paraTemp[1]);
                        }
                    }

                    //如果存在body，则设置body
                    if (textArea3.getText() != null && !textArea3.getText().isEmpty()) {
                        String jsonBody = textArea3.getText();
                        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                        RequestBody body = RequestBody.create(JSON, jsonBody);
                        build.post(body);
                    }

                }

                okHttpClient.newCall(build.build()).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        textArea4.setText("请求失败，原因：" + e.getLocalizedMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String body = response.body().string();
                        Headers headers = response.headers();
                        //String test = headers.get("namesAndValues");
                        try {
                            JsonParser jsonParser = new JsonParser();
                            JsonObject jsonObject = jsonParser.parse(body).getAsJsonObject();
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            String result = gson.toJson(jsonObject);
                            textArea4.setText(result);
                            textArea5.setText(headers.toString());
                            String[] headerArray = headers.toString().split("\n");
                            for (int i = 0; i < headerArray.length; i++) {
                                if (headerArray[i].startsWith("Set-Cookie:")) {
                                    textArea6.setText(headerArray[i].split(":")[1]);
                                    break;
                                }
                            }
                        } catch (Exception j) {
                            textArea4.setText(body);
                        }
                        response.body().close();
                    }
                });
            }
        });

        url.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    if (!url.getText().contains("?")) {
                        return;
                    }
                    textArea1.setText(url.getText().substring(url.getText().indexOf("?") + 1).replaceAll("&", "\n").replaceAll("=", ":"));
                } catch (Exception p) {

                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    if (!url.getText().contains("?")) {
                        return;
                    }
                    textArea1.setText(url.getText().substring(url.getText().indexOf("?") + 1).replaceAll("&", "\n").replaceAll("=", ":"));
                } catch (Exception p) {

                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

        textArea1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                try {
                    if (url.getText().contains("?")) {
                        urlPar = url.getText().split("\\?")[0];
                        url.setText(textArea1.getText().replaceAll("\n", "&"));
                    } else {
                        url.setText("?" + textArea1.getText().replaceAll("\n", "&"));
                    }
                } catch (Exception p) {

                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                try {
                    if (url.getText().contains("?")) {
                        urlPar = url.getText().split("\\?")[0];
                        url.setText(urlPar + "?" + textArea1.getText().replaceAll("\n", "&"));
                    } else {
                        url.setText(urlPar + "?" + textArea1.getText().replaceAll("\n", "&"));
                    }
                } catch (Exception p) {

                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });

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
        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                requestType = (String) e.getItem();
            }
        });

        textArea1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {

            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
        url.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                //url.setText("");
            }
        });
        copyResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                Transferable tText = new StringSelection(textArea4.getText());
                clip.setContents(tText, null);
                //Messages.showInfoMessage("复制成功", "提示");
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
        PostAndGetDialog dialog = new PostAndGetDialog();
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
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("开始请求");
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("取消");
        panel2.add(buttonCancel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        copyResult = new JButton();
        copyResult.setText("复制结果");
        panel2.add(copyResult, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(4, 5, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(1200, 600), null, 0, false));
        tabbedPane1 = new JTabbedPane();
        panel3.add(tabbedPane1, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        tabbedPane1.addTab("请求参数", scrollPane1);
        textArea1 = new JTextArea();
        scrollPane1.setViewportView(textArea1);
        final JScrollPane scrollPane2 = new JScrollPane();
        tabbedPane1.addTab("请求头", scrollPane2);
        textArea2 = new JTextArea();
        scrollPane2.setViewportView(textArea2);
        final JScrollPane scrollPane3 = new JScrollPane();
        tabbedPane1.addTab("body", scrollPane3);
        textArea3 = new JTextArea();
        scrollPane3.setViewportView(textArea3);
        tabbedPane2 = new JTabbedPane();
        panel3.add(tabbedPane2, new GridConstraints(3, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 400), null, 0, false));
        final JScrollPane scrollPane4 = new JScrollPane();
        tabbedPane2.addTab("body", scrollPane4);
        textArea4 = new JTextArea();
        scrollPane4.setViewportView(textArea4);
        final JScrollPane scrollPane5 = new JScrollPane();
        tabbedPane2.addTab("head", scrollPane5);
        textArea5 = new JTextArea();
        scrollPane5.setViewportView(textArea5);
        final JScrollPane scrollPane6 = new JScrollPane();
        tabbedPane2.addTab("cookies", scrollPane6);
        textArea6 = new JTextArea();
        scrollPane6.setViewportView(textArea6);
        comboBox1 = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("GET");
        defaultComboBoxModel1.addElement("POST");
        comboBox1.setModel(defaultComboBoxModel1);
        panel3.add(comboBox1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        url = new JTextField();
        url.setText("");
        panel3.add(url, new GridConstraints(0, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 4, new Insets(0, 0, 0, 0), -1, -1));
        panel3.add(panel4, new GridConstraints(2, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("请求结果");
        panel4.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("耗时：200ms");
        panel4.add(label2, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("大小：3k");
        panel4.add(label3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("状态：200 ok");
        panel4.add(label4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
