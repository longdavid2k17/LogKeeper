package pl.kantoch.dawid.logkeeper.DataModels;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class Owner
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String applicationName;

    @NotNull
    private String applicationPassword;

    @NotNull
    private String email;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date modificationDate;

    public Owner(Long id, String applicationName, String applicationPassword, String email, Date creationDate, Date modificationDate)
    {
        this.id = id;
        this.applicationName = applicationName;
        this.applicationPassword = applicationPassword;
        this.email = email;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }

    public Owner()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationPassword() {
        return applicationPassword;
    }

    public void setApplicationPassword(String applicationPassword) {
        this.applicationPassword = applicationPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", applicationName='" + applicationName + '\'' +
                ", applicationPassword='" + applicationPassword + '\'' +
                ", email='" + email + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return Objects.equals(id, owner.id) && Objects.equals(applicationName, owner.applicationName) && Objects.equals(applicationPassword, owner.applicationPassword) && Objects.equals(email, owner.email) && Objects.equals(creationDate, owner.creationDate) && Objects.equals(modificationDate, owner.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, applicationName, applicationPassword, email, creationDate, modificationDate);
    }
}
