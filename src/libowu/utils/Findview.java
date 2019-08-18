package libowu.utils;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import libowu.utils.dialog.LinghitUtil;

import java.awt.*;

public class Findview extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        LinghitUtil dialog = new LinghitUtil();
        dialog.setSize(500,300);
        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        dialog.setLocation(width/2-250,height/2 - 150);//居中
        dialog.pack();
        dialog.setVisible(true);

    }
}
