package digiovannialessandro.u5d10.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import digiovannialessandro.u5d10.ecxeptions.BadRequestException;
import digiovannialessandro.u5d10.ecxeptions.NotFoundException;
import digiovannialessandro.u5d10.ecxeptions.ValidationException;
import digiovannialessandro.u5d10.entities.Dipendente;
import digiovannialessandro.u5d10.entities.Viaggio;
import digiovannialessandro.u5d10.payloads.DipendentiPayload;
import digiovannialessandro.u5d10.repositories.DipendentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class DipendentiService {
    @Autowired
    private DipendentiRepository dipendentiRepository;
    @Autowired
    private Cloudinary imgUploader;

    public Dipendente save(DipendentiPayload payload) {

        if (dipendentiRepository.existsByEmail(payload.email())) {
            throw new BadRequestException("Email '" + payload.email() + "' già in uso.");
        }

        if (dipendentiRepository.existsByUsername(payload.username())) {
            throw new BadRequestException("Username '" + payload.username() + "' già in uso.");
        }
        Dipendente newDipendente = new Dipendente(payload.username(), payload.name(), payload.surname(), payload.email());
        Dipendente dipendenteSalvato=this.dipendentiRepository.save(newDipendente);
        return dipendenteSalvato;
    }
    public Page<Dipendente> findAll(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());
        return this.dipendentiRepository.findAll(pageable);
    }

    public Dipendente findById(int dipendenteId) {
        return this.dipendentiRepository.findById(dipendenteId).orElseThrow(() -> new NotFoundException(dipendenteId));
    }
    public String uploadImg(MultipartFile file) {
        try {
            Map result = imgUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String imageURL = (String) result.get("url");
            return imageURL;
        } catch (Exception e) {
            throw new BadRequestException("Ci sono stati problemi nel salvataggio del file!");
        }
    }
}
