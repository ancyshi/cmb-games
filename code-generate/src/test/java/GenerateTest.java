

import cn.fulugame.generate.GenerateFactory;
import cn.fulugame.generate.utils.DBUtils;
import org.junit.Test;

import java.sql.Connection;

public class GenerateTest {

    @Test
    public void test1() {
        codeGenerateForTable("ShiJiaoYun","access_token");
    }


    public void codeGenerateForTable(String author,String ... tableName){
        String driver = "com.mysql.jdbc.Driver";
        String uri = "jdbc:mysql://127.0.0.1:3306/cmb?&characterEncoding=utf-8&useUnicode=true&serverTimezone=UTC";
        String username = "root";
        String password = "123456";
        Connection connection = DBUtils.getConnectionByJDBC(driver, uri, username, password);
        GenerateFactory generateFactory = GenerateFactory.GenerateFactoryBuilder
                .aGenerateFactory()
                .withConnection(connection)
                .withAutoRemovePre(false)
                .withOutPath("E:\\generate_files")
                .withPackageName("cn.fulugame.core")
                .withAuthor(author)
                .build();
        generateFactory.generatorCode(tableName);
    }






}
