package com.xxhwap.utils;
        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.URL;
/**
 * @说明 从网络获取图片到本地
 * @author 崔素强
 * @version 1.0
 * @since
 */
public class GetImage {
    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        String url = "http://www.baidu.com/img/baidu_sylogo1.gif";
        byte[] btImg = getImageFromNetByUrl(url);
        if(null != btImg && btImg.length > 0){
            System.out.println("读取到：" + btImg.length + " 字节");
            String fileName = "百度.gif";
            writeImageToDisk(btImg, fileName);
        }else{
            System.out.println("没有从该连接获得内容");
        }
    }
    public static void downImageForNetUrl(String url){
        byte[] btImg = getImageFromNetByUrl(url);
        if(null != btImg && btImg.length > 0){
            System.out.println("读取到：" + btImg.length + " 字节");
            String fileName = "百度.gif";
            writeImageToDisk(btImg, fileName);
        }else{
            System.out.println("没有从该连接获得内容");
        }
    }
    public static void downImageForNetUrl(String strUrl,String fileName){
        //byte[] btImg = getImageFromNetByUrl(url);
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            String contentType = conn.getHeaderField("Content-disposition");
            System.out.println("type======================"+contentType);
            String prixName=getFileEndWitsh(contentType);
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
            if(null != btImg && btImg.length > 0){
                System.out.println("读取到：" + btImg.length + " 字节");
                writeImageToDisk(btImg, fileName+prixName);
            }else{
                System.out.println("没有从该连接获得内容");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public static String getFileEndWitsh(String contentType){
        //type======================attachment; filename="I_pQgUIxWjFEFlvqfcwoO41E0bYrcUheT6ag6pAPwLNTzY1HQ-RV5Dk6_2hqRqtL.jpg"
        String [] prix=contentType.split(".");
        String prixName=contentType.substring(contentType.indexOf(".")).replace("\"","");
        int length=prix.length;
        if(length>1){
            prixName=prix[1];
        }
        return prixName;
    }
    /**
     * 将图片写入到磁盘
     * @param img 图片数据流
     * @param fileName 文件保存时的名称
     */
    public static void writeImageToDisk(byte[] img, String fileName){
        try {
            Config config=new Config();
            String localPath=config.getString("downimage");
            System.out.println("filename==================================="+fileName);
            File file = new File(localPath+"/" + fileName);
            FileOutputStream fops = new FileOutputStream(file);
            fops.write(img);
            fops.flush();
            fops.close();
            System.out.println("图片已经写入到C盘");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 根据地址获得数据的字节流
     * @param strUrl 网络连接地址
     * @return
     */
    public static byte[] getImageFromNetByUrl(String strUrl){
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            InputStream inStream = conn.getInputStream();//通过输入流获取图片数据
            byte[] btImg = readInputStream(inStream);//得到图片的二进制数据
            return btImg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 从输入流中获取数据
     * @param inStream 输入流
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1 ){
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }
}
