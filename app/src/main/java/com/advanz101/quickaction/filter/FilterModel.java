package com.advanz101.quickaction.filter;

import java.util.List;

public class FilterModel {

    private String Name;
    private List<String> Cities;

    public FilterModel(String name, List<String> cities) {
        super();
        Name = name;
        Cities = cities;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<String> getCities() {
        return Cities;
    }

    public void setCities(List<String> cities) {
        Cities = cities;
    }

/*    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((Cities == null) ? 0 : Cities.hashCode());
        result = prime * result + ((Name == null) ? 0 : Name.hashCode());
        return result;
    }*/


  /*  @Override
    public String toString() {
        return "FilterModel [Name=" + Name + ", Cities=" + Cities + "]";
    }*/
}
