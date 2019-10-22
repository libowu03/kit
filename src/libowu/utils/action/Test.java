package libowu.utils.action;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.util.TextRange;

public class Test extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        //获取编辑器
        Editor data = e.getData(PlatformDataKeys.EDITOR);
        //获取鼠标选中区域
        SelectionModel selectionModel = data.getSelectionModel();
        String result = ZhConverterUtil.convertToTraditional(selectionModel.getSelectedText());
        int startPosition = selectionModel.getSelectionStart();
        int endPosition = selectionModel.getSelectionEnd();
        TextRange range = new TextRange(startPosition, endPosition);
        data.getDocument().insertString(selectionModel.getLeadSelectionOffset(),result);
    }
}
