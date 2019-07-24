package cn.sitedev.lesson.starter.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcDemoTests {


    @Autowired
    private JdbcTemplate db1JdbcTemplate;

    @Autowired
    private JdbcTemplate db2JdbcTemplate;

    @Test
    public void addDataTest() {
        String sql = "insert into user(name, age) values ('zhangsan', 14)";
        db1JdbcTemplate.execute(sql);
        sql = "insert into user(name, age) values ('lisi', 29)";
        db2JdbcTemplate.execute(sql);
    }

}
