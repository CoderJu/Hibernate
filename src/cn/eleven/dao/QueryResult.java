package cn.eleven.dao;

import java.util.List;

/**
 * Created by User on 2017/6/25.
 */
public class QueryResult {
    private int count;
    private List list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public QueryResult(int count, List list) {
        this.count = count;
        this.list = list;
    }


}
