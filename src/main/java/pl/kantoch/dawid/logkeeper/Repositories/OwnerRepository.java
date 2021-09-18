package pl.kantoch.dawid.logkeeper.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kantoch.dawid.logkeeper.DataModels.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long>
{

}
