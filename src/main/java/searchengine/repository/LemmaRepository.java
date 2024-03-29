package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import searchengine.model.LemmaModel;
import searchengine.model.SiteModel;

import java.util.List;

@Repository
public interface LemmaRepository extends JpaRepository<LemmaModel, Long> {

    LemmaModel getById(long lemmaID);

    long countBySiteModelId(SiteModel site);

    List<LemmaModel> findBySiteModelId(SiteModel siteId);

    @Query(value = "select * from lemma where lemma.lemma in :lemmas AND lemma.site_id = :site", nativeQuery = true)
    List<LemmaModel> findLemmaListBySite(List<String> lemmas, SiteModel site);

}
