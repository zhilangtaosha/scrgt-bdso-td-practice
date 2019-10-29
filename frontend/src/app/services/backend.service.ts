import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';
import {
  CelebrityDetails,
  Celebrity,
  SearchResults,
  MovieCount,
  CelebritySalary,
  FilmsByGender,
  SalaryByGender,
  AnnualTotals,
  SummaryCounts,
  FacialRecognitionData, AnnualSalary
} from '../models/models';

@Injectable()
export class BackendService {
  apiEndpoints = {
    celebrities: '/celebrities',
    search: '/search',
    filmsTotal: '/filmsTotal',
    moreFilmsTotal: '/moreFilmsTotal',
    boxOfficeTotal: '/boxOfficeTotal',
    summaryCounts: '/celebrities/query/summaryCounts',
    filmsData: '/celebrities/query/filmsByYear',
    celebritySalaryData: '/celebritySalaryData',
    celebrityByActorId: '/celebrities/query/byActorIds',
    filmsByGenderData: '/celebrities/query/filmsByGender',
    salaryByGenderData: '/celebrities/query/salaryByGender',
    salaryByYear: '/celebrities/query/salaryByYear',
    annualTotals: '/celebrities/query/annualTotals'
  };

  apiRecommendationEndpoints = {
    similarBiosRecommendation: '/similarBios',
    similarGenrePlotsRecommendation: '/similarGenrePlots',
    similarGenreStatsRecommendation: '/similarGenreStats',
    facialRecommendation: '/facialRecognition'
  };

  constructor(private readonly http: HttpClient) {}

  // ================ BACKEND APIs **/

  /**
   * Get all celebrities data
   */
  getAllCelebritiesData() {
    return this.http.get<Celebrity[]>(
      `${environment.API_URL}${this.apiEndpoints.celebrities}`
    );
  }

  /**
   * Set a celebrity's data, if ID exists update
   */
  setCelebrityData(payload: CelebrityDetails) {
    return this.http.post<any>(
      `${environment.API_URL}${this.apiEndpoints.celebrities}`,
      payload
    );
  }
  /**
   * Get a celebrity's data
   */
  getCelebrityData(celebId: number) {
    return this.http.get<any>(
      `${environment.API_URL}${this.apiEndpoints.celebrities}/${celebId}`
    );
  }

  /**
   * Get celebrities given actor id
   */
  getCelebritiesByActorsIds(actorsIds: string) {
    return this.http.get<Celebrity[]>(
      `${environment.API_URL}${this.apiEndpoints.celebrityByActorId}/?${actorsIds}`
    );
  }

  /**
   * Get search result
   */
  getSearchResult(searchParams) {
    return this.http.get<any[]>(
      `${environment.API_URL}${this.apiEndpoints.search}?query=${searchParams}`
    );
  }

  /**
   * Get Film totals
   */
  getFilmsTotal() {
    return this.http.get<SummaryCounts>(
      `${environment.API_URL}${this.apiEndpoints.summaryCounts}`
    );
  }

  /**
   * Get films by year results
   */
  getFilmsData() {
    return this.http.get<MovieCount[]>(
      `${environment.API_URL}${this.apiEndpoints.filmsData}`
    );
  }

  /**
   * Get actors' salaries by year results
   */
  getCelebritySalaryData() {
    return this.http.get<AnnualSalary[]>(
      `${environment.API_URL}${this.apiEndpoints.salaryByYear}`
    );
  }

  /**
   * Get num of films by gender
   */
  getFilmsByGenderData() {
    return this.http.get<FilmsByGender[]>(
      `${environment.API_URL}${this.apiEndpoints.filmsByGenderData}`
    );
  }

  /**
   * Get salary by gender
   */
  getSalaryByGenderData() {
    return this.http.get<SalaryByGender[]>(
      `${environment.API_URL}${this.apiEndpoints.salaryByGenderData}`
    );
  }

  /**
   * Get box office and film total by year
   */
  getAnnualTotals() {
    return this.http.get<AnnualTotals[]>(
      `${environment.API_URL}${this.apiEndpoints.annualTotals}`
    );
  }

  // ================ RECOMMENDATIONS APIs **/

  /**
   * Get Recommendation by Similar Bios
   */
  getSimilarBiosRecommendation(celebName: string) {
    const _URL = `${environment.RECOMMENDATION_API}${this.apiRecommendationEndpoints.similarBiosRecommendation}/${celebName}/`;
    return this.http.get<FacialRecognitionData>(_URL);
  }

  /**
   * Get Recommendation by Similar Genres Plots
   */
  getSimilarGenrePlotsRecommendation(celebName: string) {
    const _URL = `${environment.RECOMMENDATION_API}${this.apiRecommendationEndpoints.similarGenrePlotsRecommendation}/${celebName}/`;
    return this.http.get<FacialRecognitionData>(_URL);
  }

  /**
   * Get Recommendation by Similar Genres Stats
   */
  getSimilarGenreStatsRecommendation(celebName: string) {
    const _URL = `${environment.RECOMMENDATION_API}${this.apiRecommendationEndpoints.similarGenreStatsRecommendation}/${celebName}/`;
    return this.http.get<FacialRecognitionData>(_URL);
  }

  /**
   * Get Recommendation by Face
   */
  getFacialRecommendation(celebName: string) {
    const _URL = `${environment.RECOMMENDATION_API}${this.apiRecommendationEndpoints.facialRecommendation}/${celebName}/`;
    return this.http.get<FacialRecognitionData>(_URL);
  }
}
