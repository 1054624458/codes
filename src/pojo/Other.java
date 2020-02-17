package pojo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by li on 2017/12/18.
 */
public class Other {
    public static List allTable = new ArrayList();
    public static String tableName;
    public static String tableNamen;

    public static String getTableNamen() {
        return tableNamen;
    }

    public static void setTableNamen(String tableNamen) {
        Other.tableNamen = tableNamen;
    }

    public static String time;
    public static String packages;

    public static String getPackages() {
        return packages;
    }

    public static void setPackages(String packages) {
        Other.packages = packages;
    }

    public static String getTime() {
        return time;
    }

    public static void setTime(String time) {
        Other.time = time;
    }

    public static List getAllTable() {
        return allTable;
    }

    public static void setAllTable(List allTable) {
        Other.allTable = allTable;
    }

    public static String getTableName() {
        return tableName;
    }

    public static void setTableName(String tableName) {
        Other.tableName = tableName;
    }

    public Other() {
        SimpleDateFormat sDF = new SimpleDateFormat("yyyy.MM.dd");
        time = sDF.format(new Date());
    }
}
