package libowu.utils.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileSystemItem;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlAttribute;
import com.intellij.psi.xml.XmlDocument;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;

import java.util.HashMap;

public class FindViewByXml extends AnAction {
    private HashMap<String,String> map;

    @Override
    public void actionPerformed(AnActionEvent e) {
        Editor data = e.getData(PlatformDataKeys.EDITOR);
        SelectionModel selectionModel = data.getSelectionModel();
        if (selectionModel == null){
            return;
        }

        // TODO: insert action logic here
        map = new HashMap<>();
        PsiFileSystemItem[] timeUtils = FilenameIndex.getFilesByName(e.getProject(), selectionModel.getSelectedText()+".xml", GlobalSearchScope.allScope(e.getProject()), false);
        if (timeUtils != null && timeUtils.length != 0){
            VirtualFile virtualFile = timeUtils[0].getVirtualFile();
            PsiFile manifestFile = PsiManager.getInstance(e.getProject()).findFile( virtualFile);
            // XmlDocument xml = (XmlDocument) manifestFile.getOriginalFile();
            XmlFile xmlFile = (XmlFile) PsiManager.getInstance(e.getProject()).findFile(virtualFile);
            XmlDocument document = xmlFile.getDocument();
            XmlTag xmlTags[] = PsiTreeUtil.getChildrenOfType(manifestFile.getFirstChild(), XmlTag.class);

            if (document != null) {
                XmlTag rootTag = document.getRootTag();
                if (rootTag != null) {
                    XmlTag[] subTags = rootTag.getSubTags();
                    for (XmlTag tag : subTags) {
                        //do something you want
                        getTag(tag);
                    }
                }
            }
        }else {
            return;
        }
        Document document = data.getDocument();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                StringBuffer stringBuffer = new StringBuffer();
                for (String key:map.keySet()){
                    stringBuffer.append("\tprivate "+map.get(key)+" "+key+";\n");
                    //System.out.println(key+","+map.get(key));
                }
                System.out.println(stringBuffer.toString());
                document.insertString(document.getText().indexOf("{")+1,"\n"+stringBuffer.toString());


            }
        };
        WriteCommandAction.runWriteCommandAction(e.getProject(),runnable);
    }

    private void getTag(XmlTag tag) {
        XmlAttribute[] attributes = tag.getAttributes();
        for (XmlAttribute attribute:attributes){
            if ("android:id".equals(attribute.getName())){
                map.put(attribute.getValue().split("/")[1],tag.getName());
                //System.out.println(attribute.getValue().split("/")[1]+","+tag.getName());
            }
        }

        XmlTag[] subTags = tag.getSubTags();
        for (XmlTag t : subTags) {
            //do something you want
            getTag(t);
        }
    }
}
