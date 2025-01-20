package pl.kurs.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pl.kurs.entity.Investment;

import java.io.FileWriter;
import java.io.IOException;

@Repository
@Profile("file")
public class FileInvestmentDao implements InvestmentDao {

    @Override
    public void save(Investment investment) {
        try (FileWriter writer = new FileWriter("C:\\JavaTest\\investment.txt", true)) {
            writer.write(investment.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Investment get(Long id) {
        return null;
    }
}
