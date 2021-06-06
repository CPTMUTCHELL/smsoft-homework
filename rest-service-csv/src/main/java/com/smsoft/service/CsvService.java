package com.smsoft.service;

import com.smsoft.dto.StatusDto;
import com.smsoft.entity.Status;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvService {
    private List<Status> statuses;

    public void parseCSV() throws IOException {
        try (var file = new BufferedReader(new FileReader("./rest-service-csv/status_rcoi.csv"))) {
            statuses = new ArrayList<>();
            file.readLine();
            String line;
            while ((line = file.readLine()) != null) {
                statuses.add(new Status(line));

            }
        }
    }

    public StatusDto filterByDateGap(String dateParam) {
        var res = new StatusDto();
        List<Status> filteredList;
        if (dateParam.matches("[0-9]+")) {
          filteredList  = statuses.stream()
                    .filter(el -> el.getPut().getTime() - el.getCreated().getTime() > Long.parseLong(dateParam)
                            || el.getCreated().getTime() - el.getPut().getTime() > Long.parseLong(dateParam))
                    .collect(Collectors.toList());
        }
        else {
            long d = 0;
            String[] splitDigits = dateParam.split("\\s*[a-zA-Z]+\\s*");
            String[] splitChars = dateParam.split("\\s*[0-9]+\\s*");
            for (int i = splitChars.length - 1; i > 0; i--) {
                switch (splitChars[i]) {
                    case "ms" -> d += Long.parseLong(splitDigits[i - 1]);
                    case "s" -> d += 1000 * Long.parseLong(splitDigits[i - 1]);
                    case "m" -> d += 60000 * Long.parseLong(splitDigits[i - 1]);
                    case "h" -> d += 3.6e+6 * Long.parseLong(splitDigits[i - 1]);
                }

            }
            var finalDelay = d;
            filteredList=statuses.stream()
                    .filter(el -> el.getPut().getTime() - el.getCreated().getTime() > finalDelay
                            || el.getCreated().getTime() - el.getPut().getTime() >finalDelay)
                    .collect(Collectors.toList());
        }
        res.setCount(filteredList.size());
        res.setStatuses(filteredList);
        return res;
    }

    public StatusDto getStatusesDto() {
        var res = new StatusDto();
        res.setStatuses(statuses);
        res.setCount(statuses.size());
        return res;
    }

}
