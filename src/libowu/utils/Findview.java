package libowu.utils;

import com.google.gson.Gson;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.IdeActions;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
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
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlDocument;
import com.intellij.psi.xml.XmlTag;
import libowu.utils.dialog.LinghitUtil;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Findview extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        //LinghitUtil dialog = new LinghitUtil();
        //dialog.setSize(500,300);
        //Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
        //int height = screenSize.height;
        //int width = screenSize.width;
        //dialog.setLocation(width/2-250,height/2 - 150);//居中
        //dialog.pack();
        //dialog.setVisible(true);
        /*Editor data = e.getData(PlatformDataKeys.EDITOR);
        SelectionModel selectionModel = data.getSelectionModel();*/
        PsiFileSystemItem[] timeUtils = FilenameIndex.getFilesByName(e.getProject(), "activity_edit_collect.xml", GlobalSearchScope.allScope(e.getProject()), false);
        if (timeUtils != null && timeUtils.length != 0){
            timeUtils[0].getVirtualFile();
            PsiFile manifestFile = PsiManager.getInstance(e.getProject()).findFile( timeUtils[0].getVirtualFile());
            XmlDocument xml = (XmlDocument) manifestFile.getNode().getLighterAST().
            //XmlAttribute[] attributes = xml.getRootTag().getAttributes();

        }
/*
        try{
            //得到DOM解析器的工厂实例
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //从DOM工厂中获得DOM解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            //把要解析的xml文档读入DOM解析器
            Document doc = dbBuilder.parse(timeUtils[0].getVirtualFile().getPath());
            System.out.println(timeUtils[0].getVirtualFile().getPath());
            System.out.println("处理该文档的DomImplementation对象  = "+ doc.getImplementation());
            //得到文档名称为Student的元素的节点列表
            NodeList nList = doc.get
            //遍历该集合，显示结合中的元素及其子元素的名字
            for(int i = 0; i< nList.getLength() ; i ++){
                Element node = (Element)nList.item(i);
                System.out.println("Name: "+ node.getElementsByTagName("id").toString());
            }

        }catch (Exception j) {
            // TODO: handle exception
            j.printStackTrace();
        }
        System.out.println(timeUtils.length);*/
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
