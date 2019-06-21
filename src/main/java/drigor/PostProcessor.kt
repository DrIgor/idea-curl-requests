package drigor

import com.intellij.codeInsight.editorActions.CopyPastePreProcessor
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.RawText
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

//import com.intellij.ws.http.request.HttpRequestFileType

class PostProcessor : CopyPastePreProcessor {
    override fun preprocessOnPaste(project: Project, file: PsiFile, editor: Editor, text: String, rawText: RawText?): String {
        return if (file.fileType.name == "HTTP Request") {
            "# $text\n${toIdeaRequest(parse(text)!!)}"
        } else {
            text
        }
    }

    override fun preprocessOnCopy(file: PsiFile, startOffsets: IntArray, endOffsets: IntArray, text: String): String? = null
}
