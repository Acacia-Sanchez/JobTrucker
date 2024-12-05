package dev.acacia.job_trucker.offer;

import java.util.Optional;
import java.util.List;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<List<Offer>> findByDateOrderByStepDate(LocalDate date);
    
    @Query("SELECT o FROM Offer o WHERE o.summary LIKE %:keyword% OR o.requirements LIKE %:keyword%")
    Optional<List<Offer>> findOfferByKeyword(@Param("keyword") String keyword);

    @Transactional
    @Modifying
    @Query("UPDATE Offer o SET o.step = :newStep WHERE o.id = :id")
    int updateOfferStep(@Param("id") long id, @Param("newStep") String newStep);
}

/* 
 findOfferByKeyword(String keyword):

Funciona si tienes un atributo llamado keyword en tu entidad Offer.
Si deseas buscar en múltiples columnas (por ejemplo, en summary, requirements, etc.), deberás usar una consulta personalizada con @Query.
Ejemplo de consulta personalizada:

@Query("SELECT o FROM Offer o WHERE o.summary LIKE %:keyword% OR o.requirements LIKE %:keyword%")
Optional<List<Offer>> findOfferByKeyword(@Param("keyword") String keyword);
 */