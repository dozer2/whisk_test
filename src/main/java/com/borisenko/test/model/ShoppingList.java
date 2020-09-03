package com.borisenko.test.model;

import java.util.Map;

public class ShoppingList {
    public ListItem getList() {
        return list;
    }

    public void setList(ListItem list) {
        this.list = list;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    ListItem list;
    Map<String,Object> content;

    public static class ListItem{
        String id;
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
