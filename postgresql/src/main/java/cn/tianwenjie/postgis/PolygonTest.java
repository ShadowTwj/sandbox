package cn.tianwenjie.postgis;

import cn.tianwenjie.common.Polygon;
import cn.tianwenjie.util.PgUtil;
import com.alibaba.fastjson.JSON;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tianwj
 * @date 2021/11/30 17:37
 */
@Slf4j
public class PolygonTest {

    public static void main(String[] args) {
        File file = new File("/Users/tianwj/Desktop/cellular_online.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file));
                Connection connection = PgUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement("insert into cellular_geo_test(cellular_id, area) values(?, ?)")) {

            String line = reader.readLine();
            int count = 0;

            while (line != null) {
                count++;
                line = reader.readLine();
                String[] split = line.split("\t", 2);
                String cellularId = split[0];

                Polygon polygon = JSON.parseObject(split[1], Polygon.class);

                statement.setString(1, cellularId);
                if (polygon == null) {
                    continue;
                }
                statement.setObject(2, polygon.convertPGpolygon());

                statement.addBatch();

                if (count % 300 == 0) {
                    statement.executeBatch();
                    log.info("执行至{}条", count);
                }

            }

            if (count % 300 != 0) {
                statement.executeBatch();
                log.info("执行完成至{}条", count);
            }
        } catch (Exception e) {
            log.error("写入异常", e);
        }
    }
}
