package pl.kantoch.dawid.logkeeper.REST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kantoch.dawid.logkeeper.DataModels.Owner;
import pl.kantoch.dawid.logkeeper.Services.OwnerService;

@RestController
@RequestMapping("/log_keeper/owners")
@CrossOrigin("*")
public class OwnerController
{
    private final OwnerService ownerService;
    private final Logger LOGGER = LoggerFactory.getLogger(OwnerController.class);

    public OwnerController(OwnerService ownerService)
    {
        this.ownerService = ownerService;
    }

    @GetMapping("/save")
    public ResponseEntity<?> save(@RequestBody Owner owner)
    {
        LOGGER.warn("Processing of Owner object");
        ResponseEntity<?> response;
        if(owner.getId()==null)
        {
            response = ownerService.createOwnerEntity(owner);
        }
        else
            response = ownerService.updateOwnerEntity(owner);

        return response;
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        LOGGER.warn("Processing of Owner object deleting process");
        return ownerService.deleteOwner(id);
    }
}
