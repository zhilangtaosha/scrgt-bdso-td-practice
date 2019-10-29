package gov.dhs.uscis.bdso.celebrity.repository.impl;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import gov.dhs.uscis.bdso.celebrity.repository.CustomQueryRepository;

@Repository
public class CustomQueryRepositoryImpl implements CustomQueryRepository {

	@Autowired
	private EntityManager entityManager;
	
	private static final String GROUP_BY = "group by gender, to_char(m.release_date, 'yyyy')";
	private static final String RELEASE_DATE = "and m.release_date is not null ";
	private static final String SELECT = "select to_char(m.release_date, 'yyyy') as year, ";
	private static final String WHERE  = "where a.id = mc.act_id ";
	private static final String AND  = "and mc.mov_id = m.id ";
	private static final String FROM = "from actors a, movies m, movie_cast mc " ;

    private String queryFromWhere() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(FROM)
	        .append(WHERE)
	        .append(AND)
	        .append(RELEASE_DATE);
        
        return sb.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> queryFilmsByGender() {
		StringBuilder sb = new StringBuilder();
		sb.append("select gender, to_char(m.release_date, 'yyyy') as year, count(distinct m.id) as count ");
        sb.append(queryFromWhere());
		sb.append(GROUP_BY);

		Query q = entityManager.createNativeQuery(sb.toString());
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> querySalaryByGender() {
		StringBuilder sb = new StringBuilder();
		sb.append("select gender, to_char(m.release_date, 'yyyy') as year, sum(a.box_office) as salary ");
        sb.append(queryFromWhere());
		sb.append(GROUP_BY);

		Query q = entityManager.createNativeQuery(sb.toString());
		return q.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> queryAnnualBoxOfficeTotals() {
		StringBuilder sb = new StringBuilder();
		sb.append(SELECT);
		sb.append(" sum(distinct a.box_office) as boxOffice, count(distinct m.id) as movieCount ");
        sb.append(queryFromWhere());
		sb.append("group by to_char(m.release_date, 'yyyy')");

		Query q = entityManager.createNativeQuery(sb.toString());
		return q.getResultList();

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> queryFilmCounts() {
		StringBuilder sb = new StringBuilder();
		sb.append(SELECT);
		sb.append("count(distinct m.id) as movieCount ");
		sb.append("from movies m ");
		sb.append("where m.release_date is not null ");
		sb.append("group by to_char(m.release_date, 'yyyy')");
		
		Query q = entityManager.createNativeQuery(sb.toString());
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> querySalaryCounts() {
		StringBuilder sb = new StringBuilder();
		sb.append("select to_char(m.release_date, 'yyyy') as year, sum(distinct a.box_office) as salary ");
        sb.append(queryFromWhere());
		sb.append("group by to_char(m.release_date, 'yyyy')");
		
		Query q = entityManager.createNativeQuery(sb.toString());
		return q.getResultList();
	}
 
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> querySummaryCounts() {
		StringBuilder sb = new StringBuilder();
		sb.append("select sum(a.count) movieCount, sum(a.bo) totalBoxOffice from "); 
		sb.append("(select to_char(m.release_date, 'yyyy'), sum(distinct a.box_office) bo, count(distinct m.id) count ");
        sb.append(queryFromWhere());
		sb.append("group by to_char(m.release_date, 'yyyy')) a");
		
		Query q = entityManager.createNativeQuery(sb.toString());
		return q.getResultList();
	}

	@Override
	public BigInteger queryMultipleActorCount() {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(*) from ");
		sb.append("(select mov_id, count(distinct act_id) from movie_cast "); 
		sb.append("group by mov_id having count(distinct act_id) > 1) a");
		
		Query q = entityManager.createNativeQuery(sb.toString());
		return (BigInteger)q.getSingleResult();
	}
	
	

}
