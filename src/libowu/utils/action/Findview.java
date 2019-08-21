package libowu.utils.action;

import com.google.gson.Gson;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.VisualPosition;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.io.StreamUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.tree.TokenSet;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlDocument;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import libowu.utils.dialog.LinghitUtil;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Findview extends AnAction {


    @Override
    public void actionPerformed(AnActionEvent e) {
        shoDialog(e);
    }

    private void shoDialog(AnActionEvent e) {
        LinghitUtil dialog = new LinghitUtil();
        dialog.setSize(500,300);
        Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        dialog.setLocation(width/2-250,height/2 - 150);//居中
        dialog.pack();
        dialog.setVisible(true);
        //Editor data = e.getData(PlatformDataKeys.EDITOR);
        //SelectionModel selectionModel = data.getSelectionModel();
    }






   /* public void addInitTemplateMethod(Project project, PsiClass psiClass) {
        String method = null;
        try {
            InputStream in = getClass().getClassLoader().getResource("/templates/initTemplateInAppMethod.txt").openStream();
            method = StreamUtil.loadFromStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (method == null) {
            throw new RuntimeException("initTemplateInAppMethod shuold not be null");
        }
        final String finalMethod = method.replace("\r\n", "\n");
        WriteCommandAction.runWriteCommandAction(project, () -> {
            PsiMethod psiMethod = PsiElementFactory.SERVICE.getInstance(project).createMethodFromText(finalMethod, psiClass);
            psiClass.add(psiMethod);
        });
    }*/
}
