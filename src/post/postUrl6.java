package post;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;

public class postUrl6 {

	static int step=0;
	static int counter=0;
	static int t_1=0;
	static int t_2=0;
	
	static int i=160;
	static int j=7020;
	
	
	public static void main(String[] args) throws Exception {
		
		while(true){
			switch(step){
			case 0:
				new Reminder();
				System.out.println("Start...");
				step=1;
				break;
			case 1:
				postRequest();
				System.out.println("1. post request break out.");
				step=2;
				break;
			case 2:
				System.out.println("2. sleep 5 second.");
				sleep5();
				step=1;
				break;
			case 3:
				System.out.println("3");
				step=4;
				break;
			case 4:
				System.out.println("4");
				step=5;
				break;
			default:
				System.out.println("5");
				step=0;
				break;
			
			}
		}

	}





	private static void postRequest() throws IOException, Exception {

		
		String str="",str1="",str2="";
		str1=StringUtils.leftPad(""+i, 3,"0");

		File f=new File("D:/postUrl_"+str1+".log");
		FileWriter fw=new FileWriter(f, true);
		BufferedWriter bf=new BufferedWriter(fw);
		PrintWriter pw=new PrintWriter(bf);
		Date now=new Date();
		
		
		String sr="";
		boolean check=false;
		

		do {
			t_1=0;
			sr="";

			str1=StringUtils.leftPad(""+i, 3,"0");
			str2=StringUtils.leftPad(""+j, 5,"0");
			str="lsbh="+ str1 + str2 +"&verifycode=" +str2+ "&backurl=";
			now=new Date();
			System.out.println("======================="+t_1+"============================"+now+"====="+str1+str2+"=");
		
//		        String sr=sendPost("http://www.dalian-jw.gov.cn:8080/lhsfc/querycx.asp", "lsbh=04903399&verifycode=8107&backurl=");
		        try {
					sr=sendPost("http://www.dalian-jw.gov.cn:8080/lhsfc/querycx.asp", str);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					if(t_1>20){
						sleep5();
						break;
					}
					System.out.println("t_1="+t_1);
					t_1=0;
				}
		        check=sr.contains("姓名：</th>")|(!sr.contains("姓名"));
		        t_1=0;
		        if(sr==""|sr==null) {
		        	sleep5();
			        System.out.println("j="+j+", counter="+counter+", length="+sr.length());
		        	continue;
		        }
		        else if(!check){
			        System.out.println("j="+j+", counter="+counter+", length="+sr.length());
		        	pw.println(sr);
		        	counter=0;
		        	j++;
		        }else if(counter<2000){
		        	System.out.println("j="+j+", counter="+counter+", length="+sr.length());
		        	counter++;
		        	j++;
		        }else{
			        System.out.println("j="+j+", counter="+counter+", length="+sr.length());
		        	pw.flush();
		        	pw.close();
		        	bf.close();
		        	fw.close();
		        	counter=0;
		        	i++;
		        	j=0;
		        	step++;
		        }
		} while(i<1000&step==1);
		
		pw.flush();
		pw.close();
		bf.close();
		fw.close();
	}

	@SuppressWarnings("static-access")
	private static void sleep5() throws InterruptedException {
		Thread.currentThread().sleep(5000);
	}
	@SuppressWarnings("static-access")
	private static void sleep30() throws InterruptedException {
		Thread.currentThread().sleep(30000);
	}
    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     * @throws InterruptedException 
     */
    public static String sendPost(String url, String param) throws InterruptedException {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
            		"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(),"GBK"));
            String line;
            t_2=0;
            while ((line = in.readLine()) != null) {
            	if(t_2 > 20) {
            		System.out.println("======t_2:"+t_2);
            		t_2=0;
            		return "";
            	}
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
    public static class Reminder{
        Timer timer;
        
        public Reminder(){
            timer = new Timer();
            timer.schedule(new TimerTask(){
                public void run(){
                	t_1++;
                	t_2++;
                	System.out.println("t_1:"+t_1+", t_2:"+t_2);
                }
            }, 1000,1000);
        }
    } 

}