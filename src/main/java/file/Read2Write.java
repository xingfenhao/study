package file;


import randomAccessfile.RandomAccessFileTest;

import java.io.*;

/**
 * 从一个文件读取然后写入到另一个文件
 */
public class Read2Write {


    /**
     * 读取某个文件夹下的所有文件
     */
    public static boolean readfile(String filepath) throws FileNotFoundException, IOException {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
                System.out.println("文件");
                System.out.println("path=" + file.getPath());
                System.out.println("absolutepath=" + file.getAbsolutePath());
                System.out.println("name=" + file.getName());
                if(!file.getName().contains(".vue"))
                {
                    return false;
                }

                Long filelength = file.length();
                byte[] filecontent = new byte[filelength.intValue()];
                FileInputStream in = new FileInputStream(file);
                in.read(filecontent);
                in.close();
                String fileContent = new String(filecontent, "UTF-8");

                RandomAccessFileTest.randomWrite("E:\\project\\test\\javacode.txt",fileContent);
            } else if (file.isDirectory()) {
                System.out.println("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        System.out.println("path=" + readfile.getPath());
                        System.out.println("absolutepath="
                                + readfile.getAbsolutePath());
                        System.out.println("name=" + readfile.getName());

                        if(!readfile.getPath().contains(".vue"))
                        {
                            continue;
                        }
                        Long filelength = readfile.length();
                        byte[] filecontent = new byte[filelength.intValue()];
                        FileInputStream in = new FileInputStream(readfile);
                        in.read(filecontent);
                        in.close();
                       String fileContent = new String(filecontent, "UTF-8");

                        RandomAccessFileTest.randomWrite("E:\\project\\test\\javacode.txt",fileContent);
                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile()  Exception:" + e.getMessage());
        }
        return true;
    }

    public static void main(String[] args)
    {

        String path = "E:\\git\\middleware\\avalon-web\\src";
        try {
            readfile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
