package pl.kurs.dao;

import pl.kurs.entity.Investment;
import pl.kurs.exception.InvestmentNotFoundException;

import java.io.IOException;

public interface InvestmentDao {
    void save(Investment investment) throws IOException;
    Investment get(Long id) throws IOException, InvestmentNotFoundException;

}
