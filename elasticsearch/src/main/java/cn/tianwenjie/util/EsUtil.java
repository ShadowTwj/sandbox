package cn.tianwenjie.util;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author tianwj
 * @date 2021/7/22 22:09
 */
public class EsUtil {
    private static RestHighLevelClient restHighLevelClient;

    public static RestHighLevelClient getInstance() {
        if (restHighLevelClient == null) {
            HttpHost httpHost = new HttpHost("172.30.176.217", 9200);
            RestClient restClient = RestClient.builder(httpHost).build();
            restHighLevelClient = new RestHighLevelClient(restClient);
        }
        return restHighLevelClient;
    }
}
