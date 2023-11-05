package xin.altitude.cms.gen.code.entity;

import xin.altitude.cms.gen.code.enums.FileEnum;

/**
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public class CodeFile {
    private String fileName;
    private String fileContent;

    private String fileEnum;

    public CodeFile() {
    }

    public CodeFile(String fileName, String fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
    }

    public CodeFile(String fileName, String fileContent, String fileEnum) {
        this.fileName = fileName;
        this.fileContent = fileContent;
        this.fileEnum = fileEnum;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileEnum() {
        return fileEnum;
    }

    public void setFileEnum(String fileEnum) {
        this.fileEnum = fileEnum;
    }
}
