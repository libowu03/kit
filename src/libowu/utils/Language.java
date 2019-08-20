package libowu.utils;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import libowu.utils.dialog.CustomDialog;

import javax.swing.*;
import java.awt.*;

public class Language extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        CustomDialog dialog = new CustomDialog();
        dialog.setSize(500, 350);
        setPosition(dialog, 500, 350);
        dialog.pack();
        dialog.setVisible(true);
    }

    public void setPosition(JDialog jDialog, int w, int h) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        jDialog.setLocation(width / 2 - w / 2, height / 2 - h / 2);//居中
    }

}
