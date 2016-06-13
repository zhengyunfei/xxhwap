package com.xxhwap.utils;

import java.io.*;

/**
 * Created by Administrator on 2016/5/30.
 */
public class StringInputStreamUtils {
    /**
     * 利用BufferedReader实现Inputstream转换成String <功能详细描述>
     *
     * @param in
     * @return String
     */

    public static String Inputstr2Str_Reader(InputStream in, String encode)
    {

        String str = "";
        try
        {
            if (encode == null || encode.equals(""))
            {
                // 默认以utf-8形式
                encode = "utf-8";
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, encode));
            StringBuffer sb = new StringBuffer();

            while ((str = reader.readLine()) != null)
            {
                sb.append(str).append("\n");
            }
            return sb.toString();
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return str;
    }

    /**
     * 利用byte数组转换InputStream------->String <功能详细描述>
     *
     * @param in
     * @return
     * @see [类、类#方法、类#成员]
     */

    public static String Inputstr2Str_byteArr(InputStream in, String encode)
    {
        StringBuffer sb = new StringBuffer();
        byte[] b = new byte[1024];
        int len = 0;
        try
        {
            if (encode == null || encode.equals(""))
            {
                // 默认以utf-8形式
                encode = "utf-8";
            }
            while ((len = in.read(b)) != -1)
            {
                sb.append(new String(b, 0, len, encode));
            }
            return sb.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * 利用ByteArrayOutputStream：Inputstream------------>String <功能详细描述>
     *
     * @param in
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String Inputstr2Str_ByteArrayOutputStream(InputStream in,String encode)
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len = 0;
        try
        {
            if (encode == null || encode.equals(""))
            {
                // 默认以utf-8形式
                encode = "utf-8";
            }
            while ((len = in.read(b)) > 0)
            {
                out.write(b, 0, len);
            }
            return out.toString(encode);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 利用ByteArrayInputStream：String------------------>InputStream <功能详细描述>
     *
     * @param inStr
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static InputStream Str2Inputstr(String inStr)
    {
        try
        {
            // return new ByteArrayInputStream(inStr.getBytes());
            // return new ByteArrayInputStream(inStr.getBytes("UTF-8"));
            return new StringBufferInputStream(inStr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
