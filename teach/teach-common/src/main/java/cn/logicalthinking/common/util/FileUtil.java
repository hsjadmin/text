package cn.logicalthinking.common.util;

import cn.logicalthinking.common.exception.SysException;

import java.io.*;
import java.util.List;

/**
 * 文件操作工具类
 * @Description
 * @author 黄世杰
 * @2018-10-9
 * @version  1.0
 */
public class FileUtil {

	/**
	 * 复制文件  只能剪切文件  不能复制整个文件夹
	 * @param srcFileName  源文件夹路径
	 * @param destFileName  目标文件的路径
	 * @param overlay   目标文件存在并允许覆盖   true允许      false不允许
	 * @return
	 * @throws Exception
	 */
	public static void copyFile(String srcFileName, String destFileName,boolean overlay) {
		try {
			File srcFile = new File(srcFileName); // 根据一个路径得到File对象
			// 判断源文件是否存在
			if (!srcFile.exists()) {
				throw new SysException("源文件：" + srcFileName + "不存在！");
			} else if (!srcFile.isFile()) {// 判断是不是一个文件
				throw new SysException("复制文件失败，源文件：" + srcFileName + "不是一个文件！");
			}
			// 判断目标文件是否存在
			File destFile = new File(destFileName);// 目标文件对象destFile
			if (destFile.exists()) {
				if (overlay) {// 如果目标文件存在并允许覆盖
					new File(destFileName).delete();// 删除已经存在的目标文件
				}
			}

			// 如果目标文件所在目录不存在，则创建 目录
			if (!destFile.getParentFile().exists()) {
				// mkdirs()：创建此抽象路径名指定的目录，包括所有必需但不存在的父目录
				if (!destFile.getParentFile().mkdirs()) {
					throw new SysException("复制文件失败：创建目标文件所在目录失败");
				}
			}
			// 复制文件
			int byteread = 0;// 读取的字节数
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new FileInputStream(srcFile);
				out = new FileOutputStream(destFile);
				byte[] buffer = new byte[1024];
				while ((byteread = in.read(buffer)) != -1) {
					out.write(buffer,0,byteread);
				}

			} catch (FileNotFoundException e) {
				throw new SysException(e.getMessage());
			} catch (IOException e) {
				throw new SysException(e.getMessage());
			} finally {
				if (out != null) {
					try {out.close();} catch (IOException e) {e.printStackTrace();}
				}
				if (in != null) {
					try {in.close();} catch (IOException e) {e.printStackTrace();}
				}
				//回删文件
				srcFile.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
            throw new SysException(e.getMessage());
		}
	}


	/**
	 * 复制文件  只能复制文件  不能复制整个文件夹
	 * @param srcFileName  源文件夹路径
	 * @param destFileName  目标文件的路径
	 * @param overlay   目标文件存在并允许覆盖   true允许      false不允许
	 * @return
	 * @throws Exception
	 */
	public static void copyFileToOther(String srcFileName, String destFileName,boolean overlay) {
		try {
			File srcFile = new File(srcFileName); // 根据一个路径得到File对象
			// 判断源文件是否存在
			if (!srcFile.exists()) {
				throw new SysException("源文件：" + srcFileName + "不存在！");
			} else if (!srcFile.isFile()) {// 判断是不是一个文件
				throw new SysException("复制文件失败，源文件：" + srcFileName + "不是一个文件！");
			}
			// 判断目标文件是否存在
			File destFile = new File(destFileName);// 目标文件对象destFile
			if (destFile.exists()) {
				if (overlay) {// 如果目标文件存在并允许覆盖
					new File(destFileName).delete();// 删除已经存在的目标文件
				}
			}

			// 如果目标文件所在目录不存在，则创建 目录
			if (!destFile.getParentFile().exists()) {
				// mkdirs()：创建此抽象路径名指定的目录，包括所有必需但不存在的父目录
				if (!destFile.getParentFile().mkdirs()) {
					throw new SysException("复制文件失败：创建目标文件所在目录失败");
				}
			}
			// 复制文件
			int byteread = 0;// 读取的字节数
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new FileInputStream(srcFile);
				out = new FileOutputStream(destFile);
				byte[] buffer = new byte[1024];
				while ((byteread = in.read(buffer)) != -1) {
					out.write(buffer,0,byteread);
				}

			} catch (FileNotFoundException e) {
				throw new SysException(e.getMessage());
			} catch (IOException e) {
				throw new SysException(e.getMessage());
			} finally {
				if (out != null) {
					try {out.close();} catch (IOException e) {e.printStackTrace();}
				}
				if (in != null) {
					try {in.close();} catch (IOException e) {e.printStackTrace();}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据该路径下的文件夹获取到该文件夹里面的所有路径
	 * @param path  全路径
	 * @param
	 * @return
	 */
	public static List<String> getFile(String path,List<String> list) {
		// 获得指定文件对象
		File file = new File(path);
		// 获得该文件夹内的所有文件
		File[] array = file.listFiles();
		for (int i = 0; i < array.length; i++) {
			if (array[i].isFile()){// 如果是文件
				list.add(array[i].getPath());
			} else if (array[i].isDirectory()){// 如果是文件夹
				getFile(array[i].getPath(),list);// 文件夹需要调用递归 ，深度+1
			}
		}
		return list;
	}

	/**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                	System.out.println("失败");
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

       public static void main(String args[]){
    	   deleteDir(new File("G:/tomcat工作包/apache-tomcat-180115/webapps/SCIA/caseFile/P2018211/1943"));
           System.out.println("deleted");
    }


    //删除文件夹
    //param folderPath 文件夹完整绝对路径
     public static void delFolder(String folderPath) {
         try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
         } catch (Exception e) {
           e.printStackTrace();
         }
    }

    //删除指定文件夹下所有文件
    //param path 文件夹完整绝对路径
       public static boolean delAllFile(String path) {
           boolean flag = false;
           File file = new File(path);
           if (!file.exists()) {
             return flag;
           }
           if (!file.isDirectory()) {
             return flag;
           }
           String[] tempList = file.list();
           File temp = null;
           for (int i = 0; i < tempList.length; i++) {
              if (path.endsWith(File.separator)) {
                 temp = new File(path + tempList[i]);
              } else {
                  temp = new File(path + File.separator + tempList[i]);
              }
              if (temp.isFile()) {
                 temp.delete();
              }
              if (temp.isDirectory()) {
                 delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                 delFolder(path + "/" + tempList[i]);//再删除空文件夹
                 flag = true;
              }
           }
           return flag;
         }


}
