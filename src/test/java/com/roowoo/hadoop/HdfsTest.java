package com.roowoo.hadoop;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author zhuzengpeng
 */
public class HdfsTest {

    private ApplicationContext ctx;
    private FileSystem fileSystem;

    @Before
    public void setUp() {
        ctx = new ClassPathXmlApplicationContext("spring/hdfs.xml");
        fileSystem = (FileSystem) ctx.getBean("fileSystem");
    }

    @After
    public void tearDown() throws IOException {
        ctx = null;
        fileSystem.close();
    }

    /**
     * 在HDFS上创建一个目录
     * @throws Exception
     */
    @Test
    public void testMkdirs()throws Exception{
        fileSystem.mkdirs(new Path("/tmp/SpringHDFS/"));
    }

    @Test
    public void readFile()throws Exception{
        System.out.println(fileSystem.exists(new Path("/user/hdfs/student.txt")));
    }

    @Test
    public void copyFile()throws Exception{
        fileSystem.copyToLocalFile(new Path("/user/hdfs/student.txt"), new Path("D:/temp/student.txt"));
    }
}
