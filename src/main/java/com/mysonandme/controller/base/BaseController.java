package com.mysonandme.controller.base;

import com.mysonandme.rep.ResponseMO;

/**
 * Description: 公共控制类
 * Author: kbq
 * Date: 2019-11-04 13:26
 */
public class BaseController {

    public <T> ResponseMO<T> success(T resMo, String msg) {
        ResponseMO<T> responseMO = new ResponseMO<T>();
        responseMO.setMsg(msg);
        responseMO.setData(resMo);
        return responseMO;
    }

    public <T> ResponseMO<T> success(T resMo) {
        ResponseMO<T> responseMO = new ResponseMO<T>();
        responseMO.setData(resMo);
        return responseMO;
    }

    public <T> ResponseMO<T> success() {
        return new ResponseMO<>();
    }

    public <T> ResponseMO<T> success(String msg) {
        ResponseMO<T> responseMO = new ResponseMO<T>();
        responseMO.setMsg(msg);
        return responseMO;
    }

    public <T> ResponseMO<T> error(T resMo, String msg) {
        ResponseMO<T> responseMO = new ResponseMO<T>();
        responseMO.setResponseCodeFailure();
        responseMO.setMsg(msg);
        responseMO.setData(resMo);
        return responseMO;
    }

    public <T> ResponseMO<T> error(String msg) {
        ResponseMO<T> responseMO = new ResponseMO<T>();
        responseMO.setResponseCodeFailure();
        responseMO.setMsg(msg);
        return responseMO;
    }

    public <T> ResponseMO<T> custom(int code, String msg) {
        ResponseMO<T> responseMO = new ResponseMO<T>();
        responseMO.setCode(code);
        responseMO.setMsg(msg);
        return responseMO;
    }

    public <T> ResponseMO<T> custom(T resMo, int code, String msg) {
        ResponseMO<T> responseMO = new ResponseMO<T>();
        responseMO.setCode(code);
        responseMO.setMsg(msg);
        responseMO.setData(resMo);
        return responseMO;
    }

    public <T> ResponseMO<T> errorCode(String errorCode, String msg) {
        ResponseMO<T> responseMO = new ResponseMO<T>();
        responseMO.setResponseCodeFailure();
        responseMO.setMsg(msg);
        responseMO.setDebugInfo(errorCode);
        return responseMO;
    }
}
