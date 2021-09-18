package pl.kantoch.dawid.logkeeper.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kantoch.dawid.logkeeper.DataModels.Owner;
import pl.kantoch.dawid.logkeeper.Repositories.OwnerRepository;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Optional;

@Service
public class OwnerService
{
    private final OwnerRepository ownerRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(OwnerService.class);
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");

    public OwnerService(OwnerRepository ownerRepository)
    {
        this.ownerRepository = ownerRepository;
    }

    @Transactional
    public ResponseEntity<?> createOwnerEntity(Owner owner)
    {
        LOGGER.info("Processing save of {} entity with values {}", Owner.class.getName(),owner);
        if(validateOwnerObject(owner))
        {
            Optional<Owner> optionalOwner = ownerRepository.findByApplicationName(owner.getApplicationName());
            if(optionalOwner.isPresent())
            {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Owner of this application ["+owner.getApplicationName()+"] already exists!");
            }
            else
            {
                Owner savedOwner = ownerRepository.save(owner);
                return ResponseEntity.ok().body(savedOwner);
            }
        }
        else
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Cannot save Owner entity with insufficient data!");

    }

    @Transactional
    public ResponseEntity<?> updateOwnerEntity(Owner owner)
    {
        LOGGER.info("Processing update of {} entity with values {}", Owner.class.getName(),owner);
        if(validateOwnerObject(owner))
        {
            if(checkCorrectiveOfDataChange(owner))
            {
                Owner savedOwner = ownerRepository.save(owner);
                return ResponseEntity.ok().body(savedOwner);
            }
            else
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Cannot process this operation!");
        }
        else
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("Cannot save Owner entity with insufficient data!");
    }

    @Transactional
    public ResponseEntity<?> deleteOwner(Long id)
    {
        LOGGER.info("Processing delete of {} entity with ID {}", Owner.class.getName(),id);
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if(optionalOwner.isPresent())
        {
            Owner deletedOwner = optionalOwner.get();
            ownerRepository.delete(deletedOwner);
            LOGGER.info("Owner {} has been deleted",deletedOwner);
            return ResponseEntity.ok().body("Owner of "+deletedOwner.getApplicationName()+" has been deleted!");
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("This owner entity was not found!");

    }

    private boolean validateOwnerObject(Owner owner)
    {
        if(owner.getApplicationName() == null || owner.getApplicationName().equals(""))
            return false;
        else if(owner.getApplicationPassword() ==null || owner.getApplicationPassword().equals(""))
            return false;
        else return owner.getEmail() != null && !owner.getEmail().equals("");
    }

    private boolean checkCorrectiveOfDataChange(Owner owner)
    {
        Optional<Owner> optionalOwner = ownerRepository.findById(owner.getId());
        if(optionalOwner.isPresent())
        {
            Owner dbOwner = optionalOwner.get();
            if(owner.getApplicationName().equals(dbOwner.getApplicationName()))
            {
                if(owner.getApplicationPassword().equals(dbOwner.getApplicationPassword()))
                {
                    String dateDb = dateFormat.format(dbOwner.getCreationDate());
                    String dateOwner = dateFormat.format(owner.getCreationDate());
                    return dateOwner.equals(dateDb);
                }
                else
                    return false;
            }
            else
                return false;
        }
        else
        {
            LOGGER.error("Did not found entity of Owner with ID = {}",owner.getId());
            return false;
        }
    }
}
