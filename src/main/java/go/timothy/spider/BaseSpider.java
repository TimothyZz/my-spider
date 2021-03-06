package go.timothy.spider;

import go.timothy.config.RequestConfig;
import go.timothy.constant.HttpMethodEnum;
import go.timothy.constant.UserAgentConst;
import go.timothy.http.Header;
import go.timothy.parser.Parser;
import go.timothy.pipeline.Pipeline;
import go.timothy.request.Request;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author TimothyZz
 * @program my-spider
 * @description 爬虫基类
 * @date 2018-09-28 17:17
 **/
@Data
@Accessors(chain = true)
public class BaseSpider {
    /**
     * 名称
     */
    private final String name;

    /**
     * 初始请求资源地址
     */
    private final List<String> urls = new ArrayList<>(8);

    /**
     * 数据加工管道
     */
    private Pipeline pipeline;

    /**
     * 默认请求头
     */
    private final List<Header> headers;

    /**
     * 默认请求配置
     */
    private RequestConfig requestConfig;

    /**
     * 配置请求方法
     */
    private HttpMethodEnum method;

    /**
     * 初始请求资源默认解析器
     */
    private Parser mainParser;

    {
        this.method = HttpMethodEnum.GET;
        ArrayList<Header> headers = new ArrayList<>(1);
        headers.add(Header.of("User-Agent", UserAgentConst.CHROME_WIN10));
        this.headers = headers;
    }

    private BaseSpider(String name) {
        this.name = name;
    }

    public static BaseSpider of(String name) {
        return new BaseSpider(name);
    }

    public BaseSpider addUrls(String... urls) {
        Objects.requireNonNull(urls);
        this.urls.addAll(Arrays.asList(urls));
        return this;
    }

    public Request makeRequest(String url, Parser parser) {
        Request request = Request.of(this, url, this.method, parser);
        request.setHeaders(new ArrayList<>(this.headers));
        request.setRequestConfig(this.requestConfig);
        return request;
    }

}
