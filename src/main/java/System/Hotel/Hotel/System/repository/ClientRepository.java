package System.Hotel.Hotel.System.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import System.Hotel.Hotel.System.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query("SELECT a FROM Client a WHERE a.status = 'APROVADO' ")
    public List<Client> FindByAprovado();

    @Query("SELECT i FROM Client i WHERE i.status = 'AGUARDE' ")
    public List<Client> FindByAguarde();

    @Query("SELECT s FROM Client s WHERE s.status = 'NEGADO' ")
    public List<Client> findByNegado();

    public List<Client> findByNomeContainingIgnoreCase(String nome);
}
