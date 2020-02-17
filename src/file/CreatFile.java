package file;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import mysql.Table;
import pojo.Other;
import pojo.Path;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;

/**
 * Created by li on 2017/12/15.
 */
public class CreatFile {
    //模板生成文件
    public void create() throws Exception {
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir") + "/src/template"));
        configuration.setObjectWrapper(new DefaultObjectWrapper());
        configuration.setDefaultEncoding("utf-8");
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("tafList", Table.tandf);
        map.put("other", new Other());
        if (Path.pojoPath != null)
            createPojo(configuration, map);
        if (Path.mapperXmlPath != null)
            createXmlMapper(configuration, map);
        if (Path.mapperJavaPath != null)
            createJavaMapper(configuration, map);
        if (Path.controllerPath != null)
            createController(configuration, map);
        if (Path.servicePath != null)
            createService(configuration, map);
    }

    //生成pojo.xml
    public void createPojo(Configuration configuration, HashMap<String, Object> map) throws Exception {
        Template template = configuration.getTemplate("pojo.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.pojoPath + "/" + (Other.tableName.substring(0, 1).toUpperCase() + Other.tableName.substring(1)) + ".java"), "UTF-8");
        template.process(map, writer);
    }

    //生成mapper.xml
    public void createXmlMapper(Configuration configuration, HashMap<String, Object> map) throws Exception {
        Template template = configuration.getTemplate("mapperXml.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.mapperXmlPath + "/" + (Other.tableName.substring(0, 1).toUpperCase() + Other.tableName.substring(1)) + "Mapper.xml"), "UTF-8");
        template.process(map, writer);
    }

    //生成mapper.java
    public void createJavaMapper(Configuration configuration, HashMap<String, Object> map) throws Exception {
        Template template = configuration.getTemplate("mapperJava.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.mapperJavaPath + "/" + (Other.tableName.substring(0, 1).toUpperCase() + Other.tableName.substring(1)) + "Mapper.java"), "UTF-8");
        template.process(map, writer);
    }

    //生成controller.java
    public void createController(Configuration configuration, HashMap<String, Object> map) throws Exception {
        Template template = configuration.getTemplate("controller.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.controllerPath + "/" + (Other.tableName.substring(0, 1).toUpperCase() + Other.tableName.substring(1)) + "Controller.java"), "UTF-8");
        template.process(map, writer);
    }

    //生成service.java
    public void createService(Configuration configuration, HashMap<String, Object> map) throws Exception {
        Template template = configuration.getTemplate("service.txt");
        Writer writer = new OutputStreamWriter(new FileOutputStream(Path.servicePath + "/" + (Other.tableName.substring(0, 1).toUpperCase() + Other.tableName.substring(1)) + "Service.java"), "UTF-8");
        template.process(map, writer);
    }
}
