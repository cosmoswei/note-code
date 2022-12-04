//package com.wei;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.logging.Logger;
//
//public class NameServiceImplBaseImpl extends NameServiceGrpc.NameServiceImplBase {
//
//    private Map<String,String> map = new HashMap<String,String>();
//
//    private Logger logger = Logger.getLogger(NameServiceImplBaseImpl.class.getName());
//
//    public NameServiceImplBaseImpl() {
//
//        map.put("Sunny","125.216.242.51");
//
//        map.put("David","117.226.178.139");
//
//    }
//
//    @Override
//    public void getIpByName(Name request, StreamObserver<Ip> responseObserver) {
//        logger.log(Level.INFO,"requst is coming. args=" + request.getName());
//
//        Ip ip = Ip.newBuilder().setIp(getName(request.getName())).build();
//
//        responseObserver.onNext(ip);
//
//        responseObserver.onCompleted();
//
//    }
//
//    public String getName(String name){
//        String ip = map.get(name);
//        if(ip == null){
//            return "0.0.0.0";
//        }
//        return ip;
//    }
//}