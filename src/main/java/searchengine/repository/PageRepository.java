package searchengine.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import searchengine.model.LemmaModel;
import searchengine.model.PageModel;
import searchengine.model.SiteModel;


import java.util.Collection;
import java.util.List;


@Repository
public interface PageRepository extends JpaRepository<PageModel, Long> {

    long countBySiteId(SiteModel siteId);

    PageModel getById(long pageID);

    Iterable<PageModel> findBySiteId(SiteModel sitePath);

    @Query(value = "SELECT * FROM words_index JOIN Page  ON page.id = words_index.page_id WHERE words_index.lemma_id IN :lemmas",
            nativeQuery = true)
    List<PageModel> findByLemmaList(@Param("lemmas") Collection<LemmaModel> lemmas);

}
