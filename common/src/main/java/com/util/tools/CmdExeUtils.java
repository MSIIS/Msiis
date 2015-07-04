package com.util.tools;


import java.io.InputStream;
import java.util.List;

/**
 * <h3>JAVA执行命令行工具类</h3>
 * 
 * @author liangzh
 *
 * @since  common-0.0.1-SNAPSHOT
 * 
 * 2013年8月29日 下午3:13:31
 *
 */
public class CmdExeUtils {

    private static ProcessBuilder proc = new ProcessBuilder();

    /**
     * <b>执行命令行</b>
     * 
     * @param command
     * @throws Exception
     */
    public static void execute(List<String> command) {
        if (proc == null) {
            proc = new ProcessBuilder();
        }
        Process process = null;
        try {
            proc.command(command);
            proc.redirectErrorStream(true);
            process = proc.start();
            doWaitFor(process);
        } catch (Exception e) {
            throw new IllegalArgumentException("【执行命令】 " + command.toString() + " 失败:\r\n" + e.getMessage());
        } finally {
            if (process != null) {
                process.destroy();
            }
        }

    }

    /**
     * <b>等待进程结束,处理输出流,防止阻塞</b>
     * 
     * @param p
     * @throws Exception
     */
    private static void doWaitFor(Process p) throws Exception {
        InputStream in = null;
        try {
            in = p.getInputStream();
            //子进程退出时的值
            //int exitValue = -1;
            boolean finished = false;
            while (!finished) {
                try {
                    //读取输出流
                    byte[] bytes = new byte[1024];
                    while (in.read(bytes) != -1) {}
                    //exitValue = p.exitValue();
                    //System.out.println(exitValue);
                    finished = true;
                } catch (IllegalThreadStateException e) {
                    Thread.sleep(500);
                }
            }
        } catch (Exception e) {
            System.out.println("【等待进程结束,处理输出流,防止阻塞】发生异常");
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
