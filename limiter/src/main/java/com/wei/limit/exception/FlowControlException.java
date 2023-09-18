package com.wei.limit.exception;

public class FlowControlException extends RuntimeException {
    public FlowControlException(){
        super();
    }

    public FlowControlException(String msg){
        super(msg);
    }
}
