package com.windyoffice.springcachedemo.common;
import java.io.Serializable;

/***
 * 后台统一返回类
 * @param <T>
 */
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private String errorCode;
    private T data;

    private ServerResponse(int status){
        this.status=status;
    }

    private ServerResponse(int status, T data){
        this.status=status;
        this.data=data;
    }

    private ServerResponse(int status, String msg, T data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    private ServerResponse(String errorCode,int status, String msg, T data){
        this.errorCode=errorCode;
        this.status=status;
        this.msg=msg;
        this.data=data;
    }

    private ServerResponse(String errorCode, String msg){
        this.errorCode=errorCode;
        this.msg=msg;
    }

    private ServerResponse(int status, String msg){
        this.status=status;
        this.msg=msg;
    }

    public boolean ifsuccess(){
        return this.status==ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return status;
    }

    public T getData(){
        return data;
    }

    public String getMsg(){
        return  msg;
    }

    public  static <T> ServerResponse<T> createBySuccess(){
        return  new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public  static <T> ServerResponse<T> createBySuccessMsg(String msg){
        return  new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public  static <T> ServerResponse<T> createBySuccess(T data){
        return  new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public  static <T> ServerResponse<T> createBySuccess(String msg, T data){
        return  new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    public  static <T> ServerResponse<T> createByError(){
        return  new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDes());
    }

    public  static <T> ServerResponse<T> createByErrorMsg(String  errormsg){
        return  new ServerResponse<T>(ResponseCode.ERROR.getCode(),errormsg);
    }

    public  static <T> ServerResponse<T> createByErrorCodeMsg(String errorcode, String  errormsg){
        return  new ServerResponse<T>(errorcode,errormsg);
    }


}
