package pl.kurs.dao;

import pl.kurs.entity.Investment;

public interface InvestmentDao {
    void save(Investment investment);

    Investment get(Long id);
}
