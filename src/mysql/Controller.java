package mysql;


import file.CreatFile;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import pojo.Other;
import pojo.Path;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by li on 2017/12/15.
 */
public class Controller {
    @FXML
    private TextField url;
    @FXML
    private TextField user;
    @FXML
    private TextField password;
    @FXML
    private TextField pojoPath;
    @FXML
    private TextField mapperJavaPath;
    @FXML
    private TextField mapperXmlPath;
    @FXML
    private TextField servicePath;
    @FXML
    private TextField controllerPath;
    @FXML
    private TextField packages;

    //程序入口
    @FXML
    public void write() throws Exception {
        ResultSet resultSet;
        Table table = new Table();
        Class.forName("com.mysql.jdbc.Driver");
        Connection con;
        con = DriverManager.getConnection(url.getText(), user.getText(), password.getText());
        Statement stmt = con.createStatement();
        //获取所有表名
        DatabaseMetaData databaseMetaData = con.getMetaData();
//        String[] types = {"TABLE"};
//        resultSet = databaseMetaData.getTables(null, null, null, types);
        if(stmt.execute("show table status")) {
            resultSet=stmt.getResultSet();
            while (resultSet.next()) {
//                Other.allTable.add(resultSet.getObject("TABLE_NAME"));
                Other.allTable.add(resultSet.getString(resultSet.findColumn("name")));
            }
            Other.packages = packages.getText();
            for (int i = 0; i < Other.allTable.size(); i++) {
                resultSet = stmt.executeQuery("select * from " + Other.allTable.get(i));
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                table.getTypeAndField(resultSetMetaData);
                CreatFile creatFile = new CreatFile();
                creatFile.create();
                Table.tandf = new ArrayList<Object>();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "代码生成完成");
            alert.setHeaderText("");
            alert.setTitle("提示");
            alert.showAndWait();
            con.close();
        }
    }

    //选择pojo文件生成目录
    @FXML
    public void choosePojoDir() {
        Stage stage = null;
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);
        Path.pojoPath = file.getPath();
        pojoPath.setText(file.getPath());
    }

    //选择mapperJava文件生成目录
    @FXML
    public void chooseMapperJavaDir() {
        Stage stage = null;
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);
        Path.mapperJavaPath = file.getPath();
        mapperJavaPath.setText(file.getPath());
    }

    //选择mapperJava文件生成目录
    @FXML
    public void chooseMapperXmlDir() {
        Stage stage = null;
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);
        Path.mapperXmlPath = file.getPath();
        mapperXmlPath.setText(file.getPath());
    }

    //选择controller文件生成目录
    @FXML
    public void chooseControllerDir() {
        Stage stage = null;
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);
        Path.controllerPath = file.getPath();
        controllerPath.setText(file.getPath());
    }

    //选择service文件生成目录
    @FXML
    public void chooseServiceDir() {
        Stage stage = null;
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(stage);
        Path.servicePath = file.getPath();
        servicePath.setText(file.getPath());
    }
}
