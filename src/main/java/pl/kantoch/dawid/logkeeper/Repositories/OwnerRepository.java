package pl.kantoch.dawid.logkeeper.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kantoch.dawid.logkeeper.DataModels.Owner;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long>
{
    Optional<Owner> findByApplicationName(String applicationName);
}
