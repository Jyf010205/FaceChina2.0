package com.mysonandme.common.exception;

/**
 * Description: FaceChina-server  异常
 * Author:kbq
 * Date: 2019-11-04 13:29
 */
public class FaceChinaException extends Exception {

    public FaceChinaException(){
    }
    public FaceChinaException(String message) {
        super(message);
    }

    public FaceChinaException(Throwable cause) {
        super(cause);
    }

    public FaceChinaException(String message, Throwable cause) {
        super(message, cause);
    }
}
