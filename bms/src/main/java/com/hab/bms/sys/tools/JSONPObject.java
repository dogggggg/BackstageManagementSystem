package com.hab.bms.sys.tools;

import com.alibaba.fastjson.serializer.SerializerFeature;

public class JSONPObject {
    
    private String callBackFunction;
    
    private Object jsonObject;
    
    private SerializerFeature[] feature;

    /**
     * Getter method for property <tt>callBackFunction</tt>.
     * 
     * @return property value of callBackFunction
     */
    public String getCallBackFunction() {
        return callBackFunction;
    }

    /**
     * Setter method for property <tt>callBackFunction</tt>.
     * 
     * @param callBackFunction value to be assigned to property callBackFunction
     */
    public void setCallBackFunction(String callBackFunction) {
        this.callBackFunction = callBackFunction;
    }

    /**
     * Getter method for property <tt>jsonObject</tt>.
     * 
     * @return property value of jsonObject
     */
    public Object getJsonObject() {
        return jsonObject;
    }

    /**
     * Setter method for property <tt>jsonObject</tt>.
     * 
     * @param jsonObject value to be assigned to property jsonObject
     */
    public void setJsonObject(Object jsonObject) {
        this.jsonObject = jsonObject;
    }

    /**
     * Getter method for property <tt>feature</tt>.
     * 
     * @return property value of feature
     */
    public SerializerFeature[] getFeature() {
        return feature;
    }

    /**
     * Setter method for property <tt>feature</tt>.
     * 
     * @param featue value to be assigned to property feature
     */
    public void setFeatue(SerializerFeature[] feature) {
        this.feature = feature;
    }



}
