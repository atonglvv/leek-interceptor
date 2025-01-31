package cn.atong.leek.leekinterceptor.filter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: leek-interceptor
 * @description: 请求参数Wrapper
 * @author: atong
 * @create: 2021-10-19 22:57
 */
public class RequestParameterWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> params = new HashMap<>();

    RequestParameterWrapper(HttpServletRequest request) {
        super(request);
        //将现有parameter传递给params
        this.params.putAll(request.getParameterMap());
    }


    void addParameters(Map<String, Object> extraParams) {
        for (Map.Entry<String, Object> entry : extraParams.entrySet()) {
            addParameter(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 重写getParameter，代表参数从当前类中的map获取
     *
     * @param name name
     * @return String
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * 同上
     *
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    /**
     * 添加参数
     *
     * @param name
     * @param value
     */
    private void addParameter(String name, Object value) {
        if (value != null) {
            if (value instanceof String[]) {
                params.put(name, (String[]) value);
            } else if (value instanceof String) {
                params.put(name, new String[]{(String) value});
            } else {
                params.put(name, new String[]{String.valueOf(value)});
            }
        }
    }
}

