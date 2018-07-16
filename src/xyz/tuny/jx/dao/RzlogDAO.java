package xyz.tuny.jx.dao;

import java.util.List;
import java.util.Map;

import xyz.tuny.jx.model.Rzlog;

/**
 * rzlog 独有的操作
 */
public interface RzlogDAO extends GenericDAO<Rzlog, Long> {
    List<Rzlog> findAll(boolean withBids);

    List<Rzlog> findByName(String name, boolean fuzzy);

    public void SaveBatch(final String tableName, final String[] fields, final List<Map> datas);


}