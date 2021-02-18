package com.example.btctqc_app.services.models;

public class BusInfoModel {
    int bus_info_id;
    String name;
    String code;
    String create_time_stamp;

    public int getBus_info_id() {
        return bus_info_id;
    }

    public void setBus_info_id(int bus_info_id) {
        this.bus_info_id = bus_info_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreate_time_stamp() {
        return create_time_stamp;
    }

    public void setCreate_time_stamp(String create_time_stamp) {
        this.create_time_stamp = create_time_stamp;
    }
}
