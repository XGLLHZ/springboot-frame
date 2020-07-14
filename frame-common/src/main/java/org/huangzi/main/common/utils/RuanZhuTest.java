package org.huangzi.main.common.utils;

import java.io.*;

/**
 * @author: XGLLHZ
 * @date: 2020/5/27 上午11:25
 * @description:
 */
public class RuanZhuTest {

    public static void main(String[] args) throws Exception {
        File dir = new File("/Users/xinou/Documents/xinou/peotry/poetry/");
        //文件输出路径
        File target = new File("/Users/xinou/Desktop/厦门金融诗词闯关-后端.docx");
        BufferedWriter bw = new BufferedWriter(new FileWriter(target));

        StringBuffer sb = new StringBuffer();
        loopRead(dir, sb);
        write(sb.toString(), bw);
    }
    // 遍历文件夹下所有文件
    private static void loopRead(File dir, StringBuffer sb){
        File[] files = dir.listFiles();
        if (files!=null)
            for(File file:files){
                if(file.isDirectory()){
                    loopRead(file, sb);
                }else {
                    if(file.length()!=0){
                        sb.append(readFileToString(file));
                    }
                }

            }

    }
    //读取文件里面的内容
    private static String readFileToString(File file){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new InputStreamReader( new FileInputStream(file), "UTF-8"));
            String line = null;
            while((line = br.readLine())!=null){
                String s = line.trim();
                if (s.length()==0) {
                    continue;
                }
                if (s.startsWith("/") || s.startsWith("*")) {
                    continue;
                }
                sb.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(br!=null){
                    br.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return sb.toString();

    }
    //将读取的路径以及相应的内容写入指定的文件
    private static void write(String str, Writer writer){
        try {
            writer.write(str);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{

            try {
                if(writer!=null)
                    writer.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

}
