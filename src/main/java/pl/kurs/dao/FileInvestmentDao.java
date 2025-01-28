package pl.kurs.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import pl.kurs.entity.Investment;
import pl.kurs.exception.InvestmentNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Repository
@Profile("file")
@RequiredArgsConstructor
public class FileInvestmentDao implements InvestmentDao {
    private static final String FILE_NAME = "C:\\JavaTest\\Investment.json";
    private final ObjectMapper objectMapper;

    @Override
    public void save(Investment investment) throws IOException {
        long objectId = 0L;
        File file = new File(FILE_NAME);
        List<Investment> investmentList = new ArrayList<>();

        if (file.exists() && file.length() > 0) {
            Investment[] existingInvestment = objectMapper.readValue(file, Investment[].class);
            investmentList = new ArrayList<>(Arrays.asList(existingInvestment));
            objectId = getNextId(investmentList);
        }
        investment.setId(++objectId);
        investmentList.add(investment);
        objectMapper.writeValue(new File(FILE_NAME), investmentList);
    }

    @Override
    public Investment get(Long id) throws InvestmentNotFoundException, IOException {
        List<Investment> investmentList = Arrays.asList(objectMapper.readValue(new File(FILE_NAME), Investment[].class));
        return investmentList.stream()
                .filter(investment -> Objects.equals(investment.getId(), id))
                .findFirst()
                .orElseThrow(() -> new InvestmentNotFoundException("No Investment found with id: " + id));
    }

    private Long getNextId(List<Investment> investmentList) throws IOException {
        return investmentList.stream()
                .mapToLong(Investment::getId)
                .max()
                .orElse(0);
    }

}
