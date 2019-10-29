package gov.dhs.uscis.bdso.celebrity.service;

import gov.dhs.uscis.bdso.celebrity.enumeration.Sources;
import gov.dhs.uscis.bdso.celebrity.exception.BusinessException;

public interface DataLoadService {
    void triggerLoad() throws BusinessException;

    void loadCelebrities(Sources source) throws BusinessException;

    void loadMovies(Sources source) throws BusinessException;
}
