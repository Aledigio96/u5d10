package digiovannialessandro.u5d10.controllers;

import digiovannialessandro.u5d10.ecxeptions.ValidationException;
import digiovannialessandro.u5d10.entities.Dipendente;
import digiovannialessandro.u5d10.payloads.DipendentiPayload;
import digiovannialessandro.u5d10.payloads.RespDipendentiDTO;
import digiovannialessandro.u5d10.services.DipendentiService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    @Autowired
    private DipendentiService dipendentiService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespDipendentiDTO save(@RequestBody @Validated DipendentiPayload payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            Dipendente newDipendente = this.dipendentiService.save(payload);
            return new RespDipendentiDTO(newDipendente.getId());
        }

    }


    @GetMapping
    public Page<Dipendente> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy
    ) {

        return this.dipendentiService.findAll(page, size, sortBy);
    }


    @GetMapping("/{dipendenteId}")
    public Dipendente getById(@PathVariable int dipendenteId) {
        return this.dipendentiService.findById(dipendenteId);
    }
    @PatchMapping("/{dipendenteId}/img")
    public String uploadImage(@RequestParam("img") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        return this.dipendentiService.uploadImg(file);

    }
    @DeleteMapping("/{dipendenteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getByIdAndDelete(@PathVariable int dipendenteId) {
        this.dipendentiService.findByIdAndDelete(dipendenteId);
    }
}
