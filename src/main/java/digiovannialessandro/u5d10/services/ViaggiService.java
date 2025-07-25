package digiovannialessandro.u5d10.services;

import digiovannialessandro.u5d10.entities.Viaggio;
import digiovannialessandro.u5d10.payloads.ViaggiPayload;
import digiovannialessandro.u5d10.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ViaggiService {

    @Autowired
    private ViaggiRepository viaggiRepository;

    public Viaggio save(ViaggiPayload payload) {
        Viaggio viaggio = new Viaggio(payload.dataPartenza(), payload.destinazione(), payload.stato());
        return viaggiRepository.save(viaggio);
    }

    public Page<Viaggio> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return this.viaggiRepository.findAll(pageable);
    }
}
