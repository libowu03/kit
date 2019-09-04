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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FindViewByXml extends AnAction {
    private HashMap<String,String> map;

    @Override
    public void actionPerformed(AnActionEvent e) {
        //获取编辑器
        Editor data = e.getData(PlatformDataKeys.EDITOR);
        //获取鼠标选中区域
        SelectionModel selectionModel = data.getSelectionModel();
        if (selectionModel == null){
            return;
        }
        // TODO: insert action logic here
        //这个map用于保存要写入的声明对象的类和类名。
        map = new HashMap<>();
        //读取xml文件。selectionModel.getSelectedText()这句话为获取鼠标点击高亮区部分的文字内容。下面整句话是在工程中寻找名字为xxx.xml的文件。xxx值就是鼠标选中的高亮区的文字。
        PsiFileSystemItem[] timeUtils = FilenameIndex.getFilesByName(e.getProject(), selectionModel.getSelectedText()+".xml", GlobalSearchScope.allScope(e.getProject()), false);
        //如果没找到直接结束掉操作。
        if (timeUtils != null && timeUtils.length != 0){
            //理论上来说，xml文件大多数情况下名字是唯一的，所以这里直接取了第一个xml文件来解析
            VirtualFile virtualFile = timeUtils[0].getVirtualFile();
            //获取对应的文件
            PsiFile manifestFile = PsiManager.getInstance(e.getProject()).findFile( virtualFile);
            // XmlDocument xml = (XmlDocument) manifestFile.getOriginalFile();
            //将文件解析为xml文件
            XmlFile xmlFile = (XmlFile) PsiManager.getInstance(e.getProject()).findFile(virtualFile);
            XmlDocument document = xmlFile.getDocument();
            if (document != null) {
                //获取顶层的xml内容
                XmlTag rootTag = document.getRootTag();
                if (rootTag != null) {
                    //获取顶层后的下一层内容
                    XmlTag[] subTags = rootTag.getSubTags();
                    for (XmlTag tag : subTags) {
                        //获取个各标签的内容，里面的方法使用了递归。
                        getTag(tag);
                    }
                }
            }
        }else {
            return;
        }
        //======================下面的代码是将声明和引用写入到java文件中去==================================
        Document document = data.getDocument();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //这个stringBuffer为最终需要写入的内容
                StringBuffer stringBuffer = new StringBuffer();
                //这个map用于装载要引入的包，之所以使用hashmap，因为hashmap键不可重复，避免重复引入包名。
                HashMap<String,String> importPackage = new HashMap<>();
                for (String key:map.keySet()){
                    if (!map.get(key).contains(".")){
                        //应为没有包含“.”的一般是一些基本控件，基本控件基本都是出自android.widget里面，所以这里就固定引入android.widget.+控件类名
                        importPackage.put(map.get(key),"import android.widget."+map.get(key)+";\n");
                        stringBuffer.append("\tprivate "+map.get(key)+" "+key+";\n");
                    }else {
                        //如果包含“.”的，可能是自定义布局或谷歌后面加入的布局，这些布局一般前面都带有一串包名加上控件类名，这种我们直接引入即可。
                        importPackage.put(map.get(key),"import "+map.get(key)+";\n");
                        stringBuffer.append("\tprivate "+map.get(key).substring(map.get(key).lastIndexOf(".")+1)+" "+key+";\n");
                    }
                    //System.out.println(key+","+map.get(key));
                }

                StringBuffer packageResult = new StringBuffer();
                for (String key:importPackage.keySet()){
                    packageResult.append(importPackage.get(key));
                }
                System.out.println(stringBuffer.toString());
                //在类中写入属性。写入位置为类文件中第一次出现“{”的地点的后一行
                document.insertString(document.getText().indexOf("{")+1,"\n"+stringBuffer.toString());
                //写入导包的代码。写入位置为类文件中第一次出现“;”的位置，即package xxx;的后一行
                document.insertString(document.getText().indexOf(";")+1,"\n"+packageResult.toString());

            }
        };
        WriteCommandAction.runWriteCommandAction(e.getProject(),runnable);
    }

    private void getTag(XmlTag tag) {
        //获取标签下的所有属性
        XmlAttribute[] attributes = tag.getAttributes();
        for (XmlAttribute attribute:attributes){
            //我们只需要获取到id的内容和标签的内容即可。这里的id内容是指安卓xml里面的id值，标签内容是指id该id指代的对象的名称，比如LinearLayout等。
            if ("android:id".equals(attribute.getName())){
                //将id和该id指代的对象以键值对的形式保存到hashmap中去。
                map.put(attribute.getValue().split("/")[1],tag.getName());
                //System.out.println(attribute.getValue().split("/")[1]+","+tag.getName());
            }
        }

        //判断该标签下是否存在子标签，如果存在则进行递归调用
        XmlTag[] subTags = tag.getSubTags();
        for (XmlTag t : subTags) {
            getTag(t);
        }
    }
}
