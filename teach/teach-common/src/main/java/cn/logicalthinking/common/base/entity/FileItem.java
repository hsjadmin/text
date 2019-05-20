package cn.logicalthinking.common.base.entity;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Description 文件实体类
 * @author 黄世杰 
 * @2018-9-27
 * @version  1.0
 */
public class FileItem {
	
	//文件原名
	private String oldFileName;
	
	//文件新名
	private String newFileName;
	
	//input name
	private String inputName;
	
	//文件相当路径
	private String filePath;
	
	//文件绝对路径
	private String filePathAll;
	
	//文件大小
	private long size;
	
	//文件类型
	private String fileType;
	
	//文件后缀名
	private String fileSuffixName;
	
	//文件对象
	private MultipartFile multipartFile;

	public String getOldFileName() {
		return oldFileName;
	}

	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public String getFileSuffixName() {
		return fileSuffixName;
	}

	public void setFileSuffixName(String fileSuffixName) {
		this.fileSuffixName = fileSuffixName;
	}

	public String getInputName() {
		return inputName;
	}

	public void setInputName(String inputName) {
		this.inputName = inputName;
	}

	public String getFilePathAll() {
		return filePathAll;
	}

	public void setFilePathAll(String filePathAll) {
		this.filePathAll = filePathAll;
	}

	@Override
	public String toString() {
		return "FileItem [oldFileName=" + oldFileName + ", newFileName="
				+ newFileName + ", inputName=" + inputName + ", filePath="
				+ filePath + ", filePathAll=" + filePathAll + ", size=" + size
				+ ", fileType=" + fileType + ", fileSuffixName="
				+ fileSuffixName + ", multipartFile=" + multipartFile + "]";
	}

}
