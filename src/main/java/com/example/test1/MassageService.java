package com.example.test1;
import com.example.test1.entity.Massage;
import com.example.test1.entity.User;
import com.example.test1.repository.MassageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MassageService {
    @Autowired
    public MassageRepository massageRepository;

    public void work(User author, String text){
        Massage massage = new Massage().withData_sent(LocalDateTime.now()).withText(text).withAutor(author);
        massageRepository.save(massage);
      // return (List<Massage>) massageRepository.findAll();
    }
    public List<Massage> getAllMassages(){
        List<Massage> massages = (List<Massage>) massageRepository.findAll();
        return massages.stream().sorted(Comparator.comparing(Massage::getData_sent)).collect(Collectors.toList());
    }

    public void addfile(User byId, String s) {
        Massage massage = new Massage().withData_sent(LocalDateTime.now()).withTag(s).withAutor(byId);
        massageRepository.save(massage);
    }
    public void addfile(User byId, String s,String text) {
        Massage massage = new Massage().withData_sent(LocalDateTime.now()).withTag(s).withAutor(byId).withText(text);
        massageRepository.save(massage);
    }

    public Object getAllMassagesBetween(LocalDateTime firstBound, LocalDateTime secondBound) {
        List<Massage> massages = (List<Massage>) massageRepository.findAll();
        return massages
                .stream()
                    .filter(massage -> massage.getData_sent().isAfter(firstBound) && massage.getData_sent().isBefore(secondBound))
                        .collect(Collectors.toList())
                .stream()
                    .sorted(Comparator.comparing(Massage::getData_sent))
                        .collect(Collectors.toList());
    }
}
