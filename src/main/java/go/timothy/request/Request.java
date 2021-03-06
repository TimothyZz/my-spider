package go.timothy.request;

import go.timothy.config.RequestConfig;
import go.timothy.constant.HttpMethodEnum;
import go.timothy.http.Header;
import go.timothy.parser.Parser;
import go.timothy.response.Entity;
import go.timothy.response.Response;
import go.timothy.result.Result;
import go.timothy.spider.BaseSpider;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * @author TimothyZz
 * @program my-spider
 * @description 请求
 * @date 2018-09-29 15:55
 **/
@Data(staticConstructor = "of")
public class Request {
    /**
     * 所属爬虫
     */
    private final BaseSpider spider;
    /**
     * 请求路径
     */
    private final String url;
    /**
     * 请求方法
     */
    private final HttpMethodEnum method;
    /**
     * 请求配置
     */
    private RequestConfig requestConfig;
    /**
     * 请求头
     */
    private List<Header> headers;
    /**
     * 请求体
     */
    private Entity entity;
    /**
     * 解析器
     */
    private final Parser<Response, Result> parser;

    /**
     * 创建请求
     *
     * @param url
     * @return go.timothy.request.Request
     * @author TimothyZz
     * @date 2018/11/7 10:29
     */
    public static Request of(String url) {
        return of(null, url, null, null);
    }

    /**
     * 添加请求头
     *
     * @param name
     * @param value
     * @return go.timothy.request.Request
     * @author TimothyZz
     * @date 2018/11/7 11:08
     */
    public Request addHeader(String name, String value) {
        if (this.headers == null) {
            this.headers = new ArrayList<>(2);
        }
        this.headers.add(Header.of(name, value));
        return this;
    }
}
