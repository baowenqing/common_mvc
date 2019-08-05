package com.juziwl.commonlibrary.model;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/5/1
 * @description RxBus传递的数据和类型
 */
public class Event {

    public Event(String action) {
        this.action = action;
    }

    public Event(String action, Object object) {
        this.action = action;
        this.object = object;
    }

    public Event(String action, Object object, int position) {
        this.action = action;
        this.object = object;
        this.position = position;
    }

    public Event(String action, Object object, String tag) {
        this.action = action;
        this.object = object;
        this.tag = tag;
    }

    public String action = "";

    public Object object;

    public <T extends Object> T getObject() {
        return (T) object;
    }

    public int position;

    public String tag;
}
