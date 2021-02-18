package com.example.btctqc_app.services.models;

import androidx.annotation.NonNull;

public class SurveyModel {
    int survey_id;
    String first_name;
    String middle_name;
    String last_name;
    int bus_info_id;
    int user_id;

    String bus_code;
    String bus_name;
    String contact_number;
    String create_time_stamp;

    public int getBus_info_id() {
        return bus_info_id;
    }

    public void setBus_info_id(int bus_info_id) {
        this.bus_info_id = bus_info_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public int getSurvey_id() {
        return survey_id;
    }

    public void setSurvey_id(int survey_id) {
        this.survey_id = survey_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBus_code() {
        return bus_code;
    }

    public void setBus_code(String bus_code) {
        this.bus_code = bus_code;
    }

    public String getBus_name() {
        return bus_name;
    }

    public void setBus_name(String bus_name) {
        this.bus_name = bus_name;
    }

    public String getCreate_time_stamp() {
        return create_time_stamp;
    }

    public void setCreate_time_stamp(String create_time_stamp) {
        this.create_time_stamp = create_time_stamp;
    }
}
